package com.pascalhow.marsdiscovery.data.repo

import android.arch.lifecycle.LiveData
import com.pascalhow.marsdiscovery.data.model.MarsFootage
import com.pascalhow.marsdiscovery.extensions.toLiveData

class MarsRepository(private val marsDataStoreFactory: MarsDataStoreFactory) {

    fun getFootage(planet: String, mediaType: String): LiveData<List<MarsFootage>> {
        val marsDataStore = marsDataStoreFactory.create()
        return marsDataStore.getFootage(planet, mediaType).toLiveData()
    }
}
