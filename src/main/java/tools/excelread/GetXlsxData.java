/*
package tools.excelread;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
*/
/**
 * @author sunmm
 * Created Time 2018/2/23 9:39*//*



@SuppressWarnings("ALL")
public class GetXlsxData {
    private Workbook workbook;
    private Sheet sheet;
    int rows;
    int columns;
    public String fileName;
    public String caseName;
    public String filePath;
    public ArrayList<String> arrkey = new ArrayList<String>();
    String sourceFile;

    public GetXlsxData(String filePath, String fileName, String caseName) {
        this.fileName = fileName;
        this.caseName = caseName;
        this.filePath = filePath;
        String ext = fileName.substring(fileName.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(fileName);
            if(".xls".equals(ext)){
                workbook = new HSSFWorkbook(is);
            }else if(".xlsx".equals(ext)){
                workbook = new XSSFWorkbook(is);
            }else{
                workbook=null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
    public Object[][] getExcelData(){


        //workbook = Workbook.getWorkbook(new File(this.setPath(filePath,fileName)));
        sheet = workbook.getSheet(caseName);
        //获取该sheet行数
        rows = sheet.getLastRowNum();
        System.out.println("colNum:" + rows);
        //获取该sheet列数
        columns = sheet.getPhysicalNumberOfRows();
        System.out.println("colNum:" + columns);
        // 为了返回值是Object[][],定义一个多行单列的二维数组
        HashMap<String, String>[][] arrmap = new HashMap[rows - 1][1];

        // 对数组中所有元素hashmap进行初始化
        if (rows > 1) {
            for (int i = 0; i < rows - 1; i++) {
                arrmap[i][0] = new HashMap<String, String>(100);
            }
        } else {
            System.out.println("excel中没有数据");
        }

        // 获得首行的列名，作为hashmap的key值
        for (int c = 0; c < columns; c++) {
            String cellvalue = sheet.getCell(c, 0).getContents();
            arrkey.add(cellvalue);
        }
        // 遍历所有的单元格的值添加到hashmap中
        for (int r = 1; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                String cellvalue = sheet.getCell(c, r).getContents();
                arrmap[r - 1][0].put(arrkey.get(c), cellvalue);
            }
        }
        return arrmap;
    }
    public String setPath(String path, String fileName) throws IOException {
        File directory = new File(".");
        sourceFile = directory.getCanonicalPath() + path + fileName + ".xls";
        return sourceFile;
    }
}
*/
