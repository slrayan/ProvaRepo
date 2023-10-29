package com.proconsul.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Company implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "vat_number", nullable = false, length = 11)
	@Size(min=11,max=11,message="vat number must be of 11 characters")
	private String vatNumber;
	@Column(name = "business_name", nullable = false, length = 50)
	private String businessName;
	@Column(name = "city_Location", nullable = false, length = 50)
	private String cityLocation;
	@Column(name = "employees_number", nullable = false, length = 50)
	private int employeesNumber;
	
	public Company() {
	}

	public Company(String vatNumber, String businessName, String cityLocation, int employeesNumber) {
		this.vatNumber = vatNumber;
		this.businessName = businessName;
		this.cityLocation = cityLocation;
		this.employeesNumber = employeesNumber;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getCityLocation() {
		return cityLocation;
	}

	public void setCityLocation(String cityLocation) {
		this.cityLocation = cityLocation;
	}

	public int getEmployeesNumber() {
		return employeesNumber;
	}

	public void setEmployeesNumber(int employeesNumber) {
		this.employeesNumber = employeesNumber;
	}
	
	
	
	
	

}
