package com.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <Description> <br>
 *
 * @author shihan@qq.com<br>
 * @version 1.0<br>
 * @date 2022/11/06 <br>
 */
public class Md5excel {
    public static void main(String[] args) throws Exception {
        excel();
    }

    public static void excel() throws Exception {
        File file = new File("D:/Desktop/中国银行股份有限公司鹰潭市分行-2022新保单人员名单1.xlsx");
        if (!file.exists()){
            throw new Exception("文件不存在!");
        }
        InputStream in = new FileInputStream(file);

        // 读取整个Excel
        XSSFWorkbook sheets = new XSSFWorkbook(in);
        // 获取第一个表单Sheet
        XSSFSheet sheetAt = sheets.getSheetAt(0);

        //默认第一行为标题行，i = 0
        XSSFRow titleRow = sheetAt.getRow(0);
        // 循环获取每一行数据
        ArrayList<String> arrayList = new ArrayList();
        ArrayList<String> arrayList1 = new ArrayList();
        ArrayList<String> arrayList2 = new ArrayList();
        ArrayList<String> arrayList3 = new ArrayList();

        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheetAt.getRow(i);
            // 读取每一格内容
//            StringBuilder sb = new StringBuilder();
//            for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
//            XSSFCell titleCell = titleRow.getCell(3);
            XSSFCell cell3 = row.getCell(3);
            XSSFCell cell5 = row.getCell(5);
            if (cell3 == null || cell3.getStringCellValue() == null || cell3.getStringCellValue().equals("")) {
                continue;
            }
            cell3.setCellType(CellType.STRING);
//                sb.append(cell);
//            }
//            System.out.println("\t" + encode(sb.toString()));
            arrayList.add(String.valueOf(cell3));
            arrayList1.add(encode(String.valueOf(cell3)));
            arrayList2.add(String.valueOf(cell5));
            arrayList3.add(encode(String.valueOf(cell5)));
        }
        OutputStream outputStreamExcel = null;
        HashMap<String, ArrayList<ArrayList<String>>> map = new HashMap<String, ArrayList<ArrayList<String>>>();
        File tempFile = new File("D:/Desktop/test.xlsx");
        if(!tempFile.exists()) {
            tempFile.createNewFile();//创建文件
        }
        Workbook workbook = new XSSFWorkbook();//创建Workbook对象(excel的文档对象)
        Sheet sheet1 = workbook.createSheet("md5加密");// 建建sheet对象（excel的表单）
        // 设置单元格字体
        Font headerFont = workbook.createFont(); // 字体
        headerFont.setFontHeightInPoints((short)14);
        headerFont.setFontName("黑体");

        Row row = sheet1.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("姓名加密");
        row.createCell(2).setCellValue("身份证");
        row.createCell(3).setCellValue("身份证加密");

        for(int i=0;i<arrayList.size();i++){
            Row row1 = sheet1.createRow(i+1);
            row1.createCell(0).setCellValue(arrayList.get(i));
            row1.createCell(1).setCellValue(arrayList1.get(i));
            row1.createCell(2).setCellValue(arrayList2.get(i));
            row1.createCell(3).setCellValue(arrayList3.get(i));
        }
        outputStreamExcel = new FileOutputStream(tempFile);
        workbook.write(outputStreamExcel);
        outputStreamExcel.flush();
        outputStreamExcel.close();
    }

    public static String encode(String input) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = input.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
