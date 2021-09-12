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

import com.Defis.domain.Jobs;

public class JobsExcelExporter extends AbstractExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	public JobsExcelExporter() {
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
		
		createCell(row, 0, "User Id", cellStyle);
		createCell(row, 1, "E-mail", cellStyle);
		createCell(row, 2, "First Name", cellStyle);
		createCell(row, 3, "Last Name", cellStyle);
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
	
	public void export(List<Jobs> listJobss, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/octet", ".xlsx");
		
		writeHeaderLine();
		writeDataLines(listJobss);
		
		ServletOutputStream outputStream = response.getOutputStream();	
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	private void writeDataLines(List<Jobs> listJobss) {
		int rowIndex = 1;
		
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(false);
		font.setFontHeight(14);
		cellStyle.setFont(font);
		
		for (Jobs jobs : listJobss) {
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;
			
			createCell(row, columnIndex++, jobs.getCountry(), cellStyle);
			createCell(row, columnIndex++, jobs.getJobTitle(), cellStyle);
			createCell(row, columnIndex++, jobs.getPayment(), cellStyle);
			createCell(row, columnIndex++, jobs.getJobDescription(), cellStyle);
			createCell(row, columnIndex++, jobs.getQualification(), cellStyle);
			createCell(row, columnIndex++, jobs.getInterviewDate(), cellStyle);
			createCell(row, columnIndex++, jobs.getNoVacancy(), cellStyle);
			createCell(row, columnIndex++, jobs.getDatePosted(), cellStyle);
		}
	}
}