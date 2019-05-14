package com.pascalhow.planetmars.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.pascalhow.planetmars.data.model.MarsFootage

@Database(entities = [MarsFootage::class], version = 1)
abstract class MarsFootageDatabase : RoomDatabase() {

    abstract fun marsFootageDao(): MarsFootageDao

    companion object {
        private val lock = Any()
        private const val DB_NAME = "People.db"
        private var INSTANCE: MarsFootageDatabase? = null

        fun getInstance(context: Context): MarsFootageDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, MarsFootageDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
