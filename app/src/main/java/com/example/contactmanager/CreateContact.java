package com.example.contactmanager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.contactmanager.Model.Contact;
import com.example.contactmanager.Model.ContactViewModel;
import com.example.contactmanager.databinding.ActivityCreateContactBinding;

public class CreateContact extends AppCompatActivity {

    private ActivityCreateContactBinding binding;
    private ContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(CreateContact.this.getApplication()).create(ContactViewModel.class);

        binding.saveBtn.setOnClickListener(v -> {

            if (!TextUtils.isEmpty(binding.emailText.getText()) && !TextUtils.isEmpty(binding.nameText.getText())) {

                Contact contact = new Contact(binding.nameText.getText().toString(), binding.nameText.getText().toString());
                viewModel.insert(contact);

            } else {
                Toast.makeText(this, "Please fill the 2 fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}