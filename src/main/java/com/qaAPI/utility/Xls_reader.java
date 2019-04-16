package com.qaAPI.utility;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Xls_reader {
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public String filepath=null;
	XSSFWorkbook workbook = null;
	XSSFSheet sheet = null;
	XSSFRow row = null;
	XSSFCell cell = null;

	public Xls_reader(String filepath) throws Exception {
	
			fis = new FileInputStream(filepath);
			
			this.filepath = filepath;
			workbook = new XSSFWorkbook(fis);
			fis.close();
	}

	public int getRowCount(String sheetname) {
		int index = workbook.getSheetIndex(sheetname);
		if (index == -1)
			return 0;
		sheet = workbook.getSheet(sheetname);
		int rowCount = sheet.getLastRowNum() + 1;
		return rowCount;

	}

	public String getCellData(String sheetname, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";
			int index = workbook.getSheetIndex(sheetname);
			if (index == -1)
				return "";
			sheet = workbook.getSheet(sheetname);
			row = sheet.getRow(rowNum-1);
			cell = row.getCell(colNum);
			
			if (cell.getCellTypeEnum() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
				String cellValue = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					DateFormat df = new SimpleDateFormat("dd/MM/yy");
					Date date = cell.getDateCellValue();
					cellValue = df.format(date);
				}
				return cellValue;
			} else if (cell.getCellTypeEnum() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		} catch (Exception e) {
			e.printStackTrace();
			return "No match value";
		}
	}

	public String getCellData(String sheetname, String colName, int rowNum) {
		try {
			int colNum = -1;
			sheet = workbook.getSheet(sheetname);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}
			row = sheet.getRow(rowNum-1);
			cell = row.getCell(colNum);
			if (cell.getCellTypeEnum() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
				String cellValue = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					DateFormat df = new SimpleDateFormat("dd/MM/yy");
					Date date = cell.getDateCellValue();
					cellValue = df.format(date);
				}
				return cellValue;
			} else if (cell.getCellTypeEnum() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		} catch (Exception e) {
			e.printStackTrace();
			return "No match value";
		}

	}

	public XSSFCell setCellData(String sheetname, int colNum, int rowNum, String value) {

		try {

			sheet = workbook.getSheet(sheetname);
			row = sheet.getRow(rowNum-1);
			sheet.autoSizeColumn(colNum); 
			if (row == null) {
				row = sheet.createRow(rowNum-1);
			}
		
			cell = row.getCell(colNum);	
			if (cell == null) {
				cell = row.createCell(colNum);
			}
			// setupFont(cell);
			cell.setCellValue(value);
			fos = new FileOutputStream(filepath);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return cell;
		}
		return cell;
	}

	public XSSFCell setCellData(String sheetname, String colName, int rowNum, String value) {

		try {
			

			int colNum = -1;
			sheet = workbook.getSheet(sheetname);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}
			row = sheet.getRow(rowNum-1);
			sheet.autoSizeColumn(colNum); 
			if (row == null) {
				row = sheet.createRow(rowNum-1);
			}
			cell = row.getCell(colNum);
			if (cell == null) {
				cell = row.createCell(colNum);
			}
			// setupFont(cell);
			cell.setCellValue(value);
			fos = new FileOutputStream(filepath);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return cell;
		}
		return cell;
	}

	public int getColCount(String sheetname) {
		if (!isSheetExist(sheetname))
			return -1;
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(0);
		if (row == null)
			return -1;
		int colCount = row.getLastCellNum();
		return colCount;

	}

	public boolean isSheetExist(String sheetname) {
		int index = workbook.getSheetIndex(sheetname);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetname.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	public void setupFont(XSSFCell cell, short color) throws Exception {

		XSSFFont font = workbook.createFont();
		XSSFCellStyle style = workbook.createCellStyle();
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontHeight(11.0);
		font.setBold(true);
		font.setColor(color);
		style.setFont(font);
		style.setFillForegroundColor(HSSFColorPredefined.GREY_40_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fos = new FileOutputStream(filepath);
		workbook.write(fos);
		fos.close();
	}

	public void createHyperlink(String sheetname,XSSFCell cell, String linktext) throws Exception {
		
		linktext=linktext.replace('\\', '/');
		
		
	    sheet = workbook.getSheet(sheetname);
	    XSSFCreationHelper createHelper = workbook.getCreationHelper();
		XSSFFont link_font = workbook.createFont();
		XSSFCellStyle link_style = workbook.createCellStyle();
		link_font.setUnderline(FontUnderline.SINGLE);
		link_font.setColor(HSSFColorPredefined.BLUE.getIndex());
		link_style.setFont(link_font);
		if (!linktext.isEmpty()) {
			Hyperlink link = createHelper.createHyperlink(HyperlinkType.FILE);
			link.setAddress(linktext);
			cell.setHyperlink(link);
			cell.setCellStyle(link_style);
		}

		fos = new FileOutputStream(filepath);
		workbook.write(fos);
		fos.close();
	}
	

	public boolean addSheet(String sheetname) {

		try {
			workbook.createSheet(sheetname);
			fos = new FileOutputStream(filepath);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeSheet(String sheetname) {
		int index = workbook.getSheetIndex(sheetname);
		if (index == -1)
			return false;

		try {
			workbook.removeSheetAt(index);
			fos = new FileOutputStream(filepath);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean addColumn(String sheetname,String colName){
		//System.out.println("**************addColumn*********************");
		
		try{				
			
			int index = workbook.getSheetIndex(sheetname);
			if(index==-1)
				return false;
			
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColorPredefined.GREY_40_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		sheet=workbook.getSheetAt(index);
		
		row = sheet.getRow(0);
		if (row == null)
			row = sheet.createRow(0);
		
		//cell = row.getCell();	
		//if (cell == null)
		//System.out.println(row.getLastCellNum());
		if(row.getLastCellNum() == -1)
			cell = row.createCell(0);
		else
			cell = row.createCell(row.getLastCellNum());
	        
	        cell.setCellValue(colName);
	        cell.setCellStyle(style);
	        
	        fos = new FileOutputStream(filepath);
			workbook.write(fos);
		    fos.close();		    

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
		
		
	}

	public boolean removeColumn(String sheetName, int colNum) {
		try{
		if(!isSheetExist(sheetName))
			return false;
		
		sheet=workbook.getSheet(sheetName);
		XSSFCellStyle style = workbook.createCellStyle();
		
		style.setFillForegroundColor(HSSFColorPredefined.GREY_40_PERCENT.getIndex());
				
		style.setFillPattern(FillPatternType.NO_FILL);
		
	    
	
		for(int i =0;i<getRowCount(sheetName);i++){
			row=sheet.getRow(i);	
			if(row!=null){
				cell=row.getCell(colNum);
				if(cell!=null){
					cell.setCellStyle(style);
					row.removeCell(cell);
				}
			}
		}
		fos = new FileOutputStream(filepath);
		workbook.write(fos);
	    fos.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	public int getCellRowNum(String sheetname,String colName,String cellValue){
		
		for(int i=2;i<=getRowCount(sheetname);i++){
	    	if(getCellData(sheetname,colName , i).equalsIgnoreCase(cellValue)){
	    		return i;
	    	}
	    }
		return -1;
		
	}


}
