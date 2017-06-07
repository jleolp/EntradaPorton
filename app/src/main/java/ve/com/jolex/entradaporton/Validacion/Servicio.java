package ve.com.jolex.entradaporton.Validacion;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

import ve.com.jolex.entradaporton.Controles.HTTPURLConnection;

/**
 * Created by Leo on 12/10/2016.
 */
public class Servicio extends Service {

    String TAG = "Servicio";
    HttpURLConnection conn;
    URL url = null;

    private JSONObject json;
    private Gson gson = new Gson();
    private int success=0;
    private String mensaje = "";
    private HTTPURLConnection service;
    private Validar validar;
    TimerTask timerTask;
    String macAddress;
    String Software = "PORTON";
    int i = 0;


    String response = "";
    //Create hashmap Object to send parameters to web service
    HashMap<String, String> postDataParams;



    public Servicio() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        Log.d(TAG, "Servicio creado...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Servicio iniciado...");


        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        macAddress = wInfo.getMacAddress();
        validar = new Validar(this);




        Timer timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                i = i+1;

                Log.e("ESTO ES ", "Por tiempo " + i);
                Log.e("HORA Y MIN", fecha());
               // new AsyncVerif().execute();

                if(!isOnline()){
                    Log.e("No hay internet","Disponible");
                }else {

                    postDataParams = new HashMap<String, String>();
                    postDataParams.put("software", Software);
                    postDataParams.put("mac", macAddress);
                    Log.w("ENTRNADO EN ASYNC", " Aqui");
                    //Call ServerData() method to call webservice and store result in response
                    response = ServerData(ConsValidar.VERIFI, postDataParams);
                    try {

                        json = new JSONObject(response);
                        //Get Values from JSONobject
                        System.out.println("estado=" + json.getString("estado"));
                        success = json.getInt("estado");

                        switch (success) {
                            case 1:
                                mensaje = json.getString("mensaje");
                                validar.setESTADO(0);
                                mensaje = json.getString("mensaje");
                                break;
                            case 2:

                                validar.setESTADO(1);
                                break;
                            case 3:
                                validar.setESTADO(1);
                                break;
                            default:
                                mensaje = json.getString("mensaje");
                                break;
                        }


                        Log.w("Estado", Integer.toString(success));
                        Log.w("Mensaje", mensaje);
                        Log.w("Validar estado", String.valueOf(validar.getESTADO()));
                        mensaje = "";
                        success = 0;
                        response = "";
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 60000);



        return START_STICKY;
    }

    @Override
    public void onDestroy() { Log.d(TAG, "Servicio destruido..."); }


    public String ServerData(String path, HashMap<String, String> params) {
        try {
           // Log.d("HTTP AQUI RESPONDE", "RESPONDE");
            url = new URL(path);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(params));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                //Log.d("Output",br.toString());
                response = "";
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }



    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL url = new URL(ConsValidar.IP);
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(3000);
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
                    return new Boolean(true);
                }
            } catch (MalformedURLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }




    public String fecha(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM hh:mm");
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            return currentTimeStamp;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }



}
