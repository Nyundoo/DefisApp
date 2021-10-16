package com.Defis.exporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.Defis.domain.Agent;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class AgentPDFExporter extends AbstractExporter {

	public void export(List<Agent> listAgents, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/pdf", ".pdf");
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font =FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		
		Paragraph paragraph = new Paragraph("List of Agent", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(new Paragraph(paragraph));
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});
		
		writeTableHeader(table);
		writeTableData(table, listAgents);
		
		document.add(table);
		
		document.close();
	}

	private void writeTableData(PdfPTable table, List<Agent> listAgents) {
		for(Agent agent : listAgents) {
			table.addCell(String.valueOf(agent.getId()));
			table.addCell(agent.getEmail());
			table.addCell(agent.getFirstName());
			table.addCell(agent.getLastName());
			table.addCell(agent.getPhoneNumber1());
			table.addCell(agent.getPhoneNumber2());
			table.addCell(agent.getCurrentResidence());
			table.addCell(agent.getEmail());
			table.addCell(agent.getCounty());
			table.addCell(agent.getWard());
			table.addCell(agent.getVillageName());
		}
		}
		
	

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(14);
		
		cell.setPhrase(new Phrase("ID", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Email", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("First Name", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Last Name", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Phone Number1", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Phone Number2", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Current Residence", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Email", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("County", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Ward", font));				
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Village Name", font));				
		table.addCell(cell);
	}

}
