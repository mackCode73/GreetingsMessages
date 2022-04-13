package com.example.greetings_messages

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.updateLayoutParams


class GreetingMessages(context: Context, attrsSet: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrsSet, defStyleAttr) {

    constructor(context: Context, attrsSet: AttributeSet? = null) : this(
        context = context,
        attrsSet = attrsSet,
        defStyleAttr = 0
    )

    constructor(context: Context) : this(context = context, attrsSet = null, defStyleAttr = 0)


    var greetingHeadingTextView: TextView? = null
    var greetingHeadingImageView: CircleImageView? = null
    val Int.px get() = (this * Resources.getSystem().displayMetrics.density).toInt()


    fun setImageDrawbleAndHeading(imageUrl: String, greetingMsg: String) {

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

        greetingHeadingImageView = CircleImageView(context)
        greetingHeadingImageView!!.id = R.id.item1
        greetingHeadingImageView!!.apply {
            this.layoutParams = ViewGroup.LayoutParams(
                150.px,
                150.px
            )
            this.maxWidth = 100
            this.maxHeight = 100
            this.setPadding(50, 50, 50, 50)
        }


            if (imageUrl.isNotEmpty()) {
                Glide
                    .with(context)
                    .load(imageUrl)
                    .into(greetingHeadingImageView!!)

                /*  Glide
                      .with(context)
                      .asBitmap()
                      .load(imageUrl)
                      .into(object : CustomTarget<Bitmap>(){
                          override fun onResourceReady(
                              resource: Bitmap,
                              transition: Transition<in Bitmap>?
                          ) {
                              imageBitmap = resource
                          }

                          override fun onLoadCleared(placeholder: Drawable?) {
                              TODO("Not yet implemented")
                          }

                      })*/
            }

            //    imageBitmapTemp = imageBitmap?.let { it1 -> writeOnBitmap(it1 , greetingMsg) }
            //   greetingsBgViewBinding[index].setImageBitmap(imageBitmap)
            addView(greetingHeadingImageView)

            //setup textview constraints
            val constraintLayout  = ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT)
            constraintLayout.startToEnd = greetingHeadingImageView!!.id
            constraintLayout.endToEnd = ConstraintSet.PARENT_ID
            constraintLayout.topToTop = ConstraintSet.PARENT_ID
            constraintLayout.topMargin = 50



            //     adding TextView
            greetingHeadingTextView = TextView(context)
            greetingHeadingTextView?.apply {
                greetingHeadingTextView?.setPadding(10, 10, 10, 10)
                greetingHeadingTextView?.text = greetingMsg
                greetingHeadingTextView!!.layoutParams = constraintLayout
            }
         //   removeAllViews()
            addView(greetingHeadingTextView)

        }
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

    /*private fun writeOnBitmap(bitmap: Bitmap, greetingMsg: String): Bitmap {
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
            Bitmap.createBitmap(bitmap.width + 200, bitmap.height + 200, bitmap.config)
        val canvas = Canvas(resultImage)
        canvas.drawBitmap(bitmap, 0f, 0f, null)

        val paint = Paint()
        paint.isAntiAlias = (true)
        paint.color = (color)
        paint.textSize = (30f)
        canvas.drawRect(
            0f,
            bitmap.height.toFloat(),
            bitmap.width.toFloat(),
            bitmap.height + 100.toFloat(),
            paint
        )

        paint.color = palette.getLightVibrantColor(
            ContextCompat.getColor(
                context,
                R.color.design_default_color_secondary_variant
            )
        )
        canvas.drawText(greetingMsg, 100f, bitmap.height.toFloat() + 40, paint)
        canvas.drawText(
            "Contact us : ${greetingMsg}",
            100f,
            bitmap.height.toFloat() + 40 + 40,
            paint
        )
        paint.color = ContextCompat.getColor(context, R.color.design_default_color_primary_dark)
        canvas.drawCircle(45f, bitmap.height.toFloat() + 50f, 30f, paint)
        val path = Path()
        path.apply {
            addCircle(
                45f, bitmap.height.toFloat() + 50f,
                25f,
                Path.Direction.CCW
            )
        }

        // draw circular bitmap on canvas
        canvas.clipPath(path)
        canvas.drawBitmap(
            smallProfileImage, 20f,
            bitmap.height.toFloat() + 25f, null
        )

        return resultImage

    }*/
