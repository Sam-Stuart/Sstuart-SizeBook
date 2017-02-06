package com.example.sstuart_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.Math;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import com.google.gson.Gson;

//Activity that populates ArrayList of people objects after verifying inputs
//Opened by main activity, returns to MainActivity after data is successfully saved
public class AddEntryActivity extends AppCompatActivity {

    //variables used to access EditText field on the XML
    private EditText nameText;
    private EditText neckText;
    private EditText bustText;
    private EditText chestText;
    private EditText waistText;
    private EditText hipText;
    private EditText inseamText;
    private EditText dateText;
    private EditText commentText;
    private Person person;
    //File to load
    private static final String FILENAME = "Sstuart-Sizebook.sav";
    private ArrayList <Person> entryList = new ArrayList<Person>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        Intent intent = getIntent();
        entryList = (ArrayList<Person>) intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);

        nameText = (EditText) findViewById(R.id.editTextName);
        Button addButton = (Button) findViewById(R.id.Add);

        Button backButton = (Button) findViewById(R.id.Return);

        addButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view){
                setResult(RESULT_OK);

                String Name = nameText.getText().toString();

                if (Name != null && !Name.isEmpty() ){

                    person = new Person(Name);

                    double neckSize;
                    double bustSize;
                    double chestSize;
                    double waistSize;
                    double hipSize;
                    double inseamSize;

                    dateText = (EditText) findViewById(R.id.editTextDate);
                    String dateString = dateText.getText().toString();
                    neckText = (EditText) findViewById(R.id.editTextNeck);
                    String neck = neckText.getText().toString();
                    bustText = (EditText) findViewById(R.id.editTextBust);
                    String bust = bustText.getText().toString();
                    chestText = (EditText) findViewById(R.id.editTextChest);
                    String chest = chestText.getText().toString();
                    waistText = (EditText) findViewById(R.id.editTextWaist);
                    String waist = waistText.getText().toString();
                    hipText = (EditText) findViewById(R.id.editTextHip);
                    String hip = hipText.getText().toString();
                    inseamText = (EditText) findViewById(R.id.editTextInseam);
                    String inseam = inseamText.getText().toString();

                    commentText = (EditText) findViewById(R.id.editTextComment);
                    String comment = commentText.getText().toString();

                    if (dateString !=null && !dateString.isEmpty()){
                        try {
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            Date date = format.parse(dateString);
                            person.setDate(date);

                        }catch(Exception e){
                            createDialogBox("Improper date format, please enter in yyyy-mm-dd format, - included");
                        }
                    }
                    else{
                        person.setDate(new Date());
                    }


                    if (neck != null && !neck.isEmpty()){
                        try{
                            neckSize = Double.parseDouble(neck);
                            neckSize = formatDecimalInput(neckSize);
                            person.setNeck(neckSize);
                        }catch(Exception e){
                            neckSize = 0.0;
                            person.setNeck(neckSize);}
                    }else{
                        neckSize = 0.0;
                        person.setNeck(neckSize);
                    }

                    if (bust != null && !bust.isEmpty()){
                        try{
                            bustSize = Double.parseDouble(bust);
                            bustSize = formatDecimalInput(bustSize);
                            person.setBust(bustSize);
                        }catch(Exception e){
                            bustSize = 0.0;
                            person.setBust(bustSize);}
                    }else{
                        bustSize = 0.0;
                        person.setBust(bustSize);
                    }

                    if (chest != null && !chest.isEmpty()){
                        try{
                            chestSize = Double.parseDouble(chest);
                            chestSize = formatDecimalInput(chestSize);
                            person.setChest(chestSize);
                        }catch(Exception e){
                            chestSize = 0.0;
                            person.setChest(chestSize);}
                    }else{
                        chestSize = 0.0;
                        person.setChest(chestSize);
                    }

                    if (waist != null && !waist.isEmpty()){
                        try{
                            waistSize = Double.parseDouble(waist);
                            waistSize = formatDecimalInput(waistSize);
                            person.setWaist(waistSize);
                        }catch(Exception e){
                            waistSize = 0.0;
                            person.setWaist(waistSize);}
                    }
                    else{
                        waistSize = 0.0;
                        person.setWaist(waistSize);
                    }


                    if (hip != null && !hip.isEmpty()){
                        try{
                            hipSize = Double.parseDouble(hip);
                            hipSize = formatDecimalInput(hipSize);
                            person.setHip(hipSize);;
                        }catch(Exception e){
                            hipSize = 0.0;
                            person.setHip(hipSize);}
                    }
                    else{
                        hipSize = 0.0;
                        person.setHip(hipSize);
                    }

                    if (inseam != null && !inseam.isEmpty()){
                        try{
                            inseamSize = Double.parseDouble(inseam);
                            inseamSize = formatDecimalInput(inseamSize);
                            person.setInseam(inseamSize);;
                        }catch(Exception e){
                            inseamSize = 0.0;
                            person.setInseam(inseamSize);}
                    }
                    else{
                        inseamSize = 0.0;
                        person.setInseam(inseamSize);
                    }

                    if (comment != null && !comment.isEmpty()){
                        person.setComment(comment);
                    }
                    else{
                        person.setComment(" ");
                    }

                    entryList.add(person);
                    saveInFile();

                }else{
                    createDialogBox("Name is a required field");
                }

            }

        });

        //Listener that closes activity when clicked
        backButton.setOnClickListener(new View.OnClickListener()  {

            public void onClick(View v) {

                finish();
            }


        });

    }

    //Helper func that ensures proper numeric input format
    public double formatDecimalInput(double Value){
        if (Value <0){Value = Value * -1;}
        return  (double)Math.round(Value * 10d)/10d;

    }
    //helper function for creating pop up windows
    public void createDialogBox(String message){
        AlertDialog.Builder Test = new AlertDialog.Builder(AddEntryActivity.this);
        Test.setMessage(message);
        Test.setCancelable(true);

        AlertDialog NameTest = Test.create();
        NameTest.show();
    }

    //Saves the entries to the Json file after editing, returns to mainActivity after saving
    //Code adapted from Cmput 301 Lab 3, U of A 2017 Winter term
    public void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME,Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(entryList,out);

            out.flush();

            fos.close();

            finish();
        } catch (FileNotFoundException e) {
            createDialogBox("Err: File not found");
        } catch (IOException e) {
            createDialogBox("Err: I/O");
        }
    }


}
