package com.timers.library.DataBase;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataBaseTest {

    @Test
    public void loginByManager() {
        DataBase.start();
        System.out.println(DataBase.loginByManager("21316183","1450575459"));
    }
}