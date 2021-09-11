package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.Defis.domain.Visa;

public class VisaCsvExporter extends AbstractExporter {

public void export(List<Visa> listVisas, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Visa ID", "E-mail", "First Name", "Last Name"};
		String[] fieldMapping = {"id", "email", "firstName", "lastName"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Visa visa : listVisas) {
			csvWriter.write(visa, fieldMapping);
		}
		
		csvWriter.close();

	}
}
