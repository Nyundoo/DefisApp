package com.Defis.exporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.Defis.domain.Birth;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class BirthPDFExporter extends AbstractExporter {

	public void export(List<Birth> listBirthCerts, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/pdf", ".pdf");
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font =FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		
		Paragraph paragraph = new Paragraph("List of BirthCert", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(new Paragraph(paragraph));
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});
		
		writeTableHeader(table);
		writeTableData(table, listBirthCerts);
		
		document.add(table);
		
		document.close();
	}

	private void writeTableData(PdfPTable table, List<Birth> listBirthCerts) {
		for(Birth birthcert : listBirthCerts) {
			table.addCell(String.valueOf(birthcert.getApplicant()));
			table.addCell(String.valueOf(birthcert.getCert_no()));
			table.addCell(String.valueOf(birthcert.getCert_application_date()));
			table.addCell(String.valueOf(birthcert.getCert_reception_date()));
			table.addCell(String.valueOf(birthcert.getUser2()));
		}
		}
		
	

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(14);
		
		cell.setPhrase(new Phrase("Applicant Id", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Cert No", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Cert Application Date", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Cert Reception Date", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("User", font));				
		table.addCell(cell);
	}
}
