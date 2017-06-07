package ve.com.jolex.entradaporton.Validacion;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Leolp on 28/08/2016.
 */
public class Validar {


    private final String SHARED_PREFS_FILE = "HMPrefs";
    private final String KEY_EMAIL = "mac";
    private final String KEY_WIFI = "key_wifi";
    private final String NOM_WIFI = "nom_wifi";
    private final String NUM_PORTON = "num_porton";
    private final String ESTADO = "estado";
    private final String NOM_ADMIN = "admin";
    private final String ENTRADA = "entrada";
    private final String COD_PORT = "cod_porton";
    private final String NOM_CLIE = "nom_cli";


    private Context mContext;

    public Validar(Context context){
        mContext = context;
    }



    private SharedPreferences getSettings(){return mContext.getSharedPreferences(SHARED_PREFS_FILE, 0);}

    public String getMac(){
        return getSettings().getString(KEY_EMAIL, "");
    }
    public String getWifiNom(){
        return getSettings().getString(NOM_WIFI, "");
    }
    public String getWifiKey(){
        return getSettings().getString(KEY_WIFI, "");
    }

    public String getNumPorton(){
        return getSettings().getString(NUM_PORTON, "");
    }

    public String getCodPort(){
        return getSettings().getString(COD_PORT, "");
    }

    public int getESTADO(){ return getSettings().getInt(ESTADO, 0); }

    public int getEntrada(){
        return getSettings().getInt(ENTRADA, 0);
    }


    public String getNomAdm(){
        return getSettings().getString(NOM_ADMIN, "");
    }
    public String getNomClie(){
        return getSettings().getString(NOM_CLIE, "");
    }



    public void setMac(String mac){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(KEY_EMAIL, mac );
        editor.commit();
    }

    public void setWifiNom(String nomw){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(NOM_WIFI, nomw );
        editor.commit();
    }

    public void setWifiKey(String keyw){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(KEY_WIFI, keyw );
        editor.commit();
    }
    public void setNumPorton(String porton){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(NUM_PORTON, porton );
        editor.commit();
    }
    public void setESTADO(int estado){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putInt(ESTADO, estado );
        editor.commit();
    }

    public void setNomAdm(String admin){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(NOM_ADMIN, admin );
        editor.commit();
    }

    public void setENTRADA(int entrada){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putInt(ENTRADA, entrada );
        editor.commit();
    }


    public void setCodPort(String codPort){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(COD_PORT, codPort );
        editor.commit();
    }


    public void setNomClie(String clie){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(NOM_CLIE, clie );
        editor.commit();
    }


}
