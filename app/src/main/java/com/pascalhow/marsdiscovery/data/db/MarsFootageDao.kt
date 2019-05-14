package com.pascalhow.planetmars.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.pascalhow.planetmars.data.model.MarsFootage
import io.reactivex.Flowable

@Dao
interface MarsFootageDao {

    @Query("SELECT * FROM MarsFootage ORDER BY id DESC")
    fun getAll(): Flowable<List<MarsFootage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(marsFootage: List<MarsFootage>)
}
