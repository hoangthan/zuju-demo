package com.zuju.features.match.previous

import android.view.LayoutInflater
import android.view.View
import com.zuju.features.core.base.fragments.BaseFragment
import com.zuju.features.match.databinding.FragmentPreviousBinding
import com.zuju.features.teamplayer.SelectTeamFragment

class PreviousMatchFragment : BaseFragment<FragmentPreviousBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentPreviousBinding {
        return FragmentPreviousBinding.inflate(inflater)
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
