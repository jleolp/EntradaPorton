package ve.com.jolex.entradaporton.Constantes;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Leo on 18/09/2016.
 */
public class Vendedor {
    private final String SHARED_PREFS_FILE = "HMPrefs";
    private final String KEY_LOGIN = "login";
    private final String KEY_PASS = "pass";
    private final String KEY_IDV = "idV";
    private final String KEY_CODV = "codV";
    private final String KEY_NOMV = "nomV";
    private final String KEY_PERFILV = "perfilV";
    private final int Record = 0;




    private Context mContext;

    public Vendedor(Context context){
        mContext = context;
    }



    private SharedPreferences getSettings(){
        return mContext.getSharedPreferences(SHARED_PREFS_FILE, 0);
    }

    public String getUser(){
        return getSettings().getString(KEY_LOGIN, null);
    }

    public void setUser(String user){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(KEY_LOGIN, user );
        editor.commit();
    }

    public String getPASS(){
        return getSettings().getString(KEY_PASS, "0");
    }

    public void setPASS(String pass){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(KEY_PASS, pass );
        editor.commit();
    }

    public String getIdV(){
        return getSettings().getString(KEY_IDV, null);
    }

    public void setIdV(String idV){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(KEY_IDV, idV );
        editor.commit();
    }

    public String getCodV(){return getSettings().getString(KEY_CODV, null);}

    public void setCodV(String codV){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(KEY_CODV, codV );
        editor.commit();
    }


    public String getNomV(){
        return getSettings().getString(KEY_NOMV, null);
    }

    public void setNomV(String nomV){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(KEY_NOMV, nomV );
        editor.commit();
    }

    public String getPerfV(){
        return getSettings().getString(KEY_PERFILV, null);
    }

    public void setPerfV(String perfV){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(KEY_PERFILV, perfV );
        editor.commit();
    }



    public int getRecord(){
        return getSettings().getInt(String.valueOf(Record), 0);
    }

    public void setRecord(int f){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putInt(String.valueOf(Record), f );
        editor.commit();
    }




}
