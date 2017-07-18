package com.something.product;

import org.junit.Test;

import com.something.product.impl.Person;
import com.something.product.impl.PeopleReporter;
import com.something.product.impl.SalaryReporter;

import static org.junit.Assert.*;

public class ReporterTest {

	@Test
	public void testIt() {
		Person.allPeople.clear();
		new Person("first1", "last1");
		new Person("first2", "last2");
		new Person("first3", "last3");

		final PeopleReporter r = new PeopleReporter();
		assertEquals("first1 last1\nfirst2 last2\nfirst3 last3\n",
				r.report());
		assertEquals("first1.last1\n" +
				"first2.last2\n" +
				"first3.last3\n",
				r.dotReport());
	}
	
	@Test
	public void testXml() {
		Person.allPeople.clear();
		new Person("first1", "last1");
		new Person("first2", "last2");
		new Person("first3", "last3");

		final PeopleReporter r = new PeopleReporter();

		assertEquals( "<people><person firstName=\"first1\" lastName=\"last1\"/>\n" +
				"<person firstName=\"first2\" lastName=\"last2\"/>\n" +
				"<person firstName=\"first3\" lastName=\"last3\"/>\n" +
				"</people>",
				r.xmlAttributesReport());
		assertEquals( "<people><person>first1 last1</person><person>first2 last2</person><person>first3 last3</person></people>",
				r.xmlTextReport());

		
	}

	@Test
	public void testSalaryReport() {
		Person.allPeople.clear();
		new Person("first1", "last1").annualSalary = 10000.0;
		new Person("first2", "last2").annualSalary = 20000.0;
		new Person("first3", "last3").annualSalary = 30000.0;
		Person p4 = new Person("first4", "last4");
		p4.hourlyRate = 10.0;
		p4.isPaidVacation = false;
		p4.vacationDays = 10;
		
		SalaryReporter r = new SalaryReporter();

		assertEquals( "first1 last1,10000.0\n" +
				"first2 last2,20000.0\n" +
				"first3 last3,30000.0\n" +
				"first4 last4,20000.0\n",
				r.report());
		assertEquals( "first4 last4,20000.0\n", r.hourlyPeopleSalaryReport() );
		assertEquals("last1,first1,10000.0\n" +
				"last2,first2,20000.0\n" +
				"last3,first3,30000.0\n" +
				"last4,first4,20000.0\n" , r.csvSalaryReport());
		
		
	}


	
}
