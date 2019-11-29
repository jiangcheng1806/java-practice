package com.jiangcz.application.jdk.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) throws IOException {
        String srcPath = args[0];//文件源
        String destPath = args[1];//文件目的地

        InputStream is = new FileInputStream(srcPath);
        String destFileName = srcPath.substring(srcPath.lastIndexOf("\\") + 1);
        String destFilePath = destPath + destFileName;

        OutputStream os = new FileOutputStream(destFilePath);
        cypher(is, os);//加密class字节码
        is.close();
        os.close();
    }


    //加密方法
    public static void cypher(InputStream is, OutputStream os) throws IOException {
        int b = -1;
        while ((b = is.read()) != -1) {
            os.write(b ^ 0xff);
        }
    }

    private String classDir;

    public MyClassLoader() {
    }

    public MyClassLoader(String classDir) {
        super();
        this.classDir = classDir;
    }


    //覆盖ClassLoader的findClass方法
    @Override
    protected Class<?> findClass(String name) {

        name = name.substring(name.lastIndexOf(".") + 1);
        String classFileName = classDir + "\\" + name + ".class";
        InputStream is = null;

        try {
            is = new FileInputStream(classFileName);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            cypher(is, bos);//解密

            is.close();
            byte[] buf = bos.toByteArray();//取出字节数组流中的数据

            return defineClass(null, buf, 0, buf.length);//加载进内存

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
        //return super.findClass(name);
    }
}
