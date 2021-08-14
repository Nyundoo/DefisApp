package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.Defis.domain.Passport;

public class PassportCsvExporter extends AbstractExporter {
	public void export(List<Passport> listPassports, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Applicant", "Passport No", "Pass Paid", "Pass App Date", "Pass Recep Date", "User"};
		String[] fieldMapping = {"applicant", "passport_no", "pass_paid", "pass_application_date", "pass_reception_date", "user3"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Passport passport : listPassports) {
			csvWriter.write(passport, fieldMapping);
		}
		
		csvWriter.close();

	}
}
