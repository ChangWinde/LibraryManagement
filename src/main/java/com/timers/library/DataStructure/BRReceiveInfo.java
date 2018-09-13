package com.timers.library.DataStructure;
/*
说明：此类型来识别从前端传来的借书和还书信息
 */
public class BRReceiveInfo
{
    private String studentID;
    private String bookID;

    public String getStudentID()
    {
        return studentID;
    }

    public void setStudentID(String studentID)
    {
        this.studentID = studentID;
    }

    public String getBookID()
    {
        return bookID;
    }

    public void setBookID(String bookID)
    {
        this.bookID = bookID;
    }
}
