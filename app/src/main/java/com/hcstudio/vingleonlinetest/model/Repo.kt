package com.hcstudio.vingleonlinetest.model

import com.google.gson.annotations.SerializedName

/**
 * Created by hochul on 2018-05-13.
 */
//data class Repo(var name: String, var description: String, var star: String, var count: Int)

class Repo (
        val name: String,
        val description: String,
        @SerializedName("stargazers_count") val count: Int
)