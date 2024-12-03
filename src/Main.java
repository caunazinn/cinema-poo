import java.util.Scanner;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println(" ");
            System.out.println("======= SISTEMA DE GESTÃO =======");
            System.out.println("1. Gerenciar Atores");
            System.out.println("2. Gerenciar Funcionários");
            System.out.println("3. Gerenciar Filmes");
            System.out.println("4. Gerenciar Sessões");
            System.out.println("5. Gerenciar Assentos");
            System.out.println("6. Gerenciar Tipos de Assento");
            System.out.println("7. Gerenciar Salas");
            System.out.println("8. Gerenciar Ingressos");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuAtores(scanner);
                    break;
                case 2:
                    menuFuncionarios(scanner);
                    break;
                case 3:
                    menuFilmes(scanner);
                    break;
                case 4:
                    menuSessoes(scanner);
                    break;
                case 5:
                    menuAssentos(scanner);
                    break;
                case 6:
                    menuTiposDeAssento(scanner);
                    break;
                case 7:
                    menuSalas(scanner);
                    break;
                case 8:
                    menuIngressos(scanner);
                    break;
                case 9:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);

        scanner.close();
    }

    private static void menuAtores(Scanner scanner) {
        Ator ator = new Ator();
        int opcao;

        do {
            System.out.println(" ");
            System.out.println("=== Menu Atores ===");
            System.out.println("1. Cadastrar Ator");
            System.out.println("2. Editar Ator");
            System.out.println("3. Consultar Ator");
            System.out.println("4. Listar Atores");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do ator: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o CPF do ator: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Digite o email do ator: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite o número do registro: ");
                    int registro = scanner.nextInt();
                    scanner.nextLine();

                    Ator novoAtor = new Ator(cpf, nome, email, registro);
                    if (ator.cadastrar(novoAtor)) {
                        System.out.println("Ator cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar o ator.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o número de registro do ator que deseja editar: ");
                    int registroEditar = scanner.nextInt();
                    scanner.nextLine();

                    Ator atorEncontrado = null;
                    for (Ator a : ator.listarAtores()) {
                        if (a.getRegistro() == registroEditar) {
                            atorEncontrado = a;
                            break;
                        }
                    }

                    if (atorEncontrado != null) {
                        System.out.println("Ator encontrado: " + atorEncontrado.getNome());
                        System.out.print("Digite o novo nome (ou pressione Enter para manter): ");
                        String novoNome = scanner.nextLine();
                        if (!novoNome.isEmpty()) {
                            atorEncontrado.setNome(novoNome);
                        }

                        System.out.print("Digite o novo email (ou pressione Enter para manter): ");
                        String novoEmail = scanner.nextLine();
                        if (!novoEmail.isEmpty()) {
                            atorEncontrado.setEmail(novoEmail);
                        }

                        System.out.print("Digite o novo CPF (ou pressione Enter para manter): ");
                        String novoCpf = scanner.nextLine();
                        if (!novoCpf.isEmpty()) {
                            atorEncontrado.setCpf(novoCpf);
                        }

                        if (ator.editarAtor(atorEncontrado)) {
                            System.out.println("Ator editado com sucesso!");
                        } else {
                            System.out.println("Erro ao editar o ator.");
                        }
                    } else {
                        System.out.println("Ator não encontrado com o registro informado.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o CPF do ator que deseja consultar: ");
                    String cpfConsultar = scanner.nextLine();
                    Ator atorConsultado = ator.consultar(cpfConsultar);
                    if (atorConsultado != null) {
                        System.out.println("Ator encontrado: ");
                        System.out.println("Nome: " + atorConsultado.getNome());
                        System.out.println("CPF: " + atorConsultado.getCpf());
                        System.out.println("Email: " + atorConsultado.getEmail());
                        System.out.println("Registro: " + atorConsultado.getRegistro());
                    } else {
                        System.out.println("Ator não encontrado.");
                    }
                    break;

                case 4:
                    ArrayList<Ator> listaAtores = ator.listarAtores();
                    if (!listaAtores.isEmpty()) {
                        System.out.println("Lista de Atores:");
                        for (Ator a : listaAtores) {
                            System.out.println("Registro: " + a.getRegistro() +
                                    ", CPF: " + a.getCpf() +
                                    ", Nome: " + a.getNome() +
                                    ", Email: " + a.getEmail());
                        }
                    } else {
                        System.out.println("Nenhum ator cadastrado.");
                    }
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }



    private static void menuFuncionarios(Scanner scanner) {
        Funcionario funcionario = new Funcionario(0, "", "", "", "", new Date());
        int opcao;

        do {
            System.out.println(" ");
            System.out.println("=== Menu Funcionários ===");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Editar Funcionário");
            System.out.println("3. Consultar Funcionário");
            System.out.println("4. Listar Funcionários");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do funcionário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o CPF do funcionário: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Digite o email do funcionário: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite a matrícula do funcionário: ");
                    String matricula = scanner.nextLine();
                    System.out.print("Digite o horário de trabalho (dd/MM/yyyy HH:mm): ");
                    String horarioTrabalhoStr = scanner.nextLine();
                    Date horarioTrabalho = null;

                    try {
                        horarioTrabalho = Funcionario.DATE_FORMAT.parse(horarioTrabalhoStr);
                    } catch (ParseException e) {
                        System.out.println("Erro ao interpretar a data. Use o formato correto.");
                        break;
                    }

                    Funcionario novoFuncionario = new Funcionario(0, nome, cpf, email, matricula, horarioTrabalho);
                    if (funcionario.cadastrarFuncionario(novoFuncionario)) {
                        System.out.println("Funcionário cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar o funcionário.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o ID do funcionário que deseja editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();
                    Funcionario funcionarioEncontrado = funcionario.consultarFuncionario(idEditar);

                    if (funcionarioEncontrado != null) {
                        System.out.println("Funcionário encontrado: " + funcionarioEncontrado.getNome());
                        System.out.print("Digite o novo nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Digite o novo email: ");
                        String novoEmail = scanner.nextLine();
                        System.out.print("Digite a nova matrícula: ");
                        String novaMatricula = scanner.nextLine();
                        System.out.print("Digite o novo horário de trabalho (dd/MM/yyyy HH:mm): ");
                        String novoHorarioTrabalhoStr = scanner.nextLine();
                        Date novoHorarioTrabalho = null;

                        try {
                            novoHorarioTrabalho = Funcionario.DATE_FORMAT.parse(novoHorarioTrabalhoStr);
                        } catch (ParseException e) {
                            System.out.println("Erro ao interpretar a data. Use o formato correto.");
                            break;
                        }

                        funcionarioEncontrado.setNome(novoNome);
                        funcionarioEncontrado.setEmail(novoEmail);
                        funcionarioEncontrado.setMatricula(novaMatricula);
                        funcionarioEncontrado.setHorarioTrabalho(novoHorarioTrabalho);

                        if (funcionario.editarFuncionario(funcionarioEncontrado)) {
                            System.out.println("Funcionário editado com sucesso!");
                        } else {
                            System.out.println("Erro ao editar o funcionário.");
                        }
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do funcionário que deseja consultar: ");
                    int idConsultar = scanner.nextInt();
                    scanner.nextLine();
                    Funcionario funcionarioConsultado = funcionario.consultarFuncionario(idConsultar);

                    if (funcionarioConsultado != null) {
                        System.out.println("Funcionário encontrado:");
                        System.out.println("ID: " + funcionarioConsultado.getIdFuncionario());
                        System.out.println("Nome: " + funcionarioConsultado.getNome());
                        System.out.println("CPF: " + funcionarioConsultado.getCpf());
                        System.out.println("Email: " + funcionarioConsultado.getEmail());
                        System.out.println("Matrícula: " + funcionarioConsultado.getMatricula());
                        System.out.println("Horário de Trabalho: " + Funcionario.DATE_FORMAT.format(funcionarioConsultado.getHorarioTrabalho()));
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;

                case 4:
                    ArrayList<Funcionario> listaFuncionarios = funcionario.listarFuncionarios();
                    if (!listaFuncionarios.isEmpty()) {
                        System.out.println("Lista de Funcionários:");
                        for (Funcionario f : listaFuncionarios) {
                            System.out.println("ID: " + f.getIdFuncionario() + ", Nome: " + f.getNome() +
                                    ", CPF: " + f.getCpf() + ", Email: " + f.getEmail() +
                                    ", Matrícula: " + f.getMatricula() + ", Horário de Trabalho: " +
                                    Funcionario.DATE_FORMAT.format(f.getHorarioTrabalho()));
                        }
                    } else {
                        System.out.println("Nenhum funcionário cadastrado.");
                    }
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }


    private static void menuFilmes(Scanner scanner) {
        Filme filmeManager = new Filme(0, "", "", "", "");
        int opcao;

        do {
            System.out.println(" ");
            System.out.println("=== Menu Filmes ===");
            System.out.println("1. Cadastrar Filme");
            System.out.println("2. Editar Filme");
            System.out.println("3. Consultar Filme");
            System.out.println("4. Listar Filmes");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do filme: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite a classificação do filme: ");
                    String classificacao = scanner.nextLine();
                    System.out.print("Digite o gênero do filme: ");
                    String genero = scanner.nextLine();
                    System.out.print("Digite o status do filme: ");
                    String status = scanner.nextLine();

                    Filme novoFilme = new Filme(0, titulo, classificacao, genero, status);
                    if (filmeManager.cadastrarFilme(novoFilme)) {
                        System.out.println("Filme cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar o filme.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o ID do filme que deseja editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();
                    Filme filmeEncontrado = filmeManager.consultarFilme(idEditar);

                    if (filmeEncontrado != null) {
                        System.out.println("Filme encontrado: " + filmeEncontrado.getTitulo());
                        System.out.print("Digite o novo título: ");
                        String novoTitulo = scanner.nextLine();
                        System.out.print("Digite a nova classificação: ");
                        String novaClassificacao = scanner.nextLine();
                        System.out.print("Digite o novo gênero: ");
                        String novoGenero = scanner.nextLine();
                        System.out.print("Digite o novo status: ");
                        String novoStatus = scanner.nextLine();

                        filmeEncontrado.setTitulo(novoTitulo);
                        filmeEncontrado.setClassificacao(novaClassificacao);
                        filmeEncontrado.setGenero(novoGenero);
                        filmeEncontrado.setStatus(novoStatus);

                        if (filmeManager.editarFilme(filmeEncontrado)) {
                            System.out.println("Filme editado com sucesso!");
                        } else {
                            System.out.println("Erro ao editar o filme.");
                        }
                    } else {
                        System.out.println("Filme não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do filme que deseja consultar: ");
                    int idConsultar = scanner.nextInt();
                    scanner.nextLine();
                    Filme filmeConsultado = filmeManager.consultarFilme(idConsultar);

                    if (filmeConsultado != null) {
                        System.out.println("Filme encontrado:");
                        System.out.println("ID: " + filmeConsultado.getIdFilme());
                        System.out.println("Título: " + filmeConsultado.getTitulo());
                        System.out.println("Classificação: " + filmeConsultado.getClassificacao());
                        System.out.println("Gênero: " + filmeConsultado.getGenero());
                        System.out.println("Status: " + filmeConsultado.getStatus());
                    } else {
                        System.out.println("Filme não encontrado.");
                    }
                    break;

                case 4:
                    ArrayList<Filme> listaFilmes = filmeManager.listarFilmes();
                    if (!listaFilmes.isEmpty()) {
                        System.out.println("Lista de Filmes:");
                        for (Filme f : listaFilmes) {
                            System.out.println("ID: " + f.getIdFilme() + ", Título: " + f.getTitulo() +
                                    ", Classificação: " + f.getClassificacao() + ", Gênero: " + f.getGenero() +
                                    ", Status: " + f.getStatus());
                        }
                    } else {
                        System.out.println("Nenhum filme cadastrado.");
                    }
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuSessoes(Scanner scanner) {
        Sessao sessaoManager = new Sessao();
        int opcao;

        do {
            System.out.println(" ");
            System.out.println("=== Menu Sessões ===");
            System.out.println("1. Cadastrar Sessão");
            System.out.println("2. Editar Sessão");
            System.out.println("3. Consultar Sessão");
            System.out.println("4. Listar Sessões");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite a data e hora da sessão (formato: dd/MM/yyyy HH:mm): ");
                    String dataHoraSessao = scanner.nextLine();

                    System.out.print("Digite o nome do filme: ");
                    String filme = scanner.nextLine();

                    System.out.print("Digite a sala da sessão: ");
                    String sala = scanner.nextLine();

                    System.out.print("Digite o nome do funcionário responsável: ");
                    String funcionario = scanner.nextLine();

                    System.out.print("Digite o status da sessão (Ativo/Inativo): ");
                    String status = scanner.nextLine();

                    Sessao novaSessao = new Sessao(0, dataHoraSessao, filme, sala, funcionario, status);

                    if (sessaoManager.cadastrarSessao(novaSessao)) {
                        System.out.println("Sessão cadastrada com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar a sessão.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o ID da sessão que deseja editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();
                    Sessao sessaoEncontrada = sessaoManager.consultarSessao(idEditar);

                    if (sessaoEncontrada != null) {
                        System.out.println("Sessão encontrada: ");
                        System.out.println("ID: " + sessaoEncontrada.getIdSessao());
                        System.out.println("Data e Hora: " + sessaoEncontrada.getDataHoraSessao());
                        System.out.println("Filme: " + sessaoEncontrada.getFilme());
                        System.out.println("Sala: " + sessaoEncontrada.getSala());
                        System.out.println("Funcionário: " + sessaoEncontrada.getFuncionario());
                        System.out.println("Status: " + sessaoEncontrada.getStatus());

                        System.out.print("Digite a nova data e hora da sessão: ");
                        String novaDataHora = scanner.nextLine();
                        System.out.print("Digite o novo filme: ");
                        String novoFilme = scanner.nextLine();
                        System.out.print("Digite a nova sala: ");
                        String novaSala = scanner.nextLine();
                        System.out.print("Digite o novo funcionário: ");
                        String novoFuncionario = scanner.nextLine();
                        System.out.print("Digite o novo status: ");
                        String novoStatus = scanner.nextLine();

                        sessaoEncontrada.setDataHoraSessao(novaDataHora);
                        sessaoEncontrada.setFilme(novoFilme);
                        sessaoEncontrada.setSala(novaSala);
                        sessaoEncontrada.setFuncionario(novoFuncionario);
                        sessaoEncontrada.setStatus(novoStatus);

                        if (sessaoManager.editarSessao(sessaoEncontrada)) {
                            System.out.println("Sessão editada com sucesso!");
                        } else {
                            System.out.println("Erro ao editar a sessão.");
                        }
                    } else {
                        System.out.println("Sessão não encontrada.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID da sessão que deseja consultar: ");
                    int idConsultar = scanner.nextInt();
                    scanner.nextLine();
                    Sessao sessaoConsultada = sessaoManager.consultarSessao(idConsultar);

                    if (sessaoConsultada != null) {
                        System.out.println("Sessão encontrada:");
                        System.out.println("ID: " + sessaoConsultada.getIdSessao());
                        System.out.println("Data e Hora: " + sessaoConsultada.getDataHoraSessao());
                        System.out.println("Filme: " + sessaoConsultada.getFilme());
                        System.out.println("Sala: " + sessaoConsultada.getSala());
                        System.out.println("Funcionário: " + sessaoConsultada.getFuncionario());
                        System.out.println("Status: " + sessaoConsultada.getStatus());
                    } else {
                        System.out.println("Sessão não encontrada.");
                    }
                    break;

                case 4:
                    System.out.println("Lista de Sessões:");
                    ArrayList<Sessao> sessoes = sessaoManager.listarSessoes();
                    if (!sessoes.isEmpty()) {
                        for (Sessao sessao : sessoes) {
                            System.out.println("ID: " + sessao.getIdSessao() +
                                    ", DataHora: " + sessao.getDataHoraSessao() +
                                    ", Filme: " + sessao.getFilme() +
                                    ", Sala: " + sessao.getSala() +
                                    ", Funcionário: " + sessao.getFuncionario() +
                                    ", Status: " + sessao.getStatus());
                        }
                    } else {
                        System.out.println("Nenhuma sessão cadastrada.");
                    }
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuAssentos(Scanner scanner) {
        TipoAssento tipoAssento = new TipoAssento();
        Assento assento = new Assento();
        int opcao;

        do {
            System.out.println(" ");
            System.out.println("=== Menu Assentos ===");
            System.out.println("1. Cadastrar Assento");
            System.out.println("2. Editar Assento");
            System.out.println("3. Consultar Assento");
            System.out.println("4. Listar Assentos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o ID do tipo de assento existente ou crie um novo tipo (1 para criar novo): ");
                    int idTipo = scanner.nextInt();
                    scanner.nextLine();

                    TipoAssento tipoAssentoSelecionado;
                    if (idTipo == 1) {
                        System.out.print("Digite a descrição do novo tipo de assento: ");
                        String descricaoTipo = scanner.nextLine();

                        TipoAssento novoTipo = new TipoAssento();
                        novoTipo.setDescricao(descricaoTipo);
                        novoTipo.setStatus("Ativo");

                        if (tipoAssento.cadastrar(novoTipo)) {
                            tipoAssentoSelecionado = novoTipo;
                            System.out.println("Novo tipo de assento cadastrado com ID: " + tipoAssentoSelecionado.getIdTipoAssento());
                        } else {
                            System.out.println("Erro ao cadastrar novo tipo de assento.");
                            break;
                        }
                    } else {
                        tipoAssentoSelecionado = tipoAssento.consultar(idTipo);
                        if (tipoAssentoSelecionado == null) {
                            System.out.println("Tipo de assento não encontrado!");
                            break;
                        }
                    }

                    System.out.print("Digite o ID do assento: ");
                    int idAssento = scanner.nextInt();
                    scanner.nextLine();

                    Assento novoAssento = new Assento();
                    novoAssento.setIdAssento(idAssento);
                    novoAssento.setTipoAssento(tipoAssentoSelecionado);

                    if (assento.cadastrar(novoAssento)) {
                        System.out.println("Assento cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro: ID já existente.");
                    }
                }
                case 2 -> {
                    System.out.print("Digite o ID do assento a ser editado: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();

                    Assento assentoExistente = assento.consultar(idEditar);
                    if (assentoExistente == null) {
                        System.out.println("Assento não encontrado.");
                        break;
                    }

                    System.out.println("Assento encontrado:");
                    System.out.println("ID: " + assentoExistente.getIdAssento());
                    System.out.println("Tipo atual: " + assentoExistente.getTipoAssento().getDescricao());

                    System.out.print("Digite o novo tipo de assento (ou crie um novo com ID 1): ");
                    int idNovoTipo = scanner.nextInt();
                    scanner.nextLine();

                    TipoAssento novoTipoAssento;
                    if (idNovoTipo == 1) {
                        System.out.print("Digite a descrição do novo tipo de assento: ");
                        String descricaoNovoTipo = scanner.nextLine();

                        TipoAssento novoTipo = new TipoAssento();
                        novoTipo.setDescricao(descricaoNovoTipo);
                        novoTipo.setStatus("Ativo");

                        if (tipoAssento.cadastrar(novoTipo)) {
                            novoTipoAssento = novoTipo;
                        } else {
                            System.out.println("Erro ao cadastrar novo tipo de assento.");
                            break;
                        }
                    } else {
                        novoTipoAssento = tipoAssento.consultar(idNovoTipo);
                        if (novoTipoAssento == null) {
                            System.out.println("Tipo de assento não encontrado.");
                            break;
                        }
                    }

                    assentoExistente.setTipoAssento(novoTipoAssento);
                    if (assento.editar(assentoExistente)) {
                        System.out.println("Assento atualizado com sucesso.");
                    } else {
                        System.out.println("Erro ao atualizar o assento.");
                    }
                }
                case 3 -> {
                    System.out.print("Digite o ID do assento: ");
                    int idConsultar = scanner.nextInt();
                    scanner.nextLine();

                    Assento assentoConsultado = assento.consultar(idConsultar);
                    if (assentoConsultado != null) {
                        System.out.println("Assento encontrado:");
                        System.out.println("ID: " + assentoConsultado.getIdAssento());
                        System.out.println("Tipo: " + assentoConsultado.getTipoAssento().getDescricao());
                    } else {
                        System.out.println("Assento não encontrado.");
                    }
                }
                case 4 -> {
                    ArrayList<Assento> assentos = assento.listar();
                    if (assentos.isEmpty()) {
                        System.out.println("Nenhum assento cadastrado.");
                    } else {
                        System.out.println("Assentos cadastrados:");
                        for (Assento a : assentos) {
                            System.out.println("ID: " + a.getIdAssento() + ", Tipo: " + a.getTipoAssento().getDescricao());
                        }
                    }
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuTiposDeAssento(Scanner scanner) {
        TipoAssento tipoAssento = new TipoAssento();
        int opcao;

        do {
            System.out.println(" ");
            System.out.println("=== Menu Tipos de Assento ===");
            System.out.println("1. Cadastrar Tipo de Assento");
            System.out.println("2. Editar Tipo de Assento");
            System.out.println("3. Consultar Tipo de Assento");
            System.out.println("4. Listar Tipos de Assento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite a descrição do novo tipo de assento: ");
                    String descricao = scanner.nextLine();

                    TipoAssento novoTipo = new TipoAssento();
                    novoTipo.setDescricao(descricao);
                    novoTipo.setStatus("Ativo");

                    if (tipoAssento.cadastrar(novoTipo)) {
                        System.out.println("Novo tipo de assento cadastrado com sucesso!");
                        System.out.println("ID: " + novoTipo.getIdTipoAssento());
                    } else {
                        System.out.println("Erro: Não foi possível cadastrar o tipo de assento.");
                    }
                }
                case 2 -> {
                    System.out.print("Digite o ID do tipo de assento que deseja editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();

                    TipoAssento tipoExistente = tipoAssento.consultar(idEditar);
                    if (tipoExistente == null) {
                        System.out.println("Tipo de assento não encontrado.");
                        break;
                    }

                    System.out.println("Tipo de assento encontrado:");
                    System.out.println("ID: " + tipoExistente.getIdTipoAssento());
                    System.out.println("Descrição atual: " + tipoExistente.getDescricao());

                    System.out.print("Digite a nova descrição para o tipo de assento: ");
                    String novaDescricao = scanner.nextLine();
                    tipoExistente.setDescricao(novaDescricao);

                    System.out.print("Digite o novo status (Ativo/Inativo): ");
                    String novoStatus = scanner.nextLine();
                    tipoExistente.setStatus(novoStatus);

                    if (tipoAssento.editar(tipoExistente)) {
                        System.out.println("Tipo de assento atualizado com sucesso!");
                    } else {
                        System.out.println("Erro: Não foi possível atualizar o tipo de assento.");
                    }
                }
                case 3 -> {
                    System.out.print("Digite o ID do tipo de assento: ");
                    int idConsultar = scanner.nextInt();
                    scanner.nextLine();

                    TipoAssento tipoConsultado = tipoAssento.consultar(idConsultar);
                    if (tipoConsultado != null) {
                        System.out.println("Tipo de assento encontrado:");
                        System.out.println("ID: " + tipoConsultado.getIdTipoAssento());
                        System.out.println("Descrição: " + tipoConsultado.getDescricao());
                        System.out.println("Status: " + tipoConsultado.getStatus());
                    } else {
                        System.out.println("Tipo de assento não encontrado.");
                    }
                }
                case 4 -> {
                    ArrayList<TipoAssento> tipos = new ArrayList<>(tipoAssento.listar());
                    if (tipos.isEmpty()) {
                        System.out.println("Nenhum tipo de assento cadastrado.");
                    } else {
                        System.out.println("Tipos de assento cadastrados:");
                        for (TipoAssento tipo : tipos) {
                            System.out.println("ID: " + tipo.getIdTipoAssento() +
                                    ", Descrição: " + tipo.getDescricao() +
                                    ", Status: " + tipo.getStatus());
                        }
                    }
                }

                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuSalas(Scanner scanner) {
        Sala salaManager = new Sala();
        int opcao;

        do {
            System.out.println(" ");
            System.out.println("\n=== Menu de Gerenciamento de Salas ===");
            System.out.println("1. Cadastrar Sala");
            System.out.println("2. Editar Sala");
            System.out.println("3. Consultar Sala por ID");
            System.out.println("4. Listar Todas as Salas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite a capacidade da sala: ");
                    int capacidade = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite a descrição da sala: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Digite o status da sala (Ativa/Inativa): ");
                    String status = scanner.nextLine();

                    Sala novaSala = new Sala(0, capacidade, descricao, status);
                    if (salaManager.cadastrarSala(novaSala)) {
                        System.out.println("Sala cadastrada com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar a sala.");
                    }
                }
                case 2 -> {
                    System.out.print("Digite o ID da sala que deseja editar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Sala salaExistente = salaManager.consultarSala(id);
                    if (salaExistente == null) {
                        System.out.println("Sala com ID " + id + " não encontrada.");
                        break;
                    }

                    System.out.println("Editar informações da sala (deixe em branco para manter o valor atual):");

                    System.out.print("Nova capacidade da sala (Atual: " + salaExistente.getCapacidadeSala() + "): ");
                    String novaCapacidade = scanner.nextLine();
                    if (!novaCapacidade.isEmpty()) {
                        salaExistente.setCapacidadeSala(Integer.parseInt(novaCapacidade));
                    }

                    System.out.print("Nova descrição da sala (Atual: " + salaExistente.getDescricao() + "): ");
                    String novaDescricao = scanner.nextLine();
                    if (!novaDescricao.isEmpty()) {
                        salaExistente.setDescricao(novaDescricao);
                    }

                    System.out.print("Novo status da sala (Atual: " + salaExistente.getStatus() + "): ");
                    String novoStatus = scanner.nextLine();
                    if (!novoStatus.isEmpty()) {
                        salaExistente.setStatus(novoStatus);
                    }

                    if (salaManager.editarSala(salaExistente)) {
                        System.out.println("Sala editada com sucesso!");
                    } else {
                        System.out.println("Erro ao editar a sala.");
                    }
                }
                case 3 -> {
                    System.out.print("Digite o ID da sala: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Sala sala = salaManager.consultarSala(id);
                    if (sala == null) {
                        System.out.println("Sala com ID " + id + " não encontrada.");
                    } else {
                        System.out.println("\n=== Detalhes da Sala ===");
                        System.out.println("ID: " + sala.getIdSala());
                        System.out.println("Capacidade: " + sala.getCapacidadeSala());
                        System.out.println("Descrição: " + sala.getDescricao());
                        System.out.println("Status: " + sala.getStatus());
                    }
                }
                case 4 -> {
                    ArrayList<Sala> salas = salaManager.consultarTodasSalas();
                    if (salas.isEmpty()) {
                        System.out.println("Nenhuma sala cadastrada.");
                    } else {
                        System.out.println("\n=== Lista de Salas ===");
                        for (Sala sala : salas) {
                            System.out.println("ID: " + sala.getIdSala() + ", Capacidade: " + sala.getCapacidadeSala()
                                    + ", Descrição: " + sala.getDescricao() + ", Status: " + sala.getStatus());
                        }
                    }
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void menuIngressos(Scanner scanner) {
        Ingresso ingressoManager = new Ingresso();
        int opcao;

        do {
            System.out.println(" ");
            System.out.println("\n=== Menu de Gerenciamento de Ingressos ===");
            System.out.println("1. Cadastrar Ingresso");
            System.out.println("2. Editar Ingresso");
            System.out.println("3. Consultar Ingresso por ID");
            System.out.println("4. Listar Todos os Ingressos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o ID do ingresso: ");
                    int idIngresso = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite o valor pago pelo ingresso: ");
                    double valorPago = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Digite o ID do SalaAssento: ");
                    int idSalaAssento = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite o ID da Sessão: ");
                    int idSessao = scanner.nextInt();
                    scanner.nextLine();

                    SalaAssento salaAssento = new SalaAssento();
                    salaAssento.setIdSalaAssento(idSalaAssento);

                    Sessao sessao = new Sessao();
                    sessao.setIdSessao(idSessao);

                    Ingresso novoIngresso = new Ingresso(idIngresso, valorPago, salaAssento, sessao);

                    if (ingressoManager.cadastrar(novoIngresso)) {
                        System.out.println("Ingresso cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro: ID duplicado ou problema no cadastro.");
                    }
                }
                case 2 -> {
                    System.out.print("Digite o ID do ingresso que deseja editar: ");
                    int idIngresso = scanner.nextInt();
                    scanner.nextLine();

                    Ingresso ingressoExistente = ingressoManager.consultar(idIngresso);
                    if (ingressoExistente == null) {
                        System.out.println("Erro: Ingresso não encontrado.");
                        break;
                    }

                    System.out.println("=== Editando Ingresso ===");
                    System.out.print("Novo valor pago (Atual: " + ingressoExistente.getValorPago() + "): ");
                    String novoValor = scanner.nextLine();
                    if (!novoValor.isEmpty()) {
                        ingressoExistente.setValorPago(Double.parseDouble(novoValor));
                    }

                    System.out.print("Novo ID SalaAssento (Atual: " + ingressoExistente.getSalaAssento().getIdSalaAssento() + "): ");
                    String novoSalaAssento = scanner.nextLine();
                    if (!novoSalaAssento.isEmpty()) {
                        SalaAssento salaAssento = new SalaAssento();
                        salaAssento.setIdSalaAssento(Integer.parseInt(novoSalaAssento));
                        ingressoExistente.setSalaAssento(salaAssento);
                    }

                    System.out.print("Novo ID Sessão (Atual: " + ingressoExistente.getSessao().getIdSessao() + "): ");
                    String novoSessao = scanner.nextLine();
                    if (!novoSessao.isEmpty()) {
                        Sessao sessao = new Sessao();
                        sessao.setIdSessao(Integer.parseInt(novoSessao));
                        ingressoExistente.setSessao(sessao);
                    }

                    if (ingressoManager.editar(ingressoExistente)) {
                        System.out.println("Ingresso editado com sucesso!");
                    } else {
                        System.out.println("Erro ao editar ingresso.");
                    }
                }
                case 3 -> {
                    System.out.print("Digite o ID do ingresso: ");
                    int idIngresso = scanner.nextInt();
                    scanner.nextLine();

                    Ingresso ingresso = ingressoManager.consultar(idIngresso);
                    if (ingresso == null) {
                        System.out.println("Erro: Ingresso não encontrado.");
                    } else {
                        System.out.println("\n=== Detalhes do Ingresso ===");
                        System.out.println("ID: " + ingresso.getIdIngresso());
                        System.out.println("Valor Pago: " + ingresso.getValorPago());
                        System.out.println("ID SalaAssento: " + ingresso.getSalaAssento().getIdSalaAssento());
                        System.out.println("ID Sessão: " + ingresso.getSessao().getIdSessao());
                    }
                }
                case 4 -> {
                    ArrayList<Ingresso> ingressos = ingressoManager.listar();
                    if (ingressos.isEmpty()) {
                        System.out.println("Nenhum ingresso cadastrado.");
                    } else {
                        System.out.println("\n=== Lista de Ingressos ===");
                        for (Ingresso ingresso : ingressos) {
                            System.out.println("ID: " + ingresso.getIdIngresso() +
                                    ", Valor Pago: " + ingresso.getValorPago() +
                                    ", ID SalaAssento: " + ingresso.getSalaAssento().getIdSalaAssento() +
                                    ", ID Sessão: " + ingresso.getSessao().getIdSessao());
                        }
                    }
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Erro: Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}
