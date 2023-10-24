package com.example.roulette.presenter.presenters

import com.example.roulette.model.constant.repository
import com.example.roulette.presenter.presenterInterface.PresenterMenuInterface
import com.example.roulette.presenter.viewInterface.ViewMenuInterface

class PresenterMenu(private val view: ViewMenuInterface):PresenterMenuInterface {

    //загрузка + показ количества монет
    override fun loadMoney() {
        view.showMoney(repository.getMoneyInCashAccount())
    }

}