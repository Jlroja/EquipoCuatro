package com.example.miniproyecto1.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.miniproyecto1.R
import com.example.miniproyecto1.data.ChallengeDB
import com.example.miniproyecto1.databinding.FragmentDialogBinding
import org.json.JSONObject
import kotlin.concurrent.thread
import java.net.URL
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random


@AndroidEntryPoint
class DialogFragment : Fragment() {
    private lateinit var binding: FragmentDialogBinding
    private lateinit var db: ChallengeDB



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        db = ChallengeDB.getDatabase(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch {
            val retoAleatorio = obtenerRetoAleatorioDesdeBD()
            binding.challengeText.text = retoAleatorio

            cargarImagenPokemonAleatorio()
        }

        binding.closeButton.setOnClickListener {
            findNavController().navigate(R.id.action_dialogFragment2_to_homeFragment)
        }
    }





    private suspend fun obtenerRetoAleatorioDesdeBD(): String {
        val retos = db.challengeDao().getListChallenge()
        return if (retos.isNotEmpty()) {
            // Elegir un reto aleatorio
            retos[Random.nextInt(retos.size)].description // Asegúrate de que tu objeto Challenge tiene la propiedad `description`
        } else {
            "No hay retos disponibles"
        }
    }

    private fun cargarImagenPokemonAleatorio(){
        thread {
            val apiUrl = "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json"
            val json = URL(apiUrl).readText()
            val pokemones = JSONObject(json).getJSONArray("pokemon")
            val indiceAleatorio = (0 until pokemones.length()).random()
            val urlImagenPokemon = pokemones.getJSONObject(indiceAleatorio).getString("img")

            activity?.runOnUiThread {
                Glide.with(this@DialogFragment)
                    .load(urlImagenPokemon)
                    .into(binding.pokemonImage)


            }
            }
        }
    }



