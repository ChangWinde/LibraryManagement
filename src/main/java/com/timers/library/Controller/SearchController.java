package com.timers.library.Controller;

import com.timers.library.BookOperation.CommomBookOperation;
import com.timers.library.BookOperation.ManagerBookOperation;
import com.timers.library.DataStructure.AddStudentInfo;
import com.timers.library.DataStructure.Book;
import com.timers.library.DataStructure.Reader;
import com.timers.library.DataStructure.SearchStudentReceiveInfo;
import com.timers.library.ReaderOperatron.ManagerReaderOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;


@RestController
@RequestMapping(value = "/search")
public class SearchController
{
    private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ArrayList<Book> searchBook(@RequestBody Book bookInfo)
    {
        LOG.info("[INFO] 查询书籍:" +" ID: "+ bookInfo.getBookID() +" Name:"+bookInfo.getBookName()+ " Author:" +bookInfo.getAuthor()+ "Translator: " + bookInfo.getTranslator() + "publisher"+bookInfo.getPublisher());
        System.out.println(bookInfo.getBookName());
        CommomBookOperation cbo = new CommomBookOperation();
        return cbo.findBook(bookInfo.getBookID(), bookInfo.getBookName(), bookInfo.getPublisher(), bookInfo.getAuthor(), bookInfo.getTranslator());
    }

    @RequestMapping(value = "/reader", method = RequestMethod.POST)
    public Reader searchStudent(@RequestBody SearchStudentReceiveInfo reader)
    {
        LOG.info("[INFO] 查询读者: ID:" + reader.getStudentID());
        ManagerReaderOperation mro = new ManagerReaderOperation();
        return mro.findReader(reader.getStudentID());
    }

}
