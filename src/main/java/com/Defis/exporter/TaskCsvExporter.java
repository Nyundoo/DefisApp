package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.Defis.domain.Task;

public class TaskCsvExporter extends AbstractExporter {

	public void export(List<Task> listTasks, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Task", "Task Description", "Created At", "Deadline Date"};
		String[] fieldMapping = {"createTask", "taskDescription", "createdAt", "deadlineDate"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Task tasks : listTasks) {
			csvWriter.write(tasks, fieldMapping);
		}
		
		csvWriter.close();

	}
}
