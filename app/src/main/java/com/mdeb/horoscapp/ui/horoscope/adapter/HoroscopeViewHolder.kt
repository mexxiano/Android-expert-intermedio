package com.mdeb.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.mdeb.horoscapp.databinding.ItemHoroscopeBinding
import com.mdeb.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)
    fun render(horoscopeInfo: HoroscopeInfo, onIntemSelected: (HoroscopeInfo) -> Unit){
            binding.ivHoroscope.setImageResource(horoscopeInfo.img)
            binding.tvTitle.text  = binding.tvTitle.context.getString(horoscopeInfo.name)

        binding.parent.setOnClickListener{
            starRotation(binding.ivHoroscope, newLambda = {  onIntemSelected(horoscopeInfo) })
            onIntemSelected(horoscopeInfo)
        }
    }

    private fun starRotation(view:View, newLambda:()->Unit){
        view.animate().apply{
            duration = 750
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction { newLambda() }
            start()
        }
    }
}