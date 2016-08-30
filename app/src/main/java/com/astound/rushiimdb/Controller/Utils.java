package com.astound.rushiimdb.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Astound Rushi on 8/30/2016.
 */

public class Utils
{
    public static Date convertDate(String Date)
    {
        DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
        Date date= null;
        try
        {
            date = dateFormat.parse(Date);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
}
