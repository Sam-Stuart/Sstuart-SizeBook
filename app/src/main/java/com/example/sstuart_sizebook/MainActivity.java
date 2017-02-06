package com.example.sstuart_sizebook;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.TextView;

/*
    main activity: Shows list of entries on app start up
    provides the transiton between edit and add entry activities
 */
public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "Sstuart-Sizebook.sav";
    public final static  String EXTRA_MESSAGE = "com.example.sstuart_sizebook.ENTRIES";
    public final static  String EXTRA_MESSAGE2 = "com.example.sstuart_sizebook.INDEX";
    private ArrayList <Person> entryList = new ArrayList<>();
    private ListView entries;
    private ArrayAdapter<Person> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entries = (ListView) findViewById(R.id.list_view);

    }

    @Override
    protected  void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Person>(this,R.layout.list_item,entryList);
        entries.setAdapter(adapter);
        entries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            editEntry(view,position);
            }
        });


        TextView total = (TextView) findViewById(R.id.Title);
        total.setText("Total Entries: " + entryList.size());
    }

    //start add entry activity, passes arraylist<person> to it
    public void addEntry(View view) {
        Intent addEntryIntent = new Intent(this,AddEntryActivity.class);
        addEntryIntent.putExtra(EXTRA_MESSAGE,entryList);
        startActivity(addEntryIntent);

    }

    //start edit entry activity, passes the index of the item to be edited and the arraylist
    public void editEntry(View view,int index) {
        Intent editEntryIntent = new Intent(this,EditEntryActivity.class);
        editEntryIntent.putExtra(EXTRA_MESSAGE,entryList);
        editEntryIntent.putExtra(EXTRA_MESSAGE2,index);
        startActivity(editEntryIntent);

    }
    //Loads Json file and populates Arraylist of person objects
    //Code adapted from Cmput 301 Lab 3, U of A 2017 Winter term
    //Load from file could be in its own file for better encapsulation/modularization
    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //Feb 05 2017 18:31
            Type listType = new TypeToken<ArrayList<Person>>(){}.getType();
            entryList = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            entryList = new ArrayList<Person>();

        }
    }


}
