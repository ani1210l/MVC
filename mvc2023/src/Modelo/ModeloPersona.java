/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.security.interfaces.RSAKey;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ModeloPersona extends Persona {

    public ModeloPersona() {
    }

    public ModeloPersona(String idpersona, String nombres, String apellidos, Date fechanacimiento, String telefono, String sexo, double sueldo, int cupo) {
        super(idpersona, nombres, apellidos, fechanacimiento, telefono, sexo, sueldo, cupo);
    }





public List<Persona>listaPersonas(){ try {
        //Me retorna un "List" de "persona"
        List<Persona> lista = new ArrayList<>();
        
        String sql = "select idpersona,nombres,apellidos,fechanacimiento, telefono,sexo, sueldo, cupo from persona";
//        String sql = "select idpersona,nombres,apellidos,telefono from persona";
        
        ConectPG conpg = new ConectPG();
        ResultSet rs = conpg.consulta(sql); //La consulta nos devuelve un "ResultSet"
        
        //Pasar de "ResultSet" a "List"
        
        while(rs.next()){
            //Crear las instancias de las personas
            Persona persona = new Persona();
            
            //Todo lo que haga en la sentencia define como voy a extraer los datos
                 persona.setIdpersona(rs.getString("idpersona"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setFechanacimiento(rs.getDate("fechanacimiento"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setSexo(rs.getString("sexo"));
                persona.setSueldo(rs.getDouble("sueldo"));
                persona.setCupo(rs.getInt("cupo"));
            
            lista.add(persona); //Agrego los datos a la lista
        }
        
        //Cierro la conexion a la BD
        rs.close();
        //Retorno la lista
        return lista;
        
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    
        public SQLException crearPersona() { //Grabamos la instancia en la BD

        String sql = "INSERT INTO persona(idpersona, nombres, apellidos, fechanacimiento, telefono, sexo, sueldo, cupo, foto) VALUES ('" + getIdpersona() + "', '" + getNombres() + "', '" + getApellidos() + "', '" + getFechanacimiento() + "', '" + getTelefono() + "', '" + getSexo() + "'," + getSueldo() + ", " + getCupo() + ", null);";

        ConectPG conpg = new ConectPG();

        SQLException ex = conpg.accion1(sql); //Devuelve un valor de tipo "SQLException". Si devuelve 'null' esta bien, caso contrario me retornara la excepcion.
        return ex;
    }
    
    
        public boolean modificarPersona() { //modificamos la instancia en la BD

        String sql = "UPDATE persona SET nombres='" + getNombres() + "', apellidos='" + getApellidos() + "', fechanacimiento='" + getFechanacimiento() + "', telefono='" + getTelefono() + "', sexo='" + getSexo() + "', sueldo=" + getSueldo() + ", cupo=" + getCupo() + ", foto=null WHERE idpersona = '" + getIdpersona() + "';";

        ConectPG conpg = new ConectPG();

       return conpg.accion(sql);
    }
        
        
    public boolean eliminarPersona(String cedula) { //eliminar la instancia en la BD. Se pasa como parametro la cedula de la persona que se desea eliminar

        String sql = "DELETE FROM persona WHERE idpersona = '" + cedula + "';";

        ConectPG conpg = new ConectPG();

        return conpg.accion(sql);
    }
}

//Hacer todos los metodos 