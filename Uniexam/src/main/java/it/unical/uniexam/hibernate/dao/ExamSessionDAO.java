package it.unical.uniexam.hibernate.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.ExamSession;;


public interface ExamSessionDAO {
	
	public Long addExamSession(String description,Date dataInizio,Date dataFine,DegreeCourse degreecourseAssociated);
	public Long addExamSession(ExamSession examsession);
	public boolean canRegisterCourse(Course c);
	public Set<ExamSession> getExamsession();
	public ExamSession removeExamSession(Long idexamsession);
	public ExamSession getExamsession(Long id);
	public Set<ExamSession> getExamsessionfromdepartment(Department department);
	public Set<ExamSession> getExamsessionfromDegreeCourse(DegreeCourse degreecourse);
	public ExamSession modifyExamSession(Long id,ExamSession newexamsession);
	
	public ArrayList<ExamSession> getExamSessionsFromProfessor(Long id);
	

}
