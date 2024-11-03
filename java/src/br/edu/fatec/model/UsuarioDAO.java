package br.edu.fatec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.util.Usuario;
import br.edu.fatec.util.ConnectionFactory;

public class UsuarioDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private Usuario usuario;

    public UsuarioDAO() throws Exception {
        // chama a classe ConnectionFactory e estabelece uma conexão
        try {
            this.conn = ConnectionFactory.getConnection();
        } catch (Exception e) {
            throw new Exception("erro: \n" + e.getMessage());
        }
    }

    //CRUD:

    //CREATE
    public void create(Usuario usuario) throws Exception {
        if (usuario == null)
            throw new Exception("O valor passado não pode ser nulo.");
        try {

            String SQL = "INSERT INTO tb_cadastros (nome, senha) values (?, ?)";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.executeUpdate();

        } catch (SQLException sqle) {

            throw new Exception("Erro ao inserir dados " + sqle);

        } finally {
            ConnectionFactory.closeConnection(conn, ps);
        }
    }

    // UPDATE
    public void update(Usuario usuario) throws Exception {
        if (usuario == null)
            throw new Exception("O valor passado nao pode ser nulo.");
        try {
            String SQL = "UPDATE tb_cadastros SET nome=?, senha=? WHERE id = ?";
            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao alterar dados " + sqle);
        } finally {
            ConnectionFactory.closeConnection(conn, ps);
        }
    }

    // DELETE
    public void delete(Usuario usuario) throws Exception {
        if (usuario == null)
            throw new Exception("O valor passado nao pode ser nulo.");
        try {
            String SQL = "DELETE FROM tb_cadastros WHERE id = ?";
            conn = this.conn;
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro ao excluir dados " + sqle);
        } finally {
            ConnectionFactory.closeConnection(conn, ps);
        }
    }

    // READ
    public Usuario read(String nome) throws Exception {
        try {
            String SQL = "SELECT * FROM tb_cadastros WHERE nome=?";
            ps = conn.prepareStatement(SQL);
            ps.setString(1, nome);
            rs = ps.executeQuery(); //Classe result set para pegar campos existentes
            if (rs.next()) {
                int id = rs.getInt(1);
                String senha = rs.getString(2);
                nome = rs.getString(3);


                usuario = new Usuario(id, nome, senha);
            }
            return usuario;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionFactory.closeConnection(conn, ps, rs);
        }
    }

    // Listar todos os usuarios
    public List todosUsuarios() throws Exception {
        try {
            ps = conn.prepareStatement("SELECT * FROM tb_cadastros");
            rs = ps.executeQuery();
            List<Usuario> list = new ArrayList<Usuario>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String senha = rs.getString(3);
                list.add(new Usuario(id, nome, senha));
            }
            return list;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionFactory.closeConnection(conn, ps, rs);
        }
    }
}
