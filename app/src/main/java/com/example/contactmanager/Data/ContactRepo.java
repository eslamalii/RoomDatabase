package com.example.contactmanager.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.contactmanager.Model.Contact;
import com.example.contactmanager.util.ContactRoomDatabase;

import java.util.List;

public class ContactRepo {
    private ContactDao contactDao;
    private LiveData<List<Contact>> data;

    public ContactRepo(Application application) {
        ContactRoomDatabase contactRoom = ContactRoomDatabase.getInstance(application);
        contactDao = contactRoom.contactDao();

        data = contactDao.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts(){ return data;}

    public void insert(Contact contact){
        ContactRoomDatabase.EXECUTOR_SERVICE.execute(() -> contactDao.insert(contact));
    }

    public LiveData<Contact> getContact(int id){
        return contactDao.get(id);
    }

    public void update(Contact contact){
        ContactRoomDatabase.EXECUTOR_SERVICE.execute(() -> contactDao.updateContact(contact));
    }

    public void delete(Contact contact){
        ContactRoomDatabase.EXECUTOR_SERVICE.execute(() -> contactDao.deleteContact(contact));
    }
}
