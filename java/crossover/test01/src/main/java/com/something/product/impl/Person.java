package com.something.product.impl;

import java.util.ArrayList;
import java.util.List;

public class Person {

	public static List<Person> allPeople = new ArrayList<Person>();
	
	public String firstName;
	public String lastName;
	public Double annualSalary;
	public double hourlyRate;
	public boolean isPaidVacation;
	public int vacationDays;
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		allPeople.add(this);
	}
}
