package com.example.roulette.presenter.presenters

import android.content.Context
import com.example.roulette.model.constant.repository
import com.example.roulette.presenter.presenterInterface.PresenterPreparationInterface
import com.example.roulette.presenter.viewInterface.ViewPreparationInterface

class PresenterPreparation(private val view: ViewPreparationInterface):PresenterPreparationInterface {

    private var myBet = 0
    private var factor = 0
    private var flagRedOrBlack = false
    private var flagEvenOrOdd = false
    private var outcomeRedOrBlack = ""
    private var outcomeEvenOrOdd = ""

    //загрузка + показ количества монет
    override fun loadMoney() {
        view.showMoney(money = repository.getMoneyInCashAccount())
    }

    //увеличение ставки
    override fun addMoneyForBet(context: Context) {
        if(myBet+20 <= repository.getMoneyInCashAccount()){
            myBet+=20
            view.showMoneyForBet(money = myBet)
        }else{
            view.showToast(context = context,message = "you don't have that much money")
        }
    }

    //уменьшение ставки
    override fun minusMoneyForBet(context: Context) {
        if(myBet > 20){
            myBet-=20
            view.showMoneyForBet(money = myBet)
        }else{
            view.showToast(context = context, message = "minimum bet 20$")
        }
    }

    //исход на цвет
    override fun setFlagRedOrBlack(flag: Boolean) {
        flagRedOrBlack = flag
        if(flagRedOrBlack && flagEvenOrOdd){
            factor = 4
            view.showCoefficientBet(coefficient = "factor:x4")
        }else{
            if(flagRedOrBlack || flagEvenOrOdd){
                factor = 2
                view.showCoefficientBet(coefficient = "factor:x2")
            }else{
                factor = 0
                view.showCoefficientBet(coefficient = "select outcome")
            }
        }
    }

    //исход на четность
    override fun setFlagEvenOrOdd(flag: Boolean) {
        flagEvenOrOdd = flag
        if(flagRedOrBlack && flagEvenOrOdd){
            factor = 4
            view.showCoefficientBet(coefficient = "factor:x4")
        }else{
            if(flagRedOrBlack || flagEvenOrOdd){
                factor = 2
                view.showCoefficientBet(coefficient = "factor:x2")
            }else{
                factor = 0
                view.showCoefficientBet(coefficient = "select outcome")
            }
        }
    }

    //установка конкретного исхода
    override fun setOutcomeRedOrBlack(outcome:String) {
        outcomeRedOrBlack = outcome
    }

    //установка конкретного исхода
    override fun setOutcomeEvenOrOdd(outcome: String) {
        outcomeEvenOrOdd = outcome
    }

    //проверка ставки+переход к игре
    override fun checkBetForGame(context: Context) {
        if(myBet>=20 && (flagEvenOrOdd || flagRedOrBlack)){
            repository.outcomeEvenOrOdd = outcomeEvenOrOdd
            repository.outcomeRedOrBlack = outcomeRedOrBlack
            repository.factor = factor
            repository.bet = myBet
            repository.minusMoneyInCashAccount(myBet)
            repository.showGameFragment()
        }else{
            view.showToast(context = context, message = "minimum bet 20$ and select outcome")
        }
    }

}