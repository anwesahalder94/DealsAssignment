package com.example.dealsassignment.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.dealsassignment.view.fragment.FeaturedFragment
import com.example.dealsassignment.view.fragment.PopularFragment
import com.example.dealsassignment.view.fragment.TopFragment

class ViewPagerAdapter(private val fragmentManager: FragmentManager):
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            TopFragment()
        } else if (position == 1) {
            PopularFragment()
        } else
            FeaturedFragment()
    }

    override fun getCount(): Int {
        return 3
    }
}