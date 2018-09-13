package com.timers.library.DataStructure;

import java.util.ArrayList;

public class BorrowBookInfo
{
    private int status;
    private String message;
    private ArrayList<Book>books;


    public BorrowBookInfo(int status, String message, ArrayList<Book> books)
    {
        this.status = status;
        this.message = message;
        this.books = books;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
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
