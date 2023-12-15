package com.mdeb.horoscapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.mdeb.horoscapp.R
import com.mdeb.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.mdeb.horoscapp.domain.model.HoroscopeModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel:HoroscopeDetailViewModel by viewModels()

    private val args:HoroscopeDetailActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
       binding.ivBack.setOnClickListener{ onBackPressed()}
    }

    private fun initUIState() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeDetailViewModel.state.collect{
                        when(it){
                            is HoroscopeDetailState.Error -> errorState()
                            HoroscopeDetailState.Loading ->  loadingState()
                            is HoroscopeDetailState.Success -> successState(it)
                        }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState(){
        binding.pb.isVisible = false
    }

    private fun successState(horoscopeDetailState: HoroscopeDetailState.Success) {
        binding.pb.isVisible = false
        binding.tvTitle.text = horoscopeDetailState.sign
        binding.tvBody.text = horoscopeDetailState.prediction

       val image:Int = when(horoscopeDetailState.horoscopeModel){
            HoroscopeModel.ARIES -> R.drawable.detail_aries
            HoroscopeModel.TAURUS -> R.drawable.tauro
            HoroscopeModel.GEMINI -> R.drawable.geminis
            HoroscopeModel.CANCER -> R.drawable.cancer
            HoroscopeModel.LEO -> R.drawable.leo
            HoroscopeModel.VIRGO -> R.drawable.virgo
            HoroscopeModel.LIBRA -> R.drawable.libra
            HoroscopeModel.SCORPIO -> R.drawable.escorpio
            HoroscopeModel.CAPRICORNIO -> R.drawable.capricornio
            HoroscopeModel.SAGITARIO -> R.drawable.sagitario
            HoroscopeModel.ACUARIO -> R.drawable.aquario
            HoroscopeModel.PISCIS -> R.drawable.piscis
        }

        binding.ivDetail.setImageResource(image)
    }
}