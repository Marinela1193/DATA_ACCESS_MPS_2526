package org.example.vtschool_mps_2526.service;

import org.example.vtschool_mps_2526.models.dao.CourseDAO;
import org.example.vtschool_mps_2526.models.dto.CourseDTO;
import org.example.vtschool_mps_2526.models.entities.CourseEntity;
import org.example.vtschool_mps_2526.models.utils.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class serviceCourse {

    @Autowired
    private CourseDAO courseDAO;

    public List<CourseDTO> getCourses() {
        List<CourseDTO> courseList = new ArrayList<>();

        for(CourseEntity course : courseDAO.findAll()) {
            courseList.add(CourseMapper.INSTANCE.mapCourseEntityToCourseDTO(course));
        }
        return courseList;
    }

    public CourseDTO getCourseById(int id) {
        Optional<CourseEntity> course = courseDAO.findById(id);

        return course.isPresent() ? CourseMapper.INSTANCE.mapCourseEntityToCourseDTO(course.get()) : null;
    }

    public CourseEntity saveCourse(CourseDTO course) {

        Optional<CourseEntity> courseEntity = courseDAO.findById(course.getId());

        if(!courseEntity.isPresent()) {
            return courseDAO.save(CourseMapper.INSTANCE.mapCourseDTOToCourseEntity(course));
        }
        return null;
    }

    public CourseEntity updateCourse(CourseDTO course) {

        Optional<CourseEntity> coursEntity = courseDAO.findById(course.getId());

        if (coursEntity.isPresent()) {

            return courseDAO.save(CourseMapper.INSTANCE.mapCourseDTOToCourseEntity(course));

        }
        return null;
    }

    public boolean deleteCourseById(int id) {
        Optional<CourseEntity> coursEntity = courseDAO.findById(id);

        if (coursEntity.isPresent()) {
            courseDAO.deleteById(id);
            return true;
        }
        return false;
    }
}
