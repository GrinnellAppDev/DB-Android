package edu.grinnell.appdev.grinnelldirectory.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import edu.grinnell.appdev.grinnelldirectory.R
import kotlinx.android.synthetic.main.activity_splash2.*

//import kotlinx.android.synthetic.main.splash_screen.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
        var fadeIn = AnimationUtils.loadAnimation(this, R.anim.anim_demo)
        var fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        splashscreen.startAnimation(fadeOut)

        fadeOut.setAnimationListener(
                object: Animation.AnimationListener {
                    override fun onAnimationRepeat(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        logoImg.visibility = View.VISIBLE
                        logoImg.startAnimation(fadeIn)
                    }

                    override fun onAnimationStart(p0: Animation?) {

                    }
                }
        )
        fadeIn.setAnimationListener(
                object: Animation.AnimationListener {
                    override fun onAnimationRepeat(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        val mainIntent = Intent(this@SplashActivity, SearchPagerActivity::class.java)

                        this@SplashActivity.startActivity(mainIntent)
                        this@SplashActivity.finish()
                    }

                    override fun onAnimationStart(p0: Animation?) {

                    }
                }
        )
    }

}
