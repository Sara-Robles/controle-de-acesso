package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import util.Usuario;

public class UsuarioDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private Usuario usuario;

    public UsuarioDAO() throws Exception {
        // Abre conexão
        try {
            this.conn = ConnectionFactory.getConnection();
        } catch (Exception e) {
            throw new Exception("erro: \n" + e.getMessage());
        }
    }

    // CRUD

    // CREATE
    public void create(Usuario usuario) throws Exception {
        try {
        	// Verifcar se usuário já existe
        	String checkSQL = "SELECT COUNT(*) FROM tb_usuario WHERE cpf=?";
        	ps = conn.prepareStatement(checkSQL);
        	ps.setString(1, usuario.getCpf());
        	rs = ps.executeQuery();
        	
        	if(rs.next() && rs.getInt(1) > 0) {
        		throw new Exception("Usuário já existe.");
        	} else { 
        		// Inserir novo usuário
                String SQL = "INSERT INTO tb_usuario (cpf, nome, senha) VALUES (?, ?, ?)";
                
                ps = conn.prepareStatement(SQL);
                ps.setString(1, usuario.getCpf());
                ps.setString(2, usuario.getNome());
                ps.setString(3, usuario.getSenha());
                ps.executeUpdate();
        	}
        	
        	
        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados: " + sqle);
        } finally {
            ConnectionFactory.closeConnection(conn, ps);
        }
        
    }

    // UPDATE
    public int update(Usuario usuario) throws Exception {
    	int result;
    	try {
            String SQL = "UPDATE tb_usuario SET nome=?, senha=? WHERE cpf=?";
            
            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getCpf());
            result = ps.executeUpdate();
            
            if (result == 0) {
                throw new Exception("Usuário não encontrado.");
            }
            
        } catch (SQLException sqle) {
            throw new Exception("Erro ao alterar dados: " + sqle);
        } finally {
            ConnectionFactory.closeConnection(conn, ps);
        }
    	return result;
    }

    // DELETE
    public int delete(String cpf) throws Exception {
    	int result;
        if (cpf == null)
            throw new Exception("O valor passado nao pode ser nulo.");
        
        try {
            String SQL = "DELETE FROM tb_usuario WHERE cpf=?";
            
            ps = conn.prepareStatement(SQL);
            ps.setString(1, cpf);
            result = ps.executeUpdate();
            
            if (result == 0) {
                throw new Exception("Nenhum usuário encontrado para exclusão.");
            }
            
        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados: " + sqle);
        } finally {
            ConnectionFactory.closeConnection(conn, ps);
        }
        return result;
    }

    // READ
    public Usuario read(String cpf) throws Exception {
        try {
            String SQL = "SELECT * FROM tb_usuario WHERE cpf=?";
            
            ps = conn.prepareStatement(SQL);
            ps.setString(1, cpf);
            rs = ps.executeQuery(); // Classe ResultSet para pegar campos existentes
            
            if (rs.next()) {
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                usuario = new Usuario(cpf, nome, senha);
            } else {
            	throw new Exception("Usuário não encontrado.");
            }
           
            return usuario;
            
        } catch (SQLException sqle) {
        	throw new Exception("Erro ao procurar usuario: " + sqle.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, ps, rs);
        }
    }
    
        
        public boolean readSenha(String cpf, String senhaInput) throws Exception {
            try {
                String SQL = "SELECT senha FROM tb_usuario WHERE cpf=?";
                String senha;
                
                ps = conn.prepareStatement(SQL);
                ps.setString(1, cpf);
                rs = ps.executeQuery(); 
                
              
                if (rs.next()) {
                    senha = rs.getString("senha");
                } else {
                	throw new Exception("Usuário não encontrado.");
                }
                
                return senha.equals(senhaInput) ? true : false;
                
            } catch (SQLException sqle) {
            	throw new Exception("Erro ao procurar usuario: " + sqle.getMessage());
            } finally {
                ConnectionFactory.closeConnection(conn, ps, rs);
            }

    }
}


