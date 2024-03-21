package com.example.gethub.presentation.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gethub.activity.DetailActivity
import com.example.gethub.presentation.fragment.FollowFragment
import com.example.gethub.presentation.fragment.RepositoryFragment

class SectionPagerAdapter(activity: AppCompatActivity, private val username: String): FragmentStateAdapter(activity){

    override fun getItemCount() = DetailActivity.TAB_TITLES.size

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = RepositoryFragment()
            1, 2 -> fragment = FollowFragment()
        }
        val bundle = Bundle().apply {
            putInt(EXTRA_POSITION, position)
            putString(EXTRA_USERNAME, username)
        }
        fragment?.arguments = bundle
        return fragment as Fragment
    }

    companion object {
        const val EXTRA_POSITION = "position"
        const val EXTRA_USERNAME = "username"
    }
}