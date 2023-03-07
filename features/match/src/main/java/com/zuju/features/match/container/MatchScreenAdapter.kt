package com.zuju.features.match.container

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zuju.features.core.R
import com.zuju.features.match.previous.PreviousMatchFragment
import com.zuju.features.match.upcoming.UpcomingMatchFragment

class MatchScreenAdapter constructor(
    fragmentManager: FragmentManager,
    lifeCycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifeCycle) {

    private val screens = listOf(
        Screen(
            index = 0,
            fragment = UpcomingMatchFragment(),
            titleRes = R.string.upcoming,
        ), Screen(
            index = 1,
            fragment = PreviousMatchFragment(),
            titleRes = R.string.previous,
        )
    ).sortedBy { it.index }

    override fun getItemCount(): Int = screens.size

    override fun createFragment(position: Int): Fragment = screens[position].fragment

    fun getTitleRes(index: Int) = screens[index].titleRes

    private data class Screen(
        val index: Int,
        val fragment: Fragment,
        @StringRes val titleRes: Int,
    )
}
