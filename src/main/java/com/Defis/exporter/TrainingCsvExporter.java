package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.Defis.domain.Training;

public class TrainingCsvExporter extends AbstractExporter {

	public void export(List<Training> listTrainings, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Applicant", "Start Date", "Finish Date", "Cert No"};
		String[] fieldMapping = {"applicant", "start_date", "finish_date", "cert_no"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Training training : listTrainings) {
			csvWriter.write(training, fieldMapping);
		}
		
		csvWriter.close();

	}
}
