package com.hcstudio.vingleonlinetest.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.hcstudio.vingleonlinetest.R
import com.hcstudio.vingleonlinetest.adapter.RepositoryAdapter
import com.hcstudio.vingleonlinetest.api.GithubService
import com.hcstudio.vingleonlinetest.data.Repo
import kotlinx.android.synthetic.main.activity_repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class RepositoryActivity : AppCompatActivity() {
    val TAG = "REPO"
    var repoList = ArrayList<Repo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)


        // Layout
        val adapter = RepositoryAdapter(this, repoList);
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL

        repo_recycler_view.layoutManager = manager
        repo_recycler_view.setHasFixedSize(true)
        repo_recycler_view.adapter = adapter


        // Github Repo
        val username = intent.getStringExtra("username")
        Log.i(TAG, "repo user name : " + username)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(GithubService::class.java)
        val repos = service.listRepos("username")

        //repos.enqueue()
        repos.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                Log.i(TAG, "repo ok : " + response?.isSuccessful)

                if (response!!.isSuccessful) {
                    val repo = response.body()
                    repo?.forEach {
                        Log.i(TAG, "repo : " + repo)
                    }
                }
            }

            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                // TODO Failure
                Log.i(TAG, "fail : " + t.toString());
            }
        })

    }




}
