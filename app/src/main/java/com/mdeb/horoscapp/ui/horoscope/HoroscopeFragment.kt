package com.mdeb.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mdeb.horoscapp.databinding.FragmentHoroscopeBinding
import com.mdeb.horoscapp.domain.model.HoroscopeInfo
import com.mdeb.horoscapp.domain.model.HoroscopeModel
import com.mdeb.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private lateinit var horoscopeAdapter: HoroscopeAdapter

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
           val type = when(it){
                HoroscopeInfo.Aquarius -> HoroscopeModel.ACUARIO
                HoroscopeInfo.Aries -> HoroscopeModel.ARIES
                HoroscopeInfo.Cancer -> HoroscopeModel.CANCER
                HoroscopeInfo.Capricornio -> HoroscopeModel.CAPRICORNIO
                HoroscopeInfo.Gemini -> HoroscopeModel.GEMINI
                HoroscopeInfo.Leo -> HoroscopeModel.LEO
                HoroscopeInfo.Libra -> HoroscopeModel.LIBRA
                HoroscopeInfo.Piscis -> HoroscopeModel.PISCIS
                HoroscopeInfo.Sagitario -> HoroscopeModel.SAGITARIO
                HoroscopeInfo.Scorpio -> HoroscopeModel.SCORPIO
                HoroscopeInfo.Taurus -> HoroscopeModel.TAURUS
                HoroscopeInfo.Virgo -> HoroscopeModel.VIRGO
            }
            //Toast.makeText(context,getString(it.name) , Toast.LENGTH_LONG).show()
            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        })
        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }

    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {
                    // Han habido cambios en horoscope
                    horoscopeAdapter.updateList(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}