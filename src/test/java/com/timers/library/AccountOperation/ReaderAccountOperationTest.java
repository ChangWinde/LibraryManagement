package com.timers.library.AccountOperation;

import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Reader;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import static org.junit.Assert.*;

public class ReaderAccountOperationTest {


    @Test
    public void readerLogin() {
        DataBase.start();
        ReaderAccountOperation readerAccountOperation=new ReaderAccountOperation();
        Reader reader=readerAccountOperation.readerLogin("21316184","123456");
        if(reader!=null)
        {
            System.out.println(reader.getReaderId());
            System.out.println(reader.getName());
            System.out.println(reader.getBalance());
            System.out.println(Reader.getStatus());
        }
        else
            System.out.println("账户名或者密码错误！");
        //id或者密码分别取正例反例测试
        //正：id与密码均正确，返回当前登陆的Manager对象;反：用户名不存在或者密码错误，返回null。

    }

    @Test
    public void readerLoginOut() {
        DataBase.start();
        ReaderAccountOperation readerAccountOperation=new ReaderAccountOperation();
        readerAccountOperation.readerLogin("21316183","123456");
        System.out.println(Reader.getStatus());
        System.out.println(readerAccountOperation.readerLoginOut());
        System.out.println(Reader.getStatus());
    }

    @Test
    public void charge() {
        DataBase.start();
        ReaderAccountOperation readerAccountOperation=new ReaderAccountOperation();
        double money=readerAccountOperation.charge("2131",100);
        if(money!=-1){
            System.out.print("充值成功！");
            System.out.println("您的余额为："+money);
        }
        else {
            System.out.println("充值失败！");
        }
        //id或者密码分别取正例反例测试
        //正：id正确，返回当前登陆的Manager对象;反：id不正确或者操作失败，返回null。

    }
}