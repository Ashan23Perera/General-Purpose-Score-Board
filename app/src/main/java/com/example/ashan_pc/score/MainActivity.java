package com.example.ashan_pc.score;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity implements View.OnClickListener {

    public final static String PREF_IP = "PREF_IP_ADDRESS";
    public final static String PREF_PORT = "PREF_PORT_NUMBER";

    private Button[] buttons = null;
    private Button[] Buttons = null;
    private EditText editText1, editText2, edittext1, edittext2;
    private EditText Mins,Secs,Clock;

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("HTTP_HELPER_PREFS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        this.buttons = new Button[12];
        this.Buttons = new Button[8];

        //Score
        buttons[0] = (Button) findViewById(R.id.btnHomeUp);
        buttons[1] = (Button) findViewById(R.id.btnHomeDown);
        buttons[2] = (Button) findViewById(R.id.btnHomeSet);
        buttons[3] = (Button) findViewById(R.id.btnAwayUp);
        buttons[4] = (Button) findViewById(R.id.btnAwayDown);
        buttons[5] = (Button) findViewById(R.id.btnAwaySet);
        //Fouls
        buttons[6] = (Button) findViewById(R.id.foulsHomeUp);
        buttons[7] = (Button) findViewById(R.id.foulsHomeDown);
        buttons[8] = (Button) findViewById(R.id.foulsHomeSet);
        buttons[9] = (Button) findViewById(R.id.foulsAwayUp);
        buttons[10] = (Button) findViewById(R.id.foulsAwayDown);
        buttons[11] = (Button) findViewById(R.id.foulsAwaySet);


        Buttons[0] = (Button)findViewById(R.id.btnMainSet);
        Buttons[1] = (Button)findViewById(R.id.btnMainStart);
        Buttons[2] = (Button)findViewById(R.id.btnMainPause);
        Buttons[3] = (Button)findViewById(R.id.btnStartClock);
        Buttons[4] = (Button)findViewById(R.id.btnPauseClock);
        Buttons[5] = (Button)findViewById(R.id.btnSetClock);
        Buttons[6] = (Button)findViewById(R.id.btn24Clock);
        Buttons[7] = (Button)findViewById(R.id.btn14Clock);


        //Score
        editText1 = (EditText) findViewById(R.id.edittext1);
        editText2 = (EditText) findViewById(R.id.edittext2);
        //Fouls
        edittext1 = (EditText) findViewById(R.id.editText1);
        edittext2 = (EditText) findViewById(R.id.editText2);

        //Mins and Secs
        Mins  = (EditText)findViewById(R.id.editMainMintext2);
        Secs  = (EditText)findViewById(R.id.editMainSectext);

        //Clock
        Clock = (EditText)findViewById(R.id.editShotSec);

        //Score
        buttons[0].setOnClickListener(this);
        buttons[1].setOnClickListener(this);
        buttons[2].setOnClickListener(this);
        buttons[3].setOnClickListener(this);
        buttons[4].setOnClickListener(this);
        buttons[5].setOnClickListener(this);
        //Fouls
        buttons[6].setOnClickListener(this);
        buttons[7].setOnClickListener(this);
        buttons[8].setOnClickListener(this);
        buttons[9].setOnClickListener(this);
        buttons[10].setOnClickListener(this);
        buttons[11].setOnClickListener(this);

        //Main Clock
        Buttons[0].setOnClickListener(this);
        Buttons[1].setOnClickListener(this);
        Buttons[2].setOnClickListener(this);
        //Shot Clock
        Buttons[3].setOnClickListener(this);
        Buttons[4].setOnClickListener(this);
        Buttons[5].setOnClickListener(this);
        Buttons[6].setOnClickListener(this);
        Buttons[7].setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        //paramets to pass
        String parameterValue = null;
        String parameterName = null; //url type
        String[] values = new String[2];
        String ipAddress = "192.168.4.1";
        String portNumber = "80";


        // save the IP address and port for the next time the app is used
        editor.putString(PREF_IP, ipAddress); // set the ip address value to save
        editor.putString(PREF_PORT, portNumber); // set the port number to save
        editor.commit(); // save the IP and PORT

        //Actions

        if (view.getId() == buttons[0].getId()) {
            parameterValue = editText1.getText().toString();
            parameterName = "homescore";
            values[0] = "1";
            values[1] = "0";
        } else if (view.getId() == buttons[1].getId()) {
            parameterValue = editText1.getText().toString();
            parameterName = "homescore";
            values[0] = "0";
            values[1] = "1";
        } else if (view.getId() == buttons[2].getId()) {
            parameterValue = editText1.getText().toString();
            parameterName = "homescore";
            values[0] = "0";
            values[1] = "0";
        } else if (view.getId() == buttons[3].getId()) {
            parameterValue = editText2.getText().toString();
            parameterName = "awayscore";
            values[0] = "1";
            values[1] = "0";
        } else if (view.getId() == buttons[4].getId()) {
            parameterValue = editText2.getText().toString();
            parameterName = "awayscore";
            values[0] = "0";
            values[1] = "1";
        } else if(view.getId() == buttons[5].getId()){
            parameterValue = editText2.getText().toString();
            parameterName = "awayscore";
            values[0] = "0";
            values[1] = "0";
        }else if (view.getId() == buttons[6].getId()) {
            parameterValue = edittext1.getText().toString();
            parameterName = "homefouls";
            values[0] = "1";
            values[1] = "0";
        } else if (view.getId() == buttons[7].getId()) {
            parameterValue = edittext1.getText().toString();
            parameterName = "homefouls";
            values[0] = "0";
            values[1] = "1";
        } else if (view.getId() == buttons[8].getId()) {
            parameterValue = edittext1.getText().toString();
            parameterName = "homefouls";
            values[0] = "0";
            values[1] = "0";
        } else if (view.getId() == buttons[9].getId()) {
            parameterValue = edittext2.getText().toString();
            parameterName = "awayfouls";
            values[0] = "1";
            values[1] = "0";
        } else if (view.getId() == buttons[10].getId()) {
            parameterValue = edittext2.getText().toString();
            parameterName = "awayfouls";
            values[0] = "0";
            values[1] = "1";
        } else if(view.getId() == buttons[11].getId()){
            parameterValue = edittext2.getText().toString();
            parameterName = "awayfouls";
            values[0] = "0";
            values[1] = "0";
        }


        if(view.getId() == Buttons[0].getId()){
            parameterName = "gameclock";
            parameterValue = "No Value";
            values[0] = Mins.getText().toString();
            values[1] = Secs.getText().toString();
        }else if(view.getId() == Buttons[1].getId()){
            parameterName = "gameclockstate";
            parameterValue = "1";
            values[0] = Mins.getText().toString();
            values[1] = Secs.getText().toString();
        }else if(view.getId() == Buttons[2].getId()){
            parameterName = "gameclockstate";
            parameterValue = "0";
            values[0] = Mins.getText().toString();
            values[1] = Secs.getText().toString();
        }else if(view.getId() == Buttons[3].getId()){
            parameterName = "shotclockstate";
            parameterValue = "1";
            values[0] = "No Value";
            values[1] = "No Value";
        }else if(view.getId() == Buttons[4].getId()){
            parameterName = "shotclockstate";
            parameterValue = "0";
            values[0] = "No Value";
            values[1] = "No Value";
        }else if(view.getId() == Buttons[5].getId()){
            parameterName = "shotclock";
            parameterValue = Clock.getText().toString();
            values[0] = "No Value";
            values[1] = "No Value";
        }else if(view.getId() == Buttons[6].getId()){
            parameterName = "shotclock";
            parameterValue = "24";
            values[0] = "No Value";
            values[1] = "No Value";
        }else if(view.getId() == Buttons[7].getId()){
            parameterName = "shotclock";
            parameterValue = "14";
            values[0] = "No Value";
            values[1] = "No Value";
        }


        if (ipAddress.length() > 0 && portNumber.length() > 0) {
            new HttpRequestAsyncTask(view.getContext(), parameterValue, ipAddress, portNumber, parameterName, values).execute();
        }
    }


    private String sendRequest(String parameterValue, String ipAddress, String portNumber, String parameterName, String[] values) {

        StringBuilder content = new StringBuilder();
        // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
        String up = null, down = null, set = null, mins = null, secs = null, state = null;

        if (values.length == 2) {
            up = values[0];
            down = values[1];
            mins = values[0];
            secs = values[1];
        }
        //else{
//
//        }

        try {

            // create a url object
            String uRl = null;

            switch (parameterName) {
                case "homescore":
                    uRl = "http://" + ipAddress + "/" + parameterName + "?" + "up" + "=" + up + "&" + "down" + "=" + down + "set" + "=" + parameterValue;
                    break;
                case "awayscore":
                    uRl = "http://" + ipAddress + "/" + parameterName + "?" + "up" + "=" + up + "&" + "down" + "=" + down + "set" + "=" + parameterValue;
                    break;
                case "homefouls":
                    uRl = "http://" + ipAddress + "/" + parameterName + "?" + "up" + "=" + up + "&" + "down" + "=" + down + "set" + "=" + parameterValue;
                    break;
                case "awayfouls":
                    uRl = "http://" + ipAddress + "/" + parameterName + "?" + "up" + "=" + up + "&" + "down" + "=" + down + "set" + "=" + parameterValue;
                    break;
                case "gameclock":
                    uRl = "http://" + ipAddress + "/" + parameterName + "?" + "mins" + "=" + mins + "&" + "secs" + "=" + secs;
                    break;
                case "shotclock":
                    uRl = "http://" + ipAddress + "/" + parameterName + "?" + "set" + "=" + parameterValue;
                    break;
                case "shotclockstate":
                    uRl = "http://" + ipAddress + "/" + parameterName + "?" + "state" + "=" + parameterValue;
                    break;
                case "gameclockstate":
                    uRl = "http://" + ipAddress + "/" + parameterName + "?" + "state" + "=" + parameterValue;
                    break;

                default:
                    uRl = "http://" + ipAddress + "/" + parameterName + "?" + "state" + "=" + parameterValue;
                    break;
            }

            URL url = new URL(uRl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            int header = ((HttpURLConnection) urlConnection).getResponseCode();
            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }

            content.append(Integer.toString(header) + "\n");
            Toast.makeText(MainActivity.this, content.toString(), Toast.LENGTH_SHORT);

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();

    }

    private class HttpRequestAsyncTask extends AsyncTask<Void, Void, Void> {

        private String requestReply, ipAddress, portNumber;
        private Context context;
        private AlertDialog alertDialog;
        private String parameterName;
        private String parameterValue;
        private String[] values = null;

        public HttpRequestAsyncTask(Context context, String parameterValue, String ipAddress, String portNumber, String parameterName, String[] values) {

            this.context = context;
            this.ipAddress = ipAddress;
            this.parameterValue = parameterValue;
            this.portNumber = portNumber;
            this.parameterName = parameterName;
            this.values = values;
            alertDialog = new AlertDialog.Builder(this.context).setTitle("HTTP Response From IP Address").setCancelable(true).create();

        }

        @Override
        protected Void doInBackground(Void... params) {
            requestReply = sendRequest(parameterValue, ipAddress, portNumber, parameterName, values);
            return null;
        }

        @Override
        public void onPostExecute(Void aVoid) {
            alertDialog.setMessage(requestReply);
            if (!alertDialog.isShowing()) {
                alertDialog.show();
            }
        }

        @Override
        public void onPreExecute() {

        }
    }


}





















