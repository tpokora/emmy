package org.tpokora.storms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.dao.WarningDaoService;
import org.tpokora.storms.dao.WarningRepository;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.Warning;
import org.tpokora.storms.model.WarningEntity;
import org.tpokora.storms.services.processor.WarningsSoapRequestProcessor;
import org.tpokora.storms.services.processor.WarningsSoapResponseProcessor;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindWarningService extends StormService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindWarningService.class);

    private WarningDaoService warningDaoService;

    public FindWarningService(StormProperties stormProperties, SOAPService soapService, WarningDaoService warningDaoService) {
        super(stormProperties, soapService);
        soapRequestMessageProcessor = new WarningsSoapRequestProcessor(stormProperties);
        soapResponseMessageProcessor = new WarningsSoapResponseProcessor();
        this.warningDaoService = warningDaoService;
    }

    public List<Warning> findWarnings(Coordinates coordinates) throws SOAPException {
        LOGGER.info("==> Find Warnings : {}", coordinates);
        SOAPMessage soapMessage = soapRequestMessageProcessor.process(coordinates);
        SOAPMessage soapResponse = soapService.sendSOAPMessage(soapMessage, StormConstants.URL);
        List<Warning> warnings = (List<Warning>) soapResponseMessageProcessor.process(soapResponse);
        warningDaoService.saveAll(createWarningEntityList(warnings));
        LOGGER.info("==> {}", warnings);
        return warnings;
    }

    private List<WarningEntity> createWarningEntityList(List<Warning> warningList) {
        return warningList.stream().map(WarningEntity::valueOf).collect(Collectors.toList());
    }
}
