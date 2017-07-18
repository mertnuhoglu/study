package com.something.product.impl;

public class SalaryReporter {

	public String report() {
		return internalReport("first last", false);
	}
	
	public String csvSalaryReport() {
		return internalReport("last,first", false);
	}
	
	public String hourlyPeopleSalaryReport() {
		return internalReport("first last", true);
	}
	
	private String internalReport(String t, boolean hourlyOnly) {
		String result = "";
		for(Person p : Person.allPeople) {
			String firstLast = String.format("%s%s%s,%s\n", p.firstName, "%s", p.lastName, "%s");
			String lastFirst= String.format("%s%s%s,%s\n", p.lastName, "%s", p.firstName, "%s");
			if (p.annualSalary != null) {
				if (!hourlyOnly) {
					String firstLastLine = String.format(firstLast, "%s", p.annualSalary.toString());
					String lastFirstLine = String.format(lastFirst, "%s", p.annualSalary.toString());
					if (t.equals("first last")) {
						result += String.format(firstLastLine, " ");
					}
					if (t.equals("first.last")) {
						result += String.format(firstLastLine, ".");
					}
					if (t.equals("last,first")) {
						result += String.format(lastFirstLine, ",");
					}
				}
			} else {
				final Double vacationSum = p.isPaidVacation ? 0 : p.vacationDays * 8 * p.hourlyRate;
				final Double salary = p.hourlyRate * 40 * 52 - vacationSum;
				String firstLastLine = String.format(firstLast, "%s", salary.toString());
				String lastFirstLine = String.format(lastFirst, "%s", salary.toString());
				if (t.equals("first last")) {
                    result += String.format(firstLastLine, " ");
				}
				if (t.equals("first.last")) {
					result += String.format(firstLastLine, ".");
				}
				if (t.equals("last,first")) {
					result += String.format(lastFirstLine, ",");
				}
			}
		}
		return result;
	}
}
