package edu.uce.ec.view;

import edu.uce.ec.Api.Consumer;

import javax.swing.*;
import java.awt.*;

public class RankingListWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    Consumer c = new Consumer();
    public RankingListWindow(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Configurar el contenido de la ventana
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Etiqueta de título (por ahora vacío)
        JLabel lblTitle = new JLabel("Ranking List", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setVisible(true);
        contentPane.add(lblTitle, BorderLayout.NORTH);

        // Panel que eventualmente contendrá los datos del ranking (por ahora vacío)
        JPanel rankingPanel = new JPanel();
        rankingPanel.setLayout(new BoxLayout(rankingPanel, BoxLayout.Y_AXIS));
        contentPane.add(new JScrollPane(rankingPanel), BorderLayout.CENTER);

        // Botón para regresar a la ventana principal
        JButton btnBack = new JButton("Regresar");
        btnBack.addActionListener(e -> {
            dispose();
            Window1 mainWindow = new Window1("Mi Ventana");
            mainWindow.setVisible(true);
        });
        JButton list = new JButton("Ver lista");
        btnBack.addActionListener(e -> {
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnBack);
        buttonPanel.add(list);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
}

