package com.example.developer.contact;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.developer.contact.dao.ContactDAO;
import com.example.developer.contact.model.Contact;

import java.io.File;

public class NewContactActivity extends AppCompatActivity {

    private NewContactHelper helperContact;
    private String urlImg;
    public static final int CAMERA_CODIGO = 545;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        helperContact = new NewContactHelper(this);

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        if(contact != null){
            helperContact.fillOutForm(contact);
        }

        Button btnCamera = (Button) findViewById(R.id.btn_photo);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                urlImg = getExternalFilesDir(null) + "/"+System.currentTimeMillis()+".jpg";
                File foto = new File(urlImg);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
                startActivityForResult(intentCamera, CAMERA_CODIGO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CAMERA_CODIGO){

                helperContact.loadImg(urlImg);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_new_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_id_ok:

                ContactDAO dao = new ContactDAO(this);
                Contact contact = helperContact.pickUpData();

                if(contact.getId() != null){
                    dao.update(contact);
                    Toast.makeText(this, "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    dao.save(contact);
                    Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
