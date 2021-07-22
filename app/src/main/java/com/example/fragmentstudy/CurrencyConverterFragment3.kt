package com.example.fragmentstudy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.lang.Exception

class CurrencyConverterFragment3 : Fragment() {

    interface CurrencyCalculationListener {
        fun onCalculate(result: Double,amount: Double,from: String,to: String)
    }
    lateinit var listener: CurrencyConverterFragment3.CurrencyCalculationListener

    override fun onAttach(context: Context) {  //자기가 붙어있는 호스트 액티비티를 가져옴
        super.onAttach(context)

        if(activity is CurrencyCalculationListener){ //is : 타입 확인 연산자
            listener = activity as CurrencyCalculationListener
        }else{
            throw Exception("CurrencyCalculationListener 미구현")
        }
    }


    lateinit var fromCurrency: String
    lateinit var toCurrency: String

    companion object {
        // CurrencyConverterFragment2.newInstance("USD", "KRW")
        fun newInstance(from: String, to: String) : CurrencyConverterFragment3 {
            val fragment = CurrencyConverterFragment3()

            // Bundle은 그냥 Map이라고 생각
            val args = Bundle()
            args.putString("from", from)
            args.putString("to", to)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.currency_converter_fragment2, container, false)

        val calculateBtn = view.findViewById<Button>(R.id.calculate)
        val amount = view.findViewById<EditText>(R.id.amount)
        val result = view.findViewById<TextView>(R.id.result)

        fromCurrency = arguments?.getString("from", "USD")!!
        toCurrency = arguments?.getString("to", "USD")!!

        view.findViewById<TextView>(R.id.exchange_type).text =
            "${fromCurrency} => ${toCurrency} 변환"

        calculateBtn.setOnClickListener {
            val amount = amount.text.toString().toDouble()
            listener.onCalculate(calculateCurrency(
                amount,
                fromCurrency,
                toCurrency
            ), amount, fromCurrency, toCurrency)
        }


        return view
    }

    private val currencyExchangeMap = mapOf("USD" to 1.0, "EUR" to 0.9, "JPY" to 110.0, "KRW" to 1150.0)

    private fun calculateCurrency(amount: Double, from: String, to: String) : Double {
        // 먼저 미국 달러(USD) 단위의 통화값으로 변경
        var USDAmount = if(from != "USD") (amount / currencyExchangeMap[from]!!) else amount
        // 미국 달러(USD) 값을 기준으로 통화값을 계산하여 반환
        return currencyExchangeMap[to]!! * USDAmount
    }
}

















