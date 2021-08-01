package com.Defis.exporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.Defis.domain.Applicant;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ApplicantPDFExporter extends AbstractExporter {


	public void export(List<Applicant> listApplicants, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/pdf", ".pdf");
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font =FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph paragraph = new Paragraph("List of Applicant", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(new Paragraph(paragraph));
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});
		
		writeTableHeader(table);
		writeTableData(table, listApplicants);
		
		document.add(table);
		
		document.close();
	}

	private void writeTableData(PdfPTable table, List<Applicant> listApplicants) {
		for(Applicant applicant : listApplicants) {
			table.addCell(String.valueOf(applicant.getIdentification()));
			table.addCell(applicant.getEmail());
			table.addCell(applicant.getFirstName());
			table.addCell(applicant.getLastName());
			table.addCell(applicant.getContact());
			table.addCell(applicant.getCounty());
			table.addCell(applicant.getWard());
		}
		}
		
	

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(14);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("ID", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Email", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("First Name", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Last Name", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("getContact", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("County", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Email", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("County", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Ward", font));				
		table.addCell(cell);
	}

}