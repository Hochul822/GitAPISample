package com.hcstudio.vingleonlinetest.api

import com.hcstudio.vingleonlinetest.model.Contributor
import com.hcstudio.vingleonlinetest.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by hochul on 2018-05-13.
 */
interface GithubService {
    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>

    @GET("/repos/{owner}/{repo}/contributors")
    fun repoContributors(@Path("owner") owner: String,
                         @Path("repo") repo: String) : Call<List<Contributor>>
}