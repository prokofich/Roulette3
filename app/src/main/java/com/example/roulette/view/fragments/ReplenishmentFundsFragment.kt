package com.example.roulette.view.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import coil.load
import coil.size.Scale
import com.example.roulette.R
import com.example.roulette.databinding.FragmentReplenishmentFundsBinding
import com.example.roulette.model.constant.URL_IMAGE_BACKGROUND
import com.example.roulette.model.constant.URL_IMAGE_MONEY
import com.example.roulette.model.constant.URL_IMAGE_MONEY_FOR_MENU
import com.example.roulette.model.constant.repository
import com.example.roulette.presenter.presenters.PresenterReplenishment
import com.example.roulette.presenter.viewInterface.ViewReplenishmentInterface

class ReplenishmentFundsFragment : Fragment(),ViewReplenishmentInterface {

    private var binding: FragmentReplenishmentFundsBinding? = null
    private val presenterReplenishment = PresenterReplenishment(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReplenishmentFundsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка фонового изображения
        binding!!.idReplImg.load(URL_IMAGE_BACKGROUND){scale(Scale.FILL)}

        //загрузка остальных изображений
        binding!!.idReplImgMoney.load(URL_IMAGE_MONEY){scale(Scale.FIT)}
        binding!!.idReplImg1.load(URL_IMAGE_MONEY_FOR_MENU){scale(Scale.FIT)}
        binding!!.idReplImg2.load(URL_IMAGE_MONEY_FOR_MENU){scale(Scale.FIT)}
        binding!!.idReplImg3.load(URL_IMAGE_MONEY_FOR_MENU){scale(Scale.FIT)}

        //загрузка и показ количества денег
        presenterReplenishment.loadMoney()

        //взять ежедневный бесплатный приз
        binding!!.idReplButtonGetMoneyFree.setOnClickListener {
            hideTextView()
            setVisibleButtons(false)
            if (presenterReplenishment.checkLastDay()){
                setVisibleButtons(true)
                setEnableButtons(true)
            }else{
                showToast(requireContext(),"you will be able to top up your account only tomorrow")
            }
        }

        //взять случайный приз за плату
        binding!!.idReplButtonGetMoney.setOnClickListener {
            hideTextView()
            setVisibleButtons(false)
            if (presenterReplenishment.checkMoneyForReplenishment()){
                presenterReplenishment.minusMoney(150)
                setEnableButtons(true)
                setVisibleButtons(true)
            }else{
                showToast(requireContext(),"you have less than $150")
            }
        }

        //выбрать приз номер 1
        binding!!.idReplButtonSelect1.setOnClickListener {
            presenterReplenishment.loadNewMoneyForReplenish(requireContext(),1)
        }

        //выбрать приз номер 2
        binding!!.idReplButtonSelect2.setOnClickListener {
            presenterReplenishment.loadNewMoneyForReplenish(requireContext(),2)
        }

        //выбрать приз номер 3
        binding!!.idReplButtonSelect3.setOnClickListener {
            presenterReplenishment.loadNewMoneyForReplenish(requireContext(),3)
        }

        //выход в меню
        binding!!.idReplButtonBack.setOnClickListener {
            repository.showMenuFragment()
        }

        //выход в меню
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            repository.showMenuFragment()
        }

    }

    //очистка биндинга при очистке вью
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //показ количества денег
    @SuppressLint("SetTextI18n")
    override fun showMoney(money: Int) {
        binding!!.idReplTvMoney.text = "money: $money"
    }

    //показ всплывающего сообщения
    override fun showToast(context: Context, message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    //показ или скрытие кнопок для выбора приза
    override fun setVisibleButtons(visible: Boolean) {
        binding!!.idReplButtonSelect1.isVisible = visible
        binding!!.idReplButtonSelect2.isVisible = visible
        binding!!.idReplButtonSelect3.isVisible = visible
    }

    //включить или выключить кнопки
    override fun setEnableButtons(enable: Boolean) {
        binding!!.idReplButtonSelect1.isEnabled = enable
        binding!!.idReplButtonSelect2.isEnabled = enable
        binding!!.idReplButtonSelect3.isEnabled = enable
    }

    //скрыть текстовые поля с призами
    override fun hideTextView() {
        @Suppress("DEPRECATION")
        binding!!.idReplTvMoney1.setBackgroundDrawable(null)
        binding!!.idReplTvMoney1.text = ""
        @Suppress("DEPRECATION")
        binding!!.idReplTvMoney2.setBackgroundDrawable(null)
        binding!!.idReplTvMoney2.text = ""
        @Suppress("DEPRECATION")
        binding!!.idReplTvMoney3.setBackgroundDrawable(null)
        binding!!.idReplTvMoney3.text = ""
    }

    //показ денежного приза
    @SuppressLint("SetTextI18n")
    override fun showNewMoney(newMoney: Int, numberTextView: Int) {
        when(numberTextView){
            1 -> {
                binding!!.idReplTvMoney1.setBackgroundResource(R.drawable.background_tv_repl)
                binding!!.idReplTvMoney1.text = "$newMoney$"
            }
            2 -> {
                binding!!.idReplTvMoney2.setBackgroundResource(R.drawable.background_tv_repl)
                binding!!.idReplTvMoney2.text = "$newMoney$"
            }
            3 -> {
                binding!!.idReplTvMoney3.setBackgroundResource(R.drawable.background_tv_repl)
                binding!!.idReplTvMoney3.text = "$newMoney$"
            }
        }
    }

}