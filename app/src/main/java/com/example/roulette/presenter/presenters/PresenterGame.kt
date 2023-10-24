package com.example.roulette.presenter.presenters

import com.example.roulette.model.constant.LIST_NUMBERS_FOR_ADAPTER
import com.example.roulette.presenter.presenterInterface.PresenterGameInterface
import com.example.roulette.presenter.viewInterface.ViewGameInterface

class PresenterGame(private val view: ViewGameInterface):PresenterGameInterface {

    //загрузка случайного числа
    override fun loadRandomNumber() {
        val winNumber = LIST_NUMBERS_FOR_ADAPTER.shuffled()[0]
        view.setNumberForAdapter(winNumber)
        view.showRandomNumber(winNumber)
    }

}