package com.timers.library.BookOperation;

import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Book;
import com.timers.library.DataStructure.Reader;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//测试完成
public class ReaderBookOperation {
    /**
     * show借书函数
     * @param readerId 用户Id
     * @param bookId 书籍Id
     * @return true借书成功 fasle借书失败
     */

    public boolean borrow_Book(String bookId,String readerId)
    {
        int numberOfBook=DataBase.getBookNumber(bookId);
        if(numberOfBook>0){
            if(Reader.getStatus() == 200)
                return DataBase.borrowBook(bookId,readerId);
        }
        else
            return false;
        return false;
    }//借书后书籍不会减少，需要改动


    /**
     * show还书函数
     * @param readerId 读者Id
     * @param bookId 书籍Id
     * @return true还书成功 false还书失败
     */

    public double return_Book (String bookId,String readerId)
    {
        double fee=DataBase.getBalance(readerId);
        Date date_now=null;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        try{
            date_now=df.parse(df.format(new Date()));
        }catch (ParseException e){
            e.printStackTrace();
        }
        String BookStart=DataBase.getBookStart(bookId,readerId);
        if(BookStart!=null){
            long date_start=Long.valueOf(BookStart);
            long diff = date_now.getTime() - date_start;
            long days = diff / (1000*24*60*60 );
            if(days>30)
            {
                float rate=0.05f;
                fee = rate*(30-days);
                DataBase.changeBalance(readerId,rate*(30-days));//默许必须成功
            }
            if(DataBase.returnBook(bookId,readerId))
                return fee;
            else
                return -1;
        }
        else
            return -1;

    }//有问题

    /**
     * show续借函数
     * @param readerId 读者Id
     * @param bookId 书籍Id
     * @return true续借成功 false续借失败
     */

    public boolean renew_Book(String bookId,String readerId)
    {
        //获得借书时长days
        Date date_now=null;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        try{
            date_now=df.parse(df.format(new Date()));
        }catch (ParseException e){
            e.printStackTrace();
        }
        String BookStart=DataBase.getBookStart(bookId,readerId);
        if(BookStart!=null){
            long date_start=Long.valueOf(DataBase.getBookStart(bookId,readerId));
            long diff = date_now.getTime() - date_start;
            long days = diff / (1000*24*60*60 );
            if(days>30)
            {
                double answer=return_Book(bookId,readerId);
                if(answer!=-1)
                {
                    return borrow_Book(bookId,readerId);
                }
                else
                    return false;
            }
            else
            {
                return DataBase.renewBook(bookId,readerId);
            }
        }
        return false;
    }

}
