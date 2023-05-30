package Utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtitlity {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
     String path = null;

    public XLUtitlity(String path) {
        this.path = path;
    }


    public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException{
        fi=new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        workbook.getSheetName(0);
        sheet=workbook.getSheetAt(0);
        sheet=workbook.getSheet(sheetName);

           row=sheet.getRow(rownum);
        cell=row.createCell(colnum);
        cell.setCellValue(data);

        fo=new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

}
