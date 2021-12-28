package gauth.android.gauthlibrary.data

import java.io.Serializable

data class Token(
    val accessToken : String,
    val refreshToken : String
) : Serializable
