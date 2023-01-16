/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPersona;
import Modelo.Persona;
import Vista.VistaPersonas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Usuario
 */
public class ControlPersona {
    private ModeloPersona modelo;
    private VistaPersonas vista;
    
    public ControlPersona(ModeloPersona modelo,VistaPersonas vista){
        this.modelo=modelo;
        this.vista=vista;
        vista.setVisible(true);
        cargaPersonas();
    }
    private void cargaPersonas(){
       //Control para consultar a mi modelo o base de datos 
       //Y luego en la vista.
       List<Persona>listap = modelo.listaPersonas();
       DefaultTableModel mTabla;
       mTabla=(DefaultTableModel)vista.getTbPersona().getModel();
       mTabla.setNumRows(0);//Limpio la tabla 
       listap.stream().forEach(pe->{
           Object[]filanueva={pe.getIdpersona(),pe.getNombres(),pe.getApellidos(),String.valueOf(pe.getFechanacimiento()),pe.getSexo(),pe.getTelefono(),String.valueOf(pe.getSueldo()),String.valueOf(pe.getCupo())};
           mTabla.addRow(filanueva);
           
       });
    }
//    public void iniciaControl(){
//        vista.getBtnActualizar().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//    }
    
    public void iniciaControl(){
       vista.getBtnActualizar().addActionListener(l->cargaPersonas());
       vista.getBtnCrear().addActionListener(l-> abrirDialogo(1));
//        vista.getBtnEditar().addActionListener(l-> abrirDialogo(2));
        vista.getBtnEditar().addActionListener(l -> abrirYCargarDatosEnElDialog());
        vista.getBtnAceptar().addActionListener(l-> crearEditarPersona());
         vista.getBtnEliminar().addActionListener(l -> eliminarPersona());
    }
    
    
        public void eliminarPersona() {

        int fila = vista.getTbPersona().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            int response = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar esta información?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                String cedula;
                cedula = vista.getTbPersona().getValueAt(fila, 0).toString();

                if (modelo.eliminarPersona(cedula)) {
                    JOptionPane.showMessageDialog(null, "La persona fue eliminada exitosamente");
                    cargaPersonas();//Actualizo la tabla con los datos
                } else {
                    JOptionPane.showMessageDialog(null, "Error: La persona no se pudo eliminar");
                }
            }
        }

    }
        public void abrirYCargarDatosEnElDialog() {

        int seleccion = vista.getTbPersona().getSelectedRow();

        if (seleccion == -1) {
            JOptionPane.showMessageDialog(null, "Aun no ha seleccionado una fila");
        } else {

            String cedula = vista.getTbPersona().getValueAt(seleccion, 0).toString();
            modelo.listaPersonas().forEach((pe) -> {
                if (pe.getIdpersona().equals(cedula)) {

                    //Abre el jDialog y carga los datos en el jDialog
                    vista.getDlgCrud().setName("Editar");
                    vista.getDlgCrud().setLocationRelativeTo(vista);
                    vista.getDlgCrud().setSize(1100, 500);
                    vista.getDlgCrud().setTitle("Editar");
                    vista.getDlgCrud().setVisible(true);

                    vista.getTxtIdentificacion().setText(pe.getIdpersona());
                    vista.getTxtNombre().setText(pe.getNombres());
                    vista.getTxtApellidos().setText(pe.getApellidos());
                    vista.getTxtSexo().setText(pe.getSexo());
                    vista.getTxtTelefono().setText(pe.getTelefono());
                    vista.getjDateChooser1().setDate(pe.getFechanacimiento());
                    vista.getTxtSueldo().setText(String.valueOf(pe.getSueldo()));
                    vista.getTxtCupo().setText(String.valueOf(pe.getCupo()));
                }
            });
        }
    }
    
    private void abrirDialogo(int ce) {
        String title;
        if (ce == 1) {
            title = "Crear nueva persona";
            vista.getDlgCrud().setName("crear");
        } else {

            title = "Editar";
            vista.getDlgCrud().setName("editar");
        }

        vista.getDlgCrud().setLocationRelativeTo(vista);
        vista.getDlgCrud().setSize(700, 500);
        vista.getDlgCrud().setTitle(title);
        vista.getDlgCrud().setVisible(true);
    }
    
 private void crearEditarPersona() {
        if ("crear".equals(vista.getDlgCrud().getName())) {
            //INSERTAR
            String cedula = vista.getTxtIdentificacion().getText();
            String nombres = vista.getTxtNombre().getText();
            String apellidos = vista.getTxtApellidos().getText();
            String sexo = vista.getTxtSexo().getText();
            String telefono = vista.getTxtTelefono().getText();
            Date fecha = vista.getjDateChooser1().getDate();
            double sueldo = Double.parseDouble(vista.getTxtSueldo().getText());
            int cupo = Integer.parseInt(vista.getTxtCupo().getText());

            ModeloPersona persona = new ModeloPersona();
            persona.setIdpersona(cedula);
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setSexo(sexo);
            persona.setTelefono(telefono);

            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());//Paso de util.Date a sql.Date
            persona.setFechanacimiento(fechaSQL);
            persona.setSueldo(sueldo);
            persona.setCupo(cupo);

            if (persona.crearPersona()==null) {
                vista.getDlgCrud().setVisible(false);
                JOptionPane.showMessageDialog(vista, "Persona Creada Satisfactoriamente");
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo crear la persona");
            }

        }else {
             String cedula = vista.getTxtIdentificacion().getText();
        String nombres = vista.getTxtNombre().getText();
        String apellidos = vista.getTxtApellidos().getText();
        String sexo = vista.getTxtSexo().getText();
        String telefono = vista.getTxtTelefono().getText();
        Date fecha = vista.getjDateChooser1().getDate();
            double sueldo = Double.parseDouble(vista.getTxtSueldo().getText());
            int cupo = Integer.parseInt(vista.getTxtCupo().getText());

            ModeloPersona persona = new ModeloPersona();
            persona.setIdpersona(cedula);
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setSexo(sexo);
            persona.setTelefono(telefono);

            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());//Paso de util.Date a sql.Date
            persona.setFechanacimiento(fechaSQL);
            persona.setSueldo(sueldo);
            persona.setCupo(cupo);

        if (persona.modificarPersona()) {
            vista.getDlgCrud().setVisible(false);
            JOptionPane.showMessageDialog(vista, "Persona Modificada Satisfactoriamente");
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo modificar la persona");
        }
        }
//else hacemos el editar
        //EDITAR
       
        
        cargaPersonas(); //Actualizo la tabla con los datos

    }
}
