package com.example.developer.contact.model;

import android.widget.EditText;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Developer on 08/07/2017.
 */

public class Contact implements Serializable {

    private Long id;
    private String edt_name;
    private String edt_email;
    private String edt_phone;
    private String edt_address;
    private String img_contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEdt_name() {
        return edt_name;
    }

    public void setEdt_name(String edt_name) {
        this.edt_name = edt_name;
    }

    public String getEdt_email() {
        return edt_email;
    }

    public void setEdt_email(String edt_email) {
        this.edt_email = edt_email;
    }

    public String getEdt_phone() {
        return edt_phone;
    }

    public void setEdt_phone(String edt_phone) {
        this.edt_phone = edt_phone;
    }

    public String getEdt_address() {
        return edt_address;
    }

    public void setEdt_address(String edt_address) {
        this.edt_address = edt_address;
    }

    public String getImg_contact() {
        return img_contact;
    }

    public void setImg_contact(String img_contact) {
        this.img_contact = img_contact;
    }

    public String toString() {
        return edt_name;
    }
}
