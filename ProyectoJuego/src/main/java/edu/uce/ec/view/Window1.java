package edu.uce.ec.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window1 extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public Window1(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.black);
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);

        JLabel lbl1 = new JLabel();
        lbl1.setForeground(Color.white);
        lbl1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl1.setText("GALAGA GAME");
        lbl1.setFont(lbl1.getFont().deriveFont(Font.PLAIN, 20)); // Ajustar tamaño de fuente
        contentPane.add(lbl1, BorderLayout.CENTER);

        JPanel buttonNew = new JPanel();
        buttonNew.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8)); // Botones uno al lado del otro, centrados
        JButton btn1 = new JButton("NEW GAME");
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                NewUserWindow ventana2 = new NewUserWindow("NEW GAME");
                ventana2.setVisible(true);
            }


        });
        buttonNew.add(btn1);
        JPanel buttonCont = new JPanel();
        buttonCont.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8)); // Botones uno al lado del otro, centrados
        JButton btn2 = new JButton("CONTINUE GAME");

        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                UserWindow ventana3 = new UserWindow("CONTINUE GAME");
                ventana3.setVisible(true);
            }


        });

        buttonNew.add(btn1);
        JPanel buttonList = new JPanel();
        buttonList.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8)); // Botones uno al lado del otro, centrados
        JButton btn3 = new JButton("Ranking List");

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RankingListWindow rankingWindow = new RankingListWindow("Ranking List");
                rankingWindow.setVisible(true);

            }
        });

        buttonNew.add(btn1);
        contentPane.add(buttonNew, BorderLayout.SOUTH);

        buttonCont.add(btn2);
        contentPane.add(buttonCont, BorderLayout.SOUTH);

        buttonList.add(btn3);
        contentPane.add(buttonList, BorderLayout.SOUTH);


    }
}
