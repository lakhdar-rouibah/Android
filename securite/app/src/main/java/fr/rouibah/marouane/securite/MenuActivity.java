package fr.rouibah.marouane.securite;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import fr.rouibah.marouane.securite.classes.Menu;
import fr.rouibah.marouane.securite.classes.Nav;

/**
 * Created by utilisateur on 02/12/2018.
 */

public class MenuActivity extends Menu {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.starter();
        this.menu();

    }

}
