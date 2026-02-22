package org.example.vtschool_mps_2526.models.utils;

import org.example.vtschool_mps_2526.models.dto.ScoreDTO;
import org.example.vtschool_mps_2526.models.entities.ScoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScoreMapper {
    ScoreMapper INSTANCE = Mappers.getMapper(ScoreMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "subject", target = "subject"),
            @Mapping(source = "score", target = "score")
    })
    ScoreDTO mapScoreEntityToScoreDTO(ScoreEntity scoreEntity);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "subject", target = "subject"),
            @Mapping(source = "score", target = "score")
    })
    ScoreEntity mapScoreDTOToScoreEntity(ScoreDTO scoreDTO);
}
