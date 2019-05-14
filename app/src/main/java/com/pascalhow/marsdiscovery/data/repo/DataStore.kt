package com.pascalhow.planetmars.data.repo

import android.content.Context
import com.pascalhow.planetmars.activities.extensions.changeFormat
import com.pascalhow.planetmars.data.model.MarsFootage
import com.pascalhow.planetmars.data.db.MarsFootageDao
import com.pascalhow.planetmars.data.db.MarsFootageDatabase
import com.pascalhow.planetmars.rest.network.RestClient
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

abstract class MarsDataStore(context: Context) {

    val marsFootageList = mutableListOf<MarsFootage>()
    val marsFootageDao: MarsFootageDao

    init {
        val marsFootageDatabase = MarsFootageDatabase.getInstance(context)
        marsFootageDao = marsFootageDatabase.marsFootageDao()
    }

    abstract fun getFootage(planet: String, mediaType: String): Flowable<List<MarsFootage>>

    companion object {
        const val NEW_TIME_FORMAT = "dd MMM yyyy"
        const val OLD_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
    }
}

class DiskMarsDataStore(context: Context) : MarsDataStore(context) {

    override fun getFootage(planet: String, mediaType: String): Flowable<List<MarsFootage>> {
        return marsFootageDao.getAll()
    }
}

class CloudMarsDataStore(context: Context, private val restClient: RestClient) : MarsDataStore(context) {

    override fun getFootage(planet: String, mediaType: String): Flowable<List<MarsFootage>> {
        return restClient.search(planet, mediaType)
            .map { result ->
                Arrays.asList(result.collection?.items)
                result.collection?.items?.forEach { item ->
                    marsFootageList.add(
                        MarsFootage(
                            item.links!![0].href,
                            item.data!![0].description,
                            item.data!![0].dateCreated!!.changeFormat(
                                OLD_TIME_FORMAT,
                                NEW_TIME_FORMAT
                            )
                        )
                    )
                }
                marsFootageDao.insert(marsFootageList)
                marsFootageList.toList()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable(BackpressureStrategy.LATEST)
    }
}
