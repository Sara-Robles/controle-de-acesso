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


public class Cadastro extends JPanel {
    private static final long serialVersionUID = 1L;
	
	private JTextField txtNome, txtCpf;
    private JPasswordField txtSenha;
    private JButton btnCadastrar, btnAltualizar, btnConsultar, btnExcluir, btnLimparCampos;
    private JLabel lblTitulo, lblNome, lblSenha, txtLimiteSenha;

    private Color colorBtn = Color.decode("#8464c6");
    private Font fontTexto = new Font("Loma", Font.PLAIN, 18);
    private Font fontBtn = new Font("Loma", Font.BOLD, 16);

    public Cadastro() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(new Color(21, 20, 27));
        setLayout(null);
        
     
        lblTitulo = new JLabel("Cadastro de Usuários");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Loma", Font.BOLD, 26));
        lblTitulo.setBounds(283, 20, 271, 30);
        add(lblTitulo);

        // Nome
        lblNome = new JLabel("Nome Completo:");
        lblNome.setFont(fontTexto);
        lblNome.setForeground(Color.WHITE);
        lblNome.setBounds(51, 84, 151, 30);
        add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setFont(fontTexto);
        txtNome.setBounds(205, 84, 396, 30);
        add(txtNome);
        
        // CPF
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setForeground(Color.WHITE);
        lblCpf.setFont(fontTexto);
        lblCpf.setBounds(51, 157, 66, 30);
        add(lblCpf);
        
        txtCpf = new JTextField();
        txtCpf.setFont(fontTexto);
        txtCpf.setBounds(112, 157, 157, 30);
        limitarCaracteres(txtCpf, 11);       
        add(txtCpf);
        
        // Senha
        lblSenha = new JLabel("Senha:");
        lblSenha.setFont(fontTexto);
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(391, 157, 66, 30);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setFont(fontTexto);
        txtSenha.setBounds(465, 157, 136, 30);
        limitarCaracteres(txtSenha, 6);
        add(txtSenha);
        
        txtLimiteSenha = new JLabel("*6 digitos");
        txtLimiteSenha.setFont(new Font("Kalimati", Font.ITALIC, 14));
        txtLimiteSenha.setForeground(new Color(246, 97, 81));
        txtLimiteSenha.setBounds(465, 190, 82, 19);
        add(txtLimiteSenha);
        
        // Limpar campos
        btnLimparCampos = new JButton("Limpar Campos");
        btnLimparCampos.setFont(fontBtn);
        btnLimparCampos.setBackground(new Color(40, 39, 46));
        btnLimparCampos.setForeground(Color.WHITE);
        btnLimparCampos.setBounds(639, 123, 169, 30);
        btnLimparCampos.addActionListener(e -> limparCampos());
        add(btnLimparCampos);

        
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
        	
        	if(usuario != null) {
        		try {
    				UsuarioDAO dao = new UsuarioDAO();
    				dao.create(usuario);
    				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
    				
    			} catch (Exception e1) {
    				JOptionPane.showMessageDialog(null,e1.getMessage());
    			}
        		
        	}
        });
        add(btnCadastrar);
        

        // ATUALIZAR
        btnAltualizar= new JButton("Atualizar");
        btnAltualizar.setFont(fontBtn);
        btnAltualizar.setBackground(colorBtn);
        btnAltualizar.setForeground(Color.BLACK);
        btnAltualizar.setBounds(247, 275, 130, 40);
        
        // Listener para click no botão
        btnAltualizar.addActionListener(e -> {
        	Usuario usuario = getInputs(); // recebe inputs
        	
        	if(usuario != null) {
        		
        		
	        	try {
					UsuarioDAO dao = new UsuarioDAO();
					
					if (dao.update(usuario) != 0) { // verifica se usuário foi encontrado
						limparCampos();
						JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
					}
					
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
        	}
            
        });
        add(btnAltualizar);
        
        
        // CONSULTAR
        btnConsultar = new JButton("Consultar");
        btnConsultar.setFont(fontBtn);
        btnConsultar.setBackground(colorBtn);
        btnConsultar.setForeground(Color.BLACK);
        btnConsultar.setBounds(443, 275, 130, 40);
        
        // Listener para click no botão
        btnConsultar.addActionListener(e -> {
        	String cpf = txtCpf.getText();
        	
        	if(!cpf.isEmpty()) { // verifica se campo CPF esta vazio
        		
	        	try {
	    			UsuarioDAO dao = new UsuarioDAO();
	    			Usuario usuario = dao.read(cpf); // consulta pelo CPF, guarda retorno em instância de usuário		
					txtNome.setText(usuario.getNome()); // preenche campo de input Nome
					
					JOptionPane.showMessageDialog(null, "Usuário consta no Banco de Dados");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
	        	
        	} else {
				JOptionPane.showMessageDialog(null, "Por favor, preencha o campo de CPF.");
        	}
            
        });
        add(btnConsultar);
        
        
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
        add(btnExcluir);
        
        
    }

    private void limparCampos() {
        txtNome.setText("");
        txtSenha.setText("");
        txtCpf.setText("");
    }
    
 // Recebe inputs e armazena em instância de Usuário
    private Usuario getInputs() {
    	
    	String cpf = txtCpf.getText();
    	String nome = txtNome.getText();
    	char[] senha = txtSenha.getPassword();

    	
    	if(cpf.isEmpty() || nome.isEmpty() || senha.length == 0 ) {
    		JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos");
			return null;
    	} else {
    		Usuario usuario = new Usuario(cpf, nome, new String(senha));
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
