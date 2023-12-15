package com.mdeb.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.aristidevs.horoscapp.ui.core.listeners.OnSwipeTouchListener
import com.mdeb.horoscapp.R
import com.mdeb.horoscapp.databinding.FragmentLuckBinding
import com.mdeb.horoscapp.ui.providers.RamdomCardProviders
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject


@AndroidEntryPoint
class LuckFragment : Fragment() {


    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProviders: RamdomCardProviders
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    private fun preparePrediction() {
        val luck = randomCardProviders.getLucky()
        luck?.let{
            val currentPrediction =  getString(it.text)
            binding.tvLucky.text = currentPrediction
            binding.ivLuckyCard.setImageResource(it.image)
            binding.tvShare.setOnClickListener{
                shareResult(currentPrediction)
            }
        }
    }

    private fun shareResult(prediction:String) {
        val sendIntent: Intent = Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun initListeners() {
        //binding.ivRulette.setOnClickListener { spinRoulette() }
        binding.ivRulette.setOnTouchListener(object: OnSwipeTouchListener(requireContext()){

            @SuppressLint("ClickableViewAccessibility")
            override fun onSwipeRight() {
                spinRoulette()
            }

            @SuppressLint("ClickableViewAccessibility")
            override fun onSwipeLeft() {
                spinRoulette()
            }
        })
    }

    private fun spinRoulette() {
        val random = Random()
        val degrees = random.nextInt(1440) + 360

        val animator =
            ObjectAnimator.ofFloat(binding.ivRulette, View.ROTATION, 0f, degrees.toFloat())

        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.reverse.isVisible = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                    growCard()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

        binding.reverse.startAnimation(slideUpAnimation)

    }

    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(),R.anim.grow )
        growAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                    binding.reverse.isVisible = false
                    showPremonitionView()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

        binding.reverse.startAnimation(growAnimation)
    }

    private fun showPremonitionView() {

        val dissapearAnimation = AlphaAnimation(1.0f, 0.0f)
        dissapearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 100

        dissapearAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })

        binding.preview.startAnimation(dissapearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}