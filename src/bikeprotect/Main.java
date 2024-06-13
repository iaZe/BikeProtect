package bikeprotect;

import java.sql.SQLException;
import java.util.Scanner;

import bikeprotect.dao.BicicletaDAO;
import bikeprotect.dao.BicicletaDAOImp;
import bikeprotect.dao.UsuarioDAO;
import bikeprotect.dao.UsuarioDAOImp;
import bikeprotect.model.Bicicleta;
import bikeprotect.model.Usuario;
import bikeprotect.dao.ConnectionFactory;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
      try {
		ConnectionFactory.conectar();
	} catch (SQLException e) {
		e.printStackTrace();
	}

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAOImp();
            BicicletaDAO bicicletaDAO = new BicicletaDAOImp();

            int opcao;
            do {
                System.out.println("\nMenu:");
                System.out.println("1. Inserir bicicleta");
                System.out.println("2. Atualizar bicicleta");
                System.out.println("3. Deletar bicicleta");
                System.out.println("4. Buscar bicicleta por número de série");
                System.out.println("5. Buscar um usuário por CPF");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                  case 1:
                    System.out.print("CPF do usuário: ");
                    String cpfUsuario = scanner.nextLine();

                    Usuario usuario = usuarioDAO.buscarPorCpf(cpfUsuario);
                    if (usuario == null) {
                        System.out.println("Usuário não encontrado!");
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();
                        Usuario novoUsuario = new Usuario(cpfUsuario, nome, telefone);
                        usuarioDAO.inserir(novoUsuario);
                        System.out.println("Usuário Cadastrado");
                    }

                    System.out.print("Número de série: ");
                    String numeroSerie = scanner.nextLine();
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Tipo: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Aro: ");
                    int aro = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Cor: ");
                    String cor = scanner.nextLine();

                    Bicicleta novaBicicleta = new Bicicleta(numeroSerie, cpfUsuario, marca, modelo, tipo, aro, cor, true);
                    bicicletaDAO.inserir(novaBicicleta);
                    System.out.println("Bicicleta inserida com sucesso!");
                    break;

                  case 2:
                    System.out.print("Número de série da bicicleta a ser atualizada: ");
                    String numeroSerieAtualizar = scanner.nextLine();

                    Bicicleta bicicletaAtualizar = bicicletaDAO.buscarPorNumeroSerie(numeroSerieAtualizar);
                    if (bicicletaAtualizar == null) {
                        System.out.println("Bicicleta não encontrada!");
                        break;
                    }

                    bicicletaDAO.atualizar(bicicletaAtualizar);
                    System.out.println("Bicicleta atualizada com sucesso!");
                    break;

                  case 3:
                    System.out.print("Número de série da bicicleta a ser deletada: ");
                    String numeroSerieDeletar = scanner.nextLine();
                    bicicletaDAO.deletar(numeroSerieDeletar);
                    System.out.println("Bicicleta deletada com sucesso!");
                    break;

                  case 4:
                    System.out.print("Número de série da bicicleta a ser buscada: ");
                    String numeroSerieBuscar = scanner.nextLine();
                    Bicicleta bicicletaEncontrada = bicicletaDAO.buscarPorNumeroSerie(numeroSerieBuscar);
                    if (bicicletaEncontrada != null) {
                        System.out.println(bicicletaEncontrada);
                    } else {
                        System.out.println("Bicicleta não encontrada!");
                    }
                    break;
                    
                  case 5:
                	System.out.print("CPF do usuário: ");
                	String cpfBuscar = scanner.nextLine();
                	Usuario usuarioEncontrado = usuarioDAO.buscarPorCpf(cpfBuscar);
                	if (usuarioEncontrado != null) {
                		System.out.println(usuarioEncontrado);
                	} else {
                		System.out.println("Usuário não cadastrado!");
                	}
                	  
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } while (opcao != 0);

        } catch (SQLException e) {
            System.err.println("Erro de SQL: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}


