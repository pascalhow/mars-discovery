package com.pascalhow.marsdiscovery.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import org.reactivestreams.Publisher

fun <T : Any> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this)

fun <T : Any> LiveData<T>.toFlowable(lifecycleOwner: LifecycleOwner) =
    LiveDataReactiveStreams.toPublisher(lifecycleOwner, this)
