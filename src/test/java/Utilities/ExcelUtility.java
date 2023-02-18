package Utilities;

import com.aventstack.extentreports.gherkin.model.Scenario;
import io.netty.buffer.ByteBufInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
/*todo Kendisine verilen path deki excelin,
   istenilen,sheetindeki verilen kolan kadar bilgisini okuyup Arraylist formatinda geri döndüren fonksiyonu yaziniz
    */
public class ExcelUtility {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> tablo = getListData("src/test/java/ApachiPOI/resource/ApacheExcel2.xlsx",
                "Sayfa1", 2);
        System.out.println("tablo =" + tablo);
    }

    public static ArrayList<ArrayList<String>> getListData(String path, String sheetName, int columnCount) {
        ArrayList<ArrayList<String>> tablo = new ArrayList<>();

        Workbook workbook = null;

        try {
            FileInputStream inputStream = new FileInputStream(path);
            workbook = WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sayfa = workbook.getSheet(sheetName);
        for (int i = 0; i < sayfa.getPhysicalNumberOfRows(); i++) {

            ArrayList<String> satirData = new ArrayList<>();
            for (int j = 0; j < columnCount; j++) {
                satirData.add(sayfa.getRow(i).getCell(j).toString());

            }
            tablo.add(satirData);
        }
        return tablo;
    }

    //todo kendisine verilen path,scenario,browserTip ,zaman parametreleri ile yeni bir excele yazacak.
    public static void writeExcel(String path, Scenario scenario, String browserName, String time) {

        File f = new File(path);
        if (!f.exists()) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sayfa1");
            Row newRow = sheet.createRow(0);

            Cell newCell = newRow.createCell(0);
            newCell.setCellValue(scenario.getName());

            newCell = newRow.createCell(1);
            newCell.setCellValue(scenario.getStatus().toString());

            newCell = newRow.createCell(2);
            newCell.setCellValue(browserName);

            newCell = newRow.createCell(3);
            newCell.setCellValue(time);


            try {
                FileOutputStream outputStream = new FileOutputStream(path);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            FileInputStream inputStream = null;
            Workbook workbook = null;
            try {
                inputStream = new FileInputStream(path);
                workbook = WorkbookFactory.create(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows();
            Row newRow = sheet.createRow(rowCount);//todo kac satir var,son satirdan sonra yeni ac devam et

            Cell newCell = newRow.createCell(0);
            newCell.setCellValue(scenario.getName());

            newCell = newRow.createCell(1);
            newCell.setCellValue(scenario.getStatus().toString());

            newCell = newRow.createCell(2);
            newCell.setCellValue(browserName);

            newCell = newRow.createCell(3);
            newCell.setCellValue(time);

            try {

                inputStream.close();
                FileOutputStream outputStream = new FileOutputStream(path);
                workbook.write(outputStream);
                workbook.close();//todo hafiza bosaltildi
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

    }
}




