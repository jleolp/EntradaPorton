package ve.com.jolex.entradaporton;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class Conocenos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conocenos);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*   String url = "www.molgrupo.com.ve";
        TextView pagina = (TextView)findViewById(R.id.PagWeb);


     pagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.molgrupo.com.ve/index5.html"));
                startActivity(intent);
            }
        });*/

    }
}
