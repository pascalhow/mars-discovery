package com.pascalhow.marsdiscovery.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class MarsFootage(
    var imageUrl: String?,
    var description: String?,
    var date: String?,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
