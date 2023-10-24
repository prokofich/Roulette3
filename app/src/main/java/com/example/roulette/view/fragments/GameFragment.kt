package com.example.roulette.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.roulette.R
import com.example.roulette.databinding.FragmentGameBinding
import com.example.roulette.model.adapter.GameAdapter
import com.example.roulette.model.constant.URL_IMAGE_BACKGROUND_GAME
import com.example.roulette.model.constant.URL_IMAGE_STRELKA1
import com.example.roulette.model.constant.URL_IMAGE_STRELKA2
import com.example.roulette.model.constant.repository
import com.example.roulette.presenter.presenters.PresenterGame
import com.example.roulette.presenter.viewInterface.ViewGameInterface

class GameFragment : Fragment(),ViewGameInterface {

    private var binding: FragmentGameBinding? = null
    private val presenterGame = PresenterGame(this)
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterGame: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка фонового изображения
        binding!!.idGameImg.load(URL_IMAGE_BACKGROUND_GAME){scale(Scale.FILL)}

        recyclerView = binding!!.idGameRv
        adapterGame = GameAdapter(requireContext())
        recyclerView.layoutManager = GridLayoutManager(requireContext(),5)
        recyclerView.adapter = adapterGame

        //начало игры
        binding!!.idGameButtonGo.setOnClickListener {
            //загрузка оставшихся картинок
            binding!!.idGameButtonGo.isEnabled = false
            binding!!.idGameImgStrelka.load(URL_IMAGE_STRELKA1){scale(Scale.FIT)}
            binding!!.idGameImgStrelka2.load(URL_IMAGE_STRELKA2){scale(Scale.FIT)}
            presenterGame.loadRandomNumber()
        }

        //рестарт игры
        binding!!.idGameButtonRestart.setOnClickListener {
            repository.showPreparationGameFragment()
        }

        //выход в меню
        binding!!.idGameButtonFinish.setOnClickListener {
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

    //показ загаданного числа
    @SuppressLint("SetTextI18n")
    override fun showRandomNumber(number: Int) {
        if(number%2==0){
            binding!!.idGameTvWin.setBackgroundResource(R.drawable.background_img_prep_black)
        }else{
            binding!!.idGameTvWin.setBackgroundResource(R.drawable.background_img_prep_red)
        }
        binding!!.idGameTvWin.text = "-$number-"
    }

    //отправить загаданное число в адаптер
    override fun setNumberForAdapter(number: Int) {
        adapterGame.setWinNumber(number)
    }

}