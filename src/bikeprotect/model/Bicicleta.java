package bikeprotect.model;

public class Bicicleta {
    private String numeroSerie;
    private String cpfUsuario;
    private String marca;
    private String modelo;
    private String tipo;
    private int aro;
    private String cor;
    private boolean statusRoubo;

    public Bicicleta(String numeroSerie, String cpfUsuario, String marca, String modelo, String tipo, int aro, String cor, boolean statusRoubo) {
        this.numeroSerie = numeroSerie;
        this.cpfUsuario = cpfUsuario;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.aro = aro;
        this.cor = cor;
        this.statusRoubo = statusRoubo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAro() {
        return aro;
    }

    public void setAro(int aro) {
        this.aro = aro;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isStatusRoubo() {
        return statusRoubo;
    }

    public void setStatusRoubo(boolean statusRoubo) {
        this.statusRoubo = statusRoubo;
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "numeroSerie='" + numeroSerie + '\'' +
                ", cpfUsuario='" + cpfUsuario + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", aro=" + aro +
                ", cor='" + cor + '\'' +
                ", statusRoubo=" + statusRoubo +
                '}';
    }
}
