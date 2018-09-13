package com.timers.library.BookOperation;

import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Book;
import org.junit.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommomBookOperationTest {

    @Test
    public void findBook() {
        ArrayList<Book> books2 = null;
        ArrayList<Book> books;
        DataBase.start();
        CommomBookOperation commomBookOperation = new CommomBookOperation();

        //books=commomBookOperation.findBook("2-1","f","j","j","p");
        books2 = commomBookOperation.findBook(null, null, "y", "", null);
        if (books2 != null) {
            System.out.println(books2.size());
            Book book = books2.get(0);
            System.out.println(book.getBookName());
            System.out.println(book.getAuthor());
            System.out.println(book.getDate());
            System.out.println(book.getTime());
            System.out.println(book.getBookID());
        } else {
            System.out.println("没有找到相应的书籍！");
        }
        //精确查找
        //正：id正确，返回书籍列表；反：没有找到书籍，返回null
        //模糊查找
        //正：所有匹配信息正确，匹配到书籍，返回书籍列表；反：没有查找到对应的书籍，返回null

    }//未测试
}