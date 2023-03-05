package com.zuju.zujuassessment.main.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.zuju.zujuassessment.databinding.FragmentSelectTeamBinding

class SelectTeamFragment : DialogFragment() {

    private lateinit var binding: FragmentSelectTeamBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectTeamBinding.inflate(inflater)
        return binding.root
    }
}
