package com.example.developer.contact;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.developer.contact.model.Contact;

/**
 * Created by Developer on 08/07/2017.
 */

public class NewContactHelper {

     EditText edt_name;
     EditText edt_email;
     EditText edt_phone;
     EditText edt_address;
     ImageView img_contact;

    private Contact contact;

    public NewContactHelper(NewContactActivity ncActivity){

        edt_name = (EditText) ncActivity.findViewById(R.id.new_contact_name);
        edt_email = (EditText) ncActivity.findViewById(R.id.new_contact_email);
        edt_phone = (EditText) ncActivity.findViewById(R.id.new_contact_phone);
        edt_address = (EditText) ncActivity.findViewById(R.id.new_contact_street);
        img_contact = (ImageView) ncActivity.findViewById(R.id.img_contact);
        contact = new Contact();
    }

    public Contact pickUpData(){

      contact.setEdt_name(edt_name.getText().toString());
      contact.setEdt_email(edt_email.getText().toString());
      contact.setEdt_address(edt_address.getText().toString());
      contact.setEdt_phone(edt_phone.getText().toString());
      contact.setImg_contact((String) img_contact.getTag());

        return contact;
    }

    public void fillOutForm(Contact contact){
        edt_name.setText(contact.getEdt_name());
        edt_address.setText(contact.getEdt_address());
        edt_email.setText(contact.getEdt_email());
        edt_phone.setText(contact.getEdt_phone());
        loadImg(contact.getImg_contact());
        this.contact=contact;
    }

    public void loadImg(String urlImg){

        if(urlImg != null){
            Bitmap bitMap = BitmapFactory.decodeFile(urlImg);
            Bitmap breduzido = Bitmap.createScaledBitmap(bitMap, 300, 300, true);
            img_contact.setImageBitmap(breduzido);
            img_contact.setScaleType(ImageView.ScaleType.FIT_XY);
            img_contact.setTag(urlImg);
        }

    }
}
