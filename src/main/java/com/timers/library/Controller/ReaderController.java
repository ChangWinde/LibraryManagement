package com.timers.library.Controller;

import com.timers.library.AccountOperation.ReaderAccountOperation;
import com.timers.library.BookOperation.ReaderBookOperation;
import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.*;
import com.timers.library.ReaderOperatron.ManagerReaderOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reader")
public class ReaderController
{
    private static final Logger LOG = LoggerFactory.getLogger(ReaderController.class);
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ReaderForReturn login(@RequestBody Reader reader)
    {
        LOG.info("[INFO] 读者登陆: ID: "+reader.getReaderId());
        ReaderAccountOperation rao = new ReaderAccountOperation();
        Reader readerToLogin = rao.readerLogin(reader.getReaderId(),reader.getPassword());
        if (readerToLogin != null)
        {
            return new ReaderForReturn(readerToLogin.getName(),readerToLogin.getBalance(), readerToLogin.getBooks(), Reader.getStatus());
        }else {
            return new ReaderForReturn("",0,null,0);
        }


    }

    @RequestMapping(value = "/borrow", method = RequestMethod.POST)
    public BorrowBookInfo borrowBook(@RequestBody BRReceiveInfo bookInfo)
    {
        LOG.info("[INFO] 读者借书: ReaderID: "+bookInfo.getStudentID() + " BookID: "+bookInfo.getBookID());
        ReaderBookOperation rbo = new ReaderBookOperation();
        if(rbo.borrow_Book(bookInfo.getBookID(), bookInfo.getStudentID()))
        {
            //借书成功
            ManagerReaderOperation mro = new ManagerReaderOperation();
            Reader reader = mro.findReader(bookInfo.getStudentID());
            return new BorrowBookInfo(200,"借书成功", reader.getBooks());
        }else {
            //借书失败
            return new BorrowBookInfo(0,"借书失败",null);
        }
//        ArrayList<Book>books = new ArrayList<>();
//        Book book = new Book("aaa","dad","123","11111","232323","121212",1,true);
//        books.add(book);
//        book = new Book("bbb","dad","123","11111","232323","121212",1,true);
//        books.add(book);
//        return new BorrowBookInfo(200,"借书成功",books);
    }
    @RequestMapping(value = "/return", method = RequestMethod.POST)
    public ReturnBookInfo returnBook(@RequestBody BRReceiveInfo bookInfo)
    {
        LOG.info("[INFO] 读者还书: ID: "+bookInfo.getStudentID() + " BookID: "+bookInfo.getBookID()  );
        double fine = new ReaderBookOperation().return_Book(bookInfo.getBookID(), bookInfo.getStudentID());
        Reader reader = new ManagerReaderOperation().findReader(bookInfo.getStudentID());
        if (fine!= -1)
        {
            return new ReturnBookInfo(0,true, DataBase.findBorrowList(bookInfo.getStudentID()),fine);
        }else{
            return new ReturnBookInfo(1,false,null,fine);
        }
    }
    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public ChargeInfo charge(@RequestBody Reader chargeInfo)
    {
        LOG.info("[INFO] 读者充值: ID: "+chargeInfo.getReaderId()+" amount: "+ chargeInfo.getAmount());
        ReaderAccountOperation rao = new ReaderAccountOperation();
        int balance = rao.charge(chargeInfo.getReaderId(),chargeInfo.getAmount());
        if (balance != -1)
        {
            return new ChargeInfo(200, balance);
        }else {
            return new ChargeInfo(0,balance);
        }
    }


}
