package fr.rouibah.marouane.securite.classes;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import fr.rouibah.marouane.securite.AlertActivity;
import fr.rouibah.marouane.securite.MainCouranteActivity;
import fr.rouibah.marouane.securite.R;

/**
 * Created by utilisateur on 03/12/2018.
 */

public class Menu extends Nav {

        public  void starter(){

            ImageButton btn_danger = findViewById(R.id.danger_btn);
            btn_danger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.danger_btn);
                    view.startAnimation(scaleDown);

                    display("bonjour", "info");

                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });

            ImageButton btn_ronde = findViewById(R.id.ronde_btn);
            btn_ronde.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.ronde_btn);
                    view.startAnimation(scaleDown);

                    startActivity(new Intent(getApplicationContext(), MainCouranteActivity.class));
                }
            });

            ImageButton btn_alert = findViewById(R.id.alert_btn);
            btn_alert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.alert_btn);
                    view.startAnimation(scaleDown);

                    startActivity(new Intent(getApplicationContext(), AlertActivity.class));
                }
            });

            ImageButton btn_rs = findViewById(R.id.rs_btn);
            btn_rs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.rs_btn);
                    view.startAnimation(scaleDown);

                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });

            ImageButton btn_pointer = findViewById(R.id.pointer_btn);
            btn_pointer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.pointer_btn);
                    view.startAnimation(scaleDown);

                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });

            ImageButton btn_edit = findViewById(R.id.editer_btn);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {


                    Animation scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    ImageButton view = findViewById(R.id.editer_btn);
                    view.startAnimation(scaleDown);

                    startActivity(new Intent(getApplicationContext(), MainCouranteActivity.class));
                }
            });


        }

}
