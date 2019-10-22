package com.pascalhow.marsdiscovery.domain

import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {

    private var parentJob = Job()
    private var coroutineScope = CoroutineScope(parentJob + Dispatchers.Main)

    abstract suspend fun run(params: Params): Type

    fun execute(params: Params, onResult: (Type) -> Unit = {}) {
        val job = coroutineScope.async(Dispatchers.IO) { run(params) }
        coroutineScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    fun cleanUp() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    object None
}
