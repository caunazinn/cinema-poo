import java.io.*;
import java.util.ArrayList;

public class Ator {
    private String cpf;
    private String nome;
    private String email;
    private int registro;
    private static final String FILE_PATH = "atores.txt"; // Caminho do arquivo

    public Ator() {}

    public Ator(String cpf, String nome, String email, int registro) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.registro = registro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public boolean cadastrar(Ator novoAtor) {
        ArrayList<Ator> atores = listarAtores();

        for (Ator ator : atores) {
            if (ator.getCpf().equals(novoAtor.getCpf())) {
                System.out.println("Erro: Já existe um ator cadastrado com este CPF.");
                return false;
            }
            if (ator.getRegistro() == novoAtor.getRegistro()) {
                System.out.println("Erro: Já existe um ator cadastrado com este registro.");
                return false;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(novoAtor.getCpf() + ";" + novoAtor.getNome() + ";" + novoAtor.getEmail() + ";" + novoAtor.getRegistro());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar ator: " + e.getMessage());
        }
        return false;
    }

    public boolean editarAtor(Ator atorEditado) {
        ArrayList<Ator> atores = listarAtores();
        boolean encontrado = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Ator ator : atores) {
                if (ator.getCpf().equals(atorEditado.getCpf())) {
                    writer.write(atorEditado.getCpf() + ";" + atorEditado.getNome() + ";" + atorEditado.getEmail() + ";" + atorEditado.getRegistro());
                    encontrado = true;
                } else {
                    writer.write(ator.getCpf() + ";" + ator.getNome() + ";" + ator.getEmail() + ";" + ator.getRegistro());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao editar ator: " + e.getMessage());
        }

        return encontrado;
    }

    public Ator consultar(String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(cpf)) {
                    return new Ator(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao consultar ator: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Ator> listarAtores() {
        ArrayList<Ator> atores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Ator ator = new Ator(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
                atores.add(ator);
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar atores: " + e.getMessage());
        }
        return atores;
    }
}
