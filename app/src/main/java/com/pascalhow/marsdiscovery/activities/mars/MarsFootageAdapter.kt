package com.pascalhow.marsdiscovery.activities.mars

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pascalhow.marsdiscovery.R
import com.pascalhow.marsdiscovery.data.model.MarsFootage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.mars_footage_item.view.mars_date
import kotlinx.android.synthetic.main.mars_footage_item.view.mars_description
import kotlinx.android.synthetic.main.mars_footage_item.view.mars_image

class MarsFootageAdapter(
    private val items: List<MarsFootage>
) : RecyclerView.Adapter<MarsFootageAdapter.MarsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mars_footage_item, parent, false)

        return MarsViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(marsFootage: MarsFootage) = with(itemView) {
            Picasso.get().load(marsFootage.imageUrl).into(this.mars_image)
            mars_description.text = marsFootage.description
            mars_date.text = marsFootage.date
        }
    }

}
