package org.tpokora.services.weather.storms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.persistance.entity.weather.WarningEntity;
import org.tpokora.domain.weather.common.StormConstants;
import org.tpokora.persistance.services.weather.WarningDaoService;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.domain.weather.Warning;
import org.tpokora.services.soap.SOAPService;
import org.tpokora.services.weather.processor.WarningsSoapRequestProcessor;
import org.tpokora.services.weather.processor.WarningsSoapResponseProcessor;
import org.tpokora.services.weather.properties.StormProperties;

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
