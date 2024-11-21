import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Sistema de Gestão de Cinema ===");
            System.out.println("1. Cadastrar Gênero");
            System.out.println("2. Consultar Gênero");
            System.out.println("3. Editar Gênero");
            System.out.println("4. Listar Gêneros");
            System.out.println("5. Cadastrar Filme");
            System.out.println("6. Consultar Filme");
            System.out.println("7. Editar Filme");
            System.out.println("8. Listar Filmes");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarGenero(scanner);
                    break;
                case 2:
                    consultarGenero(scanner);
                    break;
                case 3:
                    editarGenero(scanner);
                    break;
                case 4:
                    listarGeneros();
                    break;
                case 5:
                    cadastrarFilme(scanner);
                    break;
                case 6:
                    consultarFilme(scanner);
                    break;
                case 7:
                    editarFilme(scanner);
                    break;
                case 8:
                    listarFilmes();
                    break;
                case 9:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 9);

        scanner.close();
    }
}