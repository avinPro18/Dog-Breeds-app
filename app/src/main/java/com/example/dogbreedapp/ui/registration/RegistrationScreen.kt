package com.example.dogbreedapp.ui.registration

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.dogbreedapp.base.BaseActivity
import com.example.dogbreedapp.databinding.ActivityRegistrationScreenBinding
import com.example.dogbreedapp.di.repository_helper.database.entites.User
import com.example.dogbreedapp.utils.Validations.isValidFullName
import com.example.dogbreedapp.utils.Validations.isValidPassword
import com.example.dogbreedapp.utils.Validations.isValidUsername
import com.example.dogbreedapp.utils.hideSoftKeyboard
import com.example.dogbreedapp.utils.showToastMsg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationScreen : BaseActivity<ActivityRegistrationScreenBinding, RegistrationViewModel>() {

    override fun init() {
        setClickListeners()
        observe()
    }

    private fun observe() {
        viewModel?.let {
            it.userAdded.observe(this){userAdded ->
                if(userAdded){
                    showToastMsg("User registered")
                    finish()
                }else
                    showToastMsg("Username already taken")
            }
        }
    }

    private fun setClickListeners() {
        binding.apply {
            buttonRegister.setOnClickListener {
                hideSoftKeyboard()
                val username = editTextUsername.text.toString().trim()
                val password = editTextPassword.text.toString().trim()
                val fullName = editTextFullName.text.toString().trim()
                viewModel?.let {
                    if(validate(username, password, fullName) is Boolean){
                        it.saveUserData(User(username = username, password = password, fullName = fullName))
                    }
                }
            }
        }
    }

    private fun validate(username: String, password: String, fullname: String): Any{
        return when{
            !username.isValidUsername() -> showToastMsg("Invalid username")
            !password.isValidPassword() -> showToastMsg("Invalid password (min 8 characters, 1 special character, 1 number and 1 capital letter)", Toast.LENGTH_LONG)
            !fullname.isValidFullName() -> showToastMsg("Invalid fullname")
            else -> true
        }
    }

    override fun getBindingLayout(): ActivityRegistrationScreenBinding {
        return ActivityRegistrationScreenBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): RegistrationViewModel {
        return ViewModelProvider(this)[RegistrationViewModel::class.java]
    }


}