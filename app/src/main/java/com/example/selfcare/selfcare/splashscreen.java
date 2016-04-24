package com.example.selfcare.selfcare;

/**
 * Created by sahar fathy on 11/18/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class splashscreen extends Activity
{
    private static int splashInterval = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                Intent i = new Intent(splashscreen.this, ImageSlider.class);
                startActivity(i);
                this.finish();
            }

            private void finish()
            {
                // TODO Auto-generated method stub

            }
        }, splashInterval);

    }
}
