package com.exelenter.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

//needed to import Apachi POI
public class ExcelUtility {
    //Order : 1.filePath 2.Workbook 3.Sheet 4.Rows&Cols 5.Cell (Create a method for each step)
    public static String projectPath=System.getProperty("user.dir");
    private static FileInputStream fileInputStream;
    private static Workbook workbook;
    private static Sheet sheet;
    private static void getFilePath(String filePath){
        try {
            fileInputStream = new FileInputStream(filePath);
            workbook =new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getSheet(String sheetName){
        sheet = workbook.getSheet(sheetName);
    }

    private static int rowCount(){
        return sheet.getPhysicalNumberOfRows();    //this method will return total count of rows
    }

    private static int colsCount(){
        return sheet.getRow(0).getLastCellNum();  //this method will return total count of columns
    }

//    private static String getCell(int rowIndex, int columnIndex){   //this method will read from cell value
//        String cellValue = sheet.getRow(rowIndex).getCell(columnIndex).toString();
//        return cellValue;
//    }

    // getCell methodu, exceldeki bos hucrelerde nullpointerexception hatasi veriyordu. o nedenle asagidaki kod ile try catch kullanarak methodu yeniledik.
    private static String getCell(int rowIndex, int columnIndex){
        String cellValue = "";
        try {
            if (sheet.getRow(rowIndex).getCell(columnIndex) != null) {
                cellValue = sheet.getRow(rowIndex).getCell(columnIndex).toString();
            }
        } catch (NullPointerException e) {
            System.out.println("Cell is empty at row " + rowIndex + " column " + columnIndex);
        }
        return cellValue;
    }

    public static Object[][] readFromExcel(String filePath, String sheetName){
        getFilePath(filePath);
        getSheet(sheetName);

        int rows = rowCount();
        int cols = colsCount();
        Object[][] data = new Object[rows-1][cols];
        // delete 1 from the number of rows because we dropped the title row.
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //String cellValue = sheet.getRow(i).getCell(j).toString();
                //String cellValue = getCell(i,j);\
                if (getCell(i,j)!=null){
                    data[i-1][j] = getCell(i,j);
                }
            }
        }
        try {
            workbook.close();    //closing part is optional but good practise
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
