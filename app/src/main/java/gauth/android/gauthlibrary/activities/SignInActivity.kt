package gauth.android.gauthlibrary.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import gauth.android.gauthlibrary.R
import gauth.android.gauthlibrary.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
    }

    fun setClickListener(onClickListener: View.OnClickListener) {
        binding.loginBtn.setOnClickListener(onClickListener)
    }

    fun getBinding() : ActivitySignInBinding {
        return binding
    }
}