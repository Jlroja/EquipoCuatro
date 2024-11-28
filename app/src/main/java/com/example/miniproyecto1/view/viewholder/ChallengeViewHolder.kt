package com.example.miniproyecto1.view.viewholder

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import com.example.miniproyecto1.databinding.ItemchallengesBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.miniproyecto1.R
import com.example.miniproyecto1.model.Challenge
import com.example.miniproyecto1.view.dialog.DialogoDelete
import com.example.miniproyecto1.view.dialog.DialogoEdit
import com.example.miniproyecto1.view.fragment.ChallengeFragment

class ChallengeViewHolder(binding: ItemchallengesBinding, private val fragment: ChallengeFragment):
    RecyclerView.ViewHolder(binding.root) {
    val bindingItem = binding
    fun setItemChallenge(challenge: Challenge) {
        bindingItem.textDescription.text = challenge.description

        // Editar reto
        bindingItem.iconEdit.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("clave", challenge) // Asegúrate de que Challenge es Serializable
            }
            DialogoEdit.showDialog(
                context = fragment.requireContext(),
                fragment,
                bundle = bundle
            ).show()
        }


        bindingItem.iconDelete.setOnClickListener{

            //Toast.makeText(bindingItem.root.context, "Editar", Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putSerializable("clave", challenge)
            DialogoDelete.showDialog(context = fragment.requireContext(),fragment, bundle).show()

        }
    }

    fun editItemChallenge(challenge: Challenge) {
        bindingItem.iconEdit.setOnClickListener {
            val bundle = Bundle()
            Toast.makeText(bindingItem.root.context, "Editar", Toast.LENGTH_SHORT).show()
        }

    }
}