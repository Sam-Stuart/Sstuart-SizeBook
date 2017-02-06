package com.example.sstuart_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
//EditEntry activity for editing/deleting a specified entry
public class EditEntryActivity extends AppCompatActivity {
    private static final String FILENAME = "Sstuart-Sizebook.sav";
    private ArrayList <Person> entryList = new ArrayList<>();
    private int entryIndex;
    private EditText nameText;
    private EditText neckText;
    private EditText bustText;
    private EditText chestText;
    private EditText waistText;
    private EditText hipText;
    private EditText inseamText;
    private EditText dateText;
    private EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        Intent intent = getIntent();
        entryList = (ArrayList<Person>) intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);
        entryIndex = intent.getIntExtra(MainActivity.EXTRA_MESSAGE2, -1);

        //Creates variable to using each editText field
        nameText = (EditText) findViewById(R.id.editTextName);
        dateText = (EditText) findViewById(R.id.editTextDate);
        neckText = (EditText) findViewById(R.id.editTextNeck);
        bustText = (EditText) findViewById(R.id.editTextBust);
        chestText = (EditText) findViewById(R.id.editTextChest);
        waistText = (EditText) findViewById(R.id.editTextWaist);
        hipText = (EditText) findViewById(R.id.editTextHip);
        inseamText = (EditText) findViewById(R.id.editTextInseam);
        commentText = (EditText) findViewById(R.id.editTextComment);


        //Sets all the text field with the values of the entry thats being edited
        nameText.setText(entryList.get(entryIndex).getName());
        dateText.setText(entryList.get(entryIndex).getDate());
        neckText.setText("" + entryList.get(entryIndex).getNeck());
        bustText.setText("" + entryList.get(entryIndex).getBust());
        chestText.setText("" + entryList.get(entryIndex).getChest());
        waistText.setText("" + entryList.get(entryIndex).getWaist());
        hipText.setText("" + entryList.get(entryIndex).getHip());
        inseamText.setText("" + entryList.get(entryIndex).getInseam());
        commentText.setText(entryList.get(entryIndex).getComment());



    }
    //Back button, returns to mainactivity
    public  void goBack(View view){

            finish();
    }

    public  void editEntry(View view){




        //Local vars for temporary storage of the double values
        double neckSize;
        double bustSize;
        double chestSize;
        double waistSize;
        double hipSize;
        double inseamSize;


        Intent intent = getIntent();
        entryList = (ArrayList<Person>) intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);
        entryIndex = intent.getIntExtra(MainActivity.EXTRA_MESSAGE2, -1);

        if (entryIndex == -1) {
            finish();
        }

        //Creates variable to using each editText field
        nameText = (EditText) findViewById(R.id.editTextName);
        dateText = (EditText) findViewById(R.id.editTextDate);
        neckText = (EditText) findViewById(R.id.editTextNeck);
        bustText = (EditText) findViewById(R.id.editTextBust);
        chestText = (EditText) findViewById(R.id.editTextChest);
        waistText = (EditText) findViewById(R.id.editTextWaist);
        hipText = (EditText) findViewById(R.id.editTextHip);
        inseamText = (EditText) findViewById(R.id.editTextInseam);
        commentText = (EditText) findViewById(R.id.editTextComment);


        //Sets all the text field with the values of the entry thats being edited

        //Strings repersenting whats currently input into each editText field
        String Name = nameText.getText().toString();
        String dateString = dateText.getText().toString();
        String neck = neckText.getText().toString();
        String bust = bustText.getText().toString();
        String chest = chestText.getText().toString();
        String waist = waistText.getText().toString();
        String hip = hipText.getText().toString();
        String inseam = inseamText.getText().toString();
        String comment = commentText.getText().toString();

        //Error/Input checking.
        //If format is incorrect or exceptions occur then set data to default values
        //Default values for numeric data 0.0
        //default date: current date
        //default string values: " "
        //Names are mandatory, no checks occure if name is null
        if (Name != null && !Name.isEmpty() ){
            entryList.get(entryIndex).setName(Name);

            if (dateString !=null && !dateString.isEmpty()){
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    Date date = format.parse(dateString);
                    entryList.get(entryIndex).setDate(date);

                }catch(Exception e){
                    createDialogBox("Improper date format, please enter in yyyy-mm-dd format, - included");
                }
            }
            else{
                entryList.get(entryIndex).setDate(new Date());
            }

            if (neck != null && !neck.isEmpty()){
                try{
                    neckSize = Double.parseDouble(neck);
                    neckSize = formatDecimalInput(neckSize);
                    entryList.get(entryIndex).setNeck(neckSize);
                }catch(Exception e){
                    neckSize = 0.0;
                    entryList.get(entryIndex).setNeck(neckSize);}
            }else{
                neckSize = 0.0;
                entryList.get(entryIndex).setNeck(neckSize);
            }

            if (bust != null && !bust.isEmpty()){
                try{
                    bustSize = Double.parseDouble(bust);
                    bustSize = formatDecimalInput(bustSize);
                    entryList.get(entryIndex).setBust(bustSize);
                }catch(Exception e){
                    bustSize = 0.0;
                    entryList.get(entryIndex).setBust(bustSize);}
            }else{
                bustSize = 0.0;
                entryList.get(entryIndex).setBust(bustSize);
            }

            if (chest != null && !chest.isEmpty()){
                try{
                    chestSize = Double.parseDouble(chest);
                    chestSize = formatDecimalInput(chestSize);
                    entryList.get(entryIndex).setChest(chestSize);
                }catch(Exception e){
                    chestSize = 0.0;
                    entryList.get(entryIndex).setChest(chestSize);}
            }else{
                chestSize = 0.0;
                entryList.get(entryIndex).setChest(chestSize);
            }

            if (waist != null && !waist.isEmpty()){
                try{
                    waistSize = Double.parseDouble(waist);
                    waistSize = formatDecimalInput(waistSize);
                    entryList.get(entryIndex).setWaist(waistSize);
                }catch(Exception e){
                    waistSize = 0.0;
                    entryList.get(entryIndex).setWaist(waistSize);}
            }
            else{
                waistSize = 0.0;
                entryList.get(entryIndex).setWaist(waistSize);
            }

            if (hip != null && !hip.isEmpty()){
                try{
                    hipSize = Double.parseDouble(hip);
                    hipSize = formatDecimalInput(hipSize);
                    entryList.get(entryIndex).setHip(hipSize);
                }catch(Exception e){
                    hipSize = 0.0;
                    entryList.get(entryIndex).setHip(hipSize);}
            }
            else{
                hipSize = 0.0;
                entryList.get(entryIndex).setHip(hipSize);
            }

            if (inseam != null && !inseam.isEmpty()){
                try{
                    inseamSize = Double.parseDouble(inseam);
                    inseamSize = formatDecimalInput(inseamSize);
                    entryList.get(entryIndex).setInseam(inseamSize);
                }catch(Exception e){
                    inseamSize = 0.0;
                    entryList.get(entryIndex).setInseam(inseamSize);}
            }
            else{
                inseamSize = 0.0;
                entryList.get(entryIndex).setInseam(inseamSize);
            }

            if (comment != null && !comment.isEmpty()){
                entryList.get(entryIndex).setComment(comment);
            }
            else{
                entryList.get(entryIndex).setComment(" ");
            }

            saveInFile();

        }else{
            createDialogBox("Name is required");
        }
    }
    //removes entry from arraylist and saves it
    public void deleteEntry(View view) {
        entryList.remove(entryIndex);
        saveInFile();
    }

    //Saves the entries to the Json file after editing, returns to mainActivity after saving
    //Code adapted from Cmput 301 Lab 3, U of A 2017 Winter term

    private void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(entryList,out);

            out.flush();

            fos.close();

            finish();
        } catch (FileNotFoundException e) {
            finish();
        } catch (IOException e) {
            finish();
        }

    }

    //Helper func that ensures proper numeric input format
    public double formatDecimalInput(double Value){
        if (Value <0){Value = Value * -1;}
        return (double)Math.round(Value * 10d)/10d;

    }
    //helper function for creating pop up windows that display text
    public void createDialogBox(String message){
        AlertDialog.Builder Test = new AlertDialog.Builder(EditEntryActivity.this);
        Test.setMessage(message);
        Test.setCancelable(true);

        AlertDialog NameTest = Test.create();
        NameTest.show();
    }
}
