package com.example.roulette.model.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.roulette.R
import com.example.roulette.model.constant.LIST_NUMBERS_FOR_ADAPTER
import com.example.roulette.model.constant.OUTCOME_BLACK
import com.example.roulette.model.constant.OUTCOME_EVEN
import com.example.roulette.model.constant.OUTCOME_ODD
import com.example.roulette.model.constant.OUTCOME_RED
import com.example.roulette.model.constant.repository

class GameAdapter(private val context: Context):RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val list = LIST_NUMBERS_FOR_ADAPTER
    private var winNumber = 0
    class GameViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv,parent,false)
        return GameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.id_item_rv_tv)
        textView.text = "${list[position]}"
        val cs = holder.itemView.findViewById<ConstraintLayout>(R.id.id_item_rv_cs)
        if(position%2==0){
            cs.setBackgroundResource(R.drawable.background_img_prep_red)
        }else{
            cs.setBackgroundResource(R.drawable.background_img_prep_black)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewAttachedToWindow(holder: GameViewHolder) {
        super.onViewAttachedToWindow(holder)
        val textView = holder.itemView.findViewById<TextView>(R.id.id_item_rv_tv)
        if(winNumber!=0){
            if(holder.adapterPosition == winNumber-1){
                textView.text = "-${list[holder.adapterPosition]}-"
            }
            if(repository.factor == 2){
                if((repository.outcomeRedOrBlack == OUTCOME_RED && winNumber%2!=0)
                    || (repository.outcomeRedOrBlack == OUTCOME_BLACK && winNumber%2==0)
                    || (repository.outcomeEvenOrOdd == OUTCOME_EVEN && winNumber%2==0)
                    || (repository.outcomeEvenOrOdd == OUTCOME_ODD && winNumber%2!=0)){
                    repository.showToast(context,"+${repository.bet * 2}$")
                    repository.addMoneyInCashAccount(repository.bet*2)
                }else{
                    repository.showToast(context,"-${repository.bet}$ ,YOU LOSE")
                }
            }else{
                if(repository.factor == 4){
                    if(((repository.outcomeRedOrBlack == OUTCOME_RED && winNumber%2!=0)
                                ||(repository.outcomeRedOrBlack == OUTCOME_BLACK && winNumber%2==0))
                        &&((repository.outcomeEvenOrOdd == OUTCOME_EVEN && winNumber%2==0)
                                ||(repository.outcomeEvenOrOdd == OUTCOME_ODD && winNumber%2!=0))){
                        repository.showToast(context,"+${repository.bet * 4}$")
                        repository.addMoneyInCashAccount(repository.bet*4)
                    }else{
                        repository.showToast(context,"-${repository.bet}$ ,YOU LOSE")
                    }
                }
            }
            repository.factor = 0
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setWinNumber(number:Int){
        winNumber = number
        notifyDataSetChanged()
    }

}