package com.pascalhow.marsdiscovery.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

class SchedulersProvider {

    fun io() : Scheduler = Schedulers.io()
    fun mainThread() : Scheduler = AndroidSchedulers.mainThread()!!
    fun testScheduler() : Scheduler = TestScheduler()

}
