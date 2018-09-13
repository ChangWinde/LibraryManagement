package com.timers.library.DataStructure;
/*
说明：此类用于管理员在登录时接收用户信息
 */
public class ManagerToLogin
{
    private String managerId;
    private String passWord;

    public String getManagerId()
    {
        return managerId;
    }

    public void setManagerId(String managerId)
    {
        this.managerId = managerId;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }
}
