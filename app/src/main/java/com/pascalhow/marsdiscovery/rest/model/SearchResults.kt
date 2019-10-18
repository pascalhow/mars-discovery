package com.pascalhow.marsdiscovery.rest.model

import com.google.gson.annotations.SerializedName
import com.pascalhow.marsdiscovery.data.model.MarsFootage
import com.pascalhow.marsdiscovery.data.repo.MarsDataStore
import com.pascalhow.marsdiscovery.extensions.changeFormat

class MarsSearchResults {

    @SerializedName("collection")
    var collection: Collection? = null

    override fun toString(): String {
        return "ClassPojo [collection = $collection]"
    }
}

class Collection {

    @SerializedName("items")
    var items: Array<Items>? = null

    override fun toString(): String {
        return "ClassPojo [items = $items]"
    }
}

class Items {

    @SerializedName("data")
    var data: Array<Data>? = null

    @SerializedName("links")
    var links: Array<Links>? = null

    fun toMarsFootage() =
        MarsFootage(
            links!![0].href,
            data!![0].description,
            data!![0].dateCreated!!.changeFormat(
                MarsDataStore.OLD_TIME_FORMAT,
                MarsDataStore.NEW_TIME_FORMAT
            )
        )

    override fun toString(): String {
        return "ClassPojo [data = $data, links = $links]"
    }
}

class Links {

    @SerializedName("href")
    var href: String? = null

    override fun toString(): String {
        return "ClassPojo [href = $href]"
    }
}

class Data {

    @SerializedName("date_created")
    var dateCreated: String? = null

    @SerializedName("description_508")
    var description: String? = null

    override fun toString(): String {
        return "ClassPojo [date_created = $dateCreated, description_508 = $description]"
    }
}
