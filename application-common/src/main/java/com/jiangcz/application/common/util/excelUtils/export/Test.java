package com.jiangcz.application.common.util.excelUtils.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by houshihao on 2017/3/3.
 */
public class Test {
    public static void main(String[] args) {
        FileOutputStream fos =null;
        ZipOutputStream zos = null;
        try {
            String detailCSVName = "d://1-明细记录表.xlsx";
            File tempPath = new File(detailCSVName);
            fos = new FileOutputStream(tempPath);


            BigExcelExportUtil.prepare();
            BigExcelExportUtil.writeHeader(OrderNodeReport.class, null, false);

            ArrayList<OrderNodeReport> orderNodeReports = new ArrayList<>();
            OrderNodeReport orderNodeReport = new OrderNodeReport();
            orderNodeReport.setNO(123);
            orderNodeReport.setOrderId(123L);
            orderNodeReports.add(orderNodeReport);

            BigExcelExportUtil.writeContent(orderNodeReports);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
          //  BigExcelExportUtil.flushAndClose(fos);
        }

    }

    public static void createZip(ZipOutputStream zos, String fileName) {
        try {
            zos.putNextEntry(new ZipEntry(fileName));

            StringBuilder headerCSV = new StringBuilder();
            headerCSV.append("1111111111111\n");
            headerCSV.append("\n");

            byte[] headerBytes = headerCSV.toString().getBytes("gbk");
            zos.write(headerBytes);
            zos.flush();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
