package com.Defis.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Defis.domain.Medical;

public class MedicalExcelExporter extends AbstractExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	public MedicalExcelExporter() {
		workbook = new XSSFWorkbook();
	}
	
	private void writeHeaderLine() {
		sheet = workbook.createSheet("Users");
		
		XSSFRow row = sheet.createRow(0);
		
		
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		cellStyle.setFont(font);
		
		createCell(row, 0, "Applicant Id", cellStyle);
		createCell(row, 1, "Client Info", cellStyle);
		createCell(row, 2, "Medical Center", cellStyle);
		createCell(row, 3, "Medical Type", cellStyle);
		createCell(row, 4, "Amount Paid", cellStyle);
		createCell(row, 5, "Application Date", cellStyle);
	}
	
	private void createCell(XSSFRow row, int columnIndex, Object value, CellStyle style) {
		XSSFCell cell = row.createCell(columnIndex);
		sheet.autoSizeColumn(columnIndex);
		
		if(value instanceof Integer) {
		cell.setCellValue((Integer) value);
		}else if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			cell.setCellValue((String) value);
		}
		
		cell.setCellStyle(style);
	}
	
	public void export(List<Medical> listMedicals, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/octet", ".xlsx");
		
		writeHeaderLine();
		writeDataLines(listMedicals);
		
		ServletOutputStream outputStream = response.getOutputStream();	
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	private void writeDataLines(List<Medical> listMedicals) {
		int rowIndex = 1;
		
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(false);
		font.setFontHeight(14);
		cellStyle.setFont(font);
		
		for (Medical medical : listMedicals) {
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;
			
			createCell(row, columnIndex++, medical.getApplicantId(), cellStyle);
			createCell(row, columnIndex++, medical.getClientInfo(), cellStyle);
			createCell(row, columnIndex++, medical.getMedicalCenter(), cellStyle);
			createCell(row, columnIndex++, medical.getMedicalType(), cellStyle);
			createCell(row, columnIndex++, medical.getAmountPaid(), cellStyle);
			createCell(row, columnIndex++, medical.getApplicationDate(), cellStyle);
	}
}
}