package view;

import javax.swing.*;


import java.awt.*;

public class Interface extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JTabbedPane tbPannel;
    private Cadastro cadastro;
    private Controle controle;

    public Interface() {
        setSize(850, 400);
        setTitle("Sistema de Cadastro e Controle de Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        tbPannel = new JTabbedPane();
        tbPannel.setSize(858, 376);
        setContentPane(tbPannel);

        cadastro = new Cadastro();
        controle = new Controle();

        tbPannel.addTab("Cadastro", cadastro);
        tbPannel.addTab("Controle", controle);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Interface jFrame = new Interface();
                jFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}