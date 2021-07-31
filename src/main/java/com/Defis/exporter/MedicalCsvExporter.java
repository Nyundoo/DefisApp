package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.Defis.domain.Medical;

public class MedicalCsvExporter extends AbstractExporter {
public void export(List<Medical> listMedicals, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Medical ID", "E-mail", "First Name", "Last Name"};
		String[] fieldMapping = {"id", "email", "firstName", "lastName"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Medical medical : listMedicals) {
			csvWriter.write(medical, fieldMapping);
		}
		
		csvWriter.close();

	}
}
