package com.pascalhow.marsdiscovery.rest.model

import com.pascalhow.marsdiscovery.data.model.MarsFootage
import com.pascalhow.marsdiscovery.data.repo.MarsDataStore.Companion.NEW_TIME_FORMAT
import com.pascalhow.marsdiscovery.data.repo.MarsDataStore.Companion.OLD_TIME_FORMAT
import com.pascalhow.marsdiscovery.extensions.changeFormat

class MarsFootageEntity(private val item: Items) {

    fun toMarsFootage() =
        MarsFootage(
            item.links!![0].href,
            item.data!![0].description,
            item.data!![0].dateCreated!!.changeFormat(
                OLD_TIME_FORMAT,
                NEW_TIME_FORMAT
            )
        )
}
