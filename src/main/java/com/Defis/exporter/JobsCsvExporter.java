package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.Defis.domain.Jobs;

public class JobsCsvExporter extends AbstractExporter {
	public void export(List<Jobs> listJobss, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Country", "JobT itle", "Payment", "Job Description", "Job Description", "Qualification", "Interview Date", "Number of Vacancy", "Date Posted"};
		String[] fieldMapping = {"country", "jobTitle", "payment", "jobDescription", "jobDescription", "qualification", "interviewDate", "noVacancy", "datePosted"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Jobs jobs : listJobss) {
			csvWriter.write(jobs, fieldMapping);
		}
		
		csvWriter.close();

	}
}
