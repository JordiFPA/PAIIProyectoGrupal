package edu.uce.ec.view;

import edu.uce.ec.Api.Consumer;
import edu.uce.ec.controller.Container;
import edu.uce.ec.model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.*;

public class NewUserWindow extends JFrame {
    private final Consumer consumer = new Consumer();
    private Container container;
    @Serial
    private static final long serialVersionUID = 1L;
    private final JTextField userField;
    private final JTextField passwordField;

    private JButton createButton;
    private JButton returnButton;

    public NewUserWindow(String title) {
        setTitle("Crear un nuevo Usuario");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los campos de texto
        userField = new JTextField(20);
        passwordField = new JTextField(20);

        // Crear los botones
        createButton = new JButton("Crear");
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = passwordField.getText();
                User newUser = new User(username, password, 100, 0);
                long userId = consumer.createUser(username, password, 100, 0);
                newUser.setId(userId);
                container = new Container(newUser);
                GameFrame game = new GameFrame("Hola", container.getUser());
                game.setVisible(true);
            }
        });

        returnButton = new JButton("Regresar");

        // Añadir acción al botón "Regresar"
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Window1 mainWindow = new Window1("Ventana Principal");
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
            NewUserWindow window = new NewUserWindow("Crear Nuevo Usuario");
            window.setVisible(true);
        });
    }
}
