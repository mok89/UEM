package it.unical.uniexam.mvc.controll.professor;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.ServletOutputStream;
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
@RequestMapping("professor/ajax/sign")
@SessionAttributes("user")
public class AjaxControllerSign {

	@Autowired
	ProfessorService professorService;

	@RequestMapping("/autocomplete/students")
	public String autocomplete_student(HttpServletRequest request, Model model,HttpServletResponse response){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return redirect;
//		p=plist.get(0);
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//ProfessorService.PROFESSOR_HOME;

		String idStud=request.getParameter("id");

		if(idStud!=null && !idStud.equals("")){
			ArrayList<Student>studentsMatch=professorService.getStudentsMatch(idStud);
			model.addAttribute("list", studentsMatch);
//			System.out.println(studentsMatch.size());
			return "professor/autocomplete/students";
		}
		return null;
	}
	
	@RequestMapping("/declassify_students")
	public ModelAndView declassify_students(HttpServletRequest request, Model model,HttpServletResponse response){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return new ModelAndView(redirect);
//		p=plist.get(0);
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//new ModelAndView(ProfessorService.PROFESSOR_HOME);

		ArrayList<Long>listAppealStudents=new ArrayList<Long>();
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String object = (String) parameterNames.nextElement();
			if(object.contains("idAppealStudent")){
				String idd=request.getParameter(object);
				Long id=null;
				try{
					id=Long.valueOf(idd);
					listAppealStudents.add(id);
				}catch(Exception e){new MokException(e);}
			}
		}

		Boolean res=professorService.declassifyStudents(listAppealStudents,p.getId());
		
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			if(res)
				outputStream.println("ok");
			else
				outputStream.println("no");
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			new MokException(e);
		}
		return null;
	}
	
	@RequestMapping("/sign_appealstudents_by_commissary")
	public ModelAndView sign_appealstudents_by_commissary(HttpServletRequest request, Model model,HttpServletResponse response){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return new ModelAndView(redirect);
//		p=plist.get(0);
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//new ModelAndView(ProfessorService.PROFESSOR_HOME);

		ArrayList<Long>listSignAppealStudents=new ArrayList<Long>();
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String object = (String) parameterNames.nextElement();
			if(object.contains("idAppealStudent")){
				String idd=request.getParameter(object);
				Long id=null;
				try{
					id=Long.valueOf(idd);
					listSignAppealStudents.add(id);
				}catch(Exception e){new MokException(e);}
			}
		}
		String passwd=request.getParameter("passwd");
		String res="no";
		if(passwd!=null && passwd.equals(p.getPassword())){
			if(professorService.signAppealStudentsListByCommissionary(listSignAppealStudents,p.getId()))
				res="si";
		}else
			res="passwd";
//				professorService.removeStudentsToAppeal(removeStudents);
		
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			if(res.equals("si"))
				outputStream.println("ok");
			else
				if(res.equals("no"))
					outputStream.println("no");
				else
					outputStream.println("passwd");
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			new MokException(e);
		}
		return null;
	}
	
	@RequestMapping("/sign_appealstudents")
	public ModelAndView sign_appealstudents(HttpServletRequest request, Model model,HttpServletResponse response){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return new ModelAndView(redirect);
//		p=plist.get(0);
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//new ModelAndView(ProfessorService.PROFESSOR_HOME);

		ArrayList<Long>listSignAppealStudents=new ArrayList<Long>();
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String object = (String) parameterNames.nextElement();
			if(object.contains("idAppealStudent")){
				String idd=request.getParameter(object);
				Long id=null;
				try{
					id=Long.valueOf(idd);
					listSignAppealStudents.add(id);
				}catch(Exception e){new MokException(e);}
			}
		}
		String passwd=request.getParameter("passwd");
		String res="no";
		if(passwd!=null && passwd.equals(p.getPassword())){
			res=professorService.signAppealStudentsList(listSignAppealStudents,p.getId());
		}else
			res="passwd";
//				professorService.removeStudentsToAppeal(removeStudents);
		
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.println(res);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			new MokException(e);
		}
		return null;
	}
	
	
	@RequestMapping("/prepare_students")
	public String prepare_students(HttpServletRequest request, Model model,HttpServletResponse response){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return redirect;
//		p=plist.get(0);
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//ProfessorService.PROFESSOR_HOME;
		
		ArrayList<Long>prepareStudents=new ArrayList<Long>();
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String object = (String) parameterNames.nextElement();
			if(object.contains("idAppealStudent")){
				String idd=request.getParameter(object);
				Long id=null;
				try{
					id=Long.valueOf(idd);
					prepareStudents.add(id);
				}catch(Exception e){new MokException(e);}
			}
		}
		
		Boolean res=professorService.applyPrepareAppealStudent(prepareStudents,p.getId());
		
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			if(res)
				outputStream.println("ok");
			else
				outputStream.println("no");
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			new MokException(e);
		}
		return null;
	}
	
	@RequestMapping("/remove_students")
	public ModelAndView remove_appeal_student(HttpServletRequest request, Model model,HttpServletResponse response){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return new ModelAndView(redirect);
//		p=plist.get(0);
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//new ModelAndView(ProfessorService.PROFESSOR_HOME);

		ArrayList<Long>removeStudents=new ArrayList<Long>();
		Enumeration parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String object = (String) parameterNames.nextElement();
			if(object.contains("idAppealStudent")){
				String idd=request.getParameter(object);
				Long id=null;
				try{
					id=Long.valueOf(idd);
					removeStudents.add(id);
				}catch(Exception e){new MokException(e);}
			}
		}
		
		Boolean res= professorService.removeStudentsToAppeal(removeStudents,p.getId());
		
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			if(res)
				outputStream.println("ok");
			else
				outputStream.println("no");
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			new MokException(e);
		}
		return null;
	}
	
	@RequestMapping("/list_appeals")
	public String list_appeals(HttpServletRequest request, Model model){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return redirect;
//		p=plist.get(0);
		Professor p=(Professor)(request.getSession().getAttribute("user")!=null?request.getSession().getAttribute("user"):null);
		if(p==null) return null;//ProfessorService.PROFESSOR_HOME;
		
		String appealString=request.getParameter("id");
		if(appealString!=null && appealString.length()>0){
			ArrayList<Appeal>appeanls=professorService.getAppealsMatch(p.getId(),appealString);
			model.addAttribute("listAppeals", appeanls);
		}
		
		return "professor/autocomplete/appeals";
	}

	
//	String setProfessorOrRedirect(HttpServletRequest request,Model model, ArrayList<Professor> plist) {
//		User user=professorService.getSession(request.getSession().getId());
//		if(user==null){
//			HttpSession session = request.getSession(false);
//			if(session!=null){
//				session.invalidate();
//			}
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta Error code 1", "sessione", model);
//		}
//		if(user.getClass()!=Professor.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore, Utente non riconosciuto", "Classe Utente", model);
//		}
//		plist.add((Professor)user);
//		return null;
//	}
}