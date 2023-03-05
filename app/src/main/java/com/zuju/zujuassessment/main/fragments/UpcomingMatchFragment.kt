package com.zuju.zujuassessment.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zuju.zujuassessment.databinding.FragmentUpcomingBinding

class UpcomingMatchFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentUpcomingBinding.inflate(inflater)
        return binding.root
    }
}
