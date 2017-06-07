package ve.com.jolex.entradaporton.Constantes;

/**
 * Created by Leo on 20/09/2016.
 */
public class ClientesCons {


    private String id;
    private String cId;
    private String cCodigo;
    private String cRif;
    private String cNomb;
    private String cDirecc;
    private String cTelefono;
    private String cUsuId;
    private String cVisi;
    private String cCodV;
    private String cListp;

    public ClientesCons(String Cid, String CcId, String Codigo, String Rif, String Nomb, String Direcc, String Telefono, String UsuId, String Visi, String CodV, String Listp) {
        id = Cid;
        cId = CcId;
        cCodigo = Codigo;
        cRif = Rif;
        cNomb = Nomb;
        cDirecc = Direcc;
        cTelefono = Telefono;
        cUsuId = UsuId;
        cVisi = Visi;
        cCodV = CodV;
        cListp = Listp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }


    public String getcCodigo() {
        return cCodigo;
    }

    public void setcCodigo(String cCodigo) {
        this.cCodigo = cCodigo;
    }

    public String getcRif() {
        return cRif;
    }

    public void setcRif(String cRif) {
        this.cRif = cRif;
    }

    public String getcNomb() {
        return cNomb;
    }

    public void setcNomb(String cNomb ) {
        this.cNomb = cNomb;
    }



    public String getcDirecc() {
        return cDirecc;
    }

    public void setcDirecc(String cDirecc ) {
        this.cDirecc = cDirecc;
    }


    public String getcTelefono() {
        return cTelefono;
    }

    public void setcTelefono(String cTelefono ) {
        this.cTelefono = cTelefono;
    }



    public String getcUsuId() {
        return cUsuId;
    }

    public void setcUsuId(String cUsuId ) {
        this.cUsuId = cUsuId;
    }


    public String getcVisi() {
        return cVisi;
    }

    public void setccVisi(String cVisi ) {
        this.cVisi = cVisi;
    }

    public String getcCodV() {
        return cCodV;
    }

    public void setcCodV(String cCodV ) {
        this.cCodV = cCodV;
    }



    public String getcListp() {
        return cListp;
    }

    public void setcListp(String cListp ) {
        this.cListp = cListp;
    }







    @Override
    public String toString() {
        return "Clientes{" +
                "ID='" + id + '\'' +
                ", cid='" + cId + '\'' +
                ", cCodigo='" + cCodigo + '\'' +
                ", cRif='" + cRif + '\'' +
                ", cNombre='" + cNomb + '\'' +
                ", cDireccion='" + cDirecc + '\'' +
                ", cTelefono='" + cTelefono + '\'' +
                ", cUsuarioid='" + cUsuId + '\'' +
                ", cVisitas='" + cVisi + '\'' +
                ", cCodigoV='" + cCodV + '\'' +
                ", cListap='" + cListp + '\'' +
                '}';
    }



}
