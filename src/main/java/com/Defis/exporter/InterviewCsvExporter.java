package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.Defis.domain.Interview;

public class InterviewCsvExporter extends AbstractExporter {

	public void export(List<Interview> listInterviews, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Applicant", "Start Time", "Type Of Interview", "Status"};
		String[] fieldMapping = {"applicant", "startTime", "typeOfInterview", "i_status"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Interview interviews : listInterviews) {
			csvWriter.write(interviews, fieldMapping);
		}
		
		csvWriter.close();

	}
}
