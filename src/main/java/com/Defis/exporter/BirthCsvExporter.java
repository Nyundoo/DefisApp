package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.Defis.domain.Birth;

public class BirthCsvExporter extends AbstractExporter {
	public void export(List<Birth> listBirthCerts, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"BirthCert ID", "Applicant", "Cert No", "Paid", "Cert App Date", "Cert Recep Date", "User"};
		String[] fieldMapping = {"id", "applicant", "cert_no", "paid", "cert_application_date", "cert_reception_date","user2"};
		
		csvWriter.writeHeader(csvHeader);
		
		for (Birth birthcert : listBirthCerts) {
			csvWriter.write(birthcert, fieldMapping);
		}
		
		csvWriter.close();

	}
}
