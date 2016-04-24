package com.example.selfcare.selfcare;

/**
 * Created by h on 28/03/2016.
 */
public class CheckSql_server {

    String status;

    public void set(String Sts){
        status=Sts;
    }

    public String check(){

        return status;
    }

}
