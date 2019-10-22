package com.pascalhow.marsdiscovery.data.repo

import android.accounts.NetworkErrorException
import android.content.Context
import com.pascalhow.marsdiscovery.data.db.MarsFootageDao
import com.pascalhow.marsdiscovery.data.db.MarsFootageDatabase
import com.pascalhow.marsdiscovery.data.model.MarsFootage
import com.pascalhow.marsdiscovery.rest.model.Items
import com.pascalhow.marsdiscovery.rest.model.MarsSearchResults
import com.pascalhow.marsdiscovery.rest.network.RestClient
import retrofit2.Call

abstract class MarsDataStore(context: Context) {

    val marsFootageDao: MarsFootageDao

    init {
        val marsFootageDatabase = MarsFootageDatabase.getInstance(context)
        marsFootageDao = marsFootageDatabase.marsFootageDao()
    }

    abstract fun getFootage(planet: String, mediaType: String): List<MarsFootage>

    companion object {
        const val NEW_TIME_FORMAT = "dd MMM yyyy"
        const val OLD_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    }
}

class DiskMarsDataStore(context: Context) : MarsDataStore(context) {

    override fun getFootage(planet: String, mediaType: String): List<MarsFootage> {
        return marsFootageDao.getAll()
    }
}

class CloudMarsDataStore(context: Context, private val restClient: RestClient) :
    MarsDataStore(context) {

    override fun getFootage(planet: String, mediaType: String): List<MarsFootage> {
        return request(
            restClient.search(planet, mediaType), emptyList()
        ) { itemList ->
            itemList.map { it.toMarsFootage() }.also { marsFootageDao.insert(it) }
        }
    }

    private fun request(
        call: Call<MarsSearchResults>,
        default: List<Items>,
        transform: (List<Items>) -> List<MarsFootage>
    ): List<MarsFootage> {

        val response = call.execute()

        return when (response.isSuccessful) {
            true -> {
                val items = response.body()?.collection?.items?.toList()
                transform(items ?: default)
            }
            false -> throw NetworkErrorException()
        }
    }
}
