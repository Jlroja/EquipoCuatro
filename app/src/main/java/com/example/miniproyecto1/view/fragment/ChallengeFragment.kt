package com.example.miniproyecto1.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miniproyecto1.R
import com.example.miniproyecto1.databinding.FragmentChallengeBinding
import com.example.miniproyecto1.model.Challenge
import com.example.miniproyecto1.view.adapter.ChallengeAdapter
import com.example.miniproyecto1.view.dialog.DialogoAddChallegent
import com.example.miniproyecto1.viewmodel.ChallengeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [ChallengeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ChallengeFragment : Fragment() {
    private lateinit var binding: FragmentChallengeBinding
    private val challengeViewModel : ChallengeViewModel  by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerListChallenge()
        dataChallenge()
        binding.toolbar.toolbarTitle.text = "Retos"
        binding.toolbar.backButton.setOnClickListener{
            findNavController().navigate(R.id.homeFragment)

        }
        binding.buttonAdd.setOnClickListener{
            DialogoAddChallegent.showDialog(requireContext(), this).show()

        }


    }
    private fun dataChallenge() {
        val receivedBundle = arguments

    }


    fun onInputReceived(input: String) {
        val challenge = Challenge(description = input)
        challengeViewModel.saveChallenge(challenge)
        observerListChallenge()
    }

    fun onInputEdit(challenge: Challenge) {
        challengeViewModel.updateChallenge(challenge)
        observerListChallenge()
    }

    fun onInputDelete(challenge: Challenge) {
        challengeViewModel.deleteChallenge(challenge)
        observerListChallenge()
    }


   private fun observerListChallenge(){

        challengeViewModel.getListChallenge()
        challengeViewModel.listChallenge.observe(viewLifecycleOwner){ listChallenge ->
            val recycler = binding.recyclerview
            val layoutManager = LinearLayoutManager(context)
            recycler.layoutManager = layoutManager
            val adapter = ChallengeAdapter(listChallenge,this)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()

        }

    }


}