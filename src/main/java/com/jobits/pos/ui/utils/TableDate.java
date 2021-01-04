/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Home
 */
public class TableDate extends Date {

    private Date internalDate;

    public TableDate(Date internalDate) {
        this.internalDate = internalDate;
    }

    public Date getInternalDate() {
        return internalDate;
    }

    public void setInternalDate(Date internalDate) {
        this.internalDate = internalDate;
    }

    @Override
    public int getMinutes() {
        return internalDate.getMinutes(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHours() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.HOUR_OF_DAY, internalDate.getHours());
        return a.get(Calendar.HOUR);
    }

    public String getPMAM() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.HOUR_OF_DAY, internalDate.getHours());
        return a.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
    }

    @Override
    public int getDate() {
        return internalDate.getDate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMonth() {
        return internalDate.getMonth(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getYear() {
        return internalDate.getYear(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return getDate() + "/" + getMonth() + "/" + getYear();
    }
}
