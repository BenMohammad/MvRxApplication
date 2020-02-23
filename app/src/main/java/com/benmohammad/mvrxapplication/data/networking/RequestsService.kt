package com.benmohammad.mvrxapplication.data.networking

import android.annotation.SuppressLint
import android.util.Log
import com.benmohammad.mvrxapplication.App
import com.benmohammad.mvrxapplication.data.db.ContributorEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RequestsService {

    @SuppressLint("CheckResult")
    fun getRepos() {
        val retrofit = RetrofitService.getRetrofit()
        val mApiService: APIService = retrofit.create(APIService::class.java)

        mApiService.fetchRepos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response -> response.map {
                val contributor = ContributorEntity()

                contributor.setId(it.id)
                contributor.setLogin(it.login)

                App

                    .dataStore
                    .insert(contributor)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("Insert", it.toString())
                    }, Throwable::printStackTrace)
            }
            }, Throwable::printStackTrace)
    }
}