package com.benmohammad.mvrxapplication

import android.app.Application
import com.benmohammad.mvrxapplication.data.db.Models
import io.requery.Persistable
import io.requery.android.sqlite.DatabaseSource
import io.requery.reactivex.KotlinReactiveEntityStore
import io.requery.sql.KotlinEntityDataStore
import io.requery.sql.SchemaModifier
import io.requery.sql.TableCreationMode

class App : Application() {

    companion object {
        val dataStore by lazy {
            val source = DatabaseSource(instance.applicationContext, Models.DEFAULT, 1)
            source.setTableCreationMode(TableCreationMode.DROP_CREATE)
            val config = source.configuration

            if(BuildConfig.DEBUG) {
                val modifier = SchemaModifier(config)
                modifier.createTables(TableCreationMode.DROP_CREATE)
            }

            KotlinReactiveEntityStore<Persistable>(KotlinEntityDataStore(config))
        }
        private lateinit var instance :  App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}