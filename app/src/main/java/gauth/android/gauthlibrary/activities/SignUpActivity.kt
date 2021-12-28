package gauth.android.gauthlibrary.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import gauth.android.gauthlibrary.R
import gauth.android.gauthlibrary.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
    }

    fun setClickListener(onClickListener: View.OnClickListener) {
        binding.signUpBtn.setOnClickListener(onClickListener)
    }

    fun getBinding() : ActivitySignUpBinding {
        return binding
    }
}