package com.example.selfcare.selfcare;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by h on 25/03/2016.
 */
public class BackgroundTask extends AsyncTask <String  ,Void  ,String > {

Context ctx;

    BackgroundTask(Context context){

        ctx=context;
    }
//                    task.execute(method,FName, LName, EEmail, Passward, Mobile, BirthDate, Weights, Heights, Gender);

    @Override
    protected String doInBackground(String... params) {


        String p_reg_url="http://selfcare25.3eeweb.com/regester.php";
        String record_url="http://selfcare25.3eeweb.com/records.php";
        String method =params[0].toString();
        if (method=="regester")
        {
        String FName =params[1].toString();
        String LName =params[2].toString();
        String Passward =params[3].toString();
        String Mobile=  params[4];
        String BirthDate =params[5].toString();
        String Weights=  params[6];
        String Heights= params[7];
        String Gender =params[8].toString();
        String EEmail =params[9].toString();
        String kind =params[10].toString();

            try {
                URL url =new URL(p_reg_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("FName","UTF-8")+"="+URLEncoder.encode(FName,"UTF-8")+"&"+
                        URLEncoder.encode("LName","UTF-8")+"="+URLEncoder.encode(LName,"UTF-8")+"&"+
                        URLEncoder.encode("Passward","UTF-8")+"="+URLEncoder.encode(Passward,"UTF-8")+"&"+
                        URLEncoder.encode("Mobile","UTF-8")+"="+URLEncoder.encode(Mobile,"UTF-8")+"&"+
                        URLEncoder.encode("BirthDate","UTF-8")+"="+URLEncoder.encode(BirthDate,"UTF-8")+"&"+
                        URLEncoder.encode("Weights","UTF-8")+"="+URLEncoder.encode(Weights,"UTF-8")+"&"+
                        URLEncoder.encode("Heights","UTF-8")+"="+URLEncoder.encode(Heights,"UTF-8")+"&"+
                        URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(EEmail,"UTF-8")+"&"+
                        URLEncoder.encode("Gender","UTF-8")+"="+URLEncoder.encode(Gender,"UTF-8")+"&"+
                        URLEncoder.encode("kind","UTF-8")+"="+URLEncoder.encode(kind,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                //bufferedWriter.close();
             //   os.close();
                InputStream is =httpURLConnection.getInputStream();
              //  is.close();
                return "regesteration success";



            } catch (MalformedURLException e) {
                Toast.makeText(ctx,e.toString(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(ctx,e.toString(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
return "outtry";
        }
        //heart, temp, press, Time, email , "patient"
else if (method=="record")
        {
            String heart =params[1].toString();
            String temp =params[2].toString();
            String press =params[3].toString();
            String Time=  params[4];
            String email =params[5].toString();
            String kind=  params[6];
            try {
                URL url =new URL(record_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("heart","UTF-8")+"="+URLEncoder.encode(heart,"UTF-8")+"&"+
                        URLEncoder.encode("temper","UTF-8")+"="+URLEncoder.encode(temp,"UTF-8")+"&"+
                        URLEncoder.encode("pres","UTF-8")+"="+URLEncoder.encode(press,"UTF-8")+"&"+
                        URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(Time,"UTF-8")+"&"+
                        URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("kind","UTF-8")+"="+URLEncoder.encode(kind,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
             //   bufferedWriter.close();
              //  os.close();
                InputStream is =httpURLConnection.getInputStream();
              //  is.close();
                return "records success";
        } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e){
            e.printStackTrace();
        }
        }

        return "";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        Toast.makeText(ctx,result+"hi",Toast.LENGTH_LONG).show();
    }
}
