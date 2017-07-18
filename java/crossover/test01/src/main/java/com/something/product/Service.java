package com.something.product;

public interface Service {
	
	// APIs to add data to this service (the service has no data in it when it is created)
	public void addPerson(String first, String last);
	public void addSalariedPerson(String first, String last, double annualSalary);
	public void addHourlyPerson(String first, String last, double hourlyRate, boolean isPaidVacation, int vacationDays);

	// APIs for reports listing people
	public String firstLastReport();
	public String firstDotLastReport();
	public String xmlAttributesReport();
	public String xmlTextReport();
	
	// APIs for reports listing salary details of people
	public String firstLastSalaryReport();
	public String csvSalaryReport();
	public String hourlyPeopleSalaryReport();
	
}
