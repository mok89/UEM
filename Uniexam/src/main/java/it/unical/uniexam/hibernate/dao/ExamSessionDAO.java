package it.unical.uniexam.hibernate.dao;

import java.util.Date;
import java.util.Set;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.ExamSession;;


public interface ExamSessionDAO {
	
	public Long addExamSession(String description,Date dataInizio,Date dataFine,DegreeCourse degreecourseAssociated );
	public boolean canRegisterCourse(Course c);
	public Set<ExamSession> getExamsession();
	

}