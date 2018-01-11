package xyz.twbkg.recyclerviewsample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import xyz.twbkg.recyclerviewsample.holder.ItemViewHolder
import xyz.twbkg.recyclerviewsample.holder.LoadingViewHolder
import xyz.twbkg.recyclerviewsample.model.Content

/**
 * Created by ZERO-TWO on 1/8/2018.
 */
class DataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_TYPE = 0
    val LOADING_TYPE = 1

    var data = arrayListOf<Content>()

    fun addItem(position: Int, items: List<Content>) {
        data.addAll(position, items)
        notifyDataSetChanged()
    }

    fun addItem(items: List<Content>) {
        val size = data.size
        data.addAll(items)
        notifyItemRangeInserted(size, itemCount)
    }

    fun addItem(position: Int, items: Content) {
        data.add(position, items)
        notifyItemInserted(itemCount)
    }

    fun addItem(items: Content) {
        data.add(items)
        notifyItemInserted(itemCount)
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(itemCount)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ItemViewHolder) {
            holder.setUp(data[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE) {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_ly, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.loading_ly, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].position == -1)
            LOADING_TYPE
        else ITEM_TYPE
    }
}