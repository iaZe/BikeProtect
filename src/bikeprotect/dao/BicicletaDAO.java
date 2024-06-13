package bikeprotect.dao;

import java.sql.SQLException;
import java.util.List;

import bikeprotect.model.Bicicleta;

public interface BicicletaDAO {
    void inserir(Bicicleta bicicleta) throws SQLException;
    void atualizar(Bicicleta bicicleta) throws SQLException;
    void deletar(String numeroSerie) throws SQLException;
    Bicicleta buscarPorNumeroSerie(String numeroSerie) throws SQLException;
    List<Bicicleta> buscarTodas() throws SQLException;
    List<Bicicleta> buscarPorCpfUsuario(String cpfUsuario) throws SQLException;
}
