/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc2023;

import Controlador.ControlPersona;
import Controlador.ControladorMenuPrincipal;
import Modelo.ModeloPersona;
import Vista.VistaPersonas;
import Vista.VistaPrincipal;

/**
 *
 * @author Usuario
 */
public class Mvc2023 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        VistaPrincipal vista = new VistaPrincipal();
        ControladorMenuPrincipal control = new ControladorMenuPrincipal(vista);
        control.iniciaControl();
        
    }
    
}
