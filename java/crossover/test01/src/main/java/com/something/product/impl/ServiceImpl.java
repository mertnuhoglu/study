package com.something.product.impl;

import com.something.product.Service;

public class ServiceImpl implements Service {

	PeopleReporter r = new PeopleReporter();
	SalaryReporter s = new SalaryReporter();
	
	public void addPerson(String first, String last) {
		new Person(first, last);
	}

	public void addSalariedPerson(String first, String last, double annualSalary) {
		new Person(first, last).annualSalary = annualSalary;
	}

	public void addHourlyPerson(String first, String last, double hourlyRate,
			boolean isPaidVacation, int vacationDays) {
		Person p = new Person(first, last);
		p.hourlyRate = hourlyRate;
		p.isPaidVacation = isPaidVacation;
		p.vacationDays = vacationDays;
	}

	public String firstLastReport() {
		return r.report();
	}

	public String firstDotLastReport() {
		return r.dotReport();
	}

	public String xmlAttributesReport() {
		return r.xmlAttributesReport();
	}

	public String xmlTextReport() {
		return r.xmlTextReport();
	}

	public String firstLastSalaryReport() {
		return s.report();
	}

	public String csvSalaryReport() {
		return s.csvSalaryReport();
	}

	public String hourlyPeopleSalaryReport() {
		return s.hourlyPeopleSalaryReport();
	}
}
