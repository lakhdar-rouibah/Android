package fr.rouibah.marouane.securite.classes;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;

import fr.rouibah.marouane.securite.MainActivity;
import fr.rouibah.marouane.securite.MainDisplayActivity;
import fr.rouibah.marouane.securite.MenuActivity;
import fr.rouibah.marouane.securite.MessageActivity;
import fr.rouibah.marouane.securite.PlanningActivity;
import fr.rouibah.marouane.securite.PointsActivity;
import fr.rouibah.marouane.securite.R;

/**
 * Created by utilisateur on 02/12/2018.
 */

public class Nav extends AppCompatActivity {

    public CountDownTimer countDownTimer = null;


        public void menu(){


            final ImageButton btn_point = (ImageButton) findViewById(R.id.point_btn);
            btn_point.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.point_btn);
                    view.startAnimation(scaleDown);

                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));

                    //Log.i("Tag", "message");
                }
            });


            final ImageButton btn_planning = (ImageButton) findViewById(R.id.planning_btn);
            btn_planning.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.planning_btn);
                    view.startAnimation(scaleDown);

                    startActivity(new Intent(getApplicationContext(), PlanningActivity.class));
                }
            });


            final ImageButton btn_stars = (ImageButton) findViewById(R.id.stars_btn);
            btn_stars.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.stars_btn);
                    view.startAnimation(scaleDown);

                    startActivity(new Intent(getApplicationContext(), PointsActivity.class));
                }
            });


            final ImageButton btn_book = (ImageButton) findViewById(R.id.book_btn);
            btn_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.book_btn);
                    view.startAnimation(scaleDown);

                    startActivity(new Intent(getApplicationContext(), MainDisplayActivity.class));
                }
            });


            final ImageButton btn_message = (ImageButton) findViewById(R.id.message_btn);
            btn_message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.message_btn);
                    view.startAnimation(scaleDown);

                    startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                }
            });


            final ImageButton btn_logout = (ImageButton) findViewById(R.id.logout_btn);
            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.logout_btn);
                    view.startAnimation(scaleDown);

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });

        }



    public void display(String message, String type){

        LinearLayout msg = (LinearLayout) findViewById(R.id.alert_bar);
        final TextView alert_text = (TextView) findViewById(R.id.alert_text);
        alert_text.setText(message);

        switch (type){

            case "danger":
                msg.setBackgroundColor(Color.RED);
                alert_text.setTextColor(Color.WHITE);

                break;
            case "alert":
                msg.setBackgroundColor(Color.YELLOW);
                alert_text.setTextColor(Color.BLACK);
                break;
            case "info":
                msg.setBackgroundColor(Color.GREEN);
                alert_text.setTextColor(Color.BLACK);
                break;

        }


        ObjectAnimator animation = ObjectAnimator.ofFloat(msg, "translationY", -70f);
        animation.setInterpolator (new EasingInterpolator(Ease.QUAD_OUT));
        animation.setStartDelay (500);
        animation.setDuration (1500);
        animation.setDuration(800);
        animation.start();

        countDownTimer = new CountDownTimer(10 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {


            }
            public void onFinish() {

                LinearLayout msg = (LinearLayout) findViewById(R.id.alert_bar) ;
                ObjectAnimator animation = ObjectAnimator.ofFloat(msg, "translationY", -150f);
                animation.setInterpolator (new EasingInterpolator(Ease.QUAD_OUT));
                animation.setStartDelay (500);
                animation.setDuration (1500);
                animation.setDuration(800);
                animation.start();
                countDownTimer.cancel();
            }
        };

        countDownTimer.start();
    }
}
