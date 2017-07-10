package com.example.developer.contact.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.developer.contact.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 08/07/2017.
 */

public class ContactDAO extends SQLiteOpenHelper {

    //String name_database = "ContactSchedule";

    public ContactDAO(Context context) {
        super(context, "ContactSchedule" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE Contact (" +
                "id Integer PRIMARY KEY," +
                "name TEXT,    " +
                "email TEXT,   " +
                "phone TEXT,   " +
                "url_img TEXT, " +
                "address TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int version) {

    }

    public void save (Contact contact){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getContentValues(contact);

        db.insert("Contact",null,data);
    }

    public void update (Contact contact){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getContentValues(contact);
        String [] params = {contact.getId().toString()};
        db.update("Contact",data,"id=?",params);
    }

    @NonNull
    private ContentValues getContentValues(Contact contact) {
        ContentValues data = new ContentValues();
        data.put("url_img", contact.getImg_contact());
        data.put("Name", contact.getEdt_name());
        data.put("Email", contact.getEdt_email());
        data.put("Phone", contact.getEdt_phone());
        data.put("Url_Img", contact.getImg_contact());
        data.put("Address", contact.getEdt_address());
        return data;
    }

    public List<Contact> AllListContact(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM Contact";

        List<Contact> contactList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex("id")));
            contact.setEdt_name(cursor.getString(cursor.getColumnIndex("name")));
            contact.setEdt_email(cursor.getString(cursor.getColumnIndex("email")));
            contact.setEdt_address(cursor.getString(cursor.getColumnIndex("address")));
            contact.setImg_contact(cursor.getString(cursor.getColumnIndex("url_img")));
            contact.setEdt_phone(cursor.getString(cursor.getColumnIndex("phone")));
            contactList.add(contact);
        }
      cursor.close();
    return contactList;
    }

    public void delete(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        String [] p = {contact.getId().toString()};

        Long id = contact.getId();


        db.delete("Contact","id=?",p);
    }
}
