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

import com.Defis.domain.Applicant;

public class ApplicantExcelExporter extends AbstractExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	public ApplicantExcelExporter() {
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
		
		createCell(row, 0, "getIdentification", cellStyle);
		createCell(row, 1, "Age", cellStyle);
		createCell(row, 2, "First Name", cellStyle);
		createCell(row, 3, "Last Name", cellStyle);
		createCell(row, 0, "County", cellStyle);
		createCell(row, 1, "Ward", cellStyle);
		createCell(row, 2, "Village Name", cellStyle);
		createCell(row, 3, "Email", cellStyle);
		createCell(row, 0, "Huduma No.", cellStyle);
		createCell(row, 1, "Chief Name", cellStyle);
		createCell(row, 2, "Contact", cellStyle);
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
	
	public void export(List<Applicant> listApplicants, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/octet", ".xlsx");
		
		writeHeaderLine();
		writeDataLines(listApplicants);
		
		ServletOutputStream outputStream = response.getOutputStream();	
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	private void writeDataLines(List<Applicant> listApplicants) {
		int rowIndex = 1;
		
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(false);
		font.setFontHeight(14);
		cellStyle.setFont(font);
		
		for (Applicant applicant : listApplicants) {
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;
			
			createCell(row, columnIndex++, applicant.getIdentification(), cellStyle);
			createCell(row, columnIndex++, applicant.getAge(), cellStyle);
			createCell(row, columnIndex++, applicant.getFirstName(), cellStyle);
			createCell(row, columnIndex++, applicant.getLastName(), cellStyle);
			createCell(row, columnIndex++, applicant.getCounty(), cellStyle);
			createCell(row, columnIndex++, applicant.getWard(), cellStyle);
			createCell(row, columnIndex++, applicant.getVillageName(), cellStyle);
			createCell(row, columnIndex++, applicant.getEmail(), cellStyle);
			createCell(row, columnIndex++, applicant.getHudumaNo(), cellStyle);
			createCell(row, columnIndex++, applicant.getChiefName(), cellStyle);
			createCell(row, columnIndex++, applicant.getContact(), cellStyle);
		}
	}
}
