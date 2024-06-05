package edu.uce.ec.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class NewUserWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField userField;
    private JTextField passwordField;

    private JButton createButton;
    private JButton returnButton;

    public NewUserWindow(String title) {
        setTitle("Crear un nuevo Usuario");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los campos de texto
        userField = new JTextField(20); // Hacer los JTextField más largos
        passwordField = new JTextField(20);

        // Crear los botones
        createButton = new JButton("Crear");
        returnButton = new JButton("Regresar");

        // Añadir acción al botón "Regresar"
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
                Window1 mainWindow = new Window1("Ventana Principal"); // Crea y muestra la ventana principal
                mainWindow.setVisible(true);
            }
        });

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Añadir los componentes al panel con restricciones
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Contrasenia:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(returnButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserWindow window = new UserWindow("Mi Ventana");
            window.setVisible(true);
        });
    }
}
