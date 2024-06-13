package bikeprotect.dao;

import java.sql.SQLException;
import java.util.List;

import bikeprotect.model.Usuario;

public interface UsuarioDAO {
    void inserir(Usuario usuario) throws SQLException;
    void atualizar(Usuario usuario) throws SQLException;
    void deletar(String cpf) throws SQLException;
    Usuario buscarPorCpf(String cpf) throws SQLException;
    List<Usuario> buscarTodas() throws SQLException;
    List<Usuario> buscarPorCpfUsuario(String cpfUsuario) throws SQLException;
}
