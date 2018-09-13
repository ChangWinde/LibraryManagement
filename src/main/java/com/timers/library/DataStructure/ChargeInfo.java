package com.timers.library.DataStructure;

public class ChargeInfo
{
    private String readerId;
    private String amount;
    private int status;
    private double balance;

    public ChargeInfo(int status, double balance)
    {
        this.status = status;
        this.balance = balance;
    }
    public ChargeInfo(String studentId, String amount)
    {
        this.readerId = studentId;
        this.amount = amount;
    }

    public String getStudentId()
    {
        return readerId;
    }

    public void setStudentId(String studentId)
    {
        this.readerId = studentId;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public double getBalance()
    {
        return balance;
    }
}
