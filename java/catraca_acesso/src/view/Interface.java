package view;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import model.UsuarioDAO;
import util.Usuario;

import java.awt.*;
;

public class Interface extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel pnl;
    private JLabel lblTitulo, lblNome, lblSenha, txtLimiteSenha;
    private JTextField txtNome, txtCpf;
    private JPasswordField txtSenha;
    private JButton btnCadastrar, btnAltualizar, btnConsultar, btnExcluir, btnLimpar;
    
    
    private Color colorBtn = Color.decode("#8464c6");
    private Font fontTexto = new Font("Loma", Font.PLAIN, 18);
    private Font fontBtn = new Font("Loma", Font.BOLD, 16);

    
    public Interface() {
        setSize(850, 400);
        setTitle("Cadastro de Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel Principal
        pnl = new JPanel();
        pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnl.setBackground(new Color(21, 20, 27));
        setContentPane(pnl);
        pnl.setLayout(null);
        
        lblTitulo = new JLabel("Cadastro de Usuários");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Loma", Font.BOLD, 26));
        lblTitulo.setBounds(291, 20, 258, 30);
        pnl.add(lblTitulo);

        
        //INPUTS
        
        // Nome
        lblNome = new JLabel("Nome Completo:");
        lblNome.setFont(fontTexto);
        lblNome.setForeground(Color.WHITE);
        lblNome.setBounds(51, 84, 151, 30);
        pnl.add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setFont(fontTexto);
        txtNome.setBounds(205, 84, 396, 30);
        pnl.add(txtNome);
        
        // CPF
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setForeground(Color.WHITE);
        lblCpf.setFont(fontTexto);
        lblCpf.setBounds(51, 157, 66, 30);
        pnl.add(lblCpf);
        
        txtCpf = new JTextField();
        txtCpf.setFont(fontTexto);
        txtCpf.setBounds(112, 157, 157, 30);
        limitarCaracteres(txtCpf, 11);       
        pnl.add(txtCpf);
        

        // Senha
        lblSenha = new JLabel("Senha:");
        lblSenha.setFont(fontTexto);
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(391, 157, 66, 30);
        pnl.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setFont(fontTexto);
        txtSenha.setBounds(465, 157, 136, 30);
        limitarCaracteres(txtSenha, 6);
        pnl.add(txtSenha);
        
        txtLimiteSenha = new JLabel("*6 digitos");
        txtLimiteSenha.setFont(new Font("Kalimati", Font.ITALIC, 14));
        txtLimiteSenha.setForeground(new Color(246, 97, 81));
        txtLimiteSenha.setBounds(465, 190, 82, 19);
        pnl.add(txtLimiteSenha);
        
        
        // Botão Limpar Campos
        btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setFont(fontBtn);
        btnLimpar.setBackground(new Color(40, 39, 46));
        btnLimpar.setForeground(Color.WHITE);
        btnLimpar.setBounds(651, 123, 151, 30);
        btnLimpar.addActionListener(e -> {
        	limparCampos();
        });
        pnl.add(btnLimpar);

        
        
        // BOTÕES
        
        // CADASTRAR
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(fontBtn);
        btnCadastrar.setBackground(colorBtn);
        btnCadastrar.setForeground(Color.BLACK);
        btnCadastrar.setBounds(51, 275, 130, 40);
       
        // Listener para click no botão
        btnCadastrar.addActionListener(e -> {
        	Usuario usuario = getInputs(); // recebe inputs
        	
        	if(usuario != null) { // verifica se não é nulo
        		try {
    				UsuarioDAO dao = new UsuarioDAO();
    				dao.create(usuario);
    				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
    			} catch (Exception e1) {
    				JOptionPane.showMessageDialog(null,e1.getMessage());
    			}
        		
        	}
        	
        	
        });
        pnl.add(btnCadastrar); // add botão
        

        // ATUALIZAR
        btnAltualizar= new JButton("Atualizar");
        btnAltualizar.setFont(fontBtn);
        btnAltualizar.setBackground(colorBtn);
        btnAltualizar.setForeground(Color.BLACK);
        btnAltualizar.setBounds(247, 275, 130, 40);
        
        // Listener para click no botão
        btnAltualizar.addActionListener(e -> {
        	Usuario usuario = getInputs(); // recebe inputs
        	
        	try {
				UsuarioDAO dao = new UsuarioDAO();
				dao.update(usuario);
				JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
            
        });
        pnl.add(btnAltualizar); // add botão
        
        
        // CONSULTAR
        btnConsultar = new JButton("Consultar");
        btnConsultar.setFont(fontBtn);
        btnConsultar.setBackground(colorBtn);
        btnConsultar.setForeground(Color.BLACK);
        btnConsultar.setBounds(443, 275, 130, 40);
        
        // Listener para click no botão
        btnConsultar.addActionListener(e -> {
        	try {
    			UsuarioDAO dao = new UsuarioDAO();
    			Usuario usuario = dao.read(txtCpf.getText()); // consulta através do cpf, guarda retorno em instância de usuário		
				txtNome.setText(usuario.getNome()); // preenche nome campode input
				JOptionPane.showMessageDialog(null, "Usuário consta no Banco de Dados");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
            
        });
        pnl.add(btnConsultar);

        
        // EXCLUIR
        btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(fontBtn);
        btnExcluir.setBackground(colorBtn);
        btnExcluir.setForeground(Color.BLACK);
        btnExcluir.setBounds(639, 275, 130, 40);
        
        // Listener para click no botão
        btnExcluir.addActionListener(e -> {
        	String cpf = txtCpf.getText();
        	
        	try {
				UsuarioDAO dao = new UsuarioDAO();
				
				if (dao.delete(cpf) != 0) { // verifica se usuário foi encontrado
					limparCampos();
					JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage());
			}
        });
        pnl.add(btnExcluir); // add botão

       
    }



    public static void main(String[] args) {
        EventQueue.invokeLater( ()-> {
                try {
                    Interface jFrame = new Interface();
                    jFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });

    }
    
    private void limparCampos() {
    	txtNome.setText("");
    	txtSenha.setText("");
    	txtCpf.setText("");
    }
    
    // Recebe inputs e armazena em instância de Usuário
    private Usuario getInputs() {
    	Usuario usuario = new Usuario();
    	
    	
    	usuario.setNome(txtNome.getText());
   	 	char[] password = txtSenha.getPassword();
       	usuario.setSenha(new String(password));
       	usuario.setCpf(txtCpf.getText());
   	
    	if(usuario.getNome().equals("") || usuario.getCpf().equals("")  || usuario.getSenha().equals("")  ) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos");
			return null;
    	} else {
    		return usuario;
    	}
    	
    	
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
