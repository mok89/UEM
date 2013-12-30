package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.net.URL;
import java.util.Set;

public interface ManagerDao {
	
	public Long addManager(String name, String surname, URL webSite,
			 String password, Address address);
	
	public Long addManager(Manager manager);
	public Set<Manager> getManagers();
	public Set<Manager> getManagerFromDepartment(Long idDepartment);
	public Manager getManager(Long idManager);
	public Manager removeManager(Long idManager);
	
	
	public Long addPhoneNumber(Long idManager, PhoneNumber number);
	public void removePhoneNumber(Long idManager, Long idPhoneNumber);
	public void removePhoneNumber(Long idManager, PhoneNumber idPhoneNumber);
	public Set<PhoneNumber> getPhoneNumbers(Long idManager);
	
	
	public Long setDepartmentAssociated(Long idManager, Long idDepartment);
	public boolean setDepartmentAssociated(Long idManager, Department department);

}
