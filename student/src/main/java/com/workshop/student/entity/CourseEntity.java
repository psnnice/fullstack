package com.workshop.student.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class CourseEntity {
    
    @Id
    @GeneratedValue // auto increment generate value ขึ้นมาให้เอง
    private Integer courseId;
    private String courseName;
    private String courseDescription;
    
    @OneToMany(mappedBy = "course")
    private List<EnrollEntity> enrolls;
}
