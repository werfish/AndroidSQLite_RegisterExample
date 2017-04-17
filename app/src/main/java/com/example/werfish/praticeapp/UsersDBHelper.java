package com.example.werfish.praticeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Werfish on 09.04.2017.
 */
public class UsersDBHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UsersDB.db";
    public static final String USERS_TABLE_NAME = "Users";
    public static final String USERS_COLUMN_ID = "User_ID";
    public static final String USERS_COLUMN_NAME = "Name";
    public static final String USERS_COLUMN_EMAIL = "Email";
    public static final String USERS_COLUMN_PHONE = "Phone";
    public static final String USERS_COLUMN_PASSWORD = "Password";
    public static final String USERS_COLUMN_ENCRYPTED_PASS = "Encrypted_Pass";

    public UsersDBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS Users (User_ID Integer Primary Key,Name VARCHAR, Email VARCHAR, Phone VARCHAR, Password VARCHAR, Encrypted_Pass VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

    public boolean insertUser(String name, String email, String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("password", password);
        contentValues.put("encrypted_pass", encryptPass(password));
        db.insert("Users", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor record =  db.rawQuery( "select * from Users where User_ID="+id+"", null );
        return record;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME);
        return numRows;
    }

    //this will be not needed, did this just for educational purpose (Although maybe later)
    public boolean updateUser (Integer id, String name, String email, String phone, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("password", password);
        db.update("Users", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    //this will be not needed, did this just for educational purpose (Although maybe later could be userfull)
    public Integer deleteUser (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Users", "id = ? ", new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from Users;", null );
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            array_list.add(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_NAME)));
            cursor.moveToNext();
        }
        return array_list;
    }

    public void reset(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM Users;");
    }

    //Function returns True if name exists in the database, Used for data validation
    public Boolean nameExists(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db.rawQuery("SELECT Name FROM Users Where Name='" + name + "';",null).getCount() > 0) {
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    //Function returns True if email exists in the database, Used for data validation
    public Boolean emailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        if(db.rawQuery("SELECT Name FROM Users Where Email='" + email + "';",null).getCount() > 0) {
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    private String encryptPass(String pass){
        StringBuffer sb = new StringBuffer();
        try{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pass.getBytes());
        byte byteData[] = md.digest();


        for(int i = 0; i < byteData.length; i++)
            sb.append(Integer.toString((byteData[i] & 0xFF) + 0x100, 16).substring(1));

        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return sb.toString();
    }
}

