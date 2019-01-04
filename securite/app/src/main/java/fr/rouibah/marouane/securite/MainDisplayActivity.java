package fr.rouibah.marouane.securite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.rouibah.marouane.securite.classes.Nav;

public class MainDisplayActivity extends Nav {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);

        this.menu();
    }
}
