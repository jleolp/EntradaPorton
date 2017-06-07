package ve.com.jolex.entradaporton;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import ve.com.jolex.entradaporton.Constantes.Constantes;
import ve.com.jolex.entradaporton.Constantes.Vendedor;
import ve.com.jolex.entradaporton.Controles.Gvendedor;
import ve.com.jolex.entradaporton.Controles .HTTPURLConnection;

public class Login extends AppCompatActivity {


    private Vendedor validar;
    private ProgressDialog pDialog;
    private JSONObject json;
    private Gson gson = new Gson();
    private int success=0;
    private String mensaje = "";

    private HTTPURLConnection service;

    public Gvendedor data;


    public String user = "", pass = "";


    public String nomV = "", idV = "", codV ="", perfV="";

    public EditText usuario, password;
    private CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        usuario = (EditText) findViewById(R.id.login);
        password = (EditText)findViewById(R.id.password);
        check = (CheckBox)findViewById(R.id.checkBox);

        validar = new Vendedor(this);
        service=new HTTPURLConnection();
        password.setText("");
        if(validar.getRecord() == 1){
            check.setChecked(true);
            usuario.setText(validar.getUser());
        }



    }

    // Triggers when LOGIN Button clicked
    public void checkLogin(View arg0) {

        // Get text from email and passord field
        user = usuario.getText().toString();
        pass = password.getText().toString();

        if(validar.getUser() != null){
            if ((validar.getUser().equals(user)) && (validar.getPASS().equals(pass))){
                paso();
            }else{
                if(ConectadoNet()){
                    new AsyncLogin().execute();
                }else{
                    DialogsErores(1);
                }
            }
        }else{  new AsyncLogin().execute();    }
    }

    protected Boolean ConectadoNet(){

        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo infoW = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (infoW != null) {
                if (infoW.isConnected()) {
                    return true;
                }
            }else{
                NetworkInfo infoM = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (infoM != null) {
                    if (infoM.isConnected()){
                        return true;
                    }
                }
            }
        }
        return false;
    }





    public void paso (){
        if(check.isChecked()){ validar.setRecord(1); }else{ validar.setRecord(0);}
        Intent intent = new Intent(Login.this,Inicial.class);
        //intent.putExtra("usuario",user);
        startActivity(intent);
        Login.this.finish();


    }




    /**
     * Called to create a dialog to be shown

     /**
     * Create and return an example alert dialog with an edit text box.
     */
    private Dialog DialogsErores(final int flag) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        switch (flag){
            case 1:
                builder.setTitle(R.string.No_conexion);
                builder.setMessage(R.string.Invalida_Local);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                break;
            case 2:


                break;


            default:
                break;


        }


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

            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            postDataParams = new HashMap<String, String>();
            postDataParams.put("username", user);
            postDataParams.put("clave", pass);
            //Call ServerData() method to call webservice and store result in response
            response= service.ServerData(Constantes.LOGIN,postDataParams);
            try {
                Log.e("username", user);
                Log.e("clave", pass);
                json = new JSONObject(response);
                //Get Values from JSONobject
                System.out.println("estado=" + json.getString("estado"));
                success = json.getInt("estado");

                switch (success){
                    case 1:
                        //Parsear objeto
                        JSONObject object = json.getJSONObject("control");
                        data = gson.fromJson(object.toString(), Gvendedor.class);
                        idV = data.getidVG();
                        nomV = data.getNombVG();
                        codV = data.getCodVG();
                        perfV =data.getPerfVG();
                        mensaje = json.getString("mensaje");
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
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            if(success==1) {

                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();


                validar.setUser(user);
                validar.setPASS(pass);
                validar.setNomV(nomV);
                validar.setCodV(codV);
                validar.setIdV(idV);
                validar.setPerfV(perfV);

                paso();
            }else{
                Toast.makeText(getApplicationContext(), "Error..! "+ mensaje, Toast.LENGTH_LONG).show();
            }
        }
    }




    public void conocenos (View v){
        Intent intent = new Intent(Login.this,Conocenos.class);
        startActivity(intent);

    }



}
