package com.hcstudio.vingleonlinetest.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.hcstudio.vingleonlinetest.R
import com.hcstudio.vingleonlinetest.adapter.RepositoryAdapter
import com.hcstudio.vingleonlinetest.api.GithubService
import com.hcstudio.vingleonlinetest.model.Contributor
import com.hcstudio.vingleonlinetest.model.Repo
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

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(GithubService::class.java)

        val repos = service.listRepos(username)

        showUserRepos(repos)

        showContributors(service)
    }

    private fun showUserRepos(repos: Call<List<Repo>>) {
        repos.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {

                Log.i(TAG, "repo msg : " + response?.message())

                response?.let {
                    if (response.isSuccessful) {

                        val list = response?.body()
                        list?.forEach {
                            Log.i(TAG, "user : " + it.toString())
                            repoList.add(it)
                        }

                        repo_recycler_view.adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                // TODO Failure
                Log.i(TAG, "fail : " + t.toString());
            }
        })
    }

    private fun showContributors(service: GithubService) {
        val contributors = service.repoContributors("square", "retrofit")

        contributors.enqueue(object : Callback<List<Contributor>> {
            override fun onResponse(call: Call<List<Contributor>>?, response: Response<List<Contributor>>?) {
                val list = response?.body()
                list?.forEach {
                    Log.i(TAG, it.toString())
                }
            }

            override fun onFailure(call: Call<List<Contributor>>?, t: Throwable?) {

            }
        })
    }


}
