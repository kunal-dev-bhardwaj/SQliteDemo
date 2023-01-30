package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sqlitedemo.data.MyDbHandler;
import com.example.sqlitedemo.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        MyDbHandler db = new MyDbHandler(MainActivity.this); //creating obj of handler class
        //creating a contact

//        Contact user1 = new Contact();
//        user1.setPhoneNumber("9987654120");
//        user1.setName("kunal");
//
//        //adding a contact to the db
//        db.addContact(user1);
//
//        Contact user2 = new Contact();
//        user2.setName("yashu");
//        user2.setPhoneNumber("987766544");
//        db.addContact(user2);
//
//        //updating
//        user2.setId(34);
//        user2.setName("changed yashu");
//        user2.setPhoneNumber("00000000");
//      int update=  db.updateContact(user2);
//        Log.d("db_check","affected rows :"+update);
//
//        Log.d("db_check", "contact added successfully");
        ArrayList<String> contacts = new ArrayList<>(); //creating array list for adding data

        //getting all contacts
        List<Contact> list = db.getAllContacts(); //creating list for getting data
        //using for loop for accessing data which is present in the list
//        db.deleteContacts(2);
//        db.deleteContacts(12);
        for (Contact contact : list) {

            Log.d("db_check", "id: " + contact.getId() + "\n"
                    + "phone number: " + contact.getPhoneNumber() + "\n"
                    + "name: " + contact.getName() + "\n");
            contacts.add(contact.getName() + "(" + contact.getPhoneNumber() + ")"); //data is added into array list


        }

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,contacts);
        //array or array list req, so contacts is passed here
//        Log.d("db_check", "total contact: " + db.getCount());,
        listView.setAdapter(arrayAdapter); //setting list view in array adapter


    }
}