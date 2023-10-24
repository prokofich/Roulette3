package com.example.roulette.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import coil.load
import coil.size.Scale
import com.example.roulette.databinding.FragmentRulesBinding
import com.example.roulette.model.constant.URL_IMAGE_BACKGROUND
import com.example.roulette.model.constant.repository
import com.example.roulette.presenter.presenters.PresenterRules
import com.example.roulette.presenter.viewInterface.ViewRulesInterface

class RulesFragment : Fragment(),ViewRulesInterface {

    private var binding: FragmentRulesBinding? = null
    private var presenterRules = PresenterRules(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRulesBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка фонового изображения
        binding!!.idRulesImg.load(URL_IMAGE_BACKGROUND){scale(Scale.FILL)}

        //выход в меню
        binding!!.idRulesButtonBack.setOnClickListener {
            repository.showMenuFragment()
        }

        //загрузка+показ текста правил
        presenterRules.loadTextRules()

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

    //показ правил игры
    override fun showTextRules(text: String) {
        binding!!.idRulesTvRules.text = text
    }

}