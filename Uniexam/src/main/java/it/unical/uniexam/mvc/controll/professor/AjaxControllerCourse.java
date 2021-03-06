package it.unical.uniexam.mvc.controll.professor;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("professor/ajax/course")
public class AjaxControllerCourse {

	@Autowired
	ProfessorService professorService;

	ArrayList<Course>courseAlreadyRequested;
//	add_commissionary mi deve dare il redirect to list commissionary

	@RequestMapping("/dialog/add_commissionary")
	public String add_commissionary(HttpServletRequest request, Model model){
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//ProfessorService.PROFESSOR_HOME;

		String id=request.getParameter("idProf");
		Long idProf=Long.valueOf(id);
		String idCours=request.getParameter("idCourse");
		Long idCourse=Long.valueOf(idCours);
		Boolean ris=professorService.addCommissaryAtCourse(idCourse,idProf);

		return "redirect:/professor/ajax/course/dialog/list_commissionary?id="+idCourse+"&ris="+ris;
	}

	@RequestMapping("/dialog/remove_commissionary")
	public String remove_commissionary(HttpServletRequest request, Model model){
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//ProfessorService.PROFESSOR_HOME;

		String id=request.getParameter("idProf");
		Long idProf=Long.valueOf(id);
		String idCours=request.getParameter("idCourse");
		Long idCourse=Long.valueOf(idCours);
		//se non c'è già lo inserisce...e se non sono più di 5!
		Boolean ris=professorService.removeCommissaryAtCourse(idCourse,idProf);

		return "redirect:/professor/ajax/course/dialog/list_commissionary?id="+idCourse+"&ris="+ris;
	}

	@RequestMapping("/list_professor")
	public String list_professor(HttpServletRequest request, Model model){
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//ProfessorService.PROFESSOR_HOME;

		String id=request.getParameter("id");

		if(id!=null && !id.equals("")){
			ArrayList<Professor>match=professorService.getProfessorsMatch(id);
			model.addAttribute("list", match);
//			System.out.println(studentsMatch.size());
			return "professor/autocomplete/professors";
		}
		return null;
	}

	@RequestMapping("/dialog/list_commissionary")
	public String list_commissionary(HttpServletRequest request, Model model){
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//ProfessorService.PROFESSOR_HOME;

		String idCours=request.getParameter("id");
		Long idCourse=Long.valueOf(idCours);

		ArrayList<Professor>commissionary=professorService.getListCommissionary(idCourse);
		model.addAttribute("commissionary", commissionary);
		model.addAttribute("idCourse", idCourse);
		return "professor/course/list_commissionary";
	}
	
	@RequestMapping("/course_details")
	public String course_details(HttpServletRequest request, Model model){
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//ProfessorService.PROFESSOR_HOME;
		
		String idCours=request.getParameter("id");
		Long idCourse=Long.valueOf(idCours);
		Course c=professorService.getCourseDetails(p,idCourse);
		model.addAttribute("course", c);
		return "professor/course/course_details";
	}

	
	@RequestMapping("/dialog/requested_course")
	public String dialog_requested_course(HttpServletRequest request, Model model){
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//ProfessorService.PROFESSOR_HOME;
		
		String idCours=request.getParameter("id");
		Long idCourse=Long.valueOf(idCours);
		Course c=professorService.getCourseDetails(p,idCourse);
		model.addAttribute("course", c);
		
		String ris=request.getParameter("ris");
		if(ris!=null){
			if(ris.equals("true"))
				model.addAttribute("result","success");
			else
				model.addAttribute("result","error");
		}
		return "professor/course/dialog/requested_course";
	}
	
	@RequestMapping("/dialog/addRequestedCourse")
	public ModelAndView dialog_add_requested_course(@ModelAttribute("requestedCourse") RequestedCourse requestedCourse,
			HttpServletRequest request, Model model,HttpServletResponse response) throws IOException{
		
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//new ModelAndView(ProfessorService.PROFESSOR_HOME);
		//riempire una mappa da dove posso sceliere i corsi che possono essere richiesti 
		String idCours=request.getParameter("id");
		if(idCours!=null){
			Long idCourse=Long.valueOf(idCours);
			ArrayList<String> degree = new ArrayList<String>();
			degree.add(RequestedCourse.POLICY_LIGHT);
			degree.add(RequestedCourse.POLICY_MEDIUM);
			degree.add(RequestedCourse.POLICY_STRONG);
			model.addAttribute("degree", degree);
			Set<Course> courses=professorService.getCoursesForRequestedCourseFromDepartment(idCourse);
			model.addAttribute("courses", courses);
			
			model.addAttribute("idCourse", idCourse);
		}else{
			return new ModelAndView(UtilsService.GENERAL_ERROR);
//			response.sendRedirect("redirect:"+UtilsService.redirectToErrorPageGeneral("Errore", "Errore", model));
		}
//		Course c=professorService.getCourseDetails(p,idCourse);
//		model.addAttribute("course", c);
		return new ModelAndView("professor/course/dialog/addRequestedCourse", "model", model);
	}
	
	@RequestMapping(value="/addRequestedCourseAction", method=RequestMethod.POST)
	public ModelAndView dialog_add_requested_course_action(@ModelAttribute("requestedCourse") RequestedCourse requestedCourse,
			HttpServletRequest request, Model model,HttpServletResponse response) throws IOException{
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//new ModelAndView(ProfessorService.PROFESSOR_HOME);
		
		String idCours=request.getParameter("idCourse");
		
		Long idCourse=Long.valueOf(idCours);
		
		Boolean ris=professorService.addRequestedCourse(idCourse,requestedCourse);
		
		return new ModelAndView("redirect:/professor/ajax/course/dialog/requested_course?id="+idCourse+"&ris="+ris, "model", model);
	}
	
	@RequestMapping(value="/dialog/requested_course/command", method=RequestMethod.POST)
	public String dialog_requested_course_command(HttpServletRequest request, Model model){
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;

		String DTO=request.getParameter("data");
		if(DTO!=null && DTO.startsWith("sendRequestedCourse")){
			DTO=DTO.replace("sendRequestedCourse", "");
			String []items=DTO.split(":");
			String idCours=items[0];
//			System.out.println(commands);
			Long idCourse=Long.valueOf(idCours);
			if(professorService.applyCommandForRequestedCourse(idCourse,DTO))
				model.addAttribute("result","success");
			else
				model.addAttribute("result","error");
			Course c=professorService.getCourseDetails(p,idCourse);
			model.addAttribute("course", c);
		}
		return "professor/course/dialog/requested_course";
	}
	
}