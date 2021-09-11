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

import com.Defis.domain.Birth;

public class BirthExcelExporter extends AbstractExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	public BirthExcelExporter() {
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
		createCell(row, 1, "Cert No", cellStyle);
		createCell(row, 2, "Paid", cellStyle);
		createCell(row, 3, "Cert App Date", cellStyle);
		createCell(row, 4, "Cert Recep Date", cellStyle);
		createCell(row, 5, "User", cellStyle);
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
	
	public void export(List<Birth> listBirthCerts, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/octet", ".xlsx");
		
		writeHeaderLine();
		writeDataLines(listBirthCerts);
		
		ServletOutputStream outputStream = response.getOutputStream();	
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	private void writeDataLines(List<Birth> listBirthCerts) {
		int rowIndex = 1;
		
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(false);
		font.setFontHeight(14);
		cellStyle.setFont(font);
		
		for (Birth birthcert : listBirthCerts) {
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;
			
			createCell(row, columnIndex++, birthcert.getApplicant(), cellStyle);
			createCell(row, columnIndex++, birthcert.getCert_no(), cellStyle);
			createCell(row, columnIndex++, birthcert.getCert_application_date(), cellStyle);
			createCell(row, columnIndex++, birthcert.getCert_reception_date(), cellStyle);
			createCell(row, columnIndex++, birthcert.getPaid(), cellStyle);
			createCell(row, columnIndex++, birthcert.getUser2(), cellStyle);
	}
}
}
