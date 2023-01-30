package com.example.sqlitedemo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sqlitedemo.model.Contact;
import com.example.sqlitedemo.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY, " + Params.KEY_NAME
                + " TEXT, " + Params.KEY_PHONE + " TEXT" + ")";
        Log.d("db_check", "Query bieng run is :" + create);
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Contact contact) { //this method is used for inserting data into that table
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhoneNumber());

        db.insert(Params.TABLE_NAME, null, values);
        Log.d("db_check", "successfully inserted");
        db.close();

    }

    public List<Contact> getAllContacts() { //fun to get contacts
        List<Contact> contactList = new ArrayList<>(); //creating array list to store data into list
        SQLiteDatabase db = this.getReadableDatabase(); //creating db obj to read data
        String select = "SELECT * FROM " + Params.TABLE_NAME; //generating query to select db

        //cursor is used to navigate into db table
        Cursor cursor = db.rawQuery(select, null); //creating cursor obj and passing query into cursor
        if(cursor.moveToFirst()){
            do { //using do while loop because first it will intialise then condition will check
                Contact contact= new Contact(); //creating obj of contact because we need to set data
                contact.setId(Integer.parseInt(cursor.getString(0))); //get values from cursor and setting data into contact model
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //this will move column wise 0,1,2
                contactList.add(contact); // in last adding all contact into list

            }while (cursor.moveToNext()); //checking condition here
        }


        return contactList;
    }

    public int updateContact(Contact contact){ //this obj is passed in this fun because we want get name from contact
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Params.KEY_NAME, contact.getName()); //putting value of name
        values.put(Params.KEY_PHONE, contact.getPhoneNumber()); //putting value of phone no
        //update here
        return db.update(Params.TABLE_NAME,values,Params.KEY_ID+"=?"
                ,new String[]{String.valueOf(contact.getId())}); //update query

    }
    public void deleteContacts(int id){ //we have taken id here because we need a parameter inside this
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ID+"=?",new String []{String.valueOf(id)});
        db.close(); //this is mandatory
    }
    public int getCount(){
        String query="SELECT * FROM "+Params.TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();//because we are not doing any change in db.
        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();



    }





}
