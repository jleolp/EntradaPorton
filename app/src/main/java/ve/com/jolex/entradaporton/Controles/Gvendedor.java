package ve.com.jolex.entradaporton.Controles;

/**
 * Created by Leolp on 19/09/2016.
 */
public class Gvendedor {
    /*
Atributos
*/
    private String id;
    private String codvend;
    private String nombre;
    private String perfil;


    public Gvendedor(String id, String codvend, String nombre, String perfil) {
        this.id = id;
        this.codvend = codvend;
        this.nombre = nombre;
        this.perfil = perfil;

    }

    public String getidVG() { return id; }

    public String getCodVG() { return codvend;  }
    public String getNombVG() {
        return nombre;
    }

    public String getPerfVG() {
        return perfil;
    }



}
