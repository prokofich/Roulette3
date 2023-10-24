package com.example.roulette.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.roulette.R
import com.example.roulette.databinding.ActivityMainBinding
import com.example.roulette.model.constant.APP_PREFERENCES
import com.example.roulette.model.constant.LAST_DAY
import com.example.roulette.model.constant.MAIN
import com.example.roulette.model.constant.MY_CASH_ACCOUNT
import com.example.roulette.view.fragments.MenuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //первоначальный показ фрагмента меню
        replaceFragment(MenuFragment())

        MAIN = this

        //полноэкранный режим
        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }

    //функция замены фрагмента на экране
    fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }

    //функция добавления денег к счету
    fun addMoneyInCashAccount(money:Int){
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        preferences.edit()
            .putInt(MY_CASH_ACCOUNT,getMoneyInCashAccount()+money)
            .apply()
    }

    //функция получения денежного счета
    fun getMoneyInCashAccount(): Int {
        return getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE).getInt(MY_CASH_ACCOUNT,0)
    }

    //функция уменьшения денег на счете
    fun minusMoneyInCashAccount(money:Int){
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        preferences.edit()
            .putInt(MY_CASH_ACCOUNT,getMoneyInCashAccount()-money)
            .apply()
    }

    //функция получения последнего дня,когда был пополнен счет
    fun getLastDay(): String {
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE).getString(LAST_DAY,"")
        return preferences ?: ""
    }

    //функция установки последнего дня,когда был пополнен счет
    fun setLastDay(day:String){
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        preferences.edit()
            .putString(LAST_DAY,day)
            .apply()
    }

}