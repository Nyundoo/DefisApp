package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.Defis.domain.Payment;

public class PaymentCsvExporter extends AbstractExporter {
	public void export(List<Payment> listPayments, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Payment ID", "Applicant", "Amount Paid", "Date Paid"};
		String[] fieldMapping = {"id", "applicant", "amount_paid", "date_paid"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Payment payment : listPayments) {
			csvWriter.write(payment, fieldMapping);
		}
		
		csvWriter.close();

	}
}
