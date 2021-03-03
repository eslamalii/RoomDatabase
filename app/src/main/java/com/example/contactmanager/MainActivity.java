package com.example.contactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.contactmanager.Model.Contact;
import com.example.contactmanager.Model.ContactViewModel;
import com.example.contactmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    private ActivityMainBinding binding;
    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(ContactViewModel.class);

        contactViewModel.getContacts().observe(this, contacts -> Log.d("TAG", "onChanged: " + contacts.get(0).getName()));

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

            binding.test.setText(name);
        }
    }
}

