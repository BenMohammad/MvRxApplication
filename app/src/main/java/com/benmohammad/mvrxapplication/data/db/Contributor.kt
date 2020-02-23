package com.benmohammad.mvrxapplication.data.db

import io.requery.Entity
import io.requery.Key

@Entity
interface Contributor {

    val login : String?

    @get:Key
    val id : Int?
}