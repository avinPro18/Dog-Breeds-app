package com.example.dogbreedapp.ui.home_listing

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.dogbreedapp.R
import com.example.dogbreedapp.base.BaseActivity
import com.example.dogbreedapp.databinding.ActivityHomeScreenBinding
import com.example.dogbreedapp.ui.detail.DetailScreen
import com.example.dogbreedapp.ui.home_listing.adapters.DogBreedsAdapter
import com.example.dogbreedapp.ui.login.LoginScreen
import com.example.dogbreedapp.utils.showToastMsg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : BaseActivity<ActivityHomeScreenBinding, HomeViewModel>() {

    private lateinit var dogBreedsAdapter: DogBreedsAdapter

    override fun init() {
        setRecyclerView()
        observe()
    }

    private fun observe() {
        viewModel?.let {model ->
            model.apply {
                dogBreeds.observe(this@HomeScreen){dogBreedsResp ->
                    dogBreedsAdapter.setDogBreedList(dogBreedsResp)
                }
                error.observe(this@HomeScreen){msg ->
                    showToastMsg(msg)
                }
                isLoading.observe(this@HomeScreen){isLoading ->
                    binding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_logout -> {
                viewModel?.let {
                    it.logout{
                        goToActivity(LoginScreen(), clear = true)
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setRecyclerView() {
        dogBreedsAdapter = DogBreedsAdapter{
            goToActivity(DetailScreen(), data = it)
        }
        binding.apply {
            recyclerViewDogBreeds.apply{
                adapter = dogBreedsAdapter
            }
        }
    }

    override fun getBindingLayout(): ActivityHomeScreenBinding {
        return ActivityHomeScreenBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

}