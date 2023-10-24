package com.example.roulette.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import coil.load
import coil.size.Scale
import com.example.roulette.databinding.FragmentMenuBinding
import com.example.roulette.model.constant.URL_IMAGE_BACKGROUND
import com.example.roulette.model.constant.URL_IMAGE_GAME_FOR_MENU
import com.example.roulette.model.constant.URL_IMAGE_MONEY
import com.example.roulette.model.constant.URL_IMAGE_MONEY_FOR_MENU
import com.example.roulette.model.constant.URL_IMAGE_RULES_FOR_MENU
import com.example.roulette.model.constant.repository
import com.example.roulette.presenter.presenters.PresenterMenu
import com.example.roulette.presenter.viewInterface.ViewMenuInterface

class MenuFragment : Fragment(),ViewMenuInterface {

    private var binding: FragmentMenuBinding? = null
    private val presenterMenu = PresenterMenu(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater,container,false)
        return binding?.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        @Suppress("DEPRECATION")
        super.onActivityCreated(savedInstanceState)
        presenterMenu.loadMoney() // загрузка + показ количества денег
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка фонового изображения
        binding!!.idMenuImg.load(URL_IMAGE_BACKGROUND){scale(Scale.FILL)}

        //загрузка остальных изображений
        binding!!.idMenuImgMoney.load(URL_IMAGE_MONEY){scale(Scale.FIT)}
        binding!!.idMenuImgPlay.load(URL_IMAGE_GAME_FOR_MENU){scale(Scale.FIT)}
        binding!!.idMenuImgRepl.load(URL_IMAGE_MONEY_FOR_MENU){scale(Scale.FIT)}
        binding!!.idMenuImgRules.load(URL_IMAGE_RULES_FOR_MENU){scale(Scale.FIT)}

        //переход к подготовке к игре
        binding!!.idMenuButtonPlay.setOnClickListener {
            repository.showPreparationGameFragment()
        }

        //переход к пополнению средств
        binding!!.idMenuButtonRepl.setOnClickListener {
            repository.showReplenishmentFundsFragment()
        }

        //переход к правилам игры
        binding!!.idMenuButtonRules.setOnClickListener {
            repository.showRulesFragment()
        }

        //выход из игры
        binding!!.idMenuButtonExit.setOnClickListener {
            repository.exitingApplication()
        }

        //выход из игры
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            repository.exitingApplication()
        }

    }

    //очистка биндинга при очистке вью
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //показ количества денег
    override fun showMoney(money: Int) {
        binding!!.idMenuTvMoney.text = money.toString()
    }

}