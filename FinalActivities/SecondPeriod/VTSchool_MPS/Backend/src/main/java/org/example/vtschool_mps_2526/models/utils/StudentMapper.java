package org.example.vtschool_mps_2526.models.utils;

import org.example.vtschool_mps_2526.models.dto.StudentsDTO;
import org.example.vtschool_mps_2526.models.entities.StudentEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper (unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mappings({
            @Mapping(source = "firstname", target = "firstName"),
            @Mapping(source = "lastname", target = "lastName"),
            @Mapping(source = "enrollments", target = "enrollments")
    })
    StudentsDTO mapStudentEntityToDTO(StudentEntity studentEntity);

    //@BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(source = "firstName", target = "firstname"),
            @Mapping(source = "lastName", target = "lastname"),
            @Mapping(source = "enrollments", target = "enrollments")
    })
    StudentEntity mapStudentDTOToEntity(StudentsDTO studentsDTO);
}
