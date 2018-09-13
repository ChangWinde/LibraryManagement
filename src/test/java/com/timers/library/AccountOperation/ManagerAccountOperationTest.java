package com.timers.library.AccountOperation;

import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Manager;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerAccountOperationTest {

    @Test
    public void mlogin() {
        DataBase.start();
        ManagerAccountOperation managerAccountOperation=new ManagerAccountOperation();
        Manager manager=managerAccountOperation.Mlogin("21316183","123456");
        if(manager!=null)
        {
            System.out.println("登陆成功！");
            System.out.println(Manager.getStatus());
            System.out.println(manager.getName());
        }
        else
        {
            System.out.println("用户名不存在或者密码错误！");
        }
        //id或者密码分别取正例反例测试
        //正：id与密码均正确，返回当前登陆的Manager对象;反：用户名不存在或者密码错误，返回null。

    }

    @Test
    public void mlogout() {
        DataBase.start();
        ManagerAccountOperation managerAccountOperation=new ManagerAccountOperation();
        managerAccountOperation.Mlogin("21316183","123456");
        System.out.println(Manager.getStatus());
        System.out.println(managerAccountOperation.Mlogout());
        System.out.println(Manager.getStatus());
    }
}