package com.timers.library.DataStructure;

import java.util.ArrayList;

public class ReaderForReturn
{
    private int status;
    private String name;
    private double balance;
    private ArrayList<Book>books;

    public ReaderForReturn(String name, double balance, ArrayList books, int status)
    {
        this.name = name;
        this.balance = balance;
        this.books = books;
        this.status = status;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(int balance)
    {
        this.balance = balance;
    }

    public ArrayList<Book> getBooks()
    {
        return books;
    }

    public void setBooks(ArrayList<Book> books)
    {
        this.books = books;
    }
}
