package ve.com.jolex.entradaporton.Validacion;

/**
 * Created by Leo on 16/10/2016.
 */
public class CUsuario {

    private String serial_equipo;
    private String id;
    private String serial_software;
    private String fecha_activacion;
    private String dias_vence;
    private String estado;
    private String software;
    private String nombre;
    private String rif;
    private String direccion;
    private String telefono;
    private String email;
    private String porton;

    public CUsuario(String serial_equipo, String id, String serial_software,
                    String fecha_activacion, String dias_vence, String estado,
                    String software, String nombre, String rif,
                    String direccion, String telefono, String email, String porton) {


        this.serial_equipo = serial_equipo;
        this.id = id;
        this.serial_software = serial_software;
        this.fecha_activacion = fecha_activacion;

        this.dias_vence = dias_vence;
        this.estado = estado;
        this.software = software;

        this.nombre = nombre;
        this.rif = rif;
        this.direccion = direccion;

        this.telefono = telefono;
        this.email = email;
        this.porton = porton;

    }

    public String getSerial_equipo() { return serial_equipo;  }
    public String getIdUsu() {
        return id;
    }
    public String getSerial_software() {
        return serial_software;
    }
    public String getFecha_activacion() { return fecha_activacion;  }
    public String getDias_vence() {
        return dias_vence;
    }
    public String getEstado() {
        return estado;
    }
    public String getSoftware() { return software;  }
    public String getNombre() {
        return nombre;
    }
    public String getRif() {
        return rif;
    }
    public String getDireccion() { return direccion;  }
    public String getTelefono() {
        return telefono;
    }
    public String getEmail() {
        return email;
    }
    public String getPorton() { return porton; }


}
