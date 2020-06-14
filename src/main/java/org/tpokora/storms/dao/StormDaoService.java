package org.tpokora.storms.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.storms.model.StormEntity;
import org.tpokora.storms.model.StormRequest;
import org.tpokora.storms.model.StormResponse;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@Service
public class StormDaoService {

    private final Logger LOGGER = LoggerFactory.getLogger(StormDaoService.class);

    private StormsRepository stormsRepository;

    public StormDaoService(StormsRepository stormsRepository) {
        this.stormsRepository = stormsRepository;
    }

    public StormEntity saveStormResponse(StormRequest stormRequest, StormResponse stormResponse) {
        Objects.requireNonNull(stormRequest, "StormRequest can't be null!");
        Objects.requireNonNull(stormResponse, "StormResponse can't be null!");
        LOGGER.info("==> Saving StormResponse to DB");
        if (stormResponse.getAmount() == 0) {
            LOGGER.info("==> Nothing to Save");
            return null;
        }
        StormEntity stormEntity = generatorStormEntity(stormRequest, stormResponse);
        Optional<StormEntity> stormEntityOptional =
                stormsRepository.findFirstByLongitudeAndLatitudeOrderByTimestampDesc(stormEntity.getLongitude(), stormEntity.getLatitude());
        if (stormEntityOptional.isPresent()) {
            StormEntity stormEntityFromDB = stormEntityOptional.get();
            if (getMinuteDifference(stormEntity, stormEntityFromDB) > 15) {
                return saveStormEntity(stormEntity);
            }
        } else {
            return saveStormEntity(stormEntity);
        }
        LOGGER.info("==> Nothing to Save");
        return stormEntity;
    }

    private StormEntity saveStormEntity(StormEntity stormEntity) {
        StormEntity savedStormEntity = stormsRepository.saveAndFlush(stormEntity);
        LOGGER.info("{}", savedStormEntity.toString());
        return savedStormEntity;
    }

    private long getMinuteDifference(StormEntity stormEntity, StormEntity stormEntityFromDB) {
        Objects.requireNonNull(stormEntity, "StormEntity can't be null!");
        Objects.requireNonNull(stormEntityFromDB, "stormEntityFromDB can't be null!");
        return Duration.between(stormEntityFromDB.getTimestamp(), stormEntity.getTimestamp()).getSeconds() / 60;
    }

    private StormEntity generatorStormEntity(StormRequest stormRequest, StormResponse stormResponse) {
        Objects.requireNonNull(stormRequest, "StormRequest can't be null!");
        Objects.requireNonNull(stormResponse, "StormResponse can't be null!");
        return StormEntity.builder()
                .amount(stormResponse.getAmount())
                .x(String.format("%.2f", stormRequest.getCoordinates().getX()))
                .y(String.format("%.2f", stormRequest.getCoordinates().getY()))
                .direction(stormResponse.getDirection())
                .distance(stormResponse.getDistance())
                .time(stormResponse.getTime())
                .timestamp(stormResponse.getTimestamp())
                .build();
    }
}
