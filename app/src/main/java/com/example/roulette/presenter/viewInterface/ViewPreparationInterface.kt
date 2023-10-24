package com.example.roulette.presenter.viewInterface

import android.content.Context

interface ViewPreparationInterface {
    fun showMoney(money:Int) // функция показа количества денег
    fun showMoneyForBet(money: Int) // функция показа ставки
    fun showToast(context: Context, message:String) // функция показа всплывающего сообщения
    fun showCoefficientBet(coefficient:String) // функция показа итогового коэффициента
}