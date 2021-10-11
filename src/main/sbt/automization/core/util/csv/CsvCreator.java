package sbt.automization.core.util.csv;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public final class CsvCreator {
    private final Character delimiter = '$';

    public CsvCreator() {
    }

    public File createFromExcel(File file, String sheetName) {

        File csvOutputFile = new File("tmp.csv");

        try (FileInputStream fis = new FileInputStream(file);
             FileWriter fw = new FileWriter(csvOutputFile, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(fw)) {
            //obtaining bytes from the file

            if ("".equals(sheetName)) return null;
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet(sheetName);     //creating a Sheet object to retrieve object
            //there might be a path problem
            //PrintWriter csvWriter = new PrintWriter(csvOutputFile);

            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext()) {
                Row row = itr.next();
                //Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                //while (cellIterator.hasNext())
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    //Cell cell = cellIterator.next();
                    Cell cell = row.getCell(i);
                    DataFormatter formatter = new DataFormatter();
                    String strValue = formatter.formatCellValue(cell);
                    writer.write(strValue);
                    writer.write(delimiter);
                    //System.out.print(strValue + ";");
                }
                writer.newLine();
                //System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return csvOutputFile;
    }

    public Character getDelimiter() {
        return delimiter;
    }
}

