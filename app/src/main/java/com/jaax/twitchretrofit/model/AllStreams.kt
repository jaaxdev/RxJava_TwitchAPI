package com.jaax.twitchretrofit.model

import com.google.gson.annotations.SerializedName

data class AllStreams(
    @SerializedName(value = "data")
    val streams: List<Stream>,
    val pagination: Pagination
)