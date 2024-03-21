package com.example.gethub.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.example.gethub.R
import com.example.gethub.data.remote.response.ResponseApi
import com.example.gethub.databinding.ActivityDetailBinding
import com.example.gethub.domain.model.DetailUserModel
import com.example.gethub.presentation.adapter.SectionPagerAdapter
import com.example.gethub.presentation.vm.DetailViewModel
import com.example.gethub.presentation.vm.ViewModelFactory
import com.example.gethub.util.ColorType.setColor
import com.example.gethub.util.NumberFormatter.shortenNumber
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val navArgs: DetailActivityArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState === null) {
            viewModel.setUsername(navArgs.username.toString())
        }

        setUpToolbar()
        setUpViewPager()
        setUpView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun setUpToolbar() {
        with(binding) {
            toolbar.title = navArgs.username
            setSupportActionBar(toolbar)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpViewPager() {
        val sectionPagerAdapter = SectionPagerAdapter(
            activity = this,
            username = navArgs.username.toString()
        )
        with(binding) {
            viewPager.adapter = sectionPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    private fun setUpView() {
        binding.btnRetry.setOnClickListener {
            viewModel.setUsername(navArgs.username.toString())
        }
        viewModel.getdetailUser.observe(this, userObserver)
    }

    private val userObserver = Observer<ResponseApi<DetailUserModel>> { result ->
        when (result) {
            is ResponseApi.Loading -> {
                setState(
                    isError = false,
                    isLoading = true
                )
            }
            is ResponseApi.Success -> {
                setState(
                    isError = false,
                    isLoading = false
                )
                val user = result.data
                user?.populateDetailUser()
            }
            is ResponseApi.Error -> {
                setState(
                    isError = true,
                    isLoading = false
                )
            }
        }
    }

    private fun DetailUserModel.populateDetailUser() {
        with(binding.contentDetailUser) {
            Glide.with(this@DetailActivity)
                .load(avatarUrl)
                .into(ivUser)
            tvUserType.setColor(this@DetailActivity, type)
            tvFollowersCount.text = followers.shortenNumber()
            tvRepositoryCount.text = publicRepos.shortenNumber()
            tvFollowingCount.text = following.shortenNumber()
            tvFullName.text = name
            tvUserBio.text = bio
            tvUserBio.isVisible = !bio.isNullOrBlank()
        }
    }

    private fun setState(isError: Boolean, isLoading: Boolean) {
        binding.apply {
            btnRetry.isVisible = isError
            shimmerFrameUser.root.isVisible = isLoading
            contentDetailUser.root.visibility = if (!isLoading and !isError) View.VISIBLE else View.INVISIBLE
        }
    }

    companion object {
        @StringRes
        val TAB_TITLES = arrayOf(R.string.repository, R.string.followers, R.string.following)
    }
}