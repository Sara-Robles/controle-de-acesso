package br.edu.fatec.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe "Interface" herda comportamentos da classe "JFrame" (classe mãe)
public class Interface extends JFrame {

    // Variáveis/Atributos globais
    private static final long serialVersionUID = 1L;
    private JPanel pnl;
    private JLabel lblTitulo, lblNome, lblSenha;
    private JTextField txtNome;
    private JPasswordField txtSenha;
    private JButton btnCadastrar, btnAlterar, btnExcluir, btnLimpar;
    private JTextArea txtMensagem;
    private Color Brown = new Color(228, 192, 135);
    private Color Beige = new Color(246, 239, 189);

    //Construtor (contém os componentes, suas propriedades e comportamentos)
    public Interface() {
        setSize(850, 400);
        setTitle("Cadastro de Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Painel Principal
        pnl = new JPanel();
        pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnl.setBackground(Brown);
        setContentPane(pnl);
        pnl.setLayout(null);

        lblTitulo = new JLabel("Cadastro de Usuários");
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lblTitulo.setBounds(280, 20, 300, 30);
        pnl.add(lblTitulo);

        lblNome = new JLabel("Nome Completo:");
        lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblNome.setBounds(30, 70, 200, 30);
        pnl.add(lblNome);

        //Input do nome
        txtNome = new JTextField();
        txtNome.setBounds(250, 70, 500, 30);
        pnl.add(txtNome);
        txtNome.setColumns(10);

        lblSenha = new JLabel("Senha de 6 Dígitos:");
        lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSenha.setBounds(30, 120, 200, 30);
        pnl.add(lblSenha);

        //Input da Senha
        txtSenha = new JPasswordField();
        txtSenha.setEchoChar('*');
        txtSenha.setBounds(250, 120, 500, 30);
        ((AbstractDocument) txtSenha.getDocument()).setDocumentFilter(new LimiteCaracteresFilter(6)); // Limite de 6 caracteres
        txtSenha.setColumns(10);
        pnl.add(txtSenha);

        //Output
        txtMensagem = new JTextArea();
        txtMensagem.setBounds(30, 180, 770, 70);
        pnl.add(txtMensagem);

        btnCadastrar = new JButton("Cadastrar Novo Usuário");
        btnCadastrar.setBackground(Beige);
        btnCadastrar.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnCadastrar.setVerticalTextPosition(SwingConstants.CENTER);
        btnCadastrar.setBounds(30, 300, 170, 40);
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        pnl.add(btnCadastrar);

        btnAlterar = new JButton("Alterar Senha");
        btnAlterar.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnAlterar.setVerticalTextPosition(SwingConstants.CENTER);
        btnAlterar.setBackground(Beige);
        btnAlterar.setBounds(230, 300, 170, 40);
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        pnl.add(btnAlterar);

        btnExcluir = new JButton("Excluir Usuário");
        btnExcluir.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnExcluir.setVerticalTextPosition(SwingConstants.CENTER);
        btnExcluir.setBackground(Beige);
        btnExcluir.setBounds(430, 300, 170, 40);
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        pnl.add(btnExcluir);

        btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnLimpar.setVerticalTextPosition(SwingConstants.CENTER);
        btnLimpar.setBackground(Beige);
        btnLimpar.setBounds(630, 300, 170, 40);
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNome.setText(null);
                txtSenha.setText("");
                txtMensagem.setText("Os campos estão vazios.");
            }
        });
        pnl.add(btnLimpar);
    }

    //Classe para limitar o input da senha para 6 dígitos
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

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            if ((fb.getDocument().getLength() - length + string.length()) <= max) {
                super.replace(fb, offset, length, string, attr);
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interface jFrame = new Interface();
                    jFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
