package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    	if (usuario == null)
            throw new Exception("O valor passado não pode ser nulo.");
        try {

            String SQL = "INSERT INTO tb_usuario (cpf, nome, senha) VALUES (?, ?, ?)";
            
            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getCpf());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSenha());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Erro ao inserir dados: " + sqle);
        } finally {
            ConnectionFactory.closeConnection(conn, ps);
        }
        
    }

    // UPDATE
    public void update(Usuario usuario) throws Exception {
        if (usuario == null)
            throw new Exception("O valor passado nao pode ser nulo.");
        try {
            String SQL = "UPDATE tb_usuario SET nome=?, senha=? WHERE cpf=?";
            
            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getCpf());
            ps.executeUpdate();
            
        } catch (SQLException sqle) {
            throw new Exception("Erro ao alterar dados: " + sqle);
        } finally {
            ConnectionFactory.closeConnection(conn, ps);
        }
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

    // Listar todos os usuarios
    public List todosUsuarios() throws Exception {
        try {
            ps = conn.prepareStatement("SELECT * FROM tb_usuario");
            rs = ps.executeQuery();
            
            List<Usuario> usuarios = new ArrayList<Usuario>();
            while (rs.next()) {
                String cpf = rs.getString(1);
                String nome = rs.getString(2);
                String senha = rs.getString(3);
                usuarios.add(new Usuario(cpf, nome, senha));
            }
            return usuarios;
            
        } catch (SQLException sqle) {
        	 throw new Exception("Erro ao listar usuarios: " + sqle.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, ps, rs);
        }
    }
}


