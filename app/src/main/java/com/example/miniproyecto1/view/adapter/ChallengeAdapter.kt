package com.example.miniproyecto1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.miniproyecto1.databinding.ItemchallengesBinding
import com.example.miniproyecto1.model.Challenge
import com.example.miniproyecto1.view.fragment.ChallengeFragment
import com.example.miniproyecto1.view.viewholder.ChallengeViewHolder

class ChallengeAdapter(private val listChallenge:MutableList<Challenge>, private val fragment:ChallengeFragment):
    RecyclerView.Adapter<ChallengeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val binding = ItemchallengesBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ChallengeViewHolder(binding,fragment)
    }

    override fun getItemCount(): Int {
        return listChallenge.size
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val challenge = listChallenge[position]
        holder.setItemChallenge(challenge)
    }


}