package com.timers.library.DataStructure;

public class Manager {
    private String managerId;//id
    private String name;//姓名
    private String passWord;//密码
    private static boolean status;//状态


    public Manager(String Id,String Name,String Password,boolean Status){
        this.managerId=Id;
        this.name=Name;
        this.passWord=Password;
        Manager.status=Status;
    }

    public void setId(String Id) {
        managerId = Id;
    }

    public void setName(String Name){
        name=Name;
    }

    public void setPassWord(String passWord) {
        passWord = passWord;
    }

    public static void setStatus(boolean Status) {
        Manager.status = Status;
    }

    public String getId() {
        return managerId;
    }

    public  String getName(){
        return name;
    }

    public String getPassWord() {
        return passWord;
    }

    public static boolean getStatus(){
        return Manager.status;
    }
}
