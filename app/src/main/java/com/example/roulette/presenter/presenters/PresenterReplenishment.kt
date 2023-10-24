package com.example.roulette.presenter.presenters

import android.content.Context
import com.example.roulette.model.constant.LIST_MONEY_FOR_REPLENISHMENT
import com.example.roulette.model.constant.repository
import com.example.roulette.presenter.presenterInterface.PresenterReplenishmentInterface
import com.example.roulette.presenter.viewInterface.ViewReplenishmentInterface

class PresenterReplenishment(private val view: ViewReplenishmentInterface):PresenterReplenishmentInterface {

    //загрузка + показ количества монет
    override fun loadMoney() {
        view.showMoney(money = repository.getMoneyInCashAccount())
    }

    //проверка на возможность пополнения монет
    override fun checkLastDay(): Boolean {
        return repository.getCurrentDay()!=repository.getLastDay()
    }

    //добавление денег к счету
    override fun addMoney(money: Int) {
        repository.addMoneyInCashAccount(money = money)
    }

    //покупка
    override fun minusMoney(money: Int) {
        repository.minusMoneyInCashAccount(money = money)
        view.showMoney(money = repository.getMoneyInCashAccount())
    }

    //установка текущего дня,как последнего,как последнего когда был пополнен счет
    override fun setLastDay(day: String) {
        repository.setLastDay(day = day)
    }

    //загрузка денег для добавления к счету + показ сообщений об этом
    override fun loadNewMoneyForReplenish(context: Context,numberTextView:Int) {
        val newMoney = LIST_MONEY_FOR_REPLENISHMENT.shuffled()[0]
        view.showToast(context = context, message = "+$newMoney$!")
        view.showNewMoney(newMoney = newMoney, numberTextView = numberTextView)
        view.setEnableButtons(false)
        addMoney(money = newMoney)
        loadMoney()
        setLastDay(day = repository.getCurrentDay())
    }

    //проверка возможности платно пополнить счет
    override fun checkMoneyForReplenishment(): Boolean {
        return repository.getMoneyInCashAccount()>=150
    }

}