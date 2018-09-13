package com.timers.library.BookOperation;

import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Reader;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReaderBookOperationTest {
    ReaderBookOperation readerBookOperation=new ReaderBookOperation();

    @Test
    public void borrow_Book() {
        DataBase.start();
        Reader.setStatus(200);
        boolean answer=readerBookOperation.borrow_Book("2-15","21316183");
        if(answer){
            System.out.println("借书成功！");
        }else{
            System.out.println("借书失败");
        }
    }

    @Test
    public void return_Book() {
        DataBase.start();
        Reader.setStatus(200);
        if (readerBookOperation.return_Book("2-15","21316183")!=-1){
            System.out.println("还书成功！");
        }else {
            System.out.println("该书不存在！");
        }
    }

    @Test
    public void renew_Book() {
        DataBase.start();
        Reader.setStatus(200);
        if (readerBookOperation.renew_Book("2-1","21316183")){
            System.out.println("续借成功！");
        }else{
            System.out.println("续借失败！");
        }
    }
}