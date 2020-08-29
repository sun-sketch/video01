package com.util;

import java.io.*;

public class CutFile {
    public static void cutFile(File file1, File file2){
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;

        int temp = 0;
        try {
            fileInputStream = new FileInputStream(file1);
            byte[] bytes = new byte[1024];
            fileOutputStream = new FileOutputStream(file2);
            while((temp = fileInputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes, 0, temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        file1.delete();
    }
}
