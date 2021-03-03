package com.example.contactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.contactmanager.Model.ContactViewModel;
import com.example.contactmanager.databinding.ActivityCreateContactBinding;

public class CreateContact extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String EMAIL = "email";

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
            Intent intent = new Intent();

            if (!TextUtils.isEmpty(binding.emailText.getText()) && !TextUtils.isEmpty(binding.nameText.getText())) {

                String name = binding.nameText.getText().toString();
                String email = binding.nameText.getText().toString();

                intent.putExtra(NAME, name);
                intent.putExtra(EMAIL, email);
                setResult(RESULT_OK, intent);



            } else {
                setResult(RESULT_CANCELED, intent);
            }
            finish();
        });
    }
}