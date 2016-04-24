package com.example.selfcare.selfcare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by h on 20/04/2016.
 */
public class SendRecToServer {
    DataBase reg ;
    SQLiteDatabase sqlDB ;

    String columnOfrecords[] = new String[]{TableData.Replace_CheckUP.Patient_ID, TableData.Replace_CheckUP.Temperture,
            TableData.Replace_CheckUP.Pressure, TableData.Replace_CheckUP.Heart,
            TableData.Replace_CheckUP.Date};
    Context c;
    SendRecToServer(Context context){
        c=context;
        reg =new DataBase(context);
        sqlDB = reg.getReadableDatabase();
    }

    public  void getDataofPatient (String Email){
        String result = "";
        try {

           // task.execute(method, heart, temp, press, Time, email, "patient");

            //  Cursor c = sqlDB.query(TableData.CheckUP.TableNameCH, columnOfrecords, null, null, null, null, null);


            Cursor cc = sqlDB.query(TableData.Replace_CheckUP.TableNameCHR, columnOfrecords, TableData.Replace_CheckUP.Patient_ID + "=?", new String[] { Email }, null, null, null);

            String heart ="";
            String press ="";
            String temp ="";
            String date ="";
            BackgroundTask task = new BackgroundTask(c);
            String method = "record";
            if (cc.moveToFirst()){
                while (cc.isAfterLast() == false) {

                    date=cc.getString(4);
                    heart=cc.getString(3);
                    temp=cc.getString(1);
                    press=cc.getString(2);
                    new BackgroundTask(c).execute(method, heart, temp, press, date, Email, "patient");
                    sqlDB.delete(TableData.Replace_CheckUP.TableNameCHR, TableData.Replace_CheckUP.Date+ "=?", new String[]{date});

                    cc.moveToNext();}}

        }
        catch (Exception e){
            Toast.makeText(c,e.toString(),Toast.LENGTH_LONG).show();
        } finally {

        }

    }
}
