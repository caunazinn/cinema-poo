import java.io.*;
import java.util.ArrayList;

class Sessao {
    private static final String ARQUIVO_SESSOES = "sessoes.txt";

    private int idSessao;
    private String dataHoraSessao;
    private String filme;
    private String sala;
    private String funcionario;
    private String status;

    public Sessao(int idSessao, String dataHoraSessao, String filme, String sala, String funcionario, String status) {
        this.idSessao = idSessao;
        this.dataHoraSessao = dataHoraSessao;
        this.filme = filme;
        this.sala = sala;
        this.funcionario = funcionario;
        this.status = status;
    }
    public Sessao() {

    }
    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public String getDataHoraSessao() {
        return dataHoraSessao;
    }

    public void setDataHoraSessao(String dataHoraSessao) {
        this.dataHoraSessao = dataHoraSessao;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public boolean cadastrarSessao(Sessao sessao) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_SESSOES, true))) {
            int novoId = obterProximoId();
            writer.write(novoId + ";" + sessao.getDataHoraSessao() + ";" + sessao.getFilme() + ";" +
                    sessao.getSala() + ";" + sessao.getFuncionario() + ";" + sessao.getStatus());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Sessao consultarSessao(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_SESSOES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == id) {
                    return new Sessao(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3], dados[4], dados[5]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Sessao> listarSessoes() {
        ArrayList<Sessao> sessoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_SESSOES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Sessao sessao = new Sessao(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3], dados[4], dados[5]);
                sessoes.add(sessao);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessoes;
    }

    public boolean editarSessao(Sessao sessaoAtualizada) {
        File arquivo = new File(ARQUIVO_SESSOES);
        File arquivoTemp = new File("sessoes_temp.txt");

        boolean atualizado = false;

        try (
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp))
        ) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                if (id == sessaoAtualizada.getIdSessao()) {
                    writer.write(id + ";" + sessaoAtualizada.getDataHoraSessao() + ";" +
                            sessaoAtualizada.getFilme() + ";" + sessaoAtualizada.getSala() + ";" +
                            sessaoAtualizada.getFuncionario() + ";" + sessaoAtualizada.getStatus());
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
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_SESSOES))) {
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

    public void exibirTodasSessoes() {
        ArrayList<Sessao> sessoes = listarSessoes();
        for (Sessao sessao : sessoes) {
            System.out.println("ID: " + sessao.getIdSessao() + ", DataHora: " + sessao.getDataHoraSessao() +
                    ", Filme: " + sessao.getFilme() + ", Sala: " + sessao.getSala() +
                    ", Funcionário: " + sessao.getFuncionario() + ", Status: " + sessao.getStatus());
        }
    }


}
