package com.timers.library.BookOperation;

import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Manager;

import java.text.SimpleDateFormat;
import java.util.Date;

//测试完成
public class ManagerBookOperation {
    /**
     * show 添加书籍，包含id,书名,出版商,作者,译者,入馆时间,数量
     *
     * @param id         书号
     * @param name       书名
     * @param publisher  出版商
     * @param author     作者
     * @param translator 译者
     * @param date       入馆时间
     * @param count      数量
     * @param status     状态
     * @return true添书成功 false添书失败
     */
    public boolean newBook(String id, String name, String publisher, String author, String translator, String date, int count, boolean status) throws Exception{
        if (Manager.getStatus()) {
            Date time=new SimpleDateFormat("yyyy-MM-dd").parse(date);
            String now_time=String.valueOf(time.getTime());
            return DataBase.addBook(id, name, author, translator, publisher, now_time, count, status);
        } else {
            return false;
        }
    }

    /**
     * show 根据书籍id号删除书籍
     *
     * @param id 书籍号
     * @return true删除书籍成功 false删除书籍失败
     */
    public boolean deleteBook(String id) {
        if (Manager.getStatus()) {
            return DataBase.deleteBook(id);
        } else {
            return false;
        }
    }

    /**
     * show 修改书籍的信息 包含id,书名,出版商,作者,译者,入馆时间,数量
     *
     * @param id         书号
     * @param name       书名
     * @param publisher  出版商
     * @param author     作者
     * @param translator 译者
     * @param date       入馆时间
     * @param count      数量
     * @param status     状态
     *                   String类型不修改的话为null，int类型不修改的话为-1
     * @return true添书成功 false添书失败
     */
    public boolean changeBookInfo(String id, String name, String publisher, String author, String translator, String date, int count, int status) throws Exception {
        if (Manager.getStatus()) {
            String now_time=null;
            if(!date.equals("")){
                Date time=new SimpleDateFormat("yyyy-MM-dd").parse(date);
                now_time=String.valueOf(time.getTime());
            }
            return DataBase.updateBook(id, name, author, translator, publisher, now_time, count, status);
        } else {
            return false;
        }

    }
}
