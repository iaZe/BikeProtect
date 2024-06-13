package bikeprotect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bikeprotect.model.Bicicleta;

public class BicicletaDAOImp implements BicicletaDAO {

    public BicicletaDAOImp() {
       
    }

    @Override
    public void inserir(Bicicleta bicicleta) throws SQLException {
        String sql = "INSERT INTO bicicleta (numero_serie, cpf_usuario, marca, modelo, tipo, aro, cor, status_roubo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bicicleta.getNumeroSerie());
            stmt.setString(2, bicicleta.getCpfUsuario());
            stmt.setString(3, bicicleta.getMarca());
            stmt.setString(4, bicicleta.getModelo());
            stmt.setString(5, bicicleta.getTipo());
            stmt.setInt(6, bicicleta.getAro());
            stmt.setString(7, bicicleta.getCor());
            stmt.setBoolean(8, bicicleta.isStatusRoubo());
            stmt.executeUpdate();
        }
    }
    
	@Override
	public void atualizar(Bicicleta bicicleta) throws SQLException {
		String sql = "UPDATE bicicleta SET status_roubo = ? WHERE numero_serie = ?";
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, bicicleta.isStatusRoubo());
            stmt.setString(2, bicicleta.getNumeroSerie());
            stmt.executeUpdate();
        }
	}

	@Override
	public void deletar(String numeroSerie) throws SQLException {
		String sql = "DELETE FROM bicicleta WHERE numero_serie = ?";
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroSerie);
            stmt.executeUpdate();
        }
	}

    @Override
    public Bicicleta buscarPorNumeroSerie(String numeroSerie) throws SQLException {
        String sql = "SELECT * FROM bicicleta WHERE numero_serie = ?";
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroSerie);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarBicicletaFromResultSet(rs);
                }
            }
        }
        return null; 
    }

    @Override
    public List<Bicicleta> buscarTodas() throws SQLException {
        String sql = "SELECT * FROM bicicleta";
        List<Bicicleta> bicicletas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                bicicletas.add(criarBicicletaFromResultSet(rs));
            }
        }
        return bicicletas;
    }

    @Override
    public List<Bicicleta> buscarPorCpfUsuario(String cpfUsuario) throws SQLException {
        String sql = "SELECT * FROM bicicleta WHERE cpf_usuario = ?";
        List<Bicicleta> bicicletas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpfUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    bicicletas.add(criarBicicletaFromResultSet(rs));
                }
            }
        }
        return bicicletas;
    }

    private Bicicleta criarBicicletaFromResultSet(ResultSet rs) throws SQLException {
        return new Bicicleta(
            rs.getString("numero_serie"),
            rs.getString("cpf_usuario"),
            rs.getString("marca"),
            rs.getString("modelo"),
            rs.getString("tipo"),
            rs.getInt("aro"),
            rs.getString("cor"),
            rs.getBoolean("status_roubo")
        );
    }
}
