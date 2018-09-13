package com.timers.library.DataStructure;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    SimpleDateFormat formetter=new SimpleDateFormat("yyyy-MM-dd");
    private String bookID;//书籍ID
    private String bookName;//书籍名称
    private String publisher;//书籍出版社
    private String author;//书籍作者
    private String translator;//书籍译者
    private String time;//书籍出版时间 long转换成String类型
    private String date;//书籍出版时间 yyyy-MM-dd样式
    private int count;//书籍数量
    private boolean status;//书籍状态
    private String statusStr;//用于数据传输的状态字符串
    private String countStr;//用于数据传输的数量字符串

    public Book(){
    }
    public Book(String Id,String Name,String Publisher,String Author,String Translisher,String Time,int Count,boolean Status){


        this.bookID=Id;
        this.bookName=Name;
        this.publisher=Publisher;
        this.author=Author;
        this.translator=Translisher;
        this.time=Time;
        this.date=formetter.format(new Date(Long.valueOf(Time)));
        System.out.println(this.date);
        this.count=Count;
        this.status=Status;
    }//初始化各个变量

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }//设置书籍号

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }//设置书名

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }//设置出版社

    public void setAuthor(String author) {
        this.author = author;
    }//设置作者

    public void setTranslator(String translator) {
        this.translator = translator;
    }//设置译者

    public void setTime(String time) {
        this.time = time;
    }//设置出版时期

    public void setDate(Date Thedate){
        this.date=formetter.format(Thedate);
    }

    public void setDate(String time){
        this.date=formetter.format(new Date(Long.valueOf(time)));
    }

    public void setCount(int count) {
        this.count = count;
    }//设置数量

    public void setStatus(boolean status) {
        this.status = status;
    }//获取书籍状态

    public String getBookID() {
        return bookID;
    }//获取书籍号

    public String getBookName() {
        return bookName;
    }//获取书名

    public String getPublisher() {
        return publisher;
    }//获取出版社

    public String getAuthor() {
        return author;
    }//获取作者

    public String getTranslator() {
        return translator;
    }//获取译者

    public String getTime() {
        return time;
    }//获取入馆时间

    public String getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }//获取数量

    public boolean getStatus(){
        return status;
    }

    public String getStatusStr()
    {
        return statusStr;
    }

    public void setStatusStr(String statusStr)
    {
        this.statusStr = statusStr;
    }

    public String getCountStr()
    {
        return countStr;
    }

    public void setCountStr(String countStr)
    {
        this.countStr = countStr;
    }
}
