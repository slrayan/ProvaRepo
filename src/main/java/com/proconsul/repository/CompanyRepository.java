package com.proconsul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proconsul.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,String> {
		
		//select c from Company c where c.businessName
		
		public List<Company> findByBusinessName(String businessName);
		
		//select c from Company c where c.cityLocation
		
		public List<Company> findByCityLocation(String cityLocation);
		
		
		/*
		 * find --->>>> select c from Company c
		 * By---------->>> where
		 * EmployeesNumber------->>> c.employeesNumber
		 * GreaterThan------>>>>   >
		 * 
		 * Query JQPL finale = select c from Company c where c.employeesNumber > 
		 * employeesNumber (input al metodo)
		 */
		public List<Company> findByEmployeesNumberGreaterThan(int employeesNumber);
		
		

	}
