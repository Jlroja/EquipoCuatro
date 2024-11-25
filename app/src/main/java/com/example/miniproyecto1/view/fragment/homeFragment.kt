package com.example.miniproyecto1.view.fragment

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.miniproyecto1.R
import com.example.miniproyecto1.databinding.FragmentHomeBinding

import kotlin.random.Random

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var isAudioOn = true
    private lateinit var mediaPlayer: MediaPlayer
    private var countdownTimer: CountDownTimer? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.fondo_musica)

        setupAudioToggle()
        navigationFragmentB()
        navigationInstruccionFragment()
        navigationChallengeFragment()
        shareFunction()

        // Iniciar la cuenta regresiva y animación al presionar el botón de giro
        binding.spinButton.setOnClickListener {
            if (!mediaPlayer.isPlaying && isAudioOn) {
                mediaPlayer.start()
            }
            reiniciarCuentaRegresiva()
        }
    }

    // Función de animación
    private fun animarIcono(view: View) {
        view.animate()
            .scaleX(1.2f)
            .scaleY(1.2f)
            .alpha(0.8f)
            .setDuration(300)
            .withEndAction {
                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .alpha(1f)
                    .setDuration(300)
                    .start()
            }.start()
    }

    private fun navigationChallengeFragment() {
        binding.iconChallenges.setOnClickListener {
            animarIcono(binding.iconChallenges)
            Toast.makeText(requireContext(), "Navegando a desafíos", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_challengeFragment)
        }
    }

    private fun navigationInstruccionFragment() {
        binding.iconInstructions.setOnClickListener {
            animarIcono(binding.iconInstructions)
            Toast.makeText(requireContext(), "Navegando a instrucciones", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_instructionFragment)
        }
    }

    private fun navigationFragmentB() {
        binding.StarFragmentB.setOnClickListener {
            animarIcono(binding.StarFragmentB)
            Toast.makeText(requireContext(), "Navegando a valoración", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_fragmentB)
        }
    }

    private fun setupAudioToggle() {
        binding.iconAudio.setOnClickListener {
            animarIcono(binding.iconAudio)
            Toast.makeText(requireContext(), "Activa/Desactiva el audio", Toast.LENGTH_SHORT).show()
            toggleAudio()
        }
    }

    private fun toggleAudio() {
        if (isAudioOn) {
            binding.iconAudio.setImageResource(R.drawable.audiooff)
            pauseAudio()
            Log.d("AudioToggle", "audio pausado")
        } else {
            binding.iconAudio.setImageResource(R.drawable.audio_icon)
            playAudio()
            Log.d("AudioToggle", "audio encendido")
        }
        isAudioOn = !isAudioOn
    }

    private fun playAudio() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    private fun pauseAudio() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        }
    }

    private fun shareFunction() {
        binding.iconShare.setOnClickListener {
            animarIcono(binding.iconShare)
            Toast.makeText(requireContext(), "Compartir aplicación", Toast.LENGTH_SHORT).show()
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "App Pico Botella")
                putExtra(
                    Intent.EXTRA_TEXT,
                    "App Pico Botella - ¡Solo los valientes lo juegan!\nDescarga la app aquí: https://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es_419&gl=es"
                )
            }
            startActivity(Intent.createChooser(shareIntent, "Compartir App Pico Botella"))
        }
    }

    private fun reiniciarCuentaRegresiva() {
        binding.countdownText.text = "3"
        countdownTimer?.cancel()

        countdownTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val segundosRestantes = (millisUntilFinished / 1000).toInt()
                binding.countdownText.text = segundosRestantes.toString()
            }

            override fun onFinish() {
                binding.countdownText.text = "0"
                girarBotella()
            }
        }.start()
    }

    private fun girarBotella() {
        val anguloAleatorio = Random.nextInt(1800, 3600).toFloat()

        val rotateAnimation = RotateAnimation(
            0f, anguloAleatorio,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 3000
            fillAfter = true
        }

        binding.bottleImage.startAnimation(rotateAnimation)

        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.countdownText.text = "3"
                findNavController().navigate(R.id.action_homeFragment_to_dialogFragment2)
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
        countdownTimer?.cancel()
    }
}
