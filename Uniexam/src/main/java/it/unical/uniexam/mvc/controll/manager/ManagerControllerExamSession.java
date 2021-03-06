package it.unical.uniexam.mvc.controll.manager;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ManagerService;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller
@SessionAttributes("user")
@RequestMapping(value=ManagerService.MANAGER_EXAM)
public class ManagerControllerExamSession {

	@Autowired
	ManagerService managerService;
	
	
	
	@RequestMapping(value="/add" , method=RequestMethod.POST)
	public String add(@ModelAttribute("addsession") ExamSession examsession,HttpServletRequest request, Model model){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return UtilsService.LOGIN;

		model.addAttribute("M",m);
		updatePersonalizzation(model, m);

		
		Boolean ris=managerService.addExamsession(examsession);
		
		Set<ExamSession> es=managerService.getExamSession();
		model.addAttribute("examsession", es);
		
		Set<DegreeCourse> courses=managerService.getAssociatedCourseWithDepartment(m.getDepartmentAssociated());
		model.addAttribute("courses", courses);
		
		
		return ManagerService.MANAGER_EXAM;
	}
	
	
	private void updatePersonalizzation(Model model, Manager m) {
		Map<String, String> personalizzationMap=managerService.getPersonalizzationValues(m.getId());
		model.addAttribute("personalizzationMap", personalizzationMap);
	}
	
}
