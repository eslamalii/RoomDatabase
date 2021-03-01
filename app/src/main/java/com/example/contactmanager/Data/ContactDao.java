package com.example.contactmanager.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.contactmanager.Model.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    //CRUD

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Contact contact);

    @Query("DELETE FROM contact_table")
    void deleteAllContact();

    @Query("SELECT * FROM contact_table ORDER BY Name ASC")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT * FROM contact_table WHERE contact_table.id == :id")
    LiveData<Contact> get(int id);

    @Update
    void updateContact(Contact contact);

    @Delete
    void deleteContact(Contact contact);

}
