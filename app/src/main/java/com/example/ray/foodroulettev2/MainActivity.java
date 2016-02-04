package com.example.ray.foodroulettev2;

import android.content.ContentProviderResult;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Animation rotation;
    private ImageButton imgbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeWidgets();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(item.getItemId()){
            case R.id.action_add:
                Log.i("ADDING", "ADD PRESSED");
                return true;
            case R.id.action_home:
                Log.i("HOMING", "Home pressed");
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
                return true;
            case R.id.action_mail:
                Log.d("MAILING", "mail Pressed");
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);

                smsIntent.setData(Uri.parse("smsto:"));
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("sms_body", "test");
                startActivity(smsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initializeWidgets() {
        imgbutton = (ImageButton) findViewById(R.id.imageView_ring);
        imgbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(myIntent);

            }
        });
    }
}
