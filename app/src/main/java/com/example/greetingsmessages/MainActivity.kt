package com.example.greetingsmessages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.greetingsmessages.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        mainActivityBinding.btnHelloWorld.setOnClickListener {
            initGreetingsMessages()
        }
    }

    private fun initGreetingsMessages() {
    //    mainActivityBinding.greetingImagesId.setGreetingsHeading("This is a greeting Message")
        mainActivityBinding.greetingImagesId.setImageDrawbleAndHeading("https://picsum.photos/200/300" , "This is a greeting Message")
        mainActivityBinding.btnHelloWorld.visibility =  View.GONE
    }
}