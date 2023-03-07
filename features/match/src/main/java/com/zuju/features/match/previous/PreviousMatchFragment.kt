package com.zuju.features.match.previous

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.zuju.features.core.base.fragments.BaseFragment
import com.zuju.features.match.databinding.FragmentPreviousBinding
import com.zuju.features.match.previous.PreviousMatchViewModel.PreviousMatchViewEvent
import com.zuju.features.teamplayer.SelectTeamFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviousMatchFragment : BaseFragment<FragmentPreviousBinding>() {

    private lateinit var selectTeamFragment: DialogFragment
    private val viewModel: PreviousMatchViewModel by viewModels()

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
        selectTeamFragment = SelectTeamFragment {
            selectTeamFragment.dismiss()
            viewModel.dispatchEvent(PreviousMatchViewEvent.TeamChanged(it))
        }
        selectTeamFragment.show(childFragmentManager, this.javaClass.canonicalName)
    }
}
