package gauth.android.gauthlibrary.listener

interface OnSignUpClickListener {
    fun signUp(id: String, password: String, email: String, school: String, birth: String, nickname: String, name: String)
}