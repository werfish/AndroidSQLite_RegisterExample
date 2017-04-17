package com.example.werfish.praticeapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayUser extends AppCompatActivity {

    private UsersDBHelper db;

    TextView id;
    TextView name ;
    TextView email;
    TextView phone;
    TextView plainPass;

    TextView salt;
    TextView passWithSalt;
    TextView encryptedNoSalt;
    TextView encryptedWithSalt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        //Make the query to the db
        db = new UsersDBHelper(this);

        id = (TextView) findViewById(R.id.idDisplay);
        name = (TextView) findViewById(R.id.nameDisplay);
        email = (TextView) findViewById(R.id.emailDisplay);
        phone = (TextView) findViewById(R.id.phoneDisplay);
        plainPass = (TextView) findViewById(R.id.passwordDisplay);

        salt = (TextView) findViewById(R.id.saltDisplay);
        passWithSalt = (TextView) findViewById(R.id.passWithSaltDisplay);
        encryptedNoSalt = (TextView) findViewById(R.id.hashNoSaltDisplay);
        encryptedWithSalt = (TextView) findViewById(R.id.hashWithSaltDisplay);

        Bundle extras = getIntent().getExtras();
        if(extras !=null){
            int Value = extras.getInt("id");

            Cursor rs = db.getData(Value);
            rs.moveToFirst();

            id.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_ID)));
            name.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_NAME)));
            email.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_EMAIL)));
            phone.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_PHONE)));
            plainPass.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_PASSWORD)));

            salt.setText(rs.getBlob(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_SALT)).toString());
            passWithSalt.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_AFTER_SALT)));
            encryptedNoSalt.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_ENCRYPTED_NOSALT)));
            encryptedWithSalt.setText(rs.getString(rs.getColumnIndex(UsersDBHelper.USERS_COLUMN_ENCRYPTED_PASS)));

            if (!rs.isClosed())  {
                rs.close();
            }
        }
    }
}
