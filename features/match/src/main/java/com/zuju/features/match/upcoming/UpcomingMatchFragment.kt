package com.zuju.features.match.upcoming

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import com.zuju.features.core.base.fragments.BaseFragment
import com.zuju.features.core.base.utils.observeFlow
import com.zuju.features.match.databinding.FragmentUpcomingBinding
import com.zuju.features.match.sharedviewmodel.MatchSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingMatchFragment : BaseFragment<FragmentUpcomingBinding>() {

    private lateinit var upcomingMatchAdapter: UpcomingMatchAdapter
    private val sharedViewModel: MatchSharedViewModel by activityViewModels()

    override fun getViewBinding(inflater: LayoutInflater): FragmentUpcomingBinding {
        return FragmentUpcomingBinding.inflate(inflater)
    }

    override fun initView() {
        upcomingMatchAdapter = UpcomingMatchAdapter {

        }

        binding.rcvUpcomingMatch.apply {
            adapter = upcomingMatchAdapter
            setHasFixedSize(true)
        }
    }

    override fun initObserver() {
        observeFlow(sharedViewModel.upcomingMath, ::onMatchUpdated)
    }

    private fun onMatchUpdated(newMatches: List<UpcomingMatchUi>) {
        upcomingMatchAdapter.submitList(newMatches)
    }
}
