package com.example.roulette.model

import android.content.Context
import android.widget.Toast
import com.example.roulette.model.constant.MAIN
import com.example.roulette.view.fragments.GameFragment
import com.example.roulette.view.fragments.MenuFragment
import com.example.roulette.view.fragments.PreparationGameFragment
import com.example.roulette.view.fragments.ReplenishmentFundsFragment
import com.example.roulette.view.fragments.RulesFragment
import java.util.Calendar

class Repository {

    var outcomeRedOrBlack = ""
    var outcomeEvenOrOdd = ""
    var factor = 0
    var bet = 0

    //функция показа всплывающего сообщения
    fun showToast(context: Context,message:String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    //показ фрагмента меню
    fun showMenuFragment(){
        MAIN.replaceFragment(MenuFragment())
    }

    //показ фрагмента пополнения средств
    fun showReplenishmentFundsFragment(){
        MAIN.replaceFragment(ReplenishmentFundsFragment())
    }

    //показ фрагмента подготовки к игре
    fun showPreparationGameFragment(){
        MAIN.replaceFragment(PreparationGameFragment())
    }

    //показ фрагмента с правилами игры
    fun showRulesFragment(){
        MAIN.replaceFragment(RulesFragment())
    }

    //показ фрагмента игры
    fun showGameFragment(){
        MAIN.replaceFragment(GameFragment())
    }

    //выход из приложения
    fun exitingApplication(){
        MAIN.finishAffinity()
    }

    //функция добавления денег к счету
    fun addMoneyInCashAccount(money:Int){
        MAIN.addMoneyInCashAccount(money = money)
    }

    //функция получения денежного счета
    fun getMoneyInCashAccount(): Int {
        return MAIN.getMoneyInCashAccount()
    }

    //функция уменьшения денег на счете
    fun minusMoneyInCashAccount(money:Int){
        MAIN.minusMoneyInCashAccount(money = money)
    }

    //функция получения последнего дня,когда был пополнен счет
    fun getLastDay(): String {
        return MAIN.getLastDay()
    }

    //функция установки последнего дня,когда был пополнен счет
    fun setLastDay(day:String){
        MAIN.setLastDay(day = day)
    }

    // функция получения текущей даты
    fun getCurrentDay(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)+1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return "$year.$month.$day"
    }

    fun createJsonFile(namePhone: String, locale: String, id: String): String {
        return """
                      {
                       "phone_name": "$namePhone",
                       "locale": "$locale",
                       "unique": "$id"
                      }
                      """.trimIndent()
    }

}