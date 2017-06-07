package ve.com.jolex.entradaporton.Validacion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import ve.com.jolex.entradaporton.Controles.HTTPURLConnection;

import ve.com.jolex.entradaporton.R;

public class Validacion extends AppCompatActivity {

    private Validar validar;

    private static final String TAG = "MainActivity";
    private static final int TEXT_ID = 0;


    private JSONObject json;
    private Gson gson = new Gson();
    private int success=0;
    private String mensaje = "";
    public CUsuario data;

    private ProgressDialog pDialog;

    private HTTPURLConnection service;

    String macAddress, serial;
    String urb;

    String porton, sequipo, ssoft, nomcli;


    int proce = 0;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion);

        service=new HTTPURLConnection();

        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        macAddress = wInfo.getMacAddress();
        validar = new Validar(this);

        if (validar.getMac() != null) {
            if (!validar.getMac().equals(macAddress)) {
                createExampleDialog(macAddress).show();
            } else {

                if(validar.getESTADO() == 1){
                    finishDialog().show();
                }else {
                    Log.e("PASO POR ", "AQUI");
            /*       Intent intent = new Intent(Validacion.this, MainActivity.class);
                    startActivity(intent); */
                    Validacion.this.finish();
                    Log.e(TAG, " Directo ");
                    Log.e(TAG, "valido " + validar.getMac());
                }
            }
        } else {
            createExampleDialog(macAddress).show();
        }

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }


    private Dialog createExampleDialog(final String mac) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Validar Aplicacion");
        builder.setMessage("Si valida la aplicacion en este equipo no podra instalarse en otro equipo ¿Desea Validar?");

        // Use an EditText view to get user input.
        final EditText input = new EditText(this);
        input.setId(TEXT_ID);
        builder.setView(input);

        builder.setPositiveButton("Validar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                serial = input.getText().toString();
                new AsyncLogin().execute();
                return;
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Validacion.this.finish();
                return;
            }
        });

        return builder.create();
    }


    private Dialog finishDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Aplicación Bloqueda");
        builder.setMessage("La aplicación bloqueada por el administrador de:\n"+ validar.getNomAdm()+"\n\nPor favor pongase en contacto con el");
        //       builder.setMessage(validar.getNomAdm());
        startService(new Intent(this, Servicio.class));
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Validacion.this.finish();
                return;
            }
        });

        return builder.create();
    }





    private class AsyncLogin extends AsyncTask<Void, Void, Void>
    {
        String response = "";
        //Create hashmap Object to send parameters to web service
        HashMap<String, String> postDataParams;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(Validacion.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }


        @Override
        protected Void doInBackground(Void... arg0) {
            Log.e("DoInBack", macAddress);
            Log.e("DoInBack", serial);
            postDataParams = new HashMap<String, String>();
            postDataParams.put("mac", macAddress);
            postDataParams.put("serial", serial);
            postDataParams.put("software", ConsValidar.SOFTWARE);

            //Call ServerData() method to call webservice and store result in response

            response= service.ServerData(ConsValidar.VALIDAR,postDataParams);
            try {
                json = new JSONObject(response);
                //Get Values from JSONobject
                System.out.println("estado=" + json.getString("estado"));
                success = json.getInt("estado");

                switch (success){
                    case 1:
                        //Parsear objeto
                        JSONObject object = json.getJSONObject("control");
                        data = gson.fromJson(object.toString(), CUsuario.class);
                        //Parsear objeto
                        sequipo = data.getSerial_equipo();
                        ssoft = data.getSerial_software();
                        porton = data.getPorton();
                        mensaje = json.getString("mensaje");
                        nomcli = data.getNombre();
                        Log.e("Serial_Equipo", sequipo);
                        Log.e("Serial_Soft", ssoft);
                        Log.e("Porton", porton);
                        Log.e("NOMBRE DE CLIENTE", nomcli);

                        //new Configurador().execute();
                        break;
                    default:
                        mensaje = json.getString("mensaje");
                        break;
                }


                Log.e("Estado", Integer.toString(success));
                Log.e("Mensaje", mensaje);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            //this method will be running on UI thread

            super.onPostExecute(result);

            if(pDialog.isShowing()){
                pDialog.dismiss();
            }
            if(success==1) {
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                validar.setNumPorton(porton);
                validar.setMac(macAddress);
                validar.setNomClie(nomcli);


            /*    Intent intent = new Intent(Validacion.this,MainActivity.class);
                startActivity(intent);
                Validacion.this.finish();
*/
                //   createExampleDialog(macAddress).show();
            }else{
                Toast.makeText(getApplicationContext(), "Error..! "+ mensaje, Toast.LENGTH_LONG).show();
                createExampleDialog(macAddress).show();

            }
        }
    }


    public void starActividad(){

    }

    //en el evento "Abrir ventana" leemos los datos de configuración del fichero xml
    @Override
    protected void onStart()
    {
        super.onStart();

    }
}
