package com.pascalhow.marsdiscovery.data.repo

import com.pascalhow.marsdiscovery.data.model.MarsFootage

class MarsRepository(private val marsDataStoreFactory: MarsDataStoreFactory) {

    fun getFootage(planet: String, mediaType: String): List<MarsFootage> {
        val marsDataStore = marsDataStoreFactory.create()
        return marsDataStore.getFootage(planet, mediaType)
    }
}
