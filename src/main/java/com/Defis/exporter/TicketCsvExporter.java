package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.Defis.domain.Ticket;

public class TicketCsvExporter extends AbstractExporter {


	public void export(List<Ticket> listTickets, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Applicant", "Ticket Date", "Destination", "Date Travel", "Flight Name", "Arrival Date", "Travel Status"};
		String[] fieldMapping = {"applicant", "ticket_date", "destination", "date_travel", "flight_name", "expected_arrival_date", "travel_status"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Ticket ticket : listTickets) {
			csvWriter.write(ticket, fieldMapping);
		}
		
		csvWriter.close();

	}

}
