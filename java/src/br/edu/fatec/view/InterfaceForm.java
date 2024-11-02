package br.edu.fatec.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class InterfaceForm {
    private static final long serialVersionUID = 1L;
    private JPanel panel1;
    private JTextPane textPane1;
    private JLabel lblNome, lblSenha;
    private JTextField txtNome;
    private JPasswordField txtSenha;
    private JButton btnCadastrar, btnAlterar, btnExcluir, btnLimpar;
    private JTextArea txtMensagem;

    public InterfaceForm() {
        panel1 = new JPanel();
        panel1.setVisible(true);
        panel1.setSize(800, 600);
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel1.setBackground(Color.WHITE);
        panel1.setLayout(null);

        lblNome = new JLabel("Nome Completo:");
        lblNome.setFont(new Font("Arial", Font.PLAIN, 22));
        lblNome.setBounds(70, 50, 200, 30);
        panel1.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(300, 50, 150, 30);
        txtNome.setColumns(10);
        panel1.add(txtNome);

        lblSenha = new JLabel("Senha de 6 Dígitos:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 22));
        lblSenha.setBounds(70, 100, 200, 30);
        panel1.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setEchoChar('*');
        txtSenha.setBounds(300, 100, 150, 30);
        ((AbstractDocument) txtSenha.getDocument()).setDocumentFilter(new LimiteCaracteresFilter(6)); // Limite de 6 caracteres
        panel1.add(txtSenha);
        txtSenha.setColumns(10);
    }

    static class LimiteCaracteresFilter extends DocumentFilter {
        private int max;

        public LimiteCaracteresFilter(int max) {
            this.max = max;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if ((fb.getDocument().getLength() + string.length()) <= max) {
                super.insertString(fb, offset, string, attr);
            }
        }

    }

    public static void main(String[] args) {
        new InterfaceForm();
    }
}
