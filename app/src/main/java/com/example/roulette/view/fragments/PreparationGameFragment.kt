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
import coil.load
import coil.size.Scale
import com.example.roulette.databinding.FragmentPreparationGameBinding
import com.example.roulette.model.constant.OUTCOME_BLACK
import com.example.roulette.model.constant.OUTCOME_EMPTY
import com.example.roulette.model.constant.OUTCOME_EVEN
import com.example.roulette.model.constant.OUTCOME_ODD
import com.example.roulette.model.constant.OUTCOME_RED
import com.example.roulette.model.constant.URL_IMAGE_BACKGROUND
import com.example.roulette.model.constant.URL_IMAGE_MONEY
import com.example.roulette.model.constant.repository
import com.example.roulette.presenter.presenters.PresenterPreparation
import com.example.roulette.presenter.viewInterface.ViewPreparationInterface

class PreparationGameFragment : Fragment(),ViewPreparationInterface {

    private var binding: FragmentPreparationGameBinding? = null
    private val presenterPreparation = PresenterPreparation(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreparationGameBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка фонового изображения
        binding!!.idPrepImg.load(URL_IMAGE_BACKGROUND){scale(Scale.FILL)}

        //загрузка остальных изображений
        binding!!.idPrepImgMoney.load(URL_IMAGE_MONEY){scale(Scale.FIT)}

        //загрузка+показ количества денег
        presenterPreparation.loadMoney()

        //увеличение ставки
        binding!!.idPrepButtonBetPlus.setOnClickListener {
            presenterPreparation.addMoneyForBet(requireContext())
        }

        //уменьшение ставки
        binding!!.idPrepButtonBetMinus.setOnClickListener {
            presenterPreparation.minusMoneyForBet(requireContext())
        }

        //выбор исхода: красный цвет
        binding!!.idPrepCheckboxRed.setOnClickListener {
            binding!!.idPrepCheckboxBlack.isChecked = false
            if(binding!!.idPrepCheckboxRed.isChecked){
                presenterPreparation.setOutcomeRedOrBlack(OUTCOME_RED)
                presenterPreparation.setFlagRedOrBlack(true)
            }else{
                presenterPreparation.setFlagRedOrBlack(false)
                presenterPreparation.setOutcomeRedOrBlack(OUTCOME_EMPTY)
            }
        }

        //выбор исхода: черный цвет
        binding!!.idPrepCheckboxBlack.setOnClickListener {
            binding!!.idPrepCheckboxRed.isChecked = false
            if(binding!!.idPrepCheckboxBlack.isChecked){
                presenterPreparation.setOutcomeRedOrBlack(OUTCOME_BLACK)
                presenterPreparation.setFlagRedOrBlack(true)
            }else{
                presenterPreparation.setFlagRedOrBlack(false)
                presenterPreparation.setOutcomeRedOrBlack(OUTCOME_EMPTY)
            }
        }

        //выбор исхода: четные очки
        binding!!.idPrepCheckboxEven.setOnClickListener {
            binding!!.idPrepCheckboxOdd.isChecked = false
            if(binding!!.idPrepCheckboxEven.isChecked){
                presenterPreparation.setOutcomeEvenOrOdd(OUTCOME_EVEN)
                presenterPreparation.setFlagEvenOrOdd(true)
            }else{
                presenterPreparation.setFlagEvenOrOdd(false)
                presenterPreparation.setOutcomeRedOrBlack(OUTCOME_EMPTY)
            }
        }

        //выбор исхода: нечетные очки
        binding!!.idPrepCheckboxOdd.setOnClickListener {
            binding!!.idPrepCheckboxEven.isChecked = false
            if(binding!!.idPrepCheckboxOdd.isChecked){
                presenterPreparation.setOutcomeEvenOrOdd(OUTCOME_ODD)
                presenterPreparation.setFlagEvenOrOdd(true)
            }else{
                presenterPreparation.setFlagEvenOrOdd(false)
                presenterPreparation.setOutcomeRedOrBlack(OUTCOME_EMPTY)
            }
        }

        //проверка ставки+переход к игре
        binding!!.idPrepButtonGo.setOnClickListener {
            presenterPreparation.checkBetForGame(requireContext())
        }

        //выход в меню
        binding!!.idPrepButtonBack.setOnClickListener {
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
        binding!!.idPrepTvMoney.text = "money: $money"
    }

    //показ размера ставки
    @SuppressLint("SetTextI18n")
    override fun showMoneyForBet(money: Int) {
        binding!!.idPrepTvBet.text = "$money$"
    }

    //показ всплывающего сообщения
    override fun showToast(context: Context, message: String) {
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }

    //показ коэффициента
    override fun showCoefficientBet(coefficient: String) {
        binding!!.idPrepTvCoef.text = coefficient
    }

}