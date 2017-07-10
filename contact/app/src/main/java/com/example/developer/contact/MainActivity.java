package com.example.developer.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.developer.contact.adapter.ContactAdapter;
import com.example.developer.contact.convert.ContactConverter;
import com.example.developer.contact.dao.ContactDAO;
import com.example.developer.contact.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_new_contact;
    private ListView list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Preenche Lista de contatos
        list_view = (ListView) findViewById(R.id.list_contact);

        //Vai pra tela de cadastro de contato
        btn_new_contact = (Button) findViewById(R.id.btn_add_new_contact);
        btn_new_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivity(intent);
            }
        });

        //Vai pra tela de editar os dados
        list_view.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = (Contact) list_view.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        loadListContact();
        super.onResume();
    }

    private void loadListContact() {
        ContactDAO dao = new ContactDAO(this);
        List<Contact> listContacts = dao.AllListContact();

        ContactAdapter adapter = new ContactAdapter(this, listContacts);
        list_view.setAdapter(adapter);

        registerForContextMenu(list_view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Contact contact = (Contact) list_view.getItemAtPosition(info.position);

        //Deletar
        MenuItem deleteMenu = menu.add("Deletar");
        deleteMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ContactDAO dao = new ContactDAO(MainActivity.this);
                dao.delete(contact);
                dao.close();
                loadListContact();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu_up:
                ContactDAO dao = new ContactDAO(this);
                List<Contact> list = dao.AllListContact();
                ContactConverter convert = new ContactConverter();
                //String json = convert.convertJSON(list);
                Toast.makeText(MainActivity.this, "Clikado", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
