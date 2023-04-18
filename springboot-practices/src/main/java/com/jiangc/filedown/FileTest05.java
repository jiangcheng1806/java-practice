package com.jiangc.demo.filedown;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/file")
public class FileTest05 {


    /**
     * 文件下载
     */
    @RequestMapping("/down5")
    public static void downloadFIle(HttpServletRequest request, HttpServletResponse response) {

        //数据准备工作
        List<Object> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("kk.id", 11);
        map1.put("kk.name", "张三");
        map1.put("kk.address", "北京");
        map1.put("kk.age", "36");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("kk.id", 22);
        map2.put("kk.name", "李四");
        map2.put("kk.address", "上海");
        map2.put("kk.age", "46");
        list.add(map1);
        list.add(map2);
        System.out.println(JSONObject.toJSONString(list));

        String fileName = "文件名字";

        // 创建一个Writer流
        BigExcelWriter bigExcelWriter = ExcelUtil.createBigExcelWriter(fileName, 3);
        //BigExcelWriter bigExcelWriter = new BigExcelWriter();
        //水印的内容
        String watermarkInfo = "我是管理员呀";
        try {
            //添加水印
            Workbook workbook = bigExcelWriter.getWorkbook();
            if (workbook instanceof SXSSFWorkbook) {
                insertWaterMarkTextToXlsx((SXSSFWorkbook) workbook, watermarkInfo);
            } else if (workbook instanceof XSSFWorkbook) {
                insertWaterMarkTextToXlsx((XSSFWorkbook) workbook, watermarkInfo);
            }
            // 获取下Header,判断是哪种浏览器下载文件，来解决文件名的编码命名问题
            String header = request.getHeader("User-Agent").toUpperCase();
            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                fileName = URLEncoder.encode(fileName, "utf-8");
                fileName = fileName.replace("+", "%20");    //IE下载文件名空格变+号问题
            } else {
                fileName = new String(fileName.getBytes(), "UTF8");
            }
            // 有点重复，可以不重写上面的ExcelUtils类，这是防止数据量很大时，需要提前准备文件，并不是即时写入即时返回
            String fileName1 = fileName + ".xlsx";
            response.reset();
            // 设置文件对应后缀的ContentType
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            // 返回的是流的形式
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Location", fileName1);
            response.setHeader("Cache-Control", "max-age=0");
            // 这句的作用是会打开用户那边下载文件时的那个保存位置的框，如果文件名中有中文，建议编下码
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName1, "UTF8"));
            AtomicInteger count = new AtomicInteger();
            // 开始准备写入数据
            for (Object obj : list) {
                // 将查询的数据转换为map
                Map<String, Object> map = (Map<String, Object>) obj;
                bigExcelWriter.writeRow(map, count.getAndIncrement() == 0);
            }

            bigExcelWriter.close();
            // 上面的过程就是文件的写入过程并且保存在了/excel/目录下
            // 下面就是把这个文件读取出来，以流的形式返回
            File excelFile = new File("/excel/" + fileName1);
            FileInputStream fis = new FileInputStream(excelFile);
            // 文件流上面加一层缓冲流，目的为了加快速度，减少IO
            BufferedInputStream buff = new BufferedInputStream(fis);
            // 同理，输出流上也套了一层缓冲的输出流
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

            byte[] car = new byte[1024];
            int l = 0;
            // 按字节读取文件
            while (l < excelFile.length()) {
                int j = buff.read(car, 0, 1024);
                l += j;
                // 写入out流中
                out.write(car, 0, j);
            }

            // 关闭流
            fis.close();
            buff.close();
            out.close();
            excelFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 给excel添加水印.
     *
     * @param workbook      Xlsx的Excel工作簿.
     * @param waterMarkText 水印内容.
     * @throws IOException IO异常抛出
     */
    public static void insertWaterMarkTextToXlsx(SXSSFWorkbook workbook, String waterMarkText) throws IOException {
        BufferedImage image = createWatermarkImage(waterMarkText);
        ByteArrayOutputStream imageOs = new ByteArrayOutputStream();
        ImageIO.write(image, "png", imageOs);
        int pictureIdx = workbook.addPicture(imageOs.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG);
        XSSFPictureData pictureData = (XSSFPictureData) workbook.getAllPictures().get(pictureIdx);
        //获取每个Sheet表
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            SXSSFSheet sheet = workbook.getSheetAt(i);
            //这里由于 SXSSFSheet 没有 getCTWorksheet() 方法，通过反射取出 _sh 属性
            XSSFSheet shReflect = (XSSFSheet) ReflectUtil.getFieldValue(sheet, "_sh");
            PackagePartName ppn = pictureData.getPackagePart().getPartName();
            String relType = XSSFRelation.IMAGES.getRelation();
            PackageRelationship pr = shReflect.getPackagePart().addRelationship(ppn, TargetMode.INTERNAL, relType, null);
            shReflect.getCTWorksheet().addNewPicture().setId(pr.getId());
        }
    }

    /**
     * 生成水印图片.
     *
     * @param content 需要生成水印的文字信息.
     * @return 图片字节流.
     */
    public static BufferedImage createWatermarkImage(String content) {
        final String[] textArray = content.split("\n");
        //设置水印字体、大小
        final Font font = new Font("TimesRoman", Font.PLAIN, 80);
        int width = 500;
        int height = 400;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 背景透明 开始
        Graphics2D g = image.createGraphics();
        image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g.dispose();
        // 背景透明 结束
        g = image.createGraphics();
        // 设定画笔颜色
        g.setColor(new Color(Color.lightGray.getRGB()));
        // 设置画笔字体
        g.setFont(font);
        // 设定倾斜度
        //   g.shear(0.1, -0.26);
        // 设置字体平滑
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //文字从中心开始输入，算出文字宽度，左移动一半的宽度，即居中
        FontMetrics fontMetrics = g.getFontMetrics(font);
        // 水印位置
        int x = width / 2;
        int y = height / 2;
        // 设置水印旋转
        g.rotate(Math.toRadians(-40), x, y);
        for (String s : textArray) {
            // 文字宽度
            int textWidth = fontMetrics.stringWidth(s);
            // 画出字符串
            g.drawString(s, x - (textWidth / 2), y);
            y = y + font.getSize();
        }
        // 释放画笔
        g.dispose();
        return image;
    }

    /**
     * 给excel添加水印.
     *
     * @param workbook      Xls的Excel工作簿.
     * @param waterMarkText 水印内容.
     * @throws IOException IO异常抛出
     */
    public static void insertWaterMarkTextToXlsx(XSSFWorkbook workbook, String waterMarkText) throws IOException {
        BufferedImage image = createWatermarkImage(waterMarkText);
        ByteArrayOutputStream imageOs = new ByteArrayOutputStream();
        ImageIO.write(image, "png", imageOs);
        int pictureIdx = workbook.addPicture(imageOs.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG);
        XSSFPictureData pictureData = workbook.getAllPictures().get(pictureIdx);
        //获取每个Sheet表
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            PackagePartName ppn = pictureData.getPackagePart().getPartName();
            String relType = XSSFRelation.IMAGES.getRelation();
            PackageRelationship pr = sheet.getPackagePart().addRelationship(ppn, TargetMode.INTERNAL, relType, null);
            sheet.getCTWorksheet().addNewPicture().setId(pr.getId());
        }
    }

}


