package com.example.roulette.presenter.viewInterface

import android.content.Context

interface ViewReplenishmentInterface {
    fun showMoney(money:Int) // функция показа количества денег
    fun showToast(context: Context, message:String) // функция показа всплывающего сообщения
    fun setVisibleButtons(visible:Boolean) // функция показа или скрытия кнопок для выбора приза
    fun setEnableButtons(enable:Boolean) // функция включения/выключения кнопок
    fun hideTextView() // функция скрытия текстовых полей с денежными призами
    fun showNewMoney(newMoney:Int,numberTextView:Int) // функция показа денежного приза
}