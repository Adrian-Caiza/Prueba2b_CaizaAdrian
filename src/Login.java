import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    private JPasswordField txtContraseña;
    private JTextField txtUsuario;
    private JButton btnIniciar;
    public JPanel loginIU;

    public Login() {
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/GestorCalificaciones";
                String username = "root";
                String password = "123456";
                try (Connection con = DriverManager.getConnection(url, username, password)) {
                    System.out.println("Conexion establecida");

                    String parametroUsuario = txtUsuario.getText();
                    String parametroPass = new String(txtContraseña.getPassword());

                    String query = "SELECT password FROM usuarios WHERE username = ?";
                    try (PreparedStatement stmt = con.prepareStatement(query)) {
                        stmt.setString(1, parametroUsuario);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                String passwordDB = rs.getString("1234");

                                if (passwordDB.equals(parametroPass)) {
                                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");

                                    String idQuery = "SELECT id_usuario FROM usuarios WHERE email = ?";
                                    try (PreparedStatement idStmt = con.prepareStatement(idQuery)) {
                                        idStmt.setString(1, parametroUsuario);
                                        try (ResultSet idRs = idStmt.executeQuery()) {
                                            if (idRs.next()) {
                                                int userId = idRs.getInt("id_usuario");

                                                // Mostrar el panel CALIFICACIONES
                                                JFrame frame = new JFrame("Gestor de calificaciones");
                                                frame.setContentPane(new Calificaciones().getPanel());
                                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                frame.setSize(800, 600);
                                                frame.setLocationRelativeTo(null);
                                                frame.setVisible(true);
                                            }
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                            }
                        }
                    }
                } catch (SQLException error) {
                    JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos");
                }

            }
        });
    }

}
