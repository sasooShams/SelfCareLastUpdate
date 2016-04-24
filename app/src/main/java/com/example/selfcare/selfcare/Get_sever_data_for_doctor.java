package com.example.selfcare.selfcare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by h on 23/04/2016.
 */
public class Get_sever_data_for_doctor {

    String column[] = new String[]{TableData.Doc_server_patients.Fname, TableData.Doc_server_patients.Lname,
            TableData.Doc_server_patients.Email, TableData.Doc_server_patients.Passward,
            TableData.Doc_server_patients.Moblile, TableData.Doc_server_patients.BirthDate ,
            TableData.Doc_server_patients.Weight, TableData.Doc_server_patients.Height ,
            TableData.Doc_server_patients.Gender};

    String columnOfrecords[] = new String[]{TableData.servr_CheckUP.Patient_ID, TableData.servr_CheckUP.Temperture,
            TableData.servr_CheckUP.Pressure, TableData.servr_CheckUP.Heart,
            TableData.servr_CheckUP.Date};
    DataBase reg ;
    SQLiteDatabase sqlDB ;

    public Get_sever_data_for_doctor(Context context){

         reg = new DataBase(context);
        sqlDB = reg.getWritableDatabase();
    }


    public String Fname (String Email){
        String Fname= "" ;
        Cursor cc = sqlDB.query(TableData.Doc_server_patients.doc_server_patients, column, TableData.Doc_server_patients.Email + "=?", new String[] { Email }, null, null, null);
        if (cc != null ){
            cc.moveToFirst();

            Fname=Fname+cc.getString(0);
            //sqlDB.close();
            return Fname;}

        return null;
    }


    public String Lname (String Email){
        String Fname= "" ;
        Cursor cc = sqlDB.query(TableData.Doc_server_patients.doc_server_patients, column, TableData.Doc_server_patients.Email + "=?", new String[] { Email }, null, null, null);
        if (cc != null ){
            cc.moveToFirst();

            Fname=Fname+cc.getString(1);
            //sqlDB.close();
            return Fname;}

        return null;
    }

    public String Email (String Email){
        String Fname= "" ;
        Cursor cc = sqlDB.query(TableData.Doc_server_patients.doc_server_patients, column, TableData.Doc_server_patients.Email + "=?", new String[] { Email }, null, null, null);
        if (cc != null ){
            cc.moveToFirst();

            Fname=Fname+cc.getString(2);
            //sqlDB.close();
            return Fname;}

        return null;
    }
    public String mobile (String Email){
        String Fname= "" ;
        Cursor cc = sqlDB.query(TableData.Doc_server_patients.doc_server_patients, column, TableData.Doc_server_patients.Email + "=?", new String[] { Email }, null, null, null);
        if (cc != null ){
            cc.moveToFirst();

            Fname=Fname+cc.getString(4);
            //sqlDB.close();
            return Fname;}

        return null;

    }
    public String birthdate (String Email){
        String Fname= "" ;
        Cursor cc = sqlDB.query(TableData.Doc_server_patients.doc_server_patients, column, TableData.Doc_server_patients.Email + "=?", new String[] { Email }, null, null, null);
        if (cc != null ){
            cc.moveToFirst();

            Fname=Fname+cc.getString(5);
            //sqlDB.close();
            return Fname;}

        return null;
    }
    public String weight (String Email){
        String Fname= "" ;
        Cursor cc = sqlDB.query(TableData.Doc_server_patients.doc_server_patients, column, TableData.Doc_server_patients.Email + "=?", new String[] { Email }, null, null, null);
        if (cc != null ){
            cc.moveToFirst();

            Fname=Fname+cc.getString(6);
            //sqlDB.close();
            return Fname;}

        return null;
    }

    public String height (String Email){
        String Fname= "" ;
        Cursor cc = sqlDB.query(TableData.Doc_server_patients.doc_server_patients, column, TableData.Doc_server_patients.Email + "=?", new String[] { Email }, null, null, null);
        if (cc != null ){
            cc.moveToFirst();

            Fname=Fname+cc.getString(7);
            //sqlDB.close();
            return Fname;}

        return null;
    }

    public String gendre (String Email){
        String Fname= "" ;
        Cursor cc = sqlDB.query(TableData.Doc_server_patients.doc_server_patients, column, TableData.Doc_server_patients.Email + "=?", new String[] { Email }, null, null, null);
        if (cc != null ){
            cc.moveToFirst();

            Fname=Fname+cc.getString(8);
            //sqlDB.close();
            return Fname;}

        return null;
    }


    // column of patient records

    public String heart(String Email){
        String result= "" ;
        Cursor cc = sqlDB.query(TableData.servr_CheckUP.doc_server_CheckUPs, columnOfrecords, TableData.servr_CheckUP.Patient_ID + "=?", new String[] { Email }, null, null, null);
        if (cc.moveToFirst()){

            while (cc.isAfterLast() == false) {

                result=result+cc.getString(3);
                cc.moveToNext();}
            //sqlDB.close();
            return result;}

        return null;
    }




    public String temp(String Email){
        String result= "" ;
        Cursor cc = sqlDB.query(TableData.servr_CheckUP.doc_server_CheckUPs, columnOfrecords, TableData.servr_CheckUP.Patient_ID + "=?", new String[] { Email }, null, null, null);
        if (cc.moveToFirst()){

            while (cc.isAfterLast() == false) {

                result=result+cc.getString(1);
                cc.moveToNext();}
            //sqlDB.close();
            return result;}

        return null;
    }

    public String pressure(String Email){
        String result= "" ;
        Cursor cc = sqlDB.query(TableData.servr_CheckUP.doc_server_CheckUPs, columnOfrecords, TableData.servr_CheckUP.Patient_ID + "=?", new String[] { Email }, null, null, null);
        if (cc.moveToFirst()){

            while (cc.isAfterLast() == false) {

                result=result+cc.getString(2);
                cc.moveToNext();}
            //sqlDB.close();
            return result;}

        return null;
    }
    public String date(String Email){
        String result= "" ;
        Cursor cc = sqlDB.query(TableData.servr_CheckUP.doc_server_CheckUPs, columnOfrecords, TableData.servr_CheckUP.Patient_ID + "=?", new String[] { Email }, null, null, null);
        if (cc.moveToFirst()){

            while (cc.isAfterLast() == false) {

                result=result+cc.getString(4);
                cc.moveToNext();}
            //sqlDB.close();
            return result;}

        return null;
    }

    public String lastdate(String Email){
        String result= "" ;
        Cursor cc = sqlDB.query(TableData.servr_CheckUP.doc_server_CheckUPs, columnOfrecords, TableData.servr_CheckUP.Patient_ID + "=?", new String[] { Email }, null, null, null);
        if (cc.moveToLast()){
            result=cc.getString(4);
            return result;}

        return null;
    }



}
