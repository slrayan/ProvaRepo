package com.proconsul.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proconsul.entity.Company;
import com.proconsul.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<Company> findAllCompanies() throws Exception {

		List<Company> companies = companyRepository.findAll();

		if (companies == null || companies.isEmpty()) {

			throw new Exception("Non ci sono companies nel sistema");
		}

		return companies;

	}

	@Override
	public Company findCompanyByVatNumber(String vatNumber) {

		return companyRepository.findById(vatNumber).orElseThrow(IllegalArgumentException::new);

	}

	@Override
	public List<Company> findAllCompaniesByBusinessName(String businessName) throws Exception {

		List<Company> companies = companyRepository.findByBusinessName(businessName);

		if (companies == null || companies.isEmpty()) {

			throw new Exception("Non ci sono companies nel sistema con business name " + businessName);
		}

		return companies;

	}

	@Override
	public List<Company> findAllCompaniesByCityLocation(String cityLocation) throws Exception {

		List<Company> companies = companyRepository.findByCityLocation(cityLocation);

		if (companies == null || companies.isEmpty()) {

			throw new Exception("Non ci sono companies nel sistema con city location " + cityLocation);
		}

		return companies;

	}

	@Override
	public List<Company> findAllCompaniesByEmployeesNumberGreaterThan(int employeesNumber) throws Exception {

		List<Company> companies = companyRepository.findByEmployeesNumberGreaterThan(employeesNumber);

		if (companies == null || companies.isEmpty()) {

			throw new Exception(
					"Non ci sono companies nel sistema con employees number maggiore di " + employeesNumber);
		}

		return companies;

	}

	@Override
	public Company saveOrUpdateCompany(Company company) {

		Company savedOrUpdatedCompany = null;

		try {

			savedOrUpdatedCompany = companyRepository.save(company);

		}

		catch (IllegalArgumentException ex) {

			ex.printStackTrace();

		}

		return savedOrUpdatedCompany;

	}

	@Override
	public Map<String, Boolean> removeCompany(String vatNumber) {

		Map<String, Boolean> deletionMap = new HashMap<>();

		try {

			companyRepository.deleteById(vatNumber);
			deletionMap.put("deletion", true);

		}

		catch (IllegalArgumentException ex) {

			deletionMap.put("deletion", false);

		}

		return deletionMap;

	}}

