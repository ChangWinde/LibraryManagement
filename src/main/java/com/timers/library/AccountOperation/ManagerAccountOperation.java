package com.timers.library.AccountOperation;

import com.timers.library.DataBase.DataBase;
import com.timers.library.DataStructure.Manager;

//测试完成
public class ManagerAccountOperation {
    private Manager manager;
    /**
     * show manager登录
     *@return true登录成功 false登录失败
     *@param id 管理员账号
     *@param password 密码
     */
    public Manager Mlogin(String id,String password){
        String hash_password=String.valueOf(password.hashCode())+"";
        if(DataBase.loginByManager(id,hash_password)){
            String name=DataBase.getManagerName(id);
            manager=new Manager(id,name,hash_password,true);
            return manager;
        }else{
            return manager;
        }
    }
    /**
     * show manager退出登录
     * @return true退出成功，未设置退出失败的情况。
     */
    public boolean Mlogout(){
        manager=null;
        Manager.setStatus(false);
        return true;
    }

}
