package xyz.twbkg.recyclerviewsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_act.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)

        loadmore_button.setOnClickListener {
            startActivity(Intent(this, LoadMoreActivity::class.java))
        }
        load_new_button.setOnClickListener {
            startActivity(Intent(this, LoadNewerActivity::class.java))
        }
    }
}
