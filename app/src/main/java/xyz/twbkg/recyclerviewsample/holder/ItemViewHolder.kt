package xyz.twbkg.recyclerviewsample.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_ly.*
import xyz.twbkg.recyclerviewsample.model.Content

/**
 * Created by ZERO-TWO on 1/8/2018.
 */
class ItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun setUp(content: Content) {
        message_tv.text = "${content.message} ${adapterPosition}"
    }
}