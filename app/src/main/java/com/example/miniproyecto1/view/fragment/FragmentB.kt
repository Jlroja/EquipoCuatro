package com.example.miniproyecto1.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import com.example.miniproyecto1.R
import com.example.miniproyecto1.databinding.FragmentBBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentB : Fragment() {
    private lateinit var binding: FragmentBBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationFragmentA()
        setupWebView()
    }

    private fun setupWebView() {
        val webView = binding.webView
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es_419&gl=es")  // Cargar la URL
    }

    private fun navigationFragmentA(){
        binding.backArrow.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentB_to_homeFragment)
        }
    }




}