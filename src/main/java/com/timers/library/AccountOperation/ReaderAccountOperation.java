package com.timers.library.AccountOperation;

import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Manager;
import com.timers.library.DataStructure.Reader;
import com.timers.library.ReaderOperatron.ManagerReaderOperation;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

//测试完成
public class ReaderAccountOperation {
    private Reader currentReader=null;
    /**
     * show用户登录函数
     * @param readerId 用户ID
     * @param password 用户密码
     * @return 当前账户信息
     */
    public Reader readerLogin(String readerId,String password)
    {
        String hashPassword=password.hashCode()+"";
        if(DataBase.loginByUser(readerId,hashPassword))
        {
            ManagerReaderOperation managerReaderOperation=new ManagerReaderOperation();
            Manager.setStatus(true);
            Reader.setStatus(200);//设置账户状态为登陆
            currentReader=managerReaderOperation.findReader(readerId);
            Manager.setStatus(false);
            return currentReader;
        }
        return currentReader;
    }

    /**
     * show退出登录
     * @return 登陆成功（true）
     */
    public boolean readerLoginOut()
    {
        currentReader=null;//设置账户状态为未登陆
        Reader.setStatus(0);
        return true;
    }

    /**
     * show充值函数
     * @param readerId 读者Id
     * @param fee 充值金额
     * @return 充值成功（true）
     */
    public int charge(String readerId,int fee)
    {
        double preBalance= DataBase.getBalance(readerId);
        if(preBalance!=-1)
        {
            DataBase.changeBalance(readerId,fee);
            double balance=DataBase.getBalance(readerId);
            if(balance>preBalance)
                return (int) balance;
            else
                return -1;
        }
        else
            return -1;

    }
}