package com.jiangc.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class MultiThreadDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new MysqlReadTask());
        executor.submit(new ExcelWriteTask());
        executor.shutdown();
    }

    private static class MysqlReadTask implements Runnable {
        private static final int PAGE_SIZE = 1000;
        private static final String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai";
        private static final String DB_USERNAME = "root";
        private static final String DB_PASSWORD = "root";
        private static final String SQL_QUERY = "SELECT * FROM user";

        @Override
        public void run() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                stmt.setFetchSize(Integer.MIN_VALUE);
                ResultSet rs = stmt.executeQuery(SQL_QUERY);
                int rowNumber = 0;
                while (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    ExcelWriteTask.addRow(name, email, ++rowNumber);
                }
                ExcelWriteTask.finished = true;
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ExcelWriteTask implements Runnable {
        private static final String FILE_PATH = "result.xlsx";
        private static volatile boolean finished = false;
        private static SXSSFWorkbook workbook;
        private static Sheet sheet;
        private static int currentRow = 0;

        static {
            workbook = new SXSSFWorkbook(100);
            sheet = workbook.createSheet("user");
        }

        public static synchronized void addRow(String name, String email, int rowNumber) {
            if (!finished) {
                Row row = sheet.createRow(currentRow++);
                row.createCell(0).setCellValue(name);
                row.createCell(1).setCellValue(email);
                if (rowNumber % 100 == 0) {
                    flush();
                }
            }
        }

        public static void flush() {
            try {
                File file = new File(FILE_PATH);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fio = new FileOutputStream(file);
                workbook.write(fio);
                fio.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (!finished) {
                flush();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            flush();
        }
    }
}