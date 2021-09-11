package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.Defis.domain.Applicant;

public class ApplicantCsvExporter extends AbstractExporter {
	public void export(List<Applicant> listApplicants, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Applicant ID", "E-mail", "First Name", "Last Name"};
		String[] fieldMapping = {"id", "email", "firstName", "lastName"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Applicant applicant : listApplicants) {
			csvWriter.write(applicant, fieldMapping);
		}
		
		csvWriter.close();

	}
}
