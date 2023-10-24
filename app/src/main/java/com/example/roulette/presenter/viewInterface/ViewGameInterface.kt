package com.example.roulette.presenter.viewInterface

interface ViewGameInterface {
    fun showRandomNumber(number:Int) // показать ранее загруженное число
    fun setNumberForAdapter(number: Int) // отправить число в адаптер
}