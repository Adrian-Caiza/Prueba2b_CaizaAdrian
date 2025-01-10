import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Calificaciones {
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtProgramacion;
    private JTextField txtBases;
    private JTextField txtDiseño;
    private JTextField txtAnalisis;
    private JTextField txtGestion;
    private JButton registrarButton;
    public JPanel adminIU;


    public Calificaciones() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/GestorCalificaciones";
                String user = "root";
                String password = "123456";
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = DriverManager.getConnection(url,user, password);
                    System.out.println("Se ha conectado a la BDD");
                    // Insercion: insert into estudiantes
                    String sql = "INSERT INTO estudiantes (cedula,nombre,programacion,bases_de_datos,diseño_interfazes,analisis_datos,gestion_proyectos)VALUES(?,?,?,?,?,?,?)";

                    ps=conn.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(txtCedula.getText()));
                    ps.setString(2,txtNombre.getText());
                    ps.setInt(3,Integer.parseInt(txtProgramacion.getText()));
                    ps.setInt(4,Integer.parseInt(txtBases.getText()));
                    ps.setInt(5,Integer.parseInt(txtDiseño.getText()));
                    ps.setInt(6,Integer.parseInt(txtAnalisis.getText()));
                    ps.setInt(7,Integer.parseInt(txtGestion.getText()));

                    int filasinsertadas = ps.executeUpdate();
                    System.out.println("Filas insertadas: " + filasinsertadas);

                }catch (SQLException ex) {
                    ex.printStackTrace();
                }finally {
                    try {
                        if(conn != null) {conn.close();}
                        if(ps != null){ps.close();}
                    }catch (SQLException ex) {}
                }


            }
        });
    }
    public JPanel getPanel() {
        return adminIU;
    }



}
