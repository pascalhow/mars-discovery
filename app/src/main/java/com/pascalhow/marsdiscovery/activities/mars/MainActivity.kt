package com.pascalhow.marsdiscovery

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.pascalhow.marsdiscovery.activities.mars.MarsFootageAdapter
import com.pascalhow.marsdiscovery.activities.mars.MarsViewModel
import com.pascalhow.marsdiscovery.activities.mars.MarsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MarsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkStatusProvider = NetworkStatusProvider(applicationContext)
        val repository = MarsRepository(
            MarsDataStoreFactory(
                applicationContext,
                networkStatusProvider
            )
        )
        val viewModelFactory = MarsViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MarsViewModel::class.java)

        viewModel.getMarsFootageList().observe(this, Observer { marsFootageList ->
            marsFootageList?.let {
                populateMarsFootageList(marsFootageList)
            }
        })
    }

    private fun populateMarsFootageList(list: List<MarsFootage>) {
        recyclerView.adapter = MarsFootageAdapter(list)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}
