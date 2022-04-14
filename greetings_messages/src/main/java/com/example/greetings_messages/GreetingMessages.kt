package com.example.greetings_messages

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.updateLayoutParams
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import de.hdodenhof.circleimageview.BuildConfig.APPLICATION_ID
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File
import java.io.FileOutputStream
import java.util.*


class GreetingMessages(context: Context, attrsSet: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrsSet, defStyleAttr) {

    constructor(context: Context, attrsSet: AttributeSet? = null) : this(
        context = context,
        attrsSet = attrsSet,
        defStyleAttr = 0
    )

    constructor(context: Context) : this(context = context, attrsSet = null, defStyleAttr = 0)


    var greetingHeadingTextView: TextView? = null
    var greetingHeadingImageView: ImageView? = null
    val Int.px get() = (this * Resources.getSystem().displayMetrics.density).toInt()
    // var imageBitmap: Bitmap? = null
    var imageUri : Uri? = null


    fun setImageDrawbleAndHeading(imageUrl: String, greetingMsg: String) {

        var imageBitmap: Bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)

        // set-up parent constraint layout
          this.updateLayoutParams<LayoutParams> {
              this.height = ConstraintSet.WRAP_CONTENT
              this.width = ConstraintSet.WRAP_CONTENT
              this.startToStart = ConstraintSet.START
              this.topToTop   = ConstraintSet.TOP
              this.endToEnd = ConstraintSet.END
              this.bottomToBottom = ConstraintSet.BOTTOM
              this.matchConstraintMinHeight = 300
              this.marginStart = 20
              this.marginEnd = 20
              this.circleRadius = 20
                    //add other constraints if needed
                }

        greetingHeadingImageView = ImageView(context)
        greetingHeadingImageView!!.id = R.id.item1
        greetingHeadingImageView!!.apply {
            this.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            this.maxWidth = 400
            this.maxHeight = 800
            this.setPadding(50, 50, 50, 50)
        }


            if (imageUrl.isNotEmpty()) {
               /* Glide
                    .with(context)
                    .load(imageUrl)
                    .into(greetingHeadingImageView!!)*/

                 try {
                     Glide
                         .with(context)
                         .asBitmap()
                         .load(imageUrl)
                         .into(object : CustomTarget<Bitmap>(){
                             override fun onResourceReady(
                                 resource: Bitmap,
                                 transition: Transition<in Bitmap>?
                             ) {
                                 imageBitmap = resource
                                 imageUri = getUriFromBitmap(imageBitmap , greetingMsg , context)
                                 greetingHeadingImageView!!.setImageURI(imageUri)
                             }

                             override fun onLoadCleared(placeholder: Drawable?) {
                                 TODO("Not yet implemented")
                             }

                         })
                 }catch (exception : Exception){ exception.printStackTrace() }
            }

            //getUriFromBitmap



            //    imageBitmapTemp = imageBitmap?.let { it1 -> writeOnBitmap(it1 , greetingMsg) }
            //   greetingsBgViewBinding[index].setImageBitmap(imageBitmap)
             addView(greetingHeadingImageView)

            //setup textview constraints
            /*val constraintLayout  = ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT)
            constraintLayout.startToEnd = greetingHeadingImageView!!.id
            constraintLayout.endToEnd = ConstraintSet.PARENT_ID
            constraintLayout.topToTop = ConstraintSet.PARENT_ID
            constraintLayout.topMargin = 50*/



            //     adding TextView
          /*  greetingHeadingTextView = TextView(context)
            greetingHeadingTextView?.apply {
                greetingHeadingTextView?.setPadding(10, 10, 10, 10)
                greetingHeadingTextView?.text = greetingMsg
                greetingHeadingTextView!!.layoutParams = constraintLayout*/
            }

    private fun getUriFromBitmap(imageBitmap: Bitmap?, greetingMsg: String, context: Context): Uri? {
        val bmp = writeOnBitmap(imageBitmap as Bitmap , greetingMsg , context)
        val imagefolder = File(context.cacheDir, "images")
        var uri: Uri? = null
        try {
            Log.v(GreetingMessages::class.qualifiedName, "getUriFromBitmap() try block...")
            imagefolder.mkdirs()
            val file = File(imagefolder, "shared_image.png")
            val outputStream = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.PNG, 90, outputStream)
            outputStream.flush()
            outputStream.close()
         //   uri = FileProvider.getUriForFile(context,"com.example.greetingsmessages", file)
           uri =  FileProvider.getUriForFile(Objects.requireNonNull(context), "com.example.greetingsmessages" + ".provider", file)
        } catch (e: Exception) {
            Log.v(GreetingMessages::class.qualifiedName, "getUriFromBitmap() $e")
            Toast.makeText(context, "${e.message}", Toast.LENGTH_LONG).show()
        }
        Log.v(GreetingMessages::class.qualifiedName, "getUriFromBitmap() return uri is $uri")
        return uri
    }
    //   removeAllViews()
   //            addView(greetingHeadingTextView)

        }

    // extension function to get bitmap from url
    /* private fun imageUrltoBitmap(imageUrl: String): Bitmap? {
         return try {
             BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream())
         }catch (e:IOException){
             null


             this.height = ConstraintSet.WRAP_CONTENT
                    this.width = ConstraintSet.WRAP_CONTENT
                    this.startToStart = ConstraintSet.START
                    this.topToTop   = ConstraintSet.TOP
                    this.endToEnd = ConstraintSet.END
                    this.bottomToBottom = ConstraintSet.BOTTOM
                    this.matchConstraintMinHeight = 300
                    this.marginStart = 20
                    this.marginEnd = 20
                    this.circleRadius = 20
         }
     }*/

    private fun writeOnBitmap(bitmap: Bitmap, greetingMsg: String, context: Context): Bitmap {
        Log.v(GreetingMessages::class.qualifiedName, "writeOnBitmap()")
        val smallProfileImage = Bitmap.createScaledBitmap(
            bitmap,
            300,
            100,
            false
        )
        val palette = Palette.from(bitmap).generate()
        val color = palette.getDarkVibrantColor(
            ContextCompat.getColor(
                context,
                R.color.cardview_dark_background
            )
        )
        val resultImage =
            Bitmap.createScaledBitmap(bitmap, 700,1000 , false)
        val canvas = Canvas(resultImage)
     ///   canvas.drawBitmap(bitmap, 0f, 0f, null)

        val paint = Paint()
        paint.isAntiAlias = (true)
        paint.color = (color)
        paint.textSize = (30f)
        canvas.drawRect(
            0f,
            900f,
            700f,
            1000f,
            paint
        )

        paint.color = palette.getLightVibrantColor(
            ContextCompat.getColor(
                context,
                R.color.design_default_color_secondary_variant
            )
        )
        canvas.drawText(greetingMsg, 100f, 900f + 40, paint)
        canvas.drawText(
            "Contact us : ${greetingMsg}",
            100f,
            900f + 40 + 40,
            paint
        )
        paint.color = ContextCompat.getColor(context, R.color.design_default_color_primary_dark)
        canvas.drawCircle(45f, 900f + 50f, 30f, paint)
        val path = Path()
        path.apply {
            addCircle(
                45f, 900f + 50f,
                25f,
                Path.Direction.CCW
            )
        }

        // draw circular bitmap on canvas
        canvas.clipPath(path)
        canvas.drawBitmap(
            smallProfileImage, 20f,
            900f + 25f, null
        )

        Log.v(GreetingMessages::class.qualifiedName, "writeOnBitmap() result image is ${resultImage}")
        return resultImage

    }
