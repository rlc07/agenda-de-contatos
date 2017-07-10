package com.example.developer.contact.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developer.contact.MainActivity;
import com.example.developer.contact.R;
import com.example.developer.contact.model.Contact;

import java.util.List;

/**
 * Created by Developer on 08/07/2017.
 */

public class ContactAdapter extends BaseAdapter {

    private final List<Contact> contacts;
    private final Context context;

    public ContactAdapter(Context context, List<Contact> listContacts) {

        this.contacts = listContacts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contacts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contact contact = contacts.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.list_item,null);

        TextView item_name = (TextView) view.findViewById(R.id.name_lis);
        item_name.setText(contact.getEdt_name());

        TextView item_phone = (TextView) view.findViewById(R.id.phone_lis);
        item_phone.setText(contact.getEdt_phone());

        ImageView item_img_user = (ImageView) view.findViewById(R.id.img_list);
        String urlImg = contact.getImg_contact();
        if(urlImg != null){
            Bitmap bitMap = BitmapFactory.decodeFile(urlImg);
            Bitmap breduzido = Bitmap.createScaledBitmap(bitMap, 100, 100, true);
            item_img_user.setImageBitmap(breduzido);
            item_img_user.setScaleType(ImageView.ScaleType.FIT_XY);
            item_img_user.setTag(urlImg);
        }
        return view;
    }

}
