package com.benmohammad.mvrxapplication.data.networking

import com.benmohammad.mvrxapplication.data.networking.models.ContributorResponseModel
import io.reactivex.Observable
import retrofit2.http.GET

interface APIService {

    @GET("/repos/airbnb/MvRx/contributors")
    fun fetchRepos(): Observable<List<ContributorResponseModel>>
}