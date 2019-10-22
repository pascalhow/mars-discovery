package com.pascalhow.marsdiscovery.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.pascalhow.marsdiscovery.data.model.MarsFootage

@Dao
interface MarsFootageDao {

    @Query("SELECT * FROM MarsFootage ORDER BY id DESC")
    fun getAll(): List<MarsFootage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(marsFootage: List<MarsFootage>)
}
