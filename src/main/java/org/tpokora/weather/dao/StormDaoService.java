package org.tpokora.weather.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.weather.model.StormEntity;
import org.tpokora.weather.model.StormRequest;
import org.tpokora.weather.model.StormResponse;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StormDaoService {

    private final Logger LOGGER = LoggerFactory.getLogger(StormDaoService.class);

    private IStormsRepository stormsRepository;

    public StormDaoService(IStormsRepository stormsRepository) {
        this.stormsRepository = stormsRepository;
    }

    public List<StormEntity> findAllByCoordinates(double longitude, double latitude) {
        return stormsRepository.findAllByLongitudeAndLatitudeOrderByTimestampDesc(longitude, latitude);
    }

    public StormEntity saveStormResponse(StormRequest stormRequest, StormResponse stormResponse) {
        Objects.requireNonNull(stormRequest, "StormRequest can't be null!");
        Objects.requireNonNull(stormResponse, "StormResponse can't be null!");
        LOGGER.info(">>> Saving StormResponse to DB");
        if (stormResponse.getAmount() == 0) {
            LOGGER.info(">>> Nothing to Save");
            return null;
        }
        StormEntity stormEntity = generatorStormEntity(stormRequest, stormResponse);
        Optional<StormEntity> stormEntityOptional =
                stormsRepository.findFirstByLongitudeAndLatitudeOrderByTimestampDesc(stormEntity.getLongitude(), stormEntity.getLatitude());
        if (stormEntityOptional.isPresent()) {
            StormEntity stormEntityFromDB = stormEntityOptional.get();
            if (DateUtils.getMinuteDifference(stormEntity.getTimestamp(), stormEntityFromDB.getTimestamp()) > 15) {
                return saveStormEntity(stormEntity);
            }
        } else {
            return saveStormEntity(stormEntity);
        }
        LOGGER.info(">>> Nothing to Save");
        return stormEntity;
    }

    private StormEntity saveStormEntity(StormEntity stormEntity) {
        StormEntity savedStormEntity = stormsRepository.saveAndFlush(stormEntity);
        LOGGER.info("{}", savedStormEntity.toString());
        return savedStormEntity;
    }

    private StormEntity generatorStormEntity(StormRequest stormRequest, StormResponse stormResponse) {
        Objects.requireNonNull(stormRequest, "StormRequest can't be null!");
        Objects.requireNonNull(stormResponse, "StormResponse can't be null!");
        return StormEntity.builder()
                .amount(stormResponse.getAmount())
                .longitude(stormRequest.getCoordinates().getLongitude())
                .latitude(stormRequest.getCoordinates().getLatitude())
                .direction(stormResponse.getDirection())
                .distance(stormResponse.getDistance())
                .time(stormResponse.getTime())
                .timestamp(stormResponse.getTimestamp())
                .build();
    }
}
