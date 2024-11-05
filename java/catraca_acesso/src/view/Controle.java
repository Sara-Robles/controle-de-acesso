package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import model.UsuarioDAO;
import util.Arduino;

import java.awt.*;

public class Controle extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JLabel lblSenha;
    private JTextField txtCpf;
    private JPasswordField txtSenha;
    private JButton btnLimpar, btnLiberarAcesso;
    private JTextArea txtStatusAcesso;

    private Color colorBtn = Color.decode("#8464c6");
    private Font fontTexto = new Font("Loma", Font.PLAIN, 18);
    
    public Controle() {
    	Arduino arduino = new Arduino("ttyUSB0");
    	//Arduino arduino = new Arduino("");
    	
    	
    	setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(new Color(21, 20, 27));
        setLayout(null);
        

        // CPF
        JLabel lblCpfControle = new JLabel("CPF:");
        lblCpfControle.setForeground(Color.WHITE);
        lblCpfControle.setFont(fontTexto);
        lblCpfControle.setBounds(277, 29, 54, 30);
        add(lblCpfControle);
        
        txtCpf = new JTextField();
        txtCpf.setFont(fontTexto);
        txtCpf.setBounds(226, 71, 157, 30);
        limitarCaracteres(txtCpf, 11);       
        add(txtCpf);
        

        // Senha
        lblSenha = new JLabel("Senha:");
        lblSenha.setFont(fontTexto);
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(462, 29, 63, 30);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setFont(fontTexto);
        txtSenha.setBounds(425, 71, 136, 30);
        limitarCaracteres(txtSenha, 6);
        add(txtSenha);
        
      
        // Botão Limpar Campos
        btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setFont( new Font("Loma", Font.BOLD, 14));
        btnLimpar.setBackground(new Color(40, 39, 46));
        btnLimpar.setForeground(Color.WHITE);
        btnLimpar.setBounds(597, 72, 136, 30);
        btnLimpar.addActionListener(e -> {
        	limparCampos();
        });
        add(btnLimpar);

        
        // Status Acesso
        txtStatusAcesso = new JTextArea();
        txtStatusAcesso.setBounds(333, 229, 149, 30);
        txtStatusAcesso.setFont(fontTexto);
        txtStatusAcesso.setForeground(Color.BLACK);
        txtStatusAcesso.setBackground(Color.WHITE);
        txtStatusAcesso.setEditable(false);
        add(txtStatusAcesso);

        
        btnLiberarAcesso = new JButton("Liberar Acesso");
        btnLiberarAcesso.setFont( new Font("Loma", Font.BOLD, 18));
        btnLiberarAcesso.setBackground(colorBtn);
        btnLiberarAcesso.setForeground(Color.BLACK);
        btnLiberarAcesso.setBounds(322, 150, 172, 40);
        
        // Listener para click no botão
        btnLiberarAcesso.addActionListener(e -> {
        	
        	// verifica se campos estão vazios
        	if(txtCpf.getText().isEmpty() ||
        	   txtSenha.getPassword().length == 0) { 
        		
    			JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos");
        	
        	} else {
        		
        		try {
        			
                	UsuarioDAO dao = new UsuarioDAO();
                	String cpf = txtCpf.getText();
                	String senha = new String (txtSenha.getPassword());
                	
    				boolean check = dao.readSenha(cpf, senha);
    				
    				if(check) {
    					txtStatusAcesso.setText(" Acesso liberado!");
    					txtStatusAcesso.setBackground(Color.decode("#A1EEBD"));
    					txtCpf.setText("");
    			    	txtSenha.setText("");
    			    	
    			    	arduino.setComando(true);
    				} else {
    					arduino.setComando(false);
    					txtStatusAcesso.setText(" Acesso negado!");
    					txtStatusAcesso.setBackground(Color.decode("#C96868"));
    					
    				}
    					
    			} catch (Exception e1) {
    				JOptionPane.showMessageDialog(null,e1.getMessage());
    			}
        	}

        });
        add(btnLiberarAcesso);

       
    
    }
    
    private void limparCampos() {
    	txtCpf.setText("");
    	txtSenha.setText("");
    	txtStatusAcesso.setText("");
    	txtStatusAcesso.setBackground(Color.WHITE);
    }
    
    // Limitar caracteres de Inputs
    private static void limitarCaracteres(JTextField textField, int limite) {
        PlainDocument document = (PlainDocument) textField.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (string.length() <= limite) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }
    
    
}