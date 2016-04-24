package com.example.selfcare.selfcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class InsertData {
     DataBase reg ;
    SQLiteDatabase sqlDB ;
    GetEmail get;
Context c;
    public InsertData(Context context){
c=context;
        reg =new DataBase(context);
        sqlDB = reg.getWritableDatabase();
        get= new GetEmail();


    }
    public long insert_data(String fname ,String lname,String email,String pass,int phone,String date,int weight , int height ,String gender) {
        long id;
        //SQLiteDatabase sqlDB = reg.getWritableDatabase();

        ContentValues co = new ContentValues();
        co.put(TableData.Patient_Regestration.Fname, fname);
        co.put(TableData.Patient_Regestration.Lname, lname);
        co.put(TableData.Patient_Regestration.Email, email);//primary Key
        co.put(TableData.Patient_Regestration.Passward, pass);
        co.put(TableData.Patient_Regestration.Moblile, phone);
        co.put(TableData.Patient_Regestration.BirthDate, date);
        co.put(TableData.Patient_Regestration.Weight, weight);
        co.put(TableData.Patient_Regestration.Height, height);
        co.put(TableData.Patient_Regestration.Gender, gender);
        try {
            id= sqlDB.insert(TableData.Patient_Regestration.TableName1, null, co);
            if (sqlDB != null && sqlDB.isOpen())
            sqlDB.close();
            return id;
        }

        catch (Exception e) {




        }
        if (sqlDB != null && sqlDB.isOpen())
        sqlDB.close();
        return-55 ;




    }



    public Long pDoctor_insert(String fname ,String lname,String email,int phone,String add,int ID,String Email){
        long id=-5;

        ContentValues co = new ContentValues();
        co.put(TableData.Patient_Doctor.DFname,fname);
        co.put(TableData.Patient_Doctor.DLname,lname);
        co.put(TableData.Patient_Doctor.DEmail,email);
        co.put(TableData.Patient_Doctor.DMoblile,phone);
        co.put(TableData.Patient_Doctor.DAddress,add);
        co.put(TableData.Patient_Doctor.D_ID,ID);
        co.put(TableData.Patient_Relative.Patient_ID,Email);///
        try {
            id= sqlDB.insert(TableData.Patient_Doctor.TableName3, null, co);
            if (sqlDB != null && sqlDB.isOpen())
                sqlDB.close();
            return id;
        }

        catch (Exception e) {




        }
        finally {

        if (sqlDB != null && sqlDB.isOpen())
            Log.d("DataBase operation", "Table Created");
            //sqlDB.close();


        }
        return id;
    }


    public Long checkUP(String Heart,String Temperture,String Pressure,String Time,String Email){
        long id=-5;
        ContentValues co = new ContentValues();
        try {
           // P_Email =get.getEmail();
            co.put(TableData.CheckUP.Heart, Heart);
            co.put(TableData.CheckUP.Temperture, Temperture);
            co.put(TableData.CheckUP.Pressure, Pressure);
          //  co.put(TableData.CheckUP.Comments, Comments);
            co.put(TableData.CheckUP.Patient_ID, Email);
            co.put(TableData.CheckUP.Date, Time);}
        catch (Exception e){}
        try {
            id= sqlDB.insert(TableData.CheckUP.TableNameCH, null, co);

            return id;
        }

        catch (Exception e) {
            Toast.makeText(c,e.toString()+id,Toast.LENGTH_LONG).show();
        }

        return id;
    }
    public Long Rep_checkUP(String Heart,String Temperture,String Pressure,String Time,String Email){
        long id=-5;
        ContentValues co = new ContentValues();
        try {
            co.put(TableData.Replace_CheckUP.Heart, Heart);
            co.put(TableData.Replace_CheckUP.Temperture, Temperture);
            co.put(TableData.Replace_CheckUP.Pressure, Pressure);
            //  co.put(TableData.CheckUP.Comments, Comments);
            co.put(TableData.Replace_CheckUP.Patient_ID, Email);
            co.put(TableData.Replace_CheckUP.Date, Time);}
        catch (Exception e){
            Toast.makeText(c,e.toString()+id,Toast.LENGTH_LONG).show();

        }
        try {
            id= sqlDB.insert(TableData.Replace_CheckUP.TableNameCHR, null, co);

            return id;
        }

        catch (Exception e) {
            Toast.makeText(c,e.toString()+id,Toast.LENGTH_LONG).show();
        }

        return id;
    }


    public Long relative_insert(String phone1,String phone2,String phone3,String phone4,String Email){
        long id=-5;
        ContentValues co = new ContentValues();
        try {


            co.put(TableData.Patient_Relative.RMoblile_1, phone1);
            co.put(TableData.Patient_Relative.RMoblile_2, phone2);
            co.put(TableData.Patient_Relative.RMoblile_3, phone3);
            co.put(TableData.Patient_Relative.RMoblile_4, phone4);
            co.put(TableData.Patient_Relative.Patient_ID, Email);}
        catch (Exception e){}
        try {
            id= sqlDB.insert(TableData.Patient_Relative.TableName2, null, co);
            if (sqlDB != null && sqlDB.isOpen())
                sqlDB.close();
            return id;
        }

        catch (Exception e) {

        }
        if (sqlDB != null && sqlDB.isOpen())
            sqlDB.close();

        return id;
    }

    public  String delet (){
       // sqlDB.delete(TableData.Patient_Regestration.TableName1, null, null);
      //  sqlDB.delete(TableData.Patient_Doctor.TableName3, null, null);
        sqlDB.delete(TableData.CheckUP.TableNameCH, null, null);

        return null;

    }


    public  String getData (){
        String result = "";


        try {
            String column[] = new String[]{TableData.Patient_Regestration.Fname, TableData.Patient_Regestration.Lname,
                    TableData.Patient_Regestration.Email, TableData.Patient_Regestration.Passward,
                    TableData.Patient_Regestration.Moblile, TableData.Patient_Regestration.BirthDate ,
                    TableData.Patient_Regestration.Weight, TableData.Patient_Regestration.Height, TableData.Patient_Regestration.Gender};
            String columns[] = new String[]{TableData.CheckUPDR.Pressure, TableData.CheckUPDR.doctor_ID,
                    TableData.CheckUPDR.Heart, TableData.CheckUPDR.Temperture
                     };


            Cursor c = sqlDB.query(TableData.CheckUPDR.TableNameCHDoc, columns, null, null, null, null, null);
        //  Cursor cc= sqlDB.rawQuery("SELECT * FROM"+TableData.Patient_Regestration.TableName1+" WHERE "+TableData.Patient_Regestration.Email+" = saraah@yahoo.com",null);
            Cursor cc = sqlDB.query(TableData.Patient_Regestration.TableName1, column, TableData.Patient_Regestration.Email + "=?", new String[]{"saraah@yahoo.com"}, null, null, null);

/*
            int iFname = c.getColumnIndex(TableData.Patient_Regestration.Fname);
            int iLname = c.getColumnIndex(TableData.Patient_Regestration.Lname);
            int iEmail = c.getColumnIndex(TableData.Patient_Regestration.Email);
            int iPassword = c.getColumnIndex(TableData.Patient_Regestration.Passward);
            int iPhone = c.getColumnIndex(TableData.Patient_Regestration.Moblile);
            int iBirth =c.getColumnIndex(TableData.Patient_Regestration.BirthDate);
            int iWeight = c.getColumnIndex(TableData.Patient_Regestration.Weight);
            int iHeight = c.getColumnIndex(TableData.Patient_Regestration.Height);
            int iGender = c.getColumnIndex(TableData.Patient_Regestration.Gender);
*/
            int fname = c.getColumnIndex(TableData.CheckUPDR.Temperture);
            int lname = c.getColumnIndex(TableData.CheckUPDR.doctor_ID);
         //   int email = c.getColumnIndex(TableData.Patient_Regestration.Email);


            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
                //result = result +  "\n"+cv.getString(email)+cv.getString(fname)+"\n"+"\n";
                result = result + "\n"+c.getString(fname)+"\n"+c.getString(lname)+"\n";

            return result;
        }
        catch (Exception e){
            return e.toString();
        } finally {

        }


    }


    public long insert_doctor_data(String fname ,String lname,String email,String pass,int phone,String date,int weight , int height ,String gender) {
        long id;
        //SQLiteDatabase sqlDB = reg.getWritableDatabase();

        ContentValues co = new ContentValues();
        co.put(TableData.Patient_Regestration.Fname, fname);
        co.put(TableData.Patient_Regestration.Lname, lname);
        co.put(TableData.Patient_Regestration.Email, email);//primary Key
        co.put(TableData.Patient_Regestration.Passward, pass);
        co.put(TableData.Patient_Regestration.Moblile, phone);
        co.put(TableData.Patient_Regestration.BirthDate, date);
        co.put(TableData.Patient_Regestration.Weight, weight);
        co.put(TableData.Patient_Regestration.Height, height);
        co.put(TableData.Patient_Regestration.Gender, gender);
        try {
            id= sqlDB.insert(TableData.doctor_Regestration.TableNamedoc, null, co);
            if (sqlDB != null && sqlDB.isOpen())
                sqlDB.close();
            return id;
        }

        catch (Exception e) {

        }
        if (sqlDB != null && sqlDB.isOpen())
            sqlDB.close();
        return-55 ;
    }

    public Long relative_doctor_insert(String phone1,String phone2,String phone3,String phone4,String Email){
        long id=-5;
        ContentValues co = new ContentValues();
        try {


            co.put(TableData.doctor_Relative.RMoblile_1, phone1);
            co.put(TableData.doctor_Relative.RMoblile_2, phone2);
            co.put(TableData.doctor_Relative.RMoblile_3, phone3);
            co.put(TableData.doctor_Relative.RMoblile_4, phone4);
            co.put(TableData.doctor_Relative.doctor_ID, Email);}
        catch (Exception e){

        }
        try {
            id= sqlDB.insert(TableData.doctor_Relative.TableNamedocrel, null, co);
            if (sqlDB != null && sqlDB.isOpen())
                sqlDB.close();
            return id;
        }

        catch (Exception e) {


        }
        if (sqlDB != null && sqlDB.isOpen())
            sqlDB.close();
       // return e.toString();

       return id ;
    }
    public Long checkUPDoct(String Heart,String Temperture,String Pressure,String Time,String Email){
        long id=-5;
        ContentValues co = new ContentValues();
        try {

            co.put(TableData.CheckUPDR.Heart, Heart);
            co.put(TableData.CheckUPDR.Temperture, Temperture);
            co.put(TableData.CheckUPDR.Pressure, Pressure);
            co.put(TableData.CheckUPDR.Date, Time);
            co.put(TableData.CheckUPDR.doctor_ID, Email);}
        catch (Exception e){

            Toast.makeText(c,e.toString()+id,Toast.LENGTH_LONG).show();

        }
        try {
            id= sqlDB.insert(TableData.CheckUPDR.TableNameCHDoc, null, co);

            return id;
        }

        catch (Exception e) {
            Toast.makeText(c,e.toString(),Toast.LENGTH_LONG).show();

        }
        return id;
    }

    public Long check(){
        long id=-5;
        ContentValues co = new ContentValues();
        try {

            co.put(TableData.CheckUPDR.Heart, "50");
            co.put(TableData.CheckUPDR.Temperture, "20");
            co.put(TableData.CheckUPDR.Pressure, "30");
            co.put(TableData.CheckUPDR.Date, "2016-3-13 06:34:20");
            co.put(TableData.CheckUPDR.doctor_ID, "doctor@yahoo.com");}
        catch (Exception e){

            Toast.makeText(c,e.toString()+id,Toast.LENGTH_LONG).show();

        }
        try {
            id= sqlDB.insert(TableData.CheckUPDR.TableNameCHDoc, null, co);

            return id;
        }

        catch (Exception e) {
            Toast.makeText(c,e.toString(),Toast.LENGTH_LONG).show();

        }
        return id;
    }

//server data

    public long insert_server_dataP(String fname ,String lname,String email,String pass,int phone,String date,int weight , int height ,String gender) {
        long id;
        //SQLiteDatabase sqlDB = reg.getWritableDatabase();

        ContentValues co = new ContentValues();
        co.put(TableData.Doc_server_patients.Fname, fname);
        co.put(TableData.Doc_server_patients.Lname, lname);
        co.put(TableData.Doc_server_patients.Email, email);//primary Key
        co.put(TableData.Doc_server_patients.Passward, pass);
        co.put(TableData.Doc_server_patients.Moblile, phone);
        co.put(TableData.Doc_server_patients.BirthDate, date);
        co.put(TableData.Doc_server_patients.Weight, weight);
        co.put(TableData.Doc_server_patients.Height, height);
        co.put(TableData.Doc_server_patients.Gender, gender);
        try {
            id= sqlDB.insert(TableData.Doc_server_patients.doc_server_patients, null, co);

            return id;
        }

        catch (Exception e) {




        }

        return-55 ;




    }

    public Long checkUP_server(String Heart,String Temperture,String Pressure,String Time,String Email){
        long id=-5;
        ContentValues co = new ContentValues();
        try {
            // P_Email =get.getEmail();
            co.put(TableData.servr_CheckUP.Heart, Heart);
            co.put(TableData.servr_CheckUP.Temperture, Temperture);
            co.put(TableData.servr_CheckUP.Pressure, Pressure);
            //  co.put(TableData.CheckUP.Comments, Comments);
            co.put(TableData.servr_CheckUP.Patient_ID, Email);
            co.put(TableData.servr_CheckUP.Date, Time);}
        catch (Exception e){}
        try {
            id= sqlDB.insert(TableData.servr_CheckUP.doc_server_CheckUPs, null, co);

            return id;
        }

        catch (Exception e) {
            Toast.makeText(c,e.toString()+id,Toast.LENGTH_LONG).show();
        }

        return id;
    }
}
