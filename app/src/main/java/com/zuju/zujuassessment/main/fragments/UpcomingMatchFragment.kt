package com.zuju.zujuassessment.main.fragments

import android.view.LayoutInflater
import android.view.View
import com.zuju.zujuassessment.databinding.FragmentUpcomingBinding
import com.zuju.zujuassessment.main.teams.SelectTeamFragment

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
        SelectTeamFragment().show(childFragmentManager, this.javaClass.canonicalName)
    }
}
