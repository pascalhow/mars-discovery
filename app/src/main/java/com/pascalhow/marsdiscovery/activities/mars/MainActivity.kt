package com.pascalhow.marsdiscovery.activities.mars

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.pascalhow.marsdiscovery.R
import com.pascalhow.marsdiscovery.data.model.MarsFootage
import com.pascalhow.marsdiscovery.data.repo.MarsDataStoreFactory
import com.pascalhow.marsdiscovery.data.repo.MarsRepository
import com.pascalhow.marsdiscovery.utils.NetworkStatusProvider
import kotlinx.android.synthetic.main.activity_main.mars_footage_recycler_view as recyclerView

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
