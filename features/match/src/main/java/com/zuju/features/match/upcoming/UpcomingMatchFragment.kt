package com.zuju.features.match.upcoming

import android.view.LayoutInflater
import android.view.View
import com.zuju.features.core.base.fragments.BaseFragment
import com.zuju.features.match.databinding.FragmentUpcomingBinding
import com.zuju.features.teamplayer.SelectTeamFragment

class UpcomingMatchFragment : BaseFragment<FragmentUpcomingBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentUpcomingBinding {
        return FragmentUpcomingBinding.inflate(inflater)
    }

    override fun initViewListener() {
        binding.layoutSelectTeam.root.setOnClickListener(this)
    }

    override fun onViewClicked(view: View) {
        when (view.id) {
            binding.layoutSelectTeam.root.id -> navigateToSelectTeam()
        }
    }

    private fun navigateToSelectTeam() {
        SelectTeamFragment {

        }.show(childFragmentManager, this.javaClass.canonicalName)
    }
}
