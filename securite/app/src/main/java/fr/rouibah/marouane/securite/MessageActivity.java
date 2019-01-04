package fr.rouibah.marouane.securite;
import android.content.Context;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import fr.rouibah.marouane.securite.classes.Nav;

import static android.renderscript.ScriptIntrinsicBLAS.LEFT;
import static android.renderscript.ScriptIntrinsicBLAS.RIGHT;
import static android.support.v4.view.GravityCompat.END;


public class MessageActivity extends Nav {

    //private String TAG = MessageActivity.class.getSimpleName();
    LinearLayout scroll;
    View msg;
    View sms;
    List<String[]> contactList ;
    String date, sender, message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        contactList = new ArrayList<String[]>();

        this.menu();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        new MessageActivity.GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MessageActivity.this, "Chargement des données en cours", Toast.LENGTH_LONG).show();

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected Void doInBackground(Void... arg0) {



            MessageActivity.HttpHandler sh = new MessageActivity.HttpHandler();
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
                        sender= c.getString("sender");
                        message = c.getString("message");

                        String[]  _message = {date, sender, message};





                        contactList.add(_message);




                    }

                } catch (final JSONException e) {
                    Log.e("TAG", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
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
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                       "Couldn't get json from server. Check LogCat for possible errors!",
                                       Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);


            for(int n = 0; n < contactList.size(); n++) {


                scroll = (LinearLayout) findViewById(R.id.scroll);

                if(contactList.get(n)[1].equals("me")) {

                    // it's the parent layout
                    LinearLayout father = new LinearLayout(MessageActivity.this);
                    father.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ViewGroup.LayoutParams param = father.getLayoutParams();
                    father.setLayoutParams(param);

                    father.setLayoutParams(param);
                    father.setOrientation(LinearLayout.VERTICAL);
                    father.setGravity(Gravity.RIGHT);

                    // it's the parent layout
                    LinearLayout parent = new LinearLayout(MessageActivity.this);
                    parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ViewGroup.LayoutParams params = parent.getLayoutParams();
                    params.width = 350;
                    parent.setLayoutParams(params);

                    parent.setLayoutParams(params);
                    parent.setOrientation(LinearLayout.VERTICAL);
                    parent.setBackground(ContextCompat.getDrawable(MessageActivity.this, R.drawable.bull_msg_user));
                    parent.setPadding(10, 10, 10, 10);
                    ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(parent.getLayoutParams());
                    marginParams.setMargins(0, 0, 0, 10);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(marginParams);
                    parent.setLayoutParams(layoutParams);
                    parent.setGravity(Gravity.RIGHT);


                    // it's the title layout =========================================================================================================================
                    LinearLayout title = new LinearLayout(MessageActivity.this);
                    title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    title.setOrientation(LinearLayout.HORIZONTAL);
                    title.setGravity(Gravity.LEFT/RIGHT);

                    LinearLayout sender_layout = new LinearLayout(MessageActivity.this);
                    sender_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    sender_layout.setOrientation(LinearLayout.VERTICAL);

                    TextView name = new TextView(getApplicationContext());
                    name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    name.setPadding(0, 0, 0, 0);
                    name.setText(contactList.get(n)[1]);
                    name.setTextColor(ContextCompat.getColor(MessageActivity.this, R.color.grey));
                    name.setTextSize(10);
                    ViewGroup.LayoutParams name_param = name.getLayoutParams();
                    name_param.width = 175;
                    name.setGravity(Gravity.LEFT);

                    sender_layout.addView(name);

                    LinearLayout date_layout = new LinearLayout(MessageActivity.this);
                    date_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    date_layout.setOrientation(LinearLayout.VERTICAL);

                    TextView date = new TextView(getApplicationContext());
                    date.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    date.setPadding(0, 0, 30, 0);
                    date.setText(contactList.get(n)[0]);
                    date.setTextColor(ContextCompat.getColor(MessageActivity.this, R.color.grey));
                    date.setTypeface(null, Typeface.ITALIC);
                    date.setTextSize(10);
                    date.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ViewGroup.LayoutParams date_param = date.getLayoutParams();
                    date_param.width = 175;
                    date.setGravity(Gravity.RIGHT);

                    date_layout.addView(date);
                    //=========================================================================================================================================

                    // it's message layout
                    LinearLayout message_layout = new LinearLayout(MessageActivity.this);
                    message_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    message_layout.setOrientation(LinearLayout.VERTICAL);

                    TextView message = new TextView(getApplicationContext());
                    message.setText(contactList.get(n)[2]);
                    message.setTextColor(ContextCompat.getColor(MessageActivity.this, R.color.black));
                    message.setPadding(0, 10, 0, 0);
                    message.setTextSize(12);

                    message_layout.addView(message);


                    // it's add layout
                    title.addView(sender_layout);
                    title.addView(date_layout);
                    parent.addView(title);
                    parent.addView(message_layout);
                    father.addView(parent);

                    scroll.addView(father);

                }else {





                    // it's the parent layout
                    LinearLayout father = new LinearLayout(MessageActivity.this);
                    father.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ViewGroup.LayoutParams param = father.getLayoutParams();
                    father.setLayoutParams(param);

                    father.setLayoutParams(param);
                    father.setOrientation(LinearLayout.VERTICAL);
                    father.setGravity(Gravity.LEFT);

                    // it's the parent layout
                    LinearLayout parent = new LinearLayout(MessageActivity.this);
                    parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ViewGroup.LayoutParams params = parent.getLayoutParams();
                    params.width = 350;
                    parent.setLayoutParams(params);

                    parent.setLayoutParams(params);
                    parent.setOrientation(LinearLayout.VERTICAL);
                    parent.setBackground(ContextCompat.getDrawable(MessageActivity.this, R.drawable.bull_msg));
                    parent.setPadding(10, 10, 10, 10);
                    ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(parent.getLayoutParams());
                    marginParams.setMargins(0, 0, 0, 10);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(marginParams);
                    parent.setLayoutParams(layoutParams);
                    parent.setGravity(Gravity.RIGHT);


                    // it's the title layout =========================================================================================================================
                    LinearLayout title = new LinearLayout(MessageActivity.this);
                    title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    title.setOrientation(LinearLayout.HORIZONTAL);
                    title.setGravity(Gravity.LEFT/RIGHT);

                    LinearLayout sender_layout = new LinearLayout(MessageActivity.this);
                    sender_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    sender_layout.setOrientation(LinearLayout.VERTICAL);

                    TextView name = new TextView(getApplicationContext());
                    name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    name.setPadding(0, 0, 0, 0);
                    name.setText(contactList.get(n)[1]);
                    name.setTextColor(ContextCompat.getColor(MessageActivity.this, R.color.grey));
                    name.setTextSize(10);
                    ViewGroup.LayoutParams name_param = name.getLayoutParams();
                    name_param.width = 175;
                    name.setGravity(Gravity.LEFT);

                    sender_layout.addView(name);

                    LinearLayout date_layout = new LinearLayout(MessageActivity.this);
                    date_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    date_layout.setOrientation(LinearLayout.VERTICAL);

                    TextView date = new TextView(getApplicationContext());
                    date.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    date.setPadding(0, 0, 30, 0);
                    date.setText(contactList.get(n)[0]);
                    date.setTextColor(ContextCompat.getColor(MessageActivity.this, R.color.grey));
                    date.setTypeface(null, Typeface.ITALIC);
                    date.setTextSize(10);
                    date.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    ViewGroup.LayoutParams date_param = date.getLayoutParams();
                    date_param.width = 175;
                    date.setGravity(Gravity.RIGHT);

                    date_layout.addView(date);
                    //=========================================================================================================================================


                    // it's message layout
                    LinearLayout message_layout = new LinearLayout(MessageActivity.this);
                    message_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    message_layout.setOrientation(LinearLayout.VERTICAL);

                    TextView message = new TextView(getApplicationContext());
                    message.setText(contactList.get(n)[2]);
                    message.setTextColor(ContextCompat.getColor(MessageActivity.this, R.color.black));
                    message.setPadding(0, 10, 0, 0);
                    message.setTextSize(12);

                    message_layout.addView(message);


                    // it's add lyout
                    title.addView(sender_layout);
                    title.addView(date_layout);
                    parent.addView(title);
                    parent.addView(message_layout);
                    father.addView(parent);

                    scroll.addView(father);

                }
            }

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