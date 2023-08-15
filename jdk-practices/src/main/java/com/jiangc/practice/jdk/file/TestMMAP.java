package com.jiangc.practice.jdk.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class TestMMAP {
    public static void main(String[] args) {
        File file = new File("data.txt");

        if (!file.exists()){
            file.mkdir();
        }

        long fileSize = file.length();
        try {
            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "rw").getChannel()
                    .map(FileChannel.MapMode.READ_WRITE, 0, fileSize);

            // 读取数据
            byte[] buffer = new byte[1024];
            mappedByteBuffer.get(buffer);

// 写入数据
            byte[] data = "Hello, world!".getBytes();
            mappedByteBuffer.put(data);

            // 将文件全部加载到内存中
            mappedByteBuffer.load();

// 将修改的数据刷回磁盘
            mappedByteBuffer.force();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        System.out.println("hello");
    }
}