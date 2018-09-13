package com.timers.library.DataStructure;

import java.util.ArrayList;

public class Reader {
    private String readerId;//读者账户ID
    private String name;//读者姓名
    private String password;//账户密码
    private double balance;//账户余额
    private static int status;//账户状态（是否登陆）
    private ArrayList<Book> books;
    private int amount;

    public Reader(){

    }
    public Reader(String readerId, String name, String password) {//构造函数，余额在新建账户时默认为0
        this.readerId = readerId;
        this.name = name;
        this.password = password;
        this.balance=0.0;
        Reader.status=200;

    }

    public String getReaderId() {//获得ID
        return readerId;
    }

    public void setReaderId(String readerId) {//设置ID
        this.readerId = readerId;
    }

    public String getName() {//获得姓名
        return name;
    }

    public void setName(String name) {//设置姓名
        this.name = name;
    }

    public String getPassword() {//获得密码
        return password;
    }

    public void setPassword(String password) {//设置密码
        this.password = password;
    }

    public double getBalance() {//获得余额
        return balance;
    }

    public void setBalance(double balance) {//设置余额
        this.balance = balance;
    }

    public static int getStatus() {//获得账户状态
        return Reader.status;
    }

    public static void setStatus(int status) {//设置账户状态
        Reader.status = status;
    }

    public ArrayList<Book> getBooks()
    {
        return books;
    }

    public void setBooks(ArrayList<Book> books)
    {
        this.books = books;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }
}
