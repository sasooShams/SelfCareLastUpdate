package com.example.selfcare.selfcare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by h on 21/04/2016.
 */
public class Alarm {
    GetDataOfPatient patient;
    Context c;
    String email;
    String gen ;
    String RNum1 ;
    String RNum2 ;
    String RNum3 ;
    String RNum4 ;
    Alarm(Context ct,String Email){

        c=ct;
        email=Email;
        patient =new GetDataOfPatient(c) ;
         gen =patient.gendre(email);
         RNum1 =patient.p_relative_mob1(email);
         RNum2 =patient.p_relative_mob2(email);
         RNum3 =patient.p_relative_mob3(email);
         RNum4 =patient.p_relative_mob4(email);

    }

    public void checkHeart(String hearts)
    {

        try{

          //  double heart = Double.parseDouble(Heart.getText().toString());
            double heart=Double.parseDouble(hearts);
            //if(16<=age && age<=45){
            if (60 <= heart && heart <= 100) {
                new AlertDialog.Builder(c)
                        .setTitle("Heart Beats Alert")
                        .setIcon(R.drawable.ic_launch)
                        .setMessage("Your heart beats rate is normal..check your all records")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(c,Patient_Profile.class);
                                c.startActivity(intent);
                            }
                        }).create().show();
            }
            if (80 <= heart && heart <= 100){
                if(gen=="female"){
                    new AlertDialog.Builder(c)
                            .setTitle("Heart Beats Alert")
                            .setIcon(R.drawable.ic_launch)
                            .setMessage("Your heart beats rate is normal if you are pregnant ..check your all records")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(c,Patient_Profile.class);
                                    c.startActivity(intent);
                                }
                            }).create().show();
                }
            }
            else if (70 <= heart && heart <= 100) {
                new AlertDialog.Builder(c)
                        .setTitle("Heart Beats Alert")
                        .setIcon(R.drawable.ic_launch)
                        .setMessage("Your Heartbeats rate is normal..check your all records")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(c,Patient_Profile.class);
                                c.startActivity(intent);
                            }
                        }).create().show();
            }
            if (100 < heart && heart <= 150) {
                new AlertDialog.Builder(c)
                        .setTitle("Heart Beats Alert")
                        .setIcon(R.drawable.ic_launch)
                        .setMessage("Your Heartbeats rate result of your Physical exertion,repeat heartbeats checking again in your relaxation state ..check your all records")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(c,Patient_Profile.class);
                                c.startActivity(intent);
                            }
                        }).create().show();
            }
            if (150 < heart || heart < 60) {
                try { //Relatives number usually entered here
                    String phoneNumberReciver1 = RNum1;
                    String phoneNumberReciver2 = RNum2;
                    String phoneNumberReciver3 = RNum3;
                    String phoneNumberReciver4 = RNum4;
                    String message = "Here it's SelfCare Alert , Danger case.. help me!!! ";
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNumberReciver1, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver2, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver3, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver4, null, message, null, null);
                    Toast.makeText(c,
                            "SMS Alert sent to your Relatives",
                            Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(c,
                            "SMS failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                new AlertDialog.Builder(c)
                        .setTitle("Heart Beats Alert")
                        .setIcon(R.drawable.ic_launch)
                        .setMessage("Danger case ,connect to your doctor quickly ..check your all records")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    /* Intent intent=new Intent(this,//massage.class);
                                    startActivity(intent); */
                            }
                        }).create().show();
            } else {
               // Toast.makeText(c, "Error, invalid Heartbeats", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){}
    }

    public void checkpress(String press){
        try {
            double Sys = Double.parseDouble(press);
            if (80 < Sys && Sys <= 100) {
                Toast.makeText(c, "Your pressure is very low..connect to your doctor", Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(c)
                        .setTitle("Pressure Alert")
                        .setIcon(R.drawable.ic_pre)
                        .setMessage("Your pressure is very low..connect to your doctor")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Intent intent=new Intent(this,//profile.class); startActivity(intent);
                            }
                        }).create().show();
            }
            if (100 < Sys && Sys < 120) {
                new AlertDialog.Builder(c)
                        .setTitle("Pressure Alert")
                        .setIcon(R.drawable.ic_pre)
                        .setMessage("Your pressure is low..check all your records")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(c, Patient_Profile.class);
                                c.startActivity(intent);
                            }
                        }).create().show();
            }
            if (120 <= Sys && Sys < 130) {
                new AlertDialog.Builder(c)
                        .setTitle("Pressure Alert")
                        .setIcon(R.drawable.ic_pre)
                        .setMessage("Your pressure is normal..check all your records")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(c, Patient_Profile.class);
                                c.startActivity(intent);
                            }
                        }).create().show();
            }
            if (130 <= Sys && Sys < 140) {
                new AlertDialog.Builder(c)
                        .setTitle("Pressure Alert")
                        .setIcon(R.drawable.ic_pre)
                        .setMessage("Your pressure is high..check all your records")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(c, Patient_Profile.class);
                                c.startActivity(intent);
                            }
                        }).create().show();
            }
            if (140 <= Sys && Sys < 160) {
                new AlertDialog.Builder(c)
                        .setTitle("Pressure Alert")
                        .setIcon(R.drawable.ic_pre)
                        .setMessage("Your pressure is very high..connect to your doctor")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(c, Patient_Profile.class);
                                c.startActivity(intent);
                            }
                        }).create().show();
            }
            if (160 <= Sys && Sys < 180) {
                try {  //Relatives number usually entered here
                    String phoneNumberReciver1 = RNum1;
                    String phoneNumberReciver2 = RNum2;
                    String phoneNumberReciver3 = RNum3;
                    String phoneNumberReciver4 = RNum4;
                    String message = "Here it's SelfCare Alert , Danger case.. help me!!! ";
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNumberReciver1, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver2, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver3, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver4, null, message, null, null);
                    Toast.makeText(c,
                            "SMS Alert sent to your Relatives",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(c,
                            "SMS failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                new AlertDialog.Builder(c)
                        .setTitle("Pressure Alert")
                        .setIcon(R.drawable.ic_pre)
                        .setMessage("Your pressure is extremely high..connect to your doctor doctor quickly")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Intent intent=new Intent(this,//massage.class); startActivity(intent);
                            }
                        }).create().show();
            }
            if (Sys > 180) {
                try { //Relatives number usually entered here
                    String phoneNumberReciver1 = RNum1;
                    String phoneNumberReciver2 = RNum2;
                    String phoneNumberReciver3 = RNum3;
                    String phoneNumberReciver4 = RNum4;
                    String message = "Here it's SelfCare Alert , Danger case.. help me!!! ";
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNumberReciver1, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver2, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver3, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver4, null, message, null, null);
                    Toast.makeText(c,
                            "SMS Alert sent to your Relatives",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(c,
                            "SMS failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                new AlertDialog.Builder(c)
                        .setTitle("Pressure Alert")
                        .setIcon(R.drawable.ic_pre)
                        .setMessage("Danger case ,Your pressure is extremely high..connect to your doctor quickly")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Intent intent=new Intent(this,//massage.class); startActivity(intent);
                            }
                        }).create().show();
            } else {
                // Toast.makeText(Patient_Taphost.this, "Error, invalid pressure", Toast.LENGTH_LONG).show();
            }
        }
 catch (Exception e) {
        }
        }

    public void checktemp(String temp){
        try { double Tem = Double.parseDouble(temp);
            if ((36.8 <= Tem && Tem <= 37.2)) {
                new AlertDialog.Builder(c)
                        .setTitle("Temperature Alert")
                        .setIcon(R.drawable.ic_temper)
                        .setMessage("Your temperature is normal..check your all records")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(c,Patient_Profile.class);
                                c.startActivity(intent);
                            }
                        }).create().show();
            }
            if (37.2 < Tem && Tem < 37.5) {
                new AlertDialog.Builder(c)
                        .setTitle("Temperature Alert")
                        .setIcon(R.drawable.ic_temper)
                        .setMessage("A slight rise in temperature,maybe because of the High temperature air or that you made a lot of exercises ..check your all records ")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(c,Patient_Profile.class);
                                c.startActivity(intent);
                            }
                        }).create().show();
            }
            if (37.5 < Tem && Tem <= 38.3) {
                new AlertDialog.Builder(c)
                        .setTitle("Temperature Alert")
                        .setIcon(R.drawable.ic_temper)
                        .setMessage("You catch a cold ..please connect to you doctor ..check your all records ")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(c,Patient_Profile.class);
                                c.startActivity(intent);
                            }
                        }).create().show();
            }
            if (38.3 < Tem && Tem <= 41.8) {
                try { //Relatives number usually entered here
                    String phoneNumberReciver1 = RNum1;
                    String phoneNumberReciver2 = RNum2;
                    String phoneNumberReciver3 = RNum3;
                    String phoneNumberReciver4 = RNum4;
                    String message = "Here it's SelfCare Alert , Danger case.. help me!!! ";
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNumberReciver1, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver2, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver3, null, message, null, null);
                    sms.sendTextMessage(phoneNumberReciver4, null, message, null, null);
                    Toast.makeText(c,
                            "SMS Alert sent to your Relatives",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(c,"SMS failed, please try again later!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                new AlertDialog.Builder(c)
                        .setTitle("Temperature Alert")
                        .setIcon(R.drawable.ic_temper)
                        .setMessage("danger case.. A slight rise in temperature,you have fever .. connect to your doctor quickly")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    /*Intent intent=new Intent(this,//massage.class);
                                        startActivity(intent);*/

                            }
                        }).create().show();
            }
            if (Tem == 37.5) {
                if (gen=="female"){
                    new AlertDialog.Builder(c)
                            .setTitle("Temperature Alert")
                            .setIcon(R.drawable.ic_temper)
                            .setMessage("If you on day 14 from your PMS..detecting ovulation is possible for the best chance of getting pregnant, please check your temperature after 30 minutes ..check your all records ")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                        /*
                                Intent intent=new Intent(this,//massage.class);
                                        startActivity(intent);
                                */
                                }
                            }).create().show();
                }
                else{
                    new AlertDialog.Builder(c)
                            .setTitle("Temperature Alert")
                            .setIcon(R.drawable.ic_temper)
                            .setMessage("MayBe You catch a cold ..please connect to you doctor ..check your all records ")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(c,Patient_Profile.class);
                                    c.startActivity(intent);
                                }
                            }).create().show();
                }
            } else {
                Toast.makeText(c, "Error, invalid temperature", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(c, e.toString(), Toast.LENGTH_LONG).show();

        }

    }

}

