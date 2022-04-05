package com.atak4n.rickandmortyRx.service

import com.atak4n.rickandmortyRx.model.CharacterModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterAPI {
    /*@GET("character")
    fun getData(
        @Query("page") pageIndex: Int
    ): Observable<List<CharacterModel>>*/

    @GET("character/{character-id}")
    fun getData(): Observable<List<CharacterModel>>

}

