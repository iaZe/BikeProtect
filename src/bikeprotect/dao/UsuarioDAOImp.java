package bikeprotect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import bikeprotect.model.Usuario;

public class UsuarioDAOImp implements UsuarioDAO {

    public UsuarioDAOImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (cpf, nome, telefone) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getTelefone());
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, telefone = ? WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTelefone());
            stmt.setString(4, usuario.getCpf());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletar(String cpf) throws SQLException {
        String sql = "DELETE FROM usuario WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }

    @Override
    public Usuario buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getString("cpf"), rs.getString("nome"), 
                                       rs.getString("telefone"));
                }
            }
        }
        return null;
    }
    
	@Override
	public List<Usuario> buscarTodas() throws SQLException {
		String sql = "SELECT * FROM usuarios";
		List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = ConnectionFactory.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
        	while (rs.next()) {
        		usuarios.add(criarUsuarioFromResultSet(rs));
        	}
        }
		return usuarios;
	}

	@Override
	public List<Usuario> buscarPorCpfUsuario(String cpfUsuario) throws SQLException {
		String sql = "SELECT * FROM usuarios WHERE cpf = ?";
		List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = ConnectionFactory.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
               stmt.setString(1, cpfUsuario);
               try (ResultSet rs = stmt.executeQuery()) {
                   while (rs.next()) {
                       usuarios.add(criarUsuarioFromResultSet(rs));
                   }
               }
           }
		return usuarios;
	}
	
    private Usuario criarUsuarioFromResultSet(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getString("cpf"),
            rs.getString("nome"),
            rs.getString("telefone")
        );
    }
}
