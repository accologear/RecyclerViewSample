package xyz.twbkg.recyclerviewsample

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.load_newer_act.*
import xyz.twbkg.recyclerviewsample.model.Content
import java.util.*


class LoadNewerActivity : AppCompatActivity() {

    var visibleThreshold = 5
    var firstItemVisible = 0
    var totalItemCount = 0
    var loading: Boolean = false

    var listData = ArrayList<Content>()
    var mAdapter = DataAdapter()
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.load_newer_act)

        setUpView()
        Log.d("list ", listData.size.toString())
        mAdapter.addItem(initData())
    }

    private fun setUpView() {
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        linearLayoutManager.stackFromEnd = true
        data_new_ry.apply {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
            addOnScrollListener(listener)
            adapter = mAdapter
        }
    }

    private fun initData(): List<Content> {
        return (1..30).map { Content(it, "position") }
    }

    val listener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            totalItemCount = linearLayoutManager.itemCount
            firstItemVisible = linearLayoutManager.findFirstVisibleItemPosition()
            if (!loading
                    && (firstItemVisible - visibleThreshold) <= 5) {
                // End has been reached
                // Do something
                loading = true
                mAdapter.addItem(0, Content(-1, ""))

                Handler().postDelayed({
                    var spare = ArrayList<Content>()

                    val v = recyclerView!!.getChildAt(0)
                    val top = if (v == null) 0 else v.top
                    for (i in 1..30) {
                        spare.add(0, Content(i, "position"))
                    }
                    mAdapter.removeItem(0)
                    mAdapter.addItem(0, spare)

                    linearLayoutManager.scrollToPositionWithOffset(spare.size, top);
                    loading = false
                }, 1500)

            }
        }
    }
}
