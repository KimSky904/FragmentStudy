package com.example.fragmentstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(),CurrencyConverterFragment3.CurrencyCalculationListener {
//    # 프래그먼트 정리
//
//    * 프래그먼트로 값을 전달하려면 => newInstance 클래스 메소드 구현하고 그곳으로 값을 전달 후 번들을 통해 프래그먼트로 값 전달 (arguments 속성 대입)
//    * 프래그먼트에서 값을 반환받으려면 => 호스트 액티비티 측에서 인터페이스 구현 후, 프래그먼트 측에서는 해당 인터페이스를 구현하고 있는 액티비티로 추상 메소드를 호출하며 값을 전달


    override fun onCalculate(result: Double,
                             amount: Double,
                             from: String,
                             to: String) {
        Toast.makeText(this, "${result} ${from} ${to}",
            Toast.LENGTH_SHORT)
            .show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()

        //프래그먼트 추가하기
        //첫번째 인자 : 프래그먼트가 부착될 뷰그룹의 식별자
        //두번째 인자 : 프래그먼트 객체
        //transaction.add(R.id.fragment_container,CurrencyConverterFragment1())
/*
        transaction.add(R.id.fragment_container,
            CurrencyConverterFragment2.newInstance("USD", "KRW"))
        transaction.add(R.id.fragment_container,
            CurrencyConverterFragment2.newInstance("JPY", "KRW"))
*/
        transaction.add(R.id.fragment_container,
            CurrencyConverterFragment3.newInstance("USD", "KRW"))

        transaction.add(R.id.fragment_container,
            CurrencyConverterFragment3.newInstance("JPY", "KRW"))

        transaction.commit()
    }

}