package com.timers.library.DataBase;

import com.timers.library.Controller.ReaderController;
import com.timers.library.DataStructure.Book;
import com.timers.library.DataStructure.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
public class DataBase {
    //数据库信息
    private static final Logger LOG = LoggerFactory.getLogger(DataBase.class);
    private static String dataBaseUserName = "root";
    private static String password = "mm40659614";
    private static String databaseName = "softfinalwork";
    private static String tableName1 = "Reader";
    private static String tableName2 = "Manager";
    private static String tableName3 = "BookInfo";
    private static String tableName4 = "BookCondition";

    private static Statement statement = null;

    public DataBase(){}
    /**
     * 这个函数主要是用于启动数据库连接.
     * 数据库进入的用户名与密码是作为类的私有数据成员，主要是为了方便修改某些变量比较方便
     */
    public static void start(){
        Connection connection = null;
        try{
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接
            String dbURL = "jdbc:mysql://localhost:3306/softfinalwork?user="+dataBaseUserName+"&password="+password+"&useSSL=true";

            connection = DriverManager.getConnection(dbURL);
            //创建Statement
            statement = connection.createStatement();
            //statement.execute("CREATE DATABASE "+databaseName);
            statement.execute("USE "+databaseName);
            LOG.info("[INFO]数据库已连接");
//            //创建两张表
//            //读者信息表
//            statement.execute("CREATE TABLE "+tableName1+"(studentId char(8),name char(10),password char(10),balance float)");
//            //管理者信息表
//            statement.execute("CREATE TABLE "+tableName2+"(managerId char(8),name char(10),password char(10))");
//            //书籍信息表
//            statement.execute("CREATE TABLE "+tableName3+"(bookId char(8),name char(10),publisher char(10),author char(10),translator char(10),time char(13),count SMALLINT,state TINYINT)");
//            //书籍借阅情况使用表
//            statement.execute("CREATE TABLE "+tableName4+"(studentId char(8),bookId char(8),borrowDate char(13))");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 这个函数主要是用于验证用户登录
     * @param personId 用户的登录ID
     * @param password 用户的登录密码
     * @return 如何验证正确返回true 失败返回false
     */
    public static boolean loginByUser(String personId,String password){
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+tableName1+" WHERE studentId='"+personId+"' AND password='"+password+"'");
            if(resultSet.next()) {
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 这个函数主要是用于验证图书管理员登录
     * @param personId 图书管理员的登录ID
     * @param password 图书管理员的登录密码
     * @return 如何验证正确返回true 失败返回false
     */
    public static boolean loginByManager(String personId,String password){
        ResultSet resultSet = null;
        try{
            resultSet = statement.executeQuery("SELECT * FROM "+tableName2+" WHERE managerId='"+personId+"' AND password='"+password+"'");
            if(resultSet.next()) {
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //图书管理员权限函数
    /**
     * @param bookId 书籍的ID号
     * @param name 书籍名称
     * @param publisher 出版社
     * @param author 书籍作者
     * @param translator 书籍译者
     * @param time 出版时间
     * @param count 书籍库存数量
     * @param state 书籍是否可以被借阅
     * @return 如果书籍存在，则返回false，如果不存在，插入记录成功返回true
     */
    public static boolean addBook(String bookId,String name,String publisher,String author,String translator,String time,int count,boolean state){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM "+tableName3+" WHERE bookId='"+bookId+"'");
            //如果书籍已经存在在数据库库，返回false 不加入目录
            if(resultSet.next()) {
                return false;
            }
            else{
                statement.executeUpdate("INSERT INTO "+tableName3+"(bookId,name,publisher,author,translator,time,count,state)VALUES("+"'"+bookId+"','"+name+"','"+publisher+"','"+author+"','"+translator+"',"+time +","+count+","+state+")");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * @param bookId 书籍的ID号
     * @return 如果所删除的书籍ID号输入错误就返回false，否则是true
     */
    public static boolean deleteBook(String bookId){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM "+tableName3+" WHERE bookId='"+bookId+"'");
            if(resultSet.next()) {
                statement.executeUpdate("DELETE FROM "+tableName3+" WHERE bookId='"+bookId+"'");
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * @param bookId 书籍ID
     * @param name 书籍名称
     * @param publisher 出版社名称
     * @param author 作者名称
     * @param translator 译者名称
     * @param time 出版时间
     * @param count 书籍保存数量
     * @param state 书籍是否可以外借
     * @return 如果书籍ID不存在就返回false
     */
    public static boolean updateBook(String bookId,String name,String publisher,String author,String translator,String time,int count,int state){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + tableName3 + " WHERE bookId='" + bookId + "'");
            if (!resultSet.next()) {
                return false;
            }else {
                if(name.equals(""))
                    name = resultSet.getString("name");
                if(publisher.equals(""))
                    publisher = resultSet.getString("publisher");
                if(author.equals(""))
                    author = resultSet.getString("author");
                if(translator.equals(""))
                    translator = resultSet.getString("translator");
                if(time == null)
                    time = resultSet.getString("time");
                if(count == -1)
                    count = resultSet.getInt("count");
                if(state == -1)
                    state = resultSet.getShort("state");
                statement.executeUpdate("UPDATE " + tableName3 + " SET name='"+name+"',publisher='"+publisher+"',author='"+author+"',translator='"+translator+"',time='"+time+"',count="+count+",state="+state+" WHERE bookId='"+bookId+"'");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * @param studentId 学号
     * @param name 姓名
     * @param password 密码
     * @param balance 余额
     * @return 如果已经有这个学号就会返回false
     */
    public static boolean addReader(String studentId,String name,String password,double balance){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + tableName1 + " WHERE studentId='" + studentId + "'");
            if(resultSet.next())
                return false;
            else{
                statement.executeUpdate("INSERT INTO "+tableName1+" VALUES('"+studentId+"','"+name+"','"+password+"',"+balance+")");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * @param studentId 学生学号
     * @return 如果没有这个学号就返回false
     */
    public static boolean deleteReader(String studentId){
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + tableName1 + " WHERE studentId='" + studentId + "'");
            if(resultSet.next()){
                statement.executeUpdate("DELETE FROM " + tableName1 + " WHERE studentId='"+studentId+"'");
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * @param bookId 书籍ID
     * @return 返回书籍数量
     */
    public static int getBookNumber(String bookId){
        ResultSet resultSet = null;
        int count = -1;
        try {
            resultSet = statement.executeQuery("SELECT * FROM "+tableName3+" WHERE bookId='"+bookId+"'");
            if(resultSet.next()){
                count = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    /**
     * @param managerId 图书馆管理员ID
     * @return 返回他的名字
     */
    public static String getManagerName(String managerId){
        ResultSet resultSet = null;
        String name = "";
        try {
            resultSet = statement.executeQuery("SELECT * FROM "+tableName2+" WHERE managerId='"+managerId+"'");
            if(resultSet.next())
                name = resultSet.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    //读者权限函数
    /**
     * @param bookId 所借书籍ID
     * @param studentId 学号
     * @return
     */
    public static boolean borrowBook(String bookId,String studentId){ResultSet resultSet = null;
        try{
            Date date = new Date();
            String time = Long.toString(date.getTime());
            statement.executeUpdate("INSERT INTO "+tableName4+" VALUES('"+studentId+"','"+bookId+"','"+time+"')");
            resultSet = statement.executeQuery("SELECT * FROM "+tableName3+" WHERE bookId='"+bookId+"'");
            resultSet.next();
            int count = resultSet.getInt("count")-1;
            statement.executeUpdate("UPDATE " + tableName3 + " SET count="+count+" WHERE bookId='"+bookId+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * @param bookId 书籍ID
     * @param studentId 学号
     * @return
     */
    public static boolean returnBook(String bookId,String studentId){
        try {
            statement.executeUpdate("DELETE FROM "+tableName4+" WHERE bookId='"+bookId+"' AND studentId='"+studentId+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * @param studentId 学号
     * @return
     */
    public static float getBalance(String studentId){
        ResultSet resultSet = null;
        float money = -1;
        try {
            resultSet = statement.executeQuery("SELECT * FROM "+tableName1+" WHERE studentId='"+studentId+"'");
            if(resultSet.next())
                money = resultSet.getFloat("balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return money;
    }
    /**
     * @param studentId 学号
     * @param money 金额改变量
     * @return 总金额
     */
    public static float changeBalance(String studentId,float money){
        ResultSet resultSet = null;
        float oldMoney = -1;
        try {
            resultSet = statement.executeQuery("SELECT * FROM "+tableName1+" WHERE studentId='"+studentId+"'");
            if(resultSet.next())
                oldMoney = resultSet.getFloat("balance");
            oldMoney += money;
            statement.executeUpdate("UPDATE " + tableName1 + " SET balance='"+oldMoney+"' WHERE studentId='"+studentId+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oldMoney;
    }
    /**
     * @param bookId 书籍ID
     * @param studentId 学号
     * @return
     */
    public static String  getBookStart(String bookId,String studentId){
        ResultSet resultSet = null;
        String borrowDate = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM "+tableName4+" WHERE bookId='"+bookId+"' And studentId='"+studentId+"'");
            if(resultSet.next()){
                borrowDate = resultSet.getString("borrowDate");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowDate;
    }
    /**
     * @param bookId 书籍ID
     * @param studentId 学号
     * @return
     */
    public static boolean renewBook(String bookId,String studentId){
        ResultSet resultSet = null;
        String borrowDate = "";
        try {
            resultSet = statement.executeQuery("SELECT * FROM "+tableName4+" WHERE bookId='"+bookId+"' And studentId='"+studentId+"'");
            if(resultSet.next()){
                borrowDate = resultSet.getString("borrowDate");
            }

            borrowDate = Long.toString(Long.valueOf(borrowDate)+259200000);
            statement.executeUpdate("UPDATE " + tableName4 + " SET borrowDate='"+borrowDate+"' WHERE bookId='"+bookId+"' And studentId='"+studentId+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * @param studentId 学号
     * @return 返回一个读者类 包含读者的信息
     */
    public static Reader findAllInfo(String studentId){
        ResultSet resultSet = null;
        Reader reader = new Reader();
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + tableName1 + " WHERE studentId='" + studentId + "'");
            if(resultSet.next()){
                reader.setReaderId(resultSet.getString("studentId"));
                reader.setName(resultSet.getString("name"));
                reader.setBalance(resultSet.getFloat("balance"));
                reader.setBooks(findBorrowList(studentId));
            }else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reader;
    }
    /**
     * @param bookId 书籍ID
     * @param name 书籍名称,如果未填写，请提供参数null
     * @param publisher 出版社
     * @param author 作者
     * @param translator 译者名称
     * @return 返回书籍的列表
     */
    public static ArrayList<Book> infoSearch(String bookId, String name, String publisher, String author, String translator){
        ArrayList<Book> bookList= new ArrayList<Book>();
        ResultSet resultSet = null;
        try {
            if(bookId != null){
                resultSet = statement.executeQuery("SELECT * FROM "+tableName3+" WHERE bookId='"+bookId+"'");
                while(resultSet.next()){
                    Book book = new Book();
                    book.setBookID(bookId);
                    book.setBookName(resultSet.getString("name"));
                    book.setPublisher(resultSet.getString("publisher"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setTime(resultSet.getString("time"));
                    book.setDate(resultSet.getString("time"));
                    book.setCount(resultSet.getInt("count"));
                    book.setStatus(resultSet.getBoolean("state"));
                    book.setTranslator(resultSet.getString("translator"));
                    bookList.add(book);
                }
            }else{
                resultSet = statement.executeQuery("SELECT * FROM "+tableName3+" WHERE name LIKE '"+name+"' AND publisher LIKE '"+publisher+"' AND author LIKE '"+author+"' AND translator LIKE '"+translator+"'");
                while(resultSet.next()){
                    Book book = new Book();
                    book.setBookID(resultSet.getString("bookId"));
                    book.setBookName(resultSet.getString("name"));
                    book.setPublisher(resultSet.getString("publisher"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setTime(resultSet.getString("time"));
                    book.setDate(resultSet.getString("time"));
                    book.setCount(resultSet.getInt("count"));
                    book.setStatus(resultSet.getBoolean("state"));
                    book.setTranslator(resultSet.getString("translator"));
                    bookList.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
    /**
     * @param studentId 用户账号
     * @return 返回用户的借阅书单
     */
    public static ArrayList<Book> findBorrowList(String studentId){
        ResultSet resultSet = null;
        ArrayList<String> bookIdList = new ArrayList<String>();
        ArrayList<Book> bookList = new ArrayList<Book>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + tableName4 + " WHERE studentId='" + studentId + "'");
            if(resultSet == null)
                return null;
            while(resultSet.next()){
                String id = resultSet.getString("bookId");
                bookIdList.add(id);
            }
            int size = bookIdList.size();
            for(int i = 0;i<size;i++){
                resultSet = statement.executeQuery("SELECT * FROM " + tableName3 + " WHERE bookId='" + bookIdList.get(i) + "'");
                resultSet.next();
                Book book = new Book();
                book.setBookID(resultSet.getString("bookId"));
                book.setBookName(resultSet.getString("name"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setAuthor(resultSet.getString("translator"));
                book.setTime(resultSet.getString("time"));
                book.setDate(resultSet.getString("time"));
                book.setCount(resultSet.getInt("count"));
                book.setStatus(resultSet.getBoolean("state"));
                book.setBookID(bookIdList.get(i));
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

}
