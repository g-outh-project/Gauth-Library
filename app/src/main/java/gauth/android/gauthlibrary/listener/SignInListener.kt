package gauth.android.gauthlibrary.listener

import gauth.android.gauthlibrary.data.Token

interface SignInListener {
    fun onSuccess(token : Token)
    fun onFail()
}