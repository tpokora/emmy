package org.tpokora.storms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.soap.SOAPService;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.storms.dao.StormsRepository;
import org.tpokora.storms.model.StormEntity;
import org.tpokora.storms.model.StormRequest;
import org.tpokora.storms.model.StormResponse;
import org.tpokora.storms.services.processor.StormSoapRequestProcessor;
import org.tpokora.storms.services.processor.StormSoapResponseProcessor;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.List;

@Service
public class FindStormService extends StormService {

    private StormsRepository stormsRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(FindStormService.class);

    public FindStormService(StormProperties stormProperties, SOAPService soapService, StormsRepository stormsRepository) {
        super(stormProperties, soapService);
        this.stormsRepository = stormsRepository;
        soapRequestMessageProcessor = new StormSoapRequestProcessor(stormProperties);
        soapResponseMessageProcessor = new StormSoapResponseProcessor();
    }

    public StormResponse checkStorm(StormRequest stormRequest) throws SOAPException {
        LOGGER.info("==> Find Storm : {}", stormRequest);
        SOAPMessage soapMessage = soapRequestMessageProcessor.process(stormRequest);
        SOAPMessage soapResponse = soapService.sendSOAPMessage(soapMessage, StormConstants.URL);
        StormResponse stormResponse = (StormResponse) soapResponseMessageProcessor.process(soapResponse);
        if (stormResponse != null) {
            saveStormResponse(stormRequest, stormResponse);
        }
        LOGGER.info("==> {}", stormResponse);
        return stormResponse;
    }

    public StormEntity saveStormResponse(StormRequest stormRequest, StormResponse stormResponse) {
        LOGGER.debug("==> Saving StormResponse to DB");
        if (stormResponse.getAmount() == 0) {
            LOGGER.debug("==> Nothing to Save");
            return null;
        }
        StormEntity stormEntity = StormEntity.builder()
                .amount(stormResponse.getAmount())
                .x(String.format("%.2f", stormRequest.getCoordinates().getX()))
                .y(String.format("%.2f", stormRequest.getCoordinates().getY()))
                .direction(stormResponse.getDirection())
                .distance(stormResponse.getDistance())
                .time(stormResponse.getTime())
                .timestamp(stormResponse.getTimestamp())
                .build();
        List<StormEntity> stormEntityList = stormsRepository
                .getStormEntitiesByCoordinatesSortByDate(stormEntity.getX(), stormEntity.getY());
        if (stormEntityList.isEmpty()) {
            StormEntity savedStormEntity = stormsRepository.saveAndFlush(stormEntity);
            LOGGER.debug("{}", savedStormEntity.toString());
            return savedStormEntity;
        }

        return stormEntity;
    }
}
