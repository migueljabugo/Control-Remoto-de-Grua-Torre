/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventana;

import com.miguelangel.controlgruatorre.cliente.DatosConexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import mygame.graficos;
import persistencia.GruaP;

/**
 *
 * @author MiguelAngel
 */
public class Ventana extends javax.swing.JFrame {
     // Variables declaration - do not modify                     
    private JButton btnGuardarUsuario;
    private JButton btnBorrarUsuario;
    private JButton btnIniciarServicio;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPasswordField textClaveUsuario;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField textNombreUsuario;
    private List<DatosConexion> usuarios;
    // End of variables declaration      

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
        this.usuarios = leerUsuarios();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
                            
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        textNombreUsuario = new javax.swing.JTextField();
        textClaveUsuario = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnGuardarUsuario = new javax.swing.JButton();
        btnBorrarUsuario = new javax.swing.JButton();
        btnIniciarServicio = new javax.swing.JButton();
        usuarios= leerUsuarios();
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        
        
        jTable1.setModel(new ModeloTablaUsuarios(usuarios));
        
        jScrollPane1.setViewportView(jTable1);

        

        jLabel1.setText("Nombre de usuario");

        jLabel2.setText("Contraseña");

        btnGuardarUsuario.setText("Guardar usuario");

        btnBorrarUsuario.setText("Borrar usuario");

        btnIniciarServicio.setText("Iniciar servidor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIniciarServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(textNombreUsuario)
                        .addComponent(textClaveUsuario)
                        .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(btnBorrarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(217, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textClaveUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarUsuario)
                        .addGap(18, 18, 18)
                        .addComponent(btnBorrarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIniciarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        
        
        btnGuardarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nombre, clave;
                nombre = textNombreUsuario.getText().toString();
                String c="";
                for(int i=0; i< textClaveUsuario.getPassword().length;i++){
                    c+=textClaveUsuario.getPassword()[i];
                }
                clave = c;
                if(clave.length()>0 && nombre.length()>0){
                    boolean usuarioDuplicado=false;
                    for(DatosConexion d : usuarios){
                        if(nombre.equals(d.getUsuario())){
                           usuarioDuplicado=true; 
                        }
                    }
                    if(!usuarioDuplicado){
                        usuarios.add(new DatosConexion(nombre, clave));
                        escribirUsuarios();
                        textClaveUsuario.setText("");
                        textNombreUsuario.setText("");
                        JOptionPane.showMessageDialog(null, "usuario creado satisfactoriamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "usuario duplicado");
                        textClaveUsuario.setText("");
                        textNombreUsuario.setText("");
                    }
                    jTable1.setModel(new ModeloTablaUsuarios(usuarios));
                    
                }else{
                    JOptionPane.showMessageDialog(null, "usuario o contraseña demasiado cortos");
                }
            }
        });
        btnBorrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                
                if(jTable1.getSelectedRow()>=0){
                    int seleccion =JOptionPane.showConfirmDialog(null, "¿Desea eliminar el usuario?");
                    if(seleccion==JOptionPane.OK_OPTION){
                        usuarios.remove(jTable1.getSelectedRow());
                        escribirUsuarios();
                        //actualizar tabla
                        jTable1.setModel(new ModeloTablaUsuarios(usuarios));
                    }  
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione un usuario");
                }
            }
        });
        btnIniciarServicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        graficos app = new graficos();
                        app.start();
                    }
                }).start();
            }
        });
        

        pack();
    }                       
                                       

    
   
    public List<DatosConexion> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<DatosConexion> usuarios) {
        this.usuarios = usuarios;
    }
    
    public List<DatosConexion> leerUsuarios(){
        GruaP grua=null;
        try {
            JAXBContext context = JAXBContext.newInstance(GruaP.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            grua = (GruaP)unmarshaller.unmarshal(new File("usuarios.xml"));
            
        } catch (JAXBException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grua.getUsuarios();
    }
    
    public void escribirUsuarios(){
        try {
            JAXBContext contex = JAXBContext.newInstance(GruaP.class);
            Marshaller marshaller = contex.createMarshaller();
            GruaP grua = new GruaP();
            grua.setUsuarios(this.usuarios);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(grua, new FileWriter("usuarios.xml"));
            marshaller.marshal(grua, System.out);
            
        } catch (JAXBException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                
}
