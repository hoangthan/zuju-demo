package com.zuju.features.match.container

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.zuju.features.core.R
import com.zuju.features.core.base.fragments.BaseFragment
import com.zuju.features.core.base.utils.ProgressScreen
import com.zuju.features.core.base.utils.consumeResultFlow
import com.zuju.features.core.base.utils.observeFlow
import com.zuju.features.match.databinding.FragmentMatchContainerBinding
import com.zuju.features.match.sharedviewmodel.MatchSharedViewModel
import com.zuju.features.match.sharedviewmodel.MatchSharedViewModel.MatchViewEvent
import com.zuju.features.teamplayer.SelectTeamFragment
import com.zuju.features.teamplayer.TeamPlayerUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchContainerFragment : BaseFragment<FragmentMatchContainerBinding>(), ProgressScreen {

    lateinit var selectTeamDialog: DialogFragment
    private val sharedViewModel: MatchSharedViewModel by activityViewModels()

    override fun initView() {
        initViewPager()
    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentMatchContainerBinding {
        return FragmentMatchContainerBinding.inflate(inflater)
    }

    override fun initViewListener() {
        binding.layoutSelectTeam.root.setOnClickListener(this)
    }

    override fun onViewClicked(view: View) {
        when (view.id) {
            binding.layoutSelectTeam.root.id -> openSelectTeamDialog()
        }
    }

    override val loadingView: View by lazy { binding.loadingView }

    override fun initObserver() {
        consumeResultFlow(sharedViewModel.matchFlow)
        observeFlow(sharedViewModel.currentTeamFlow, ::onSelectedTeamChange)
    }

    private fun onSelectedTeamChange(teamPlayerUi: TeamPlayerUi?) {
        val teamName = teamPlayerUi?.name ?: getString(R.string.all_team)
        binding.layoutSelectTeam.tvSelectedTeam.text = teamName
    }

    private fun openSelectTeamDialog() {
        selectTeamDialog = SelectTeamFragment {
            selectTeamDialog.dismiss()
            sharedViewModel.dispatchEvent(MatchViewEvent.ChangeTeam(it))
        }
        selectTeamDialog.show(parentFragmentManager, this.javaClass.canonicalName)
    }

    private fun initViewPager() {
        val adapter = MatchScreenAdapter(parentFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setText(adapter.getTitleRes(position))
        }.attach()
    }
}
