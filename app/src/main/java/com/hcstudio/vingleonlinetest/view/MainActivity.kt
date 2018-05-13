package com.hcstudio.vingleonlinetest.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hcstudio.vingleonlinetest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repo_link.setOnClickListener {
            val intent = Intent(this, RepositoryActivity::class.java)
            var repo = repo_link.text
            val split = repo.split('/')
            val username = split[split.size - 1]
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }
}
