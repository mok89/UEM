package it.unical.uniexam.mvc.controll.student;

import it.unical.uniexam.mvc.service.StudentService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class StudentController {
	
	@Autowired StudentService studentService;
	
	@RequestMapping(value=StudentService.STUDENT_HOME, method=RequestMethod.GET)
	public String homeStudent(HttpServletRequest request,Model model){	
		return StudentService.STUDENT_HOME;
	}
	
	@RequestMapping(value=StudentService.STUDENT_APPEAL, method=RequestMethod.GET)
	public String appeal(HttpServletRequest request, Model model) {
		
		return StudentService.STUDENT_APPEAL;
	}
	
}
