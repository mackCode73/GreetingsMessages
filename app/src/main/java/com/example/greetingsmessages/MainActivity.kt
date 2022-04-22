package com.example.greetingsmessages

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
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
        mainActivityBinding.greetingImagesId.setImageDrawbleAndHeading("https://picsum.photos/200/300/?blur=2" , "This is a greeting Message")
       /* val bitmap =
            AppCompatResources.getDrawable(this, R.drawable.ic_baseline_local_activity_24)?.toBitmap()
        mainActivityBinding.greetingImagesId.getUriFromBitmap(bitmap , "Testing Message" , this)*/
        mainActivityBinding.btnHelloWorld.visibility = View.GONE
    }
}