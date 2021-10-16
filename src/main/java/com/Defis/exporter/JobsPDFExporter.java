package com.Defis.exporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.Defis.domain.Jobs;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class JobsPDFExporter extends AbstractExporter {

	public void export(List<Jobs> listJobss, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/pdf", ".pdf");
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font =FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		
		Paragraph paragraph = new Paragraph("List of Job", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(new Paragraph(paragraph));
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});
		
		writeTableHeader(table);
		writeTableData(table, listJobss);
		
		document.add(table);
		
		document.close();
	}

	private void writeTableData(PdfPTable table, List<Jobs> listJobss) {
		for(Jobs jobs : listJobss) {
			table.addCell(String.valueOf(jobs.getId()));
			table.addCell(jobs.getCountry());
			table.addCell(String.valueOf(jobs.getPayment()));
			table.addCell(jobs.getJobDescription());
			table.addCell(String.valueOf(jobs.getInterviewDate()));
			table.addCell(String.valueOf(jobs.getNoVacancy()));
			table.addCell(String.valueOf(jobs.getDatePosted()));
		}
		
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(14);
		
		cell.setPhrase(new Phrase("ID", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Country", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Payment", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Job Description", font));				
		table.addCell(cell);
		
		
		cell.setPhrase(new Phrase("Interview Date", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("No Vacancy", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Date Posted", font));				
		table.addCell(cell);
	}
}
