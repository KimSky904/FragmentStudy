package com.example.fragmentstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(),CurrencyConverterFragment3.CurrencyCalculationListener {


    override fun onCalculate(result: Double, amount: Double, from: String, to: String) {
        Toast.makeText(this,"${result} ${from} ${to}",Toast.LENGTH_SHORT).show()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()

        //프래그먼트 추가하기
        //첫번째 인자 : 프래그먼트가 부착될 뷰그룹의 식별자
        //두번째 인자 : 프래그먼트 객체
        //transaction.add(R.id.fragment_container,CurrencyConverterFragment1())

        transaction.add(R.id.fragment_container,
            CurrencyConverterFragment2.newInstance("USD", "KRW"))
        transaction.add(R.id.fragment_container,
            CurrencyConverterFragment2.newInstance("JPY", "KRW"))


        transaction.commit()
    }

}