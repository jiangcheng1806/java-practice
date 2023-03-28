package com.jiangc.practice.algorithm.mapreduce;

import java.io.File;

/**
 * 类名称：FileUtil<br>
 * 类描述：<br>
 * 创建时间：2018年07月17日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class FileUtil {

    public static boolean deleteDir(String path) {
        File dir = new File(path);
        if (dir.exists()){
            for (File f : dir.listFiles()){
                if (f.isDirectory()){
                    deleteDir(f.getName());
                } else {
                    f.delete();
                }
            }
            dir.delete();
            return true;
        } else {
            System.out.println("文件（夹）不存在！");
            return false;
        }
    }
}
