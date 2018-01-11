package xyz.twbkg.recyclerviewsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.load_more_act.*
import xyz.twbkg.recyclerviewsample.model.Content

class LoadMoreActivity : AppCompatActivity() {

    var visibleThreshold = 5
    var lastVisibleItem = 0
    var totalItemCount = 0
    var loading: Boolean = false

    var listData = ArrayList<Content>()
    var mAdapter = DataAdapter()
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.load_more_act)

        setUpView()
        Log.d("list ", listData.size.toString())
        mAdapter.addItem(initData())
    }

    private fun setUpView() {
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        data_more_ry.apply {
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
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
            if (!loading
                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                // End has been reached
                // Do something
                loading = true
                mAdapter.addItem(Content(-1, ""))

                Handler().postDelayed({
                    var spare = ArrayList<Content>()
                    (listData.size..20).mapTo(spare) { Content(it, "position") }
                    mAdapter.removeItem(listData.size - 1)

                    mAdapter.addItem(spare)
                    loading = false
                }, 1500)

            }
        }
    }
}
