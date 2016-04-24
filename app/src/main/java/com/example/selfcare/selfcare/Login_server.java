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
 * Created by h on 28/03/2016.
 */
public class Login_server extends AsyncTask<String  ,Void  ,String > {

    Context ctx;


    Login_server(Context context) {

        ctx = context;
    }
    @Override
    protected String doInBackground(String... params) {

        if (params[0].toString()=="doc_email" ){
            String doc_email =params[1].toString();
            String p_email =params[2].toString();
            String up_url="http://selfcare25.3eeweb.com/up_doc_email.php";

try{
    URL url = new URL(up_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream os=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            String data = URLEncoder.encode("DOC_email","UTF-8")+"="+URLEncoder.encode(doc_email,"UTF-8")+"&"+
                    URLEncoder.encode("EEmail","UTF-8")+"="+URLEncoder.encode(p_email,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            InputStream is =httpURLConnection.getInputStream();
            is.close();
            return "update success";



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        }


        String login_url="http://selfcare25.3eeweb.com/update.php";

        String FName =params[1].toString();
        String LName =params[2].toString();
        String Passward =params[3].toString();
        String Mobile=  params[4];
        String BirthDate =params[5].toString();
        String Weights=  params[6];
        String Heights= params[7];
        String Gender =params[8].toString();
        String EEmail =params[9].toString();
        String doc_email =params[10].toString();
        String kind =params[11].toString();
            URL url = null;
            try {
                url = new URL(login_url);

                HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
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
                        URLEncoder.encode("DOC_email","UTF-8")+"="+URLEncoder.encode(doc_email,"UTF-8")+"&"+
                        URLEncoder.encode("kind","UTF-8")+"="+URLEncoder.encode(kind,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is =httpURLConnection.getInputStream();
                is.close();
                return "update success";



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;


    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
String res="";
    @Override
    protected void onPostExecute(String result) {

        Toast.makeText(ctx,result+"hi",Toast.LENGTH_LONG).show();
    }

}
