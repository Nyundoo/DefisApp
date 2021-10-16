package com.Defis.exporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.Defis.domain.Medical;
import com.Defis.domain.Visa;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class VisaPDFExporter extends AbstractExporter {

	public void export(List<Visa> listVisas, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/pdf", ".pdf");
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font =FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		
		Paragraph paragraph = new Paragraph("List of Visa", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(new Paragraph(paragraph));
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});
		
		writeTableHeader(table);
		writeTableData(table, listVisas);
		
		document.add(table);
		
		document.close();
	}

	private void writeTableData(PdfPTable table, List<Visa> listVisas) {
		for(Visa visa : listVisas) {
			table.addCell(String.valueOf(visa.getApplicant()));
			table.addCell(String.valueOf(visa.getType_of_visa()));
			table.addCell(String.valueOf(visa.getVisa_apply_date()));
			table.addCell(String.valueOf(visa.getVisa_reception_date()));
			table.addCell(String.valueOf(visa.getUser4()));
			table.addCell(String.valueOf(visa.isStatus()));
		}
		}
		
	

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(14);
		
		cell.setPhrase(new Phrase("Applicant Id", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Type of Visa", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Visa Apply Date", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Visa reception Date", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("User", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Status", font));				
		table.addCell(cell);
	}
}
