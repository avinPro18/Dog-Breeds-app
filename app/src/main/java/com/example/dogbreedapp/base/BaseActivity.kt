package com.example.dogbreedapp.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.dogbreedapp.models.Result
import com.example.dogbreedapp.utils.logE
import com.example.dogbreedapp.utils.showToastMsg
import kotlinx.coroutines.launch

abstract class BaseActivity<BindingLayout: ViewBinding, ViewModelType: ViewModel>: AppCompatActivity() {

    lateinit var binding: BindingLayout
    var viewModel: ViewModelType? = null
    var loader: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBindingLayout()
        setContentView(binding.root)
        viewModel = getViewModelClass()
        loader = setLoaderView()
        init()
    }

    abstract fun init()
    abstract fun getBindingLayout(): BindingLayout
    abstract fun getViewModelClass(): ViewModelType

    /**
     * return null if no loader reqd.
     */
    open fun setLoaderView(): View? {
        return null
    }

    /**
     * These functions can only be used if loader view is set
     */
    open fun showLoader() =
        checkIfViewIsSet(loader) { loader?.let { it.visibility = View.VISIBLE } }

    open fun hideLoader() = checkIfViewIsSet(loader) { loader?.let { it.visibility = View.GONE } }

    private fun checkIfViewIsSet(view: View?, proceed: () -> Unit) {
        if (view != null)
            proceed()
        else
            logE("View not set in activity")
    }

    open fun <T : Parcelable> goToActivity(activity: Activity, data: T? = null, clear: Boolean = false) {
        val intent = Intent(this, activity::class.java)
        if (data != null)
            intent.putExtra("data", data)
        startActivity(
            if(clear)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            else
                intent
        )
    }

    open fun goToActivity(activity: Activity, clear: Boolean = false) {
        val intent = Intent(this, activity::class.java)
        startActivity(
            if(clear)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            else
                intent
        )
    }

}

