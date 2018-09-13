package com.timers.library.BookOperation;

import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Manager;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerBookOperationTest {
    ManagerBookOperation managerBookOperation=new ManagerBookOperation();
    @Test
    public void newBook() throws Exception{
        DataBase.start();
        Manager.setStatus(true);
        if(managerBookOperation.newBook("2-16","yzc","成","YZC","hi","2014-12-5",5,true)){
            System.out.println("添加书籍成功！");
        }else{
            System.out.println("添加书籍失败！");
        }
        //id分别取正反例测试(正：id不存在，操作成功返回true；反：id存在，返回false)
    }//未测试

    @Test
    public void deleteBook() {
        DataBase.start();
        Manager.setStatus(true);
        if(managerBookOperation.deleteBook("2-16")){
            System.out.println("成功删除书籍");
        }else{
            System.out.println("该书籍不存在！");
        }
        //id分别取正反例测试（正：id存在，操作成功返回true；反：id不存在，返回false）
    }

    @Test
    public void changeBookInfo() throws Exception{
        DataBase.start();
        Manager.setStatus(true);
        if (managerBookOperation.changeBookInfo("2-15","java",null,null,null,null,20,-1)){
            System.out.println("书籍信息修改成功！");
        }else{
            System.out.println("书籍不存在！");
        }
        //id分别取正例反例测试（正：id存在，操作成功返回true；反：id不存在，返回false）
    }
}