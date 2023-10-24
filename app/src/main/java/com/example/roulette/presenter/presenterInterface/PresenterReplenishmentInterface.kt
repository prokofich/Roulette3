package com.example.roulette.presenter.presenterInterface

import android.content.Context

interface PresenterReplenishmentInterface {
    fun loadMoney() // функция загрузки количества денег
    fun checkLastDay():Boolean // функция проверки возможности пополнения счета
    fun addMoney(money:Int) // функция добавления монет
    fun minusMoney(money:Int) // функция покупки
    fun setLastDay(day:String) // функция установки текущего дня,как последнего,когда были пополнены монеты
    fun loadNewMoneyForReplenish(context: Context,numberTextView:Int) //функция получения денежного приза
    fun checkMoneyForReplenishment():Boolean // функция проверки возможности платно пополнить счет
}