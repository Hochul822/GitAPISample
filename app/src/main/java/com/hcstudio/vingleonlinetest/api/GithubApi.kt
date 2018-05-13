package com.hcstudio.vingleonlinetest.api

import com.hcstudio.vingleonlinetest.data.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by hochul on 2018-05-13.
 */
public interface GithubService {
    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>
}