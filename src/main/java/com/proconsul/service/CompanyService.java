package com.proconsul.service;

import java.util.List;
import java.util.Map;

import com.proconsul.entity.Company;

public interface CompanyService {
	


	public List<Company> findAllCompanies() throws Exception;
	
	public Company findCompanyByVatNumber(String vatNumber);
	
	public List<Company> findAllCompaniesByBusinessName(String businessName) throws Exception;
	
	public List<Company> findAllCompaniesByCityLocation(String cityLocation) throws Exception;
	
	public List<Company> findAllCompaniesByEmployeesNumberGreaterThan(int employeesNumber) throws Exception;
	
	public Company saveOrUpdateCompany(Company company);
	
	public Map<String,Boolean> removeCompany(String vatNumber);
}
