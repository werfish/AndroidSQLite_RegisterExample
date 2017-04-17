package com.example.werfish.praticeapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayUser extends AppCompatActivity {

    private UsersDBHelper db;

    TextView name ;
    TextView email;
    TextView phone;
    TextView pass;
    TextView encrypted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        //Make the query to the db
        db = new UsersDBHelper(this);

        name = (TextView) findViewById(R.id.nameDisplay);
        email = (TextView) findViewById(R.id.emailDisplay);
        phone = (TextView) findViewById(R.id.phoneDisplay);
        pass = (TextView) findViewById(R.id.passDisplay);
        encrypted = (TextView) findViewById(R.id.encryptedDisplay);

        Bundle extras = getIntent().getExtras();
        if(extras !=null){
            int Value = extras.getInt("id");

            Cursor rs = db.getData(Value);
            rs.moveToFirst();

            name.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_NAME)));
            email.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_EMAIL)));
            phone.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_PHONE)));
            pass.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_PASSWORD)));
            encrypted.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_ENCRYPTED_PASS)));

            if (!rs.isClosed())  {
                rs.close();
            }
        }
    }
}
