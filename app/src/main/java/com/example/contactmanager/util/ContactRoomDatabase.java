package com.example.contactmanager.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.contactmanager.Data.ContactDao;
import com.example.contactmanager.Model.Contact;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;

    public abstract ContactDao contactDao();

    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile ContactRoomDatabase INSTANCE;

//    private static final RoomDatabase.Callback CALLBACK =
//            new RoomDatabase.Callback() {
//                @Override
//                public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                    super.onCreate(db);
//                    EXECUTOR_SERVICE.execute(() -> {
//                        ContactDao contactDao = INSTANCE.contactDao();
//                        contactDao.deleteAllContact();
//
//                        Contact contact = new Contact("Eslam", "eslam@hotmail.com");
//                        contactDao.insert(contact);
//
//                        contact = new Contact("Ahmed", "ahmed@hotmail.com");
//                        contactDao.insert(contact);
//
//                        contact = new Contact("salah", "salah@hotmail.com");
//                        contactDao.insert(contact);
//
//                        contact = new Contact("Foad", "foad@hotmail.com");
//                        contactDao.insert(contact);
//
//                        contact = new Contact("Sarah", "sarah@hotmail.com");
//                        contactDao.insert(contact);
//
//                    });
//                }
//            };

    public static ContactRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ContactRoomDatabase.class, "contact_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
