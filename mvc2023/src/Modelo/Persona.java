
package Modelo;

import java.sql.Date;

public class Persona {
    String idpersona;
    String nombres;
    String apellidos;
    Date fechanacimiento;
    String telefono;
    String sexo;
    double sueldo;
    int cupo;

    public Persona() {
    }

    public Persona(String idpersona, String nombres, String apellidos, Date fechanacimiento, String telefono, String sexo, double sueldo, int cupo) {
        this.idpersona = idpersona;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
        this.telefono = telefono;
        this.sexo = sexo;
        this.sueldo = sueldo;
        this.cupo = cupo;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }


    public String getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(String idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Persona{" + "idpersona=" + idpersona + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fechanacimiento=" + fechanacimiento + ", telefono=" + telefono + ", sexo=" + sexo + ", sueldo=" + sueldo + ", cupo=" + cupo + '}';
    }



}
