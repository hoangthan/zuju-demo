package com.zuju.features.match.previous

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import com.zuju.features.core.base.fragments.BaseFragment
import com.zuju.features.core.base.utils.observeFlow
import com.zuju.features.match.databinding.FragmentPreviousBinding
import com.zuju.features.match.sharedviewmodel.MatchSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviousMatchFragment : BaseFragment<FragmentPreviousBinding>() {

    private lateinit var matchAdapter: PreviousMatchAdapter
    private val sharedViewModel: MatchSharedViewModel by activityViewModels()

    override fun getViewBinding(inflater: LayoutInflater): FragmentPreviousBinding {
        return FragmentPreviousBinding.inflate(inflater)
    }

    override fun initView() {
        super.initView()
        matchAdapter = PreviousMatchAdapter {

        }

        binding.rcvPreviousMatch.apply {
            adapter = matchAdapter
            setHasFixedSize(true)
        }
    }

    override fun initObserver() {
        observeFlow(sharedViewModel.previousMatch, ::onMatchUpdated)
    }

    private fun onMatchUpdated(newData: List<PreviousMatchUi>) {
        matchAdapter.submitList(newData)
    }
}
