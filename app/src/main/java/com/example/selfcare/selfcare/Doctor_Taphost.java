package com.example.selfcare.selfcare;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by sahar fathy on 2/1/2016.
 */

public class Doctor_Taphost extends AppCompatActivity {

    private MenuItem mSearchAction;
    private MenuItem logOut;
    private MenuItem sett;
    private MenuItem userProf;


    private boolean isSearchOpened = false;
    private boolean isLog = false;
    private boolean isSettOpened = false;
    private boolean isProfOpened = false;
    private EditText edtSeach;

    private int mInterval = 5000*500000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    TabHost PatabHost;
    EditText Heart, Temp, pressure, comment;
    Button save;
    InsertData reg;
    SharedPreferences sharedpreferences;
    String email = "";
    Build args;

    DialogFragment newFragment;
    Context c;
    //belutooth side
    private static final String TAG = "bluetooth2";

    private Handler serverHandler;

    final int RECIEVE_MESSAGE = 1;        // Status  for Handler
    private BluetoothAdapter BA = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder sb = new StringBuilder();
    ImageButton start;

    private ConnectedThread mConnectedThread;

    // SPP UUID service
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //ده الماك ادريس الخاص بالموديول بتاعنا
    private static String address = "20:15:10:19:69:37";

    BroadcastReceiver receiver;
    String tepr="";
     TabHost tabHost;
    GetDataOfDoctor get ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_taphost);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("SelfCare");
       /*ab.setLogo(R.drawable.ic_launch);
        ab.setDisplayUseLogoEnabled(false);
        ab.setDisplayShowHomeEnabled(false);

*/

        get  = new GetDataOfDoctor(this);
        reg = new InsertData(this);//database
        sharedpreferences = getSharedPreferences("MyPREFERENCES", 0);
        email = sharedpreferences.getString("doctor_email", "null");

        args = new Build();


        tabHost = (TabHost) findViewById(R.id.doctabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Measurements");
        tabSpec.setContent(R.id.dr_measurements);
        tabSpec.setIndicator("Measurements");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Requestes");
        tabSpec.setContent(R.id.dr_Requestes);
        tabSpec.setIndicator("Requestes");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Notification");
        tabSpec.setContent(R.id.dr_Notification);
        tabSpec.setIndicator("Notification");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Messages");
        tabSpec.setContent(R.id.dr_Messages);
        tabSpec.setIndicator("Messages");
        tabHost.addTab(tabSpec);


        /// tab 1
        Heart = (EditText) findViewById(R.id.drhartrateres);
        Temp = (EditText) findViewById(R.id.drtemperatureres);
        pressure = (EditText) findViewById(R.id.drpresserres);
        //   comment = (EditText) findViewById(R.id.Comments);
        //   save = (Button) findViewById(R.id.Accepttbtn);
        Toast.makeText(getBaseContext(), email, Toast.LENGTH_LONG).show();
        c = this;
//Bletooth

        // BA = BluetoothAdapter.getDefaultAdapter();
        //turnOn();
/*
        try {
            mHandler = new Handler();
            startRepeatingTask();
        }
        catch (Exception e){

            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();

        }*/
        try {
            serverHandler = new Handler();
            startcheck();
        } catch (Exception e) {

            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
        Temp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                record();

            }
        });

    }
    // data brought from server
    String firstname;
    String lastname;
    String Email="";
    int mobile;
    String birthdate;
    String gen;
    int weight;
    int height;

    String prss;
    String hearts;
    String temps;
    String time;

    Runnable server = new Runnable() {
        @Override
        public void run() {
            try {
                checkserver(); //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                serverHandler.postDelayed(server, 30000);
            }
        }
    };
String ee;
    private void checkserver() {
        try {
            Login_server lg =new Login_server(c);

            BackgroundTask task = new BackgroundTask(c);
            if (isNetworkAvailable()==true) {

                Toast.makeText(getBaseContext(), isNetworkAvailable() + "", Toast.LENGTH_LONG).show();

                task.execute("regester", get.Fname(email), get.Lname(email), get.Pass(email), get.mobile(email),
                        get.birthdate(email), get.weight(email), get.height(email), get.gendre(email), email, "doctor");

                lg.execute("", get.Fname(email), get.Lname(email),get.Pass(email), get.mobile(email), get.birthdate(email), get.weight(email), get.height(email), get.gendre(email), email,"", "doctor");
                // lg.execute("doc_email", get.Demail(email), email);
                Doc_server_records doc =new Doc_server_records(c) ;
                doc.execute(email);
               // Toast.makeText(getBaseContext(),ee, Toast.LENGTH_LONG).show();

                //  doc.return_records();


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();
        } finally {


        }

    }
    void startcheck() {
        server.run();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




    Runnable mStatusChecker = new Runnable() {
    @Override
    public void run() {
        try {
            updateStatus(); //this function can change value of mInterval.
        } finally {
            // 100% guarantee that this always happens, even if
            // your update method throws an exception
            mHandler.postDelayed(mStatusChecker, mInterval);
        }
    }
};

    private void updateStatus() {
        try {
            turnOnBT();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e+"", Toast.LENGTH_LONG).show();
        }


        finally {
            mConnectedThread.write("0");

        }

    }

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    private void turnOn() {
        try{
            if (!BA.isEnabled()) {
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn, 0);
                Toast.makeText(getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();
            } else {
                // Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception r){
            Toast.makeText(getApplicationContext(),r+"", Toast.LENGTH_LONG).show();

        }
    }



    private void turnOnBT() {
        BluetoothDevice device = BA.getRemoteDevice(address);

        BluetoothSocket mmSocket;
        BluetoothDevice mmDevice;
        final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        BluetoothSocket tmp = null;
        mmDevice = device;

        try {
            tmp = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }


        mmSocket = tmp;

        BA.cancelDiscovery();
        try {
            mmSocket.connect();




        } catch (IOException connectException) {

            try {

                mmSocket.close();
            } catch (IOException closeException) {
            }  return;
        }


        mConnectedThread = new ConnectedThread(mmSocket);
        // mConnectedThread.write("0");
        mConnectedThread.start();




    }








String heart = "";
String temp = "";
String press = "";
String comm = "";

    public void record()  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Time = sdf.format(new Date());




        inite();
//String Heart,String Temperture,String Pressure,String Comments,String Time,String Email
        long id = reg.checkUPDoct(heart, temp, press, Time, email);
        GetDataOfPatient reg1=new GetDataOfPatient(c);
        //  pressure.setText(reg1.date(email));
        Toast.makeText(getBaseContext(), Time, Toast.LENGTH_LONG).show();

        if (id > 0) {
            BackgroundTask task = new BackgroundTask(c);
            String method = "record";
            task.execute(method, heart, temp, press,Time, email , "doctor");


            Toast.makeText(getBaseContext(), "successful", Toast.LENGTH_LONG).show();
        }

        else
            Toast.makeText(getBaseContext(), "Faild", Toast.LENGTH_LONG).show();
        reg.getData();

        // reg.delet();
        //  Intent i = new Intent(Patient_Profile.this, Test.class);
        //  startActivity(i);



    }

    private void inite() {
        heart = Heart.getText().toString();
        temp = Temp.getText().toString();
       // comm = comment.getText().toString();
        press = pressure.getText().toString();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mSearchAction = menu.findItem(R.id.search_id);
        logOut = menu.findItem(R.id.sign_id);
        sett =menu.findItem(R.id.setting_id);
        userProf =menu.findItem(R.id.profile_id);
        return true;
    }

    /// icons clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_id:
                Toast.makeText(getApplicationContext(), "Your profile is selected", Toast.LENGTH_LONG).show();
                gotoProf();
                return true;

            case R.id.setting_id:
                Toast.makeText(getApplicationContext(), "Setting icon is selected", Toast.LENGTH_LONG).show();
                gotoSett();
                return true;

            case R.id.sign_id:
                open();
                // Toast.makeText(getApplicationContext(), "Sign out", Toast.LENGTH_LONG).show();
                return true;

            case R.id.search_id:
                handleMenuSearch();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /// search function
    protected void handleMenuSearch() {
        ActionBar action = getSupportActionBar(); //get the actionbar

        if (isSearchOpened)  //test if the search is open
        {
            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar
            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_search));//add the search icon in the action bar
            isSearchOpened = false;
        } else { //open the search entry
            action.setDisplayShowCustomEnabled(true); //enable it to display a custom view in the action bar
            action.setCustomView(R.layout.icons);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title
            edtSeach = (EditText) action.getCustomView().findViewById(R.id.edtSearch); //the text editor
            //this is a listener to do a search when the user clicks on search button
            edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        doSearch();
                        return true;
                    }
                    return false;
                }
            });
            edtSeach.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //open the keyboard focused in the edtSearch
            imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_close)); //add the close icon
            isSearchOpened = true;
        }
    }

    @Override
    public void onBackPressed() {
        if (isSearchOpened) {
            handleMenuSearch();
            return;
        }
        super.onBackPressed();
    }

    private void doSearch() {
/////////////////////////////// deal with server
    }




    // log out function
    protected void open() {
        ActionBar action = getSupportActionBar();
        if (isLog) {
            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true);
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_shutdown));
            isLog = false;
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure that You want to Log out");

            alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(Doctor_Taphost.this, "Sign out", Toast.LENGTH_LONG).show();

                    //// switch to register or user screen
                      Intent intent=new Intent(Doctor_Taphost.this,LoginActivity.class);
                     startActivity(intent);

                }
            });
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    protected void gotoSett() { //settings screen
        ActionBar action = getSupportActionBar();
        if (isSettOpened)  //test if setting icon selected
        {
            action.setDisplayShowCustomEnabled(false);
            action.setDisplayShowTitleEnabled(true);
            isSettOpened = false;
        }
        else { //open the setting activity
            /*
            Intent intent = new Intent(this,//.class);
            startActivity(intent);
                   */
        }
    }
    private void gotoProf() { //profile screen
        ActionBar action = getSupportActionBar();
        if (isProfOpened)  //test if the profile icon selected
        {
            action.setDisplayShowCustomEnabled(false);
            action.setDisplayShowTitleEnabled(true);
            isProfOpened = false;
        } else { //open the profile activity
            Intent intent = new Intent(this, Doctor_Profile.class);
            startActivity(intent);

        }

    }


    public class Doc_server_records extends AsyncTask<String  ,Void  ,String > {

        Context ctx;

        Doc_server_records(Context context){

            ctx=context;
        }

        String json_url ;
        String json_reader ="";
        @Override
        protected void onPreExecute() {
            json_url="http://selfcare25.3eeweb.com/json_rec.php";
        }
        @Override
        protected String doInBackground(String... params) {

            URL url = null;
            String Email =params[0];
            try {
                url = new URL(json_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("Email", "UTF-8")+"="+URLEncoder.encode(Email,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while ((json_reader=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(json_reader+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                Toast.makeText(ctx,e.toString(),Toast.LENGTH_LONG).show();
                return e.toString();
            } catch (IOException e) {
                Toast.makeText(ctx,e.toString(),Toast.LENGTH_LONG).show();
                return e.toString();
            }

            // return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Heart.setText(result);
         Toast.makeText(ctx,result.toString(),Toast.LENGTH_LONG).show();
           // res=result;
             return_records(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        public void return_records(String res) {



            long dd = -80;
                JSONObject jsonObjectss;
                JSONArray jsonArray;
                try {

                    jsonObjectss = new JSONObject(res);
                    jsonArray = jsonObjectss.optJSONArray("server");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Email = jsonObject.optString("Email").toString();
                        firstname=jsonObject.optString("FirstName").toString();
                        lastname=jsonObject.optString("LastName").toString();
                        mobile= Integer.parseInt( jsonObject.optString("Mobile").toString());
                        birthdate=jsonObject.optString("BirthDate").toString();
                        height=Integer.parseInt(jsonObject.optString("Height").toString());
                        weight=Integer.parseInt(jsonObject.optString("Weight").toString());
                        gen=jsonObject.optString("Gender").toString();
                        hearts=jsonObject.optString("Heart").toString();
                        temps=jsonObject.optString("Temperture").toString();
                        prss=jsonObject.optString("Pressure").toString();
                        time=jsonObject.optString("Date").toString();
                        //(String fname ,String lname,String email,String pass,int phone,String date,int weight , int height ,String gender)
                SDS();
                    }

                    ee=email;

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
    }
Get_sever_data_for_doctor getserver;
    public void SDS() {
        getserver=new Get_sever_data_for_doctor(c);
        Long id=  reg.insert_server_dataP(firstname, lastname,Email, "0", mobile, birthdate, weight, height, gen);
       // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long dd = reg.checkUP_server(hearts, temps, prss, time, Email);
        Toast.makeText(c,dd+"",Toast.LENGTH_LONG).show();
       /* try {
            Date   servDate=  df.parse(time);
            Date lastDate=   df.parse(getserver.lastdate(Email));
            if (servDate.after(lastDate)) {

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

       // Toast.makeText(c,dd+"",Toast.LENGTH_LONG).show();
//String ss ="sara@yahoo.com";
//Heart.setText(getserver.Email(ss)+getserver.Fname(ss)+getserver.temp(ss));
    }


    public class ConnectedThread extends Thread {
    OutputStream  mmOutStream;
    InputStream mmInStream;
    private ConnectedThread(BluetoothSocket socket) {
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run() {
        byte[] buffer = new byte[256];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                // Read from the InputStream
                bytes = mmInStream.read(buffer);        // Get number of bytes and message in "buffer"
                h.obtainMessage(1, bytes, -1, buffer).sendToTarget();     // Send to message queue Handler
            } catch (IOException e) {
                break;
            }
        }
    }

    Handler h = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:                                                   // if receive massage
                    byte[] readBuf = (byte[]) msg.obj;
                    String strIncom = new String(readBuf, 0, msg.arg1);                 // create string from bytes array
                    sb.append(strIncom);                                                // append string
                    int endOfLineIndex = sb.indexOf("\r\n");                            // determine the end-of-line
                    if (endOfLineIndex > 0) {                                            // if end-of-line,
                        String sbprint = sb.substring(0, endOfLineIndex);               // extract string
                        sb.delete(0, sb.length());// and clear
                        Temp.setText(sbprint);
                    }
                    break;
            }
        };
    };

    /* Call this from the main activity to send data to the remote device */
    public void write(String message) {

        byte[] msgBuffer = message.getBytes();
        try {
            mmOutStream.write(msgBuffer);
        } catch (IOException e) {
        }}

}


}
