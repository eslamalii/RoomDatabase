package com.example.contactmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.contactmanager.Model.Contact;
import com.example.contactmanager.Model.ContactViewModel;
import com.example.contactmanager.adapter.RecyclerViewAdapter;
import com.example.contactmanager.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnContactClickListener {

    public static final int REQUEST_CODE = 1;
    private static final String TAG = "TAG";
    private ActivityMainBinding binding;
    private ContactViewModel contactViewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Contact> contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(ContactViewModel.class);

        contactViewModel.getContacts().observe(this, contacts -> {


            //Setup Adapter
            recyclerViewAdapter = new RecyclerViewAdapter(contacts, this,this);
            binding.recyclerView.setAdapter(recyclerViewAdapter);

        });


        binding.addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateContact.class);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra(CreateContact.NAME);
            String email = data.getStringExtra(CreateContact.EMAIL);

            Contact contact = new Contact(name, email);

            contactViewModel.insert(contact);
        }
    }

    @Override
    public void onContactClick(int position) {
        Contact contact = contactViewModel.contacts.getValue().get(position);
        Log.d(TAG, "onContentClick: " + contact.getName());
    }
}

