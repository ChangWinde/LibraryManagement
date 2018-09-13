package com.timers.library.ReaderOperatron;

import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Manager;
import com.timers.library.DataStructure.Reader;

//测试完成
public class ManagerReaderOperation {
    /**
     * show 添加读者，包含读者id，读者姓名，读者密码(hash加密)，账户金额
     * @return true添加读者成功 false添加读者失败
     * @param id 读者id号
     * @param username 读者姓名
     * @param password 密码
     * @param balance 账户金额
     */
    public boolean newReader(String id,String username,String password,double balance){
        password=password.hashCode()+"";
        if(Manager.getStatus()){
            return DataBase.addReader(id,username,password,balance);
        }else{
            return false;
        }
    }

    /**
     * show 根据读者的id查找读者信息
     * @return 只返回一个读者对象
     * @param id 读者id号
     */
    public Reader findReader(String id){
        if(true){
            Reader reader = DataBase.findAllInfo(id);
            reader.setBooks(DataBase.findBorrowList(id));
            return reader;
        }else{
            return null;
        }
    }

}
