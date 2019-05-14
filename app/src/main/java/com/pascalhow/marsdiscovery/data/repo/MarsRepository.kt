package com.pascalhow.planetmars.data.repo

import android.arch.lifecycle.LiveData
import com.pascalhow.planetmars.activities.extensions.toLiveData
import com.pascalhow.planetmars.data.model.MarsFootage

class MarsRepository(private val marsDataStoreFactory: MarsDataStoreFactory) {

    fun getFootage(planet: String, mediaType: String): LiveData<List<MarsFootage>> {
        val marsDataStore = marsDataStoreFactory.create()
        return marsDataStore.getFootage(planet, mediaType).toLiveData()
    }
}
