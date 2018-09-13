package com.timers.library.BookOperation;


import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Book;

import java.util.ArrayList;

//测试完成
public class CommomBookOperation {
    /**
     *show 根据精确查(书的id)找或者模糊查找(书名，出版商，作者，译者)查询书籍
     *@return 返回书（可能包含多种查询结果）的链表
     *@param id 书号
     *@param name 书名
     *@param publisher 出版商
     *@param author 作者
     *@param translator 译者
     */
    public ArrayList<Book> findBook(String id, String name, String publisher, String author, String translator){
        ArrayList<Book> bookList;
        if(id!=null){
            bookList=DataBase.infoSearch(id,name,author,publisher,translator);
            System.out.println(bookList.size());
            if(bookList.size()!=0)
                return bookList;
            else
                return null;
        }else{

            if(name==null || name=="")
                name="%";
            else
                name="%"+name+"%";

            if(publisher==null || publisher=="")
                publisher="%";
            else
                publisher="%"+publisher+"%";

            if(author==null || author=="")
                author="%";
            else
                author="%"+author+"%";

            if(translator==null || translator=="")
                translator="%";
            else
                translator="%"+translator+"%";

            bookList=DataBase.infoSearch(null,name,publisher,author,translator);
            if(bookList.size()!=0)
                return bookList;
            else
                return null;
        }
    }
}
