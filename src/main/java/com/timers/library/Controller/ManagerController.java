package com.timers.library.Controller;

import com.timers.library.AccountOperation.ManagerAccountOperation;
import com.timers.library.BookOperation.*;
import com.timers.library.DataStructure.*;
import com.timers.library.ReaderOperatron.ManagerReaderOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/manager")
public class ManagerController
{
    private static final Logger LOG = LoggerFactory.getLogger(ManagerController.class);
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ManagerForReturn login(@RequestBody ManagerToLogin manager)
    {
        LOG.info("[INFO] 管理员登陆: ID:" +manager.getManagerId());
        Manager tempManager = new ManagerAccountOperation().Mlogin(manager.getManagerId(),manager.getPassWord());
        if (tempManager != null)
        {
            return new ManagerForReturn(200,"登陆成功");
        }else {
            return new ManagerForReturn(0,"登录失败");
        }

    }

    @RequestMapping(value = "/addreader", method = RequestMethod.POST)
    public AddStudentInfo addReader(@RequestBody Reader studentInfo)
    {
        LOG.info("[INFO] 添加读者: ID: "+ studentInfo.getReaderId() +" Name: "+studentInfo.getName() );
        ManagerReaderOperation mro = new ManagerReaderOperation();
         if(mro.newReader(studentInfo.getReaderId(), studentInfo.getName(),studentInfo.getPassword(),studentInfo.getBalance()))
         {
             //添加成功
             return new AddStudentInfo(200,"注册成功");
         }else {
             return new AddStudentInfo(0,"注册失败");
         }
    }
    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public AddBookInfo addBook(@RequestBody Book book)throws Exception
    {
        LOG.info("[INFO] 添加书籍: ID: "+ book.getBookID() + " Name: "+ book.getBookName());
        ManagerBookOperation mbo = new ManagerBookOperation();
        boolean isOk = false;
        try
        {
            isOk = mbo.newBook(book.getBookID(),book.getBookName(),book.getPublisher(),book.getAuthor(),book.getTranslator(),book.getTime(), book.getCount(),book.getStatus());

        }catch (Exception e){
            e.getStackTrace();
        }
        if(isOk)
        {
            AddBookInfo addBookInfo = new AddBookInfo(200, "入库成功");
            return addBookInfo;
        }else {
            AddBookInfo addBookInfo = new AddBookInfo(0, "入库失败");
            return addBookInfo;
        }


    }
    @RequestMapping(value = "/modifybookinfo", method = RequestMethod.POST)
    public AddBookInfo changeBookInfo(@RequestBody Book book)throws Exception
    {
        LOG.info("[INFO] 修改图书信息: ID: "+book.getBookID());
        ManagerBookOperation mbo = new ManagerBookOperation();
        int status = 1;
        if (book.getStatusStr().equals(""))
        {
            //无需修改状态
            status = -1;

        }else {
            if (book.getStatusStr().equals("true"))
            {

            }else {
                status = 1;
            }
        }
        int count = 0;
        if (book.getCountStr().equals(""))
        {
            count = -1;
        }else {
            count = new Integer(book.getCountStr());
        }

        boolean isOk = false;
        try{
            isOk = mbo.changeBookInfo(book.getBookID(),book.getBookName(),book.getPublisher(),book.getAuthor(),book.getTranslator(),book.getTime(), count,status);

        }catch (Exception e)
        {

        }
        if(isOk)
        {
            return new AddBookInfo(200, "修改成功");
        }else {
            return new AddBookInfo(0,"修改失败");
        }


    }

    @RequestMapping(value = "/deletebook", method = RequestMethod.POST)
    public DeleteBookInfo deleteBook(@RequestBody DeleteBookReceiveInfo bookInfo)
    {
        LOG.info("[INFO] 删除书籍: ID: "+bookInfo.getBookID());
        ManagerBookOperation mbo = new ManagerBookOperation();
        if(mbo.deleteBook(bookInfo.getBookID()))
        {
            return new DeleteBookInfo(200,"删除成功");
        }else {
            return new DeleteBookInfo(0,"删除失败");
        }
    }
}
