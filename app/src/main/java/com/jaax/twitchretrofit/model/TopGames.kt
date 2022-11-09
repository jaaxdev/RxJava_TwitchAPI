package com.jaax.twitchretrofit.model

import com.google.gson.annotations.SerializedName

data class TopGames(
    @SerializedName(value = "data")
    val games: List<Game>,
    val pagination: Pagination
)