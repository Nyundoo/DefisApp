package com.Defis.exporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.Defis.domain.Payment;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PaymentPDFExporter extends AbstractExporter {

	public void export(List<Payment> listPayments, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/pdf", ".pdf");

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);

		Paragraph paragraph = new Paragraph("List of Payment", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(new Paragraph(paragraph));

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		table.setWidths(new float[] { 1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f });

		writeTableHeader(table);
		writeTableData(table, listPayments);

		document.add(table);

		document.close();
	}

	private void writeTableData(PdfPTable table, List<Payment> listPayments) {
		for (Payment payment : listPayments) {
			table.addCell(String.valueOf(payment.getApplicant()));
			table.addCell(String.valueOf(payment.getAmount_paid()));
			table.addCell(String.valueOf(payment.getDate_paid()));
		}
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(14);
		cell.setPhrase(new Phrase("Applicant", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Amount Paid", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Date Paid", font));

	}
}
