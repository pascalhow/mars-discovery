package com.pascalhow.marsdiscovery.rest.model

import com.google.gson.annotations.SerializedName

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
