package it.unical.uniexam.hibernate.dao;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.User;

public interface CourseDAO {
	/**
	 * Standard method
	 */
	
	/**
	 * Adding a Course and return the your id
	 */
	public Long addCourse(String codeCourse,String nameCourse,
			Integer creditCourse,Long idProfessorHolder,
			Set<RequestedCourse>requestedCourses,URL webSite, DegreeCourse degreeCourse);
	
	public Long addCourse(Course course);
	
	public ArrayList<Course> getCourses();
	public ArrayList<Course> getCoursesFromDegreeCourse(Long idDegreeCourse);
	public Course getCourse(Long idCourse);
	public Course getCourseAll(Long idCourse);
//	public Course removeCourse(Long idCourse);

	/**
	 * Advanced method 
	 */
	
	/**
	 * Adding a requested course a one course
	 */
	public boolean addRequestedCourse(Long idCourse,Long idCourseRequested,String degree);
	public RequestedCourse removeRequestedCourse(Long idCourse,Long idCourseRequested);
	public RequestedCourse RemoveReqCourseForManager(Long idCourse,Long idCoursereq);
	public Boolean modifyDegreeRequestedCourse(Long idCourse,Long idCourseRequested,String degree); // can be derived by a remove and a add
	
	public boolean setHolderProfessor(Long idCourse,Long idProfessor);
	public boolean setHolderProfessor(Long idCourse, Professor professor);
	public Professor getHolderProfessor(Long idCourse);
	
	//commission
	public Long addProfessorAtCommission(Long idCourse,Long idProfessor);
	public Long addProfessorAtCommission(Long idCourse,Professor professor);
	public boolean setCommission(Long idCourse,Set<Professor>commission);
	public Professor removeProfessorFromCommission(Long idCourse,Long idProfessor);
	public Set<Professor> removeCommission(Long idCourse);
	
	public Set<RequestedCourse> getRequestedCourses(Long idCourse);
	public RequestedCourse getRequestedCourse(Long idRequestedCourse);
	public Set<RequestedCourse> getRequestedCourses(Long idCourse,String degreeOdPolicy);

	public ArrayList<Course> getAssociatedCourseWithGroups(User user);
	
	public String getNote(Long idCourse);
	public Boolean setNote(Long idCourse,String note);

	public Boolean modifyDegreeRequestedCourse(Long idCourse,Long idCourseRequested, String degree,Session session);
	public RequestedCourse removeRequestedCourse(Long idCourse, Long idCourseRequested,Session session);
	
	public Session getSession();

	public Collection<? extends Course> getCoursesFromDepartment(Long idDepartment);

	public Long getDepartmentFromCourse(Long idCourse);

	public ArrayList<Professor> getListCommissaryCourse(Long idCourse);
	
	public ArrayList<Course> getCoursesFromStudent(Long idStudent);

	public Course getCourseDetailforManager(Long idCourse);
//
	public Boolean removeHolderProfessor(Long idCourse, Long professor);
//
	public Course removeCourse(Long idCourse, Long idDegreeCourse);
	public Course removeCourseforManager(Long idCourse,Long idDegreeCourse);
//
	public Long addCourseforManager(String code, String name,
			Integer credits, DegreeCourse degreeCourse);
}
