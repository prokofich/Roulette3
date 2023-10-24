package com.example.roulette.presenter.presenterInterface

import android.content.Context

interface PresenterPreparationInterface {
    fun loadMoney() // функция загрузки количества денег
    fun addMoneyForBet(context: Context) // функция увеличения ставки
    fun minusMoneyForBet(context: Context) // функция уменьшения ставки
    fun setFlagRedOrBlack(flag:Boolean) // установить исход на цвет
    fun setFlagEvenOrOdd(flag:Boolean) // установить исход на четность очков
    fun setOutcomeRedOrBlack(outcome:String) // установить конкретный исход на цвет
    fun setOutcomeEvenOrOdd(outcome: String) // установить конкретный исход на четность
    fun checkBetForGame(context: Context) // проверка ставки перед игрой
}