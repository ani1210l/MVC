/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPersona;
import Vista.VistaPersonas;
import Vista.VistaPrincipal;

/**
 *
 * @author Usuario
 */
public class ControladorMenuPrincipal {
    VistaPrincipal vistaPrincipal;

    public ControladorMenuPrincipal() {
    }

    public ControladorMenuPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        vistaPrincipal.setVisible(true);
    }
    
    public void iniciaControl(){
        vistaPrincipal.getMenuPersonas().addActionListener(l->crudPersonas());
        vistaPrincipal.getBtnPersonas().addActionListener(l -> crudPersonas());
    }
    
    private void crudPersonas(){
         VistaPersonas vista =new VistaPersonas();
        ModeloPersona modelo= new ModeloPersona();
        vistaPrincipal.getDspPrincipal().add(vista);
        
        ControlPersona control = new ControlPersona(modelo, vista);
        control.iniciaControl();
        
    }
}
