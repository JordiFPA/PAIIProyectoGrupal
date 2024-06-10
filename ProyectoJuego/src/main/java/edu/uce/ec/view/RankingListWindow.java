package edu.uce.ec.view;

import edu.uce.ec.Api.Consumer;
import edu.uce.ec.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RankingListWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private Consumer consumer = new Consumer();

    public RankingListWindow(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Configurar el contenido de la ventana
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Etiqueta de título
        JLabel lblTitle = new JLabel("Ranking List", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setVisible(true);
        contentPane.add(lblTitle, BorderLayout.NORTH);

        // Panel para mostrar el ranking
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
        list.addActionListener(e -> {
            List<User> rankingList = consumer.getRanking();
            rankingPanel.removeAll(); // Limpiar el panel antes de agregar los datos

            for (User user : rankingList) {
                JLabel userLabel = new JLabel(user.getName() + " - Score: " + user.getScore());
                rankingPanel.add(userLabel);
            }
            rankingPanel.revalidate();
            rankingPanel.repaint();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnBack);
        buttonPanel.add(list);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
}
