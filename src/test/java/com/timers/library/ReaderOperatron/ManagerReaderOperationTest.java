package com.timers.library.ReaderOperatron;

import com.timers.library.BookOperation.ManagerBookOperation;
import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Manager;
import com.timers.library.DataStructure.Reader;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerReaderOperationTest {

    @Test
    public void newReader() {
        DataBase.start();
        Manager.setStatus(true);
        ManagerReaderOperation managerReaderOperation=new ManagerReaderOperation();
        if(managerReaderOperation.newReader("21316186","杨昱昊","123456",0.0)){
            System.out.println("添加读者成功！");
        }else{
            System.out.println("添加读者失败！");
        }
    }

    @Test
    public void findReader() {
        DataBase.start();
        ManagerReaderOperation managerReaderOperation=new ManagerReaderOperation();
        Manager.setStatus(true);
        Reader reader=managerReaderOperation.findReader("21316183");
        if(reader!=null){
            System.out.println(reader.getReaderId());
            System.out.println(reader.getName());
            System.out.println(reader.getBalance());
            System.out.println(reader.getReaderId());
            System.out.println(reader.getBooks().size());
            System.out.println(reader.getBooks().get(1).getBookID());
        }else{
            System.out.println("没有找到该读者！");
        }


    }



}