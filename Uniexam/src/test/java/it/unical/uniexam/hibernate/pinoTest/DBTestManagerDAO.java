package it.unical.uniexam.hibernate.pinoTest;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.dao.DepartmentDAO;
import it.unical.uniexam.hibernate.dao.ExamSessionDAO;
import it.unical.uniexam.hibernate.dao.ManagerDao;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DegreeCourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DepartmentDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ExamSessionDAOimpl;
import it.unical.uniexam.hibernate.dao.impl.ManagerDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ProfessorDAOImp;
import it.unical.uniexam.hibernate.dao.impl.StudentDAOImpl;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.User.TYPE;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

public class DBTestManagerDAO {

	 private static DepartmentDAO departmentDAO = new DepartmentDAOImpl();
     private static CourseDAO courseDAO = new CourseDAOImpl();
     private static ManagerDao managerDAO = new ManagerDAOImpl();
     private static DegreeCourseDAO degreeCourseDAO = new DegreeCourseDAOImpl();
     private static ExamSessionDAO examsessionDAO=new ExamSessionDAOimpl();
     private static StudentDAO studentDAO = new StudentDAOImpl();
 	private static ProfessorDAO professorDAO=new ProfessorDAOImp();

     
     @BeforeClass
     public static void prepareDB() throws MalformedURLException, InterruptedException {
             Department department = new Department("A2", "BIOLOGIA", new Address("COSENZA", "ITALY", "87100", "VIA PIETRO BUCCI, 56"));
             
             Long idDepartment = departmentDAO.addDepartment(department);
             DegreeCourse dg=new DegreeCourse("INFORMATICA", department);
             Long idDegreeCourse = degreeCourseDAO.addDegreeCourse(dg);
             ExamSession examsession=new ExamSession("Sessione febbraio", new Date(),new Date() , dg);
             ExamSession examsession2=new ExamSession("Sessione luglio", new Date(),new Date() , dg);
             ExamSession examsession3=new ExamSession("Sessione settembre", new Date(),new Date() , dg);

             Long idexamsession2=examsessionDAO.addExamSession(examsession2);
             Long idexamsession=examsessionDAO.addExamSession(examsession);
             Long idexamsession3=examsessionDAO.addExamSession(examsession3);
          
             DegreeCourse dg2=new DegreeCourse("TERRA", department);
             Long idDegreeCourse2 = degreeCourseDAO.addDegreeCourse(dg2);        
            
             Department department2 = new Department("A3", "MATEMATICA", new Address("COSENZA", "ITALY", "87100", "VIA PIETRO BUCCI, 56"));
             Long idDepartment2 = departmentDAO.addDepartment(department2);
             ExamSession examsession4=new ExamSession("Sessione prova", new Date(),new Date() , dg2);
             Long idexamsession4=examsessionDAO.addExamSession(examsession4);

           
             Course c=new Course("A", "Data mining", null, 5, null, null, null, dg);
             Long idcourse=courseDAO.addCourse(c);
             Course c2=new Course("A", "Data warehouse", null, 5, null, null, null, dg);
             Long idcourse2=courseDAO.addCourse(c2);
             Course c3=new Course("B", "Chimica", null, 5, null, null, null, dg2);
             Long idcourse3=courseDAO.addCourse(c3);

             
     		 HashSet<Email> emails3 = new HashSet<Email>();
     		 emails3.add(new Email(Email.TYPE.UFFICIAL, "cali@gmail.com"));

             Long idprofessor=professorDAO.addProfessor("Ciccio", "Calimeri", new URL("http:\\www.cali.com"),  
     				"mero", new Address("Cs", "Italia", "87036", "Asia"),emails3,new HashSet<PhoneNumber>(), department);
             
             HashSet<Email> emails4 = new HashSet<Email>();
     		 emails4.add(new Email(Email.TYPE.UFFICIAL, "ricca@gmail.com"));
             
     		 Long idprofessor2=professorDAO.addProfessor("Ciccio", "Ricca", new URL("http:\\www.cali.com"),  
      				"mero", new Address("Cs", "Italia", "87036", "Asia"),emails4,new HashSet<PhoneNumber>(), department2);
              
     		 
             courseDAO.setHolderProfessor(idcourse, idprofessor);
             courseDAO.setHolderProfessor(idcourse2, idprofessor);
             courseDAO.setHolderProfessor(idcourse2, idprofessor2);
             
             HashSet<Email> emails = new HashSet<Email>();
             emails.add(new Email(Email.TYPE.UFFICIAL, "manager@unical.it"));
             
             HashSet<Email> emails2 = new HashSet<Email>();
     		emails2.add(new Email(Email.TYPE.UFFICIAL, "studente@unical.it"));
             
             Address address = new Address("Cosenza", "Italy", "87100", "Via Univ, 10");
             HashSet<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
             phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.HOME, "00000000"));
             HashSet<PhoneNumber> phoneNumbers2 = new HashSet<PhoneNumber>();
             phoneNumbers2.add(new PhoneNumber(PhoneNumber.TYPE.HOME, "00000000"));
             Long idManager = managerDAO.addManager("Pino", "Lombardo",  new URL("http:\\www.pinolombardo.com"), "0000", address, emails, phoneNumbers, department);
             
             Long idStudent = studentDAO.addStundent("Fabrizio", "Cava", "CVAFRZ88D14D086G", "1234", address, emails2, phoneNumbers2, dg, "158658");
             
             Thread.sleep(3000);
     }
     
     @Test
     public void checkStudent() {
             assertTrue(managerDAO.getManagers().size()==1);
     }
	
     @Test
     public void checkDegreeCourseOfAllDepartmets() {
             assertTrue(degreeCourseDAO.getDegreeCourses().size()==2);
     }
     
}
