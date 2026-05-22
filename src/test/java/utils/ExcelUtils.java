//package dataDriven;
//
//import java.io.FileInputStream;
//
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelUtils {
//	
//	static String path = "TestData/tasks.xlsx";
//	
//	public static String getData(int row, int col) {
//		try {
//			FileInputStream file = new FileInputStream(path);
//			XSSFWorkbook wb = new XSSFWorkbook(file);
//			XSSFSheet sheet = wb.getSheet("Sheet1");
//			
//			return sheet.getRow(row).getCell(col).toString();
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			return "";
//		}
//	}
//	
//	public static int getRowCount() {
//		try {
//			FileInputStream file = new FileInputStream(path);
//			XSSFWorkbook wb = new XSSFWorkbook(file);
//			XSSFSheet sheet = wb.getSheet("Sheet1");
//			
//			return sheet.getLastRowNum();
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			return 0;
//		}
//	}
//}


//package dataDriven;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.*;
//import java.io.FileInputStream;
//
//public class ExcelUtils {
//
//    static String path = "TestData/tasks.xlsx";
//
//    public static String getData(int row, int col) {
//        try {
//            FileInputStream file = new FileInputStream(path);
//            XSSFWorkbook wb = new XSSFWorkbook(file);
//            XSSFSheet sheet = wb.getSheet("Sheet1");
//
//            String data = sheet.getRow(row).getCell(col).toString();
//            
//           return sheet.getRow(row).getCell(col).toString();
//            
//            wb.close();
//            file.close();
//            
//            return data;
//
//        } catch (Exception e) {
//            return "";
//        }
//    }
//
//    
//
//    public static int getRowCount() {
//        try {
//            FileInputStream file = new FileInputStream(path);
//            XSSFWorkbook wb = new XSSFWorkbook(file);
//            XSSFSheet sheet = wb.getSheet("Sheet1");
//
//           return sheet.getLastRowNum();
//            int rowCount = sheet.getLastRowNum();
//            wb.close();
//            file.close();
//            return rowCount;
//
//        } catch (Exception e) {
//            return 0;
//        }
//    }
//}


package utils;

import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;

public class ExcelUtils {

    static String path = "TestData/tasks.xlsx";

    public static String getData(int row, int col) {
        try {
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheet("Sheet1");

            String data = sheet.getRow(row).getCell(col).toString();

            wb.close();
            file.close();

            return data;

        } catch (Exception e) {
            return "";
        }
    }

    public static int getRowCount() {
        try {
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheet("Sheet1");

            int rowCount = sheet.getLastRowNum();

            wb.close();
            file.close();

            return rowCount;

        } catch (Exception e) {
            return 0;
        }
    }
}
