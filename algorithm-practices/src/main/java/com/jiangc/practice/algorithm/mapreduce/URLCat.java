package com.jiangc.practice.algorithm.mapreduce;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 类名称：URLCat<br>
 * 类描述：用于打开和读取文件的内容<br>
 * 创建时间：2018年07月17日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class URLCat {

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args) throws IOException {
        InputStream in = null;

        try {
            in = new URL(args[0]).openStream();
            IOUtils.copyBytes(in,System.out,4096,false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
