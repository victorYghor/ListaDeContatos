package com.example.contacts.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contacts.Contact

@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase: RoomDatabase() {

    abstract val dao: ContactDao
}