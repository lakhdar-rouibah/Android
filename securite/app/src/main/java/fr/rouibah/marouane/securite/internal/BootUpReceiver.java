package fr.rouibah.marouane.securite.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.rouibah.marouane.securite.MainActivity;

/**
 * Created by utilisateur on 13/12/2018.
 */





public class BootUpReceiver extends BroadcastReceiver
{
    public static String Identifier = "fromBootUp";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(Identifier, true);
        context.startActivity(i);
    }
}