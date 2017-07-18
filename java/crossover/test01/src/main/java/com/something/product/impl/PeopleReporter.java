package com.something.product.impl;

public class PeopleReporter {

	public String report() {
		return internalReport("first last");
	}

	public String dotReport() {
		return internalReport("first.last");
	}
	
	public String xmlAttributesReport() {
		return internalReport("xmlAttributesReport");
	}

	public String xmlTextReport() {
		return internalReport("xmlTextReport");
	}
	
	private String internalReport(String t) {
		String result = "";
		
		if (t.startsWith("xml")) {
			result = "<people>";
		}
		
		for(Person p : Person.allPeople) {
			if (t.equals("first last")) {
				result += p.firstName + " " + p.lastName + "\n";
			}
			if (t.equals("first.last")) {
				result += p.firstName + "." + p.lastName + "\n";
			}
			if (t.equals("last,first")) {
				result += p.lastName + "," + p.firstName + "\n";
			}
			if (t.equals("xmlAttributesReport")) {
				result += "<person firstName=\"" + p.firstName + "\" lastName=\"" + p.lastName + "\"/>\n";
			}
			if (t.equals("xmlTextReport")) {
				result += "<person>" + p.firstName + " " + p.lastName + "</person>";
			}
		}
		
		if (t.startsWith("xml")) {
			result += "</people>";
		}
		return result;
	}

	
}
