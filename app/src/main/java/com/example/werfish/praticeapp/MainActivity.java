package com.example.werfish.praticeapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final UsersDBHelper db = new UsersDBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing all the text fields and buttons
        final EditText nameField = (EditText) findViewById(R.id.nameTextField);
        Button btnRecords = (Button) findViewById(R.id.button_Records);


        //Insert test records to the database
        if (db.numberOfRows() == 0)
        {
        db.insertUser("Robert Mazurowski","someEmail@gmail.com","0048098141234","Vegetables");
        db.insertUser("Stephen Stvens","stevel@stephen.com","0044806472867","Stv123");
        db.insertUser("Tiger Vrom Woods","tigerFromWoodsl@gmail.com","NONE","Vegetables");
        db.insertUser("AnotherGuy WithTheSamePassword","iHavegenericPassword@gmail.com","0094666666","Vegetables");
        }

        //set onclick listener for the adding button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Boolean validationFailed = Boolean.FALSE;
                String errorMessage = null;

                String name = nameField.getText().toString();

                //Perform several validation checks
                if(isNameEmpty(name)){
                    validationFailed = true;
                    errorMessage = "The name is empty!";
                }else if(db.nameExists(name)){
                    validationFailed = true;
                    errorMessage = "This name exists in the database!";
                }else if(isEmailEmpty(email)){
                    validationFailed = true;
                    errorMessage = "The email is empty!";
                }else if(db.emailExists(email)){
                    validationFailed = true;
                    errorMessage = "This email is already registered!";
                }else if(isPasswordEmpty(password)){
                    validationFailed = true;
                    errorMessage = "The password is empty!";
                }else if(!isPasswordsMatch(password,repeatPass)){
                    validationFailed = true;
                    errorMessage = "The repeated password does not match with original!";
                }

                if(validationFailed == Boolean.TRUE) {
                    //Toast with the error message
                    Toast errorToast = Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT);
                    errorToast.setGravity(Gravity.CENTER, 0, 0);
                    errorToast.show();
                }else{

                    clearFields();

                    Toast errorToast = Toast.makeText(getApplicationContext(), "The record has been added to the database", Toast.LENGTH_SHORT);
                    errorToast.setGravity(Gravity.CENTER, 0, 0);
                    errorToast.show();
                }

            }

            private void clearFields(){
                nameField.setText("");
                emailField.setText("");
                phoneField.setText("");
                passField.setText("");
                repeatPassField.setText("");
            }

            private Boolean isNameEmpty(String name) {
                //checks if the password is empty
                if(name.equals("")){
                    return true;
                }else{
                    return false;
                }
            }

            private Boolean isPasswordEmpty(String password) {
                //checks if the password is empty
                if(password.equals("")){
                    return true;
                }else{
                    return false;
                }
            }

            private Boolean isEmailEmpty(String email){
                //checks if email is Empty
                if(email.equals("")){
                    return true;
                }else{
                    return false;
                }
            }

            private Boolean isPasswordsMatch(String pass,String repeat){
                if(pass.equals(repeat)){
                    return true;
                }else{
                    return false;
                }
            }
        });

        btnRecords.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UsersActivity.class);
                startActivity(intent);
                clearFields();
            }

            private void clearFields(){

            }
        });


    }
    
}
