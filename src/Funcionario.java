import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Funcionario {
    private static final String ARQUIVO_FUNCIONARIOS = "funcionarios.txt";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private int idFuncionario;
    private String nome;
    private String cpf;
    private String email;
    private String matricula;
    private Date horarioTrabalho;

    public Funcionario(int idFuncionario, String nome, String cpf, String email, String matricula, Date horarioTrabalho) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.matricula = matricula;
        this.horarioTrabalho = horarioTrabalho;
    }


    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(Date horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }

    public boolean cadastrarFuncionario(Funcionario funcionario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_FUNCIONARIOS, true))) {
            int novoId = obterProximoId();
            writer.write(novoId + ";" + funcionario.getNome() + ";" + funcionario.getCpf() + ";" +
                    funcionario.getEmail() + ";" + funcionario.getMatricula() + ";" +
                    DATE_FORMAT.format(funcionario.getHorarioTrabalho()));
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Funcionario consultarFuncionario(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_FUNCIONARIOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == id) {
                    return new Funcionario(
                            Integer.parseInt(dados[0]),
                            dados[1],
                            dados[2],
                            dados[3],
                            dados[4],
                            DATE_FORMAT.parse(dados[5])
                    );
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Funcionario> listarFuncionarios() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_FUNCIONARIOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Funcionario funcionario = new Funcionario(
                        Integer.parseInt(dados[0]),
                        dados[1],
                        dados[2],
                        dados[3],
                        dados[4],
                        DATE_FORMAT.parse(dados[5])
                );
                funcionarios.add(funcionario);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public boolean editarFuncionario(Funcionario funcionarioAtualizado) {
        File arquivo = new File(ARQUIVO_FUNCIONARIOS);
        File arquivoTemp = new File("funcionarios_temp.txt");

        boolean atualizado = false;

        try (
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp))
        ) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);

                if (id == funcionarioAtualizado.getIdFuncionario()) {
                    writer.write(id + ";" + funcionarioAtualizado.getNome() + ";" +
                            funcionarioAtualizado.getCpf() + ";" + funcionarioAtualizado.getEmail() + ";" +
                            funcionarioAtualizado.getMatricula() + ";" +
                            DATE_FORMAT.format(funcionarioAtualizado.getHorarioTrabalho()));
                    atualizado = true;
                } else {
                    writer.write(linha);
                }
                writer.newLine();
            }

            if (atualizado && arquivo.delete()) {
                arquivoTemp.renameTo(arquivo);
            }
            return atualizado;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    private int obterProximoId() {
        int maxId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_FUNCIONARIOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                maxId = Math.max(maxId, Integer.parseInt(dados[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxId + 1;
    }

}
