package com.example.ray.foodroulettev2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class addActivity extends AppCompatActivity {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Bind(R.id.placeEditText)
    EditText placeEditText;
    @Bind(R.id.addressEditText)
    EditText addressEditText;

    @Bind(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        refreshListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                return true;
            case R.id.action_home:
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
                return true;
            case R.id.action_mail:

                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                Intent getMe = getIntent();
                String number = getMe.getStringExtra("sms_body");
                smsIntent.setData(Uri.parse("smsto:"));
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("sms_body", Integer.parseInt(number));
                startActivity(smsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.addButton)
    void addButtonOnClick() {
        String place = placeEditText.getText().toString();
        String address = addressEditText.getText().toString();
        Place places = new Place();
        List<Place> placeList = readPlacesListFromDb();

        if (place.isEmpty()) {
            alertView("You entered an empty place! Try again!");
        } else {
            places.setPlace(place);
            places.setAddress(address);
            placeList.add(places);
        }

        SharedPreferences sharedPreferences = this.getSharedPreferences("place-sp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            String placeListStr = objectMapper.writeValueAsString(placeList);
            editor.putString("place-list", placeListStr);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("TEST", "Inserted the Place record!");
        refreshListView();
    }

    @OnClick(R.id.clearButton)
    void clearButtonOnClick() {
        Place places = new Place();

        List<Place> placeList = readPlacesListFromDb();

        SharedPreferences sharedPreferences = this.getSharedPreferences("place-sp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        Log.i("TEST", "Deleted the Place record!");
        refreshListView();
    }

    private void refreshListView() {
        List<Place> placeList = readPlacesListFromDb();
        listView.setAdapter(new ArrayAdapter<Place>(
                this, android.R.layout.simple_list_item_1, placeList
        ));
    }

    private List<Place> readPlacesListFromDb() {
        List<Place> placeList = new ArrayList<Place>();

        SharedPreferences sharedPreferences = getSharedPreferences("place-sp", MODE_PRIVATE);

        String placeListStr = sharedPreferences.getString("place-list", null);
        if (placeListStr != null) {
            try {
                placeList = objectMapper.readValue(placeListStr,
                        new TypeReference<List<Place>>() {
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return placeList;
    }

    private void alertView(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Uhoh!")
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                }).show();
    }
}