package com.proconsul.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proconsul.entity.Company;
import com.proconsul.service.CompanyService;
/*
 * GET     http://localhost:8080/rest/api/companies
 * GET     http://localhost:8080/rest/api/companies/vat-number/10283771000
 * GET     http://localhost:8080/rest/api/companies/business-name/company1
 * GET     http://localhost:8080/rest/api/companies/city-location/Rome
 * GET     http://localhost:8080/rest/api/companies/employees-number/29
 * POST    http://localhost:8080/rest/api/companies---->>> JSON
 * PUT     http://localhost:8080/rest/api/companies---->>> JSON
 * DELETE  http://localhost:8080/rest/api/companies/vat-number/10283771002
 * GET     http://localhost:8080/rest/api/companies/xml
 * POST    http://localhost:8080/rest/api/companies/validation---->>> JSON
 * POST    http://localhost:8080/rest/api/companies/responseEntity---->>> JSON
 * GET     http://localhost:8080/rest/api/companies/hateoas/vat-number/10283771000
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest/api/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("")
	public List<Company> findAllCompanies() throws Exception {

		return companyService.findAllCompanies();

	}

	// {vatNumber} = path variable
	@GetMapping("/vat-number/{vatNumber}")
	public Company findCompanyByVatNumber(@PathVariable String vatNumber) {
		
		System.out.println(vatNumber);
		return companyService.findCompanyByVatNumber(vatNumber);

	}

	@GetMapping("/business-name/{businessName}")
	public List<Company> findAllCompaniesByBusinessName(@PathVariable String businessName) throws Exception {

		return companyService.findAllCompaniesByBusinessName(businessName);
	}

	@GetMapping("/city-location/{cityLocation}")
	public List<Company> findAllCompaniesByCityLocation(@PathVariable String cityLocation) throws Exception {

		return companyService.findAllCompaniesByCityLocation(cityLocation);

	}

	@GetMapping("/employees-number/{employeesNumber}")
	public List<Company> findAllCompaniesByEmployeesNumberGreaterThan(@PathVariable int employeesNumber)
			throws Exception {

		return companyService.findAllCompaniesByEmployeesNumberGreaterThan(employeesNumber);
	}

	/*
	 * Quando il consumer consumerà questa risorsa dovrà inviare nel corpo della
	 * richiesta http un json con le informazioni di una nuova company da inserire
	 * (esempio) :
	 * 
	 * { "vatNumber":"10283771008", "businessName":"company10",
	 * "cityLocation":"Rome", "employeesNumber" : 45 }
	 */
	@PostMapping("")
	public Company saveCompany(@RequestBody Company company) {

		return companyService.saveOrUpdateCompany(company);
	}

	@PutMapping("")
	public Company updateCompany(@RequestBody Company company) {

		return companyService.saveOrUpdateCompany(company);
	}

	@DeleteMapping("/vat-number/{vatNumber}")
	public Map<String, Boolean> removeCompany(@PathVariable String vatNumber) {

		return companyService.removeCompany(vatNumber);

	}

	/*
	 * esempio di un metodo che restituisce al consumer un xml
	 */
	@GetMapping(path = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public List<Company> findAllCompaniesXML() throws Exception {

		return companyService.findAllCompanies();

	}

	/*
	 * esempio di un metodo che restituisce nel caso in cui si verfifichi una
	 * eccezione di validazione un JSON con le proprietà dell'oggetto ErrorMessage
	 */
	@PostMapping("/validation")
	public Company saveValidationCompany(@Valid @RequestBody Company company) {

		return companyService.saveOrUpdateCompany(company);
	}

	/*
	 * esempio di un metodo che restituisce un json di risposta customizzato sia per
	 * quel che concerne il body della response, sia per quel che concerne lo status
	 * code da inserire nell'header della response
	 */
	@PostMapping("/responseEntity")
	public ResponseEntity<?> customSaveCompany(@RequestBody Company company) {

		companyService.saveOrUpdateCompany(company);
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("save company operation", "ok");

		return new ResponseEntity<>(responseMap, HttpStatus.CREATED);

	}

	/*
	 * esempio di metodo che implementa il pattern HATEOAS aggiungendo al json di
	 * risposta un link http://localhost:8080/rest/api/companies con nome
	 * "all-companies"
	 */
	@GetMapping("/hateoas/vat-number/{vatNumber}")
	public EntityModel<Company> findCompanyByVatNumberHateoas(@PathVariable String vatNumber) throws Exception {

		Company company = companyService.findCompanyByVatNumber(vatNumber);

		/*
		 * Questa riga di codice rappresenta l'azione preliminare per poter fare
		 * l'attach di un link all'nterno del json di risposta
		 */
		EntityModel<Company> resource = EntityModel.of(company);

		// http://localhost:8080/rest/api/companies
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAllCompanies());

		// associazione di una etichetta al link
		resource.add(linkTo.withRel("all-companies"));

		return resource;

	}

}