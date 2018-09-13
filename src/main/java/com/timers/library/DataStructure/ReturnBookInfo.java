package com.timers.library.DataStructure;

import java.util.ArrayList;

public class ReturnBookInfo
{
    private int overdue;
    private boolean enough;
    private ArrayList<Book>books;
    private double fine;


    public ReturnBookInfo(int overdue, boolean enough, ArrayList<Book> books, double fine)
    {
        this.overdue = overdue;
        this.enough = enough;
        this.books = books;
        this.fine = fine;
    }

    public int getOverdue()
    {
        return overdue;
    }

    public void setOverdue(int overdue)
    {
        this.overdue = overdue;
    }

    public boolean isEnough()
    {
        return enough;
    }

    public void setEnough(boolean enough)
    {
        this.enough = enough;
    }

    public ArrayList<Book> getBooks()
    {
        return books;
    }

    public void setBooks(ArrayList<Book> books)
    {
        this.books = books;
    }

    public double getFine()
    {
        return fine;
    }

    public void setFine(double fine)
    {
        this.fine = fine;
    }


}
