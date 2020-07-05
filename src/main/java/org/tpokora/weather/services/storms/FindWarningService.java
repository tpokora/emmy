package org.tpokora.weather.services.storms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.weather.properties.StormProperties;
import org.tpokora.weather.common.StormConstants;
import org.tpokora.weather.dao.WarningDaoService;
import org.tpokora.weather.model.Coordinates;
import org.tpokora.weather.model.Warning;
import org.tpokora.weather.model.entity.WarningEntity;
import org.tpokora.weather.services.processor.WarningsSoapRequestProcessor;
import org.tpokora.weather.services.processor.WarningsSoapResponseProcessor;

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
        LOGGER.info(">>> Find Warnings : {}", coordinates);
        SOAPMessage soapMessage = soapRequestMessageProcessor.process(coordinates);
        SOAPMessage soapResponse = soapService.sendSOAPMessage(soapMessage, StormConstants.URL);
        List<Warning> warnings = (List<Warning>) soapResponseMessageProcessor.process(soapResponse);
        warningDaoService.saveAll(createWarningEntityList(warnings, coordinates));
        LOGGER.info(">>> {}", warnings);
        return warnings;
    }

    private List<WarningEntity> createWarningEntityList(List<Warning> warningList, Coordinates coordinates) {
        List<WarningEntity> warningEntityList = warningList.stream().map(WarningEntity::valueOf).collect(Collectors.toList());
        warningEntityList.forEach(warningEntity -> warningEntity.addCoordinates(coordinates));
        return warningEntityList;
    }
}
