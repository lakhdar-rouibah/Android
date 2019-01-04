package fr.rouibah.marouane.securite;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import fr.rouibah.marouane.securite.classes.Nav;

public class AlertActivity extends Nav {

    List<String[]> contactList ;
    String date, sender, message;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        this.menu();

        sendSMS("0651294448", "Bonjour marouane c'est moi");
        //new AlertActivity.GetContacts().execute();
    }



    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute () {
            super.onPreExecute();
            Toast.makeText(AlertActivity.this, "Chargement des données en cours", Toast.LENGTH_LONG).show();

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected Void doInBackground (Void... arg0) {


            AlertActivity.HttpHandler sh = new AlertActivity.HttpHandler();
            // Making a request to url and getting response
            String url = "https://marouane.rouibah.fr/1carte/data/message.php?var=azerty";

            String jsonStr = sh.makeServiceCall(url);

            Log.e("TAG", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {


                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("messages");


                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        date = c.getString("date");
                        sender = c.getString("sender");
                        message = c.getString("message");

                        String[] _message = {date, sender, message};


                        contactList.add(_message);


                    }

                } catch (final JSONException e) {
                    Log.e("TAG", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run () {
                            Toast.makeText(getApplicationContext(),
                                           "Json parsing error: " + e.getMessage(),
                                           Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e("TAG", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run () {
                        Toast.makeText(getApplicationContext(),
                                       "Couldn't get json from server. Check LogCat for possible errors!",
                                       Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute (Void result) {
            super.onPostExecute(result);


        }

    }


    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                           Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                           Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public class HttpHandler {

        //private final String TAG = MessageActivity.HttpHandler.class.getSimpleName();

        public HttpHandler() {
        }

        public String makeServiceCall(String reqUrl) {
            String response = null;
            try {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                // read the response
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
            } catch (MalformedURLException e) {
                Log.e("TAG", "MalformedURLException: " + e.getMessage());
            } catch (ProtocolException e) {
                Log.e("TAG", "ProtocolException: " + e.getMessage());
            } catch (IOException e) {
                Log.e("TAG", "IOException: " + e.getMessage());
            } catch (Exception e) {
                Log.e("TAG", "Exception: " + e.getMessage());
            }
            return response;
        }

        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return sb.toString();
        }
    }
}
