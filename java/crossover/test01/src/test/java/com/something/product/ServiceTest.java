package com.something.product;

import org.junit.Test;

import com.something.product.impl.ServiceImpl;

import static org.junit.Assert.*;

public class ServiceTest {
	@Test
	public void test() {
		Service s = new ServiceImpl();

		s.addSalariedPerson("first1",  "last1", 10000.0);
		s.addSalariedPerson("first2",  "last2", 20000.0);
		s.addSalariedPerson("first3",  "last3", 30000.0);
		s.addHourlyPerson("first4", "last4", 10.0, false, 10);

		assertEquals( "<people><person firstName=\"first1\" lastName=\"last1\"/>\n" +
				"<person firstName=\"first2\" lastName=\"last2\"/>\n" +
				"<person firstName=\"first3\" lastName=\"last3\"/>\n" +
				"<person firstName=\"first4\" lastName=\"last4\"/>\n" +
				"</people>",
				s.xmlAttributesReport());

		assertEquals( "<people><person>first1 last1</person><person>first2 last2</person><person>first3 last3</person><person>first4 last4</person></people>",
				s.xmlTextReport());

		assertEquals( "first1 last1,10000.0\n" +
				"first2 last2,20000.0\n" +
				"first3 last3,30000.0\n" +
				"first4 last4,20000.0\n",
				s.firstLastSalaryReport());

		assertEquals( "first4 last4,20000.0\n", s.hourlyPeopleSalaryReport());

		assertEquals("first1 last1\n" +
						"first2 last2\n" +
						"first3 last3\n" +
						"first4 last4\n",
				s.firstLastReport());
		assertEquals( "first1.last1\n" +
						"first2.last2\n" +
						"first3.last3\n" +
						"first4.last4\n",
				s.firstDotLastReport());

		assertEquals( "last1,first1,10000.0\n" +
						"last2,first2,20000.0\n" +
						"last3,first3,30000.0\n" +
						"last4,first4,20000.0\n",
				s.csvSalaryReport());
	}
}
