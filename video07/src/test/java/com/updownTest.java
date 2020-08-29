package com;

import com.domain.Upload;
import com.service.impl.UploadServiceImpl;
import com.util.CutFile;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;

public class updownTest {

    @Test
    public void delect(){
        String path="D:\\a.txt";
        File file=new File(path);
        if (file.exists()){
            System.out.println("exists");
            file.delete();
            System.out.println("delect");
        }
    }

    @Test
    public void test01() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UploadServiceImpl uploadService=context.getBean("uploadServiceImpl",UploadServiceImpl.class);
        Upload upload=uploadService.queryUploadById(2);
        System.out.println(upload);
    }

    @Test
    public void filecut() throws IOException {
        File initfile=new File("D:\\a.txt");
        File newfile=new File("D:\\other\\a.txt");
        newfile.createNewFile();
        CutFile.cutFile(initfile,newfile);
    }
}
