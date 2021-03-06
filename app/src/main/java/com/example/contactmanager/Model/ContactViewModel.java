package com.example.contactmanager.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.contactmanager.Data.ContactRepo;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    public static ContactRepo contactRepo;
    public final LiveData<List<Contact>> contacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactRepo = new ContactRepo(application);
        contacts = contactRepo.getAllContacts();
    }

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public void insert(Contact contact) {
        contactRepo.insert(contact);
    }

    public void deleteAllContacts() {
        contactRepo.deleteAllContacts();
    }
}
