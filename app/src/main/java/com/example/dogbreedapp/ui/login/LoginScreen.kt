package com.example.dogbreedapp.ui.login

import androidx.lifecycle.ViewModelProvider
import com.example.dogbreedapp.base.BaseActivity
import com.example.dogbreedapp.databinding.ActivityLoginBinding
import com.example.dogbreedapp.ui.home_listing.HomeScreen
import com.example.dogbreedapp.ui.registration.RegistrationScreen
import com.example.dogbreedapp.utils.Validations.isValidPassword
import com.example.dogbreedapp.utils.Validations.isValidUsername
import com.example.dogbreedapp.utils.hideSoftKeyboard
import com.example.dogbreedapp.utils.showToastMsg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginScreen : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun init() {
        setClickListeners()
        observe()
    }

    private fun observe() {
        viewModel?.let {
            it.user.observe(this@LoginScreen){user ->
                goToActivity(HomeScreen(), clear = true)
            }
            it.error.observe(this@LoginScreen){msg ->
                hideSoftKeyboard()
                showToastMsg(msg)
            }
            it.isLoggedIn.observe(this@LoginScreen){isLoggedIn ->
                if(isLoggedIn)
                    goToActivity(HomeScreen(), clear = true)
            }
        }
    }

    private fun setClickListeners() {
        binding.apply {
            buttonLogin.setOnClickListener {
                hideSoftKeyboard()
                //Check in DB
                val username = editTextUsername.text.toString().trim()
                val password = editTextPassword.text.toString().trim()
                if(username.isValidUsername() && password.isValidPassword()){
                    viewModel?.let {
                        it.getUserByUsernamePassword(username, password)
                    }
                }else{
                    showToastMsg("Invalid username or password")
                }
            }
            buttonRegister.setOnClickListener {
                clearUsernameAndPassword()
                goToActivity(RegistrationScreen())
            }
        }
    }

    private fun clearUsernameAndPassword() {
        binding.apply {
            editTextUsername.setText("")
            editTextPassword.setText("")
        }
    }

    override fun getBindingLayout(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }

}