import java.io.*;
import java.util.ArrayList;

public class SalaAssento {
    private int idSalaAssento;
    private Assento assento;
    private Sala sala;

    private static final String ARQUIVO = "salaAssentos.txt";


    public SalaAssento() {
    }

    public SalaAssento(int idSalaAssento, Assento assento, Sala sala) {
        this.idSalaAssento = idSalaAssento;
        this.assento = assento;
        this.sala = sala;
    }

    public int getIdSalaAssento() {
        return idSalaAssento;
    }

    public void setIdSalaAssento(int idSalaAssento) {
        this.idSalaAssento = idSalaAssento;
    }

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public boolean cadastrar(SalaAssento novoSalaAssento) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));
             BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);
                if (idExistente == novoSalaAssento.getIdSalaAssento()) {
                    return false;
                }
            }

            bw.write(novoSalaAssento.getIdSalaAssento() + ";" +
                    novoSalaAssento.getAssento().getIdAssento() + ";" +
                    novoSalaAssento.getSala().getIdSala());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao cadastrar SalaAssento: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(SalaAssento salaAssentoEditado) {
        ArrayList<String> linhasAtualizadas = new ArrayList<>();
        boolean encontrou = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == salaAssentoEditado.getIdSalaAssento()) {
                    encontrou = true;
                    linhasAtualizadas.add(salaAssentoEditado.getIdSalaAssento() + ";" +
                            salaAssentoEditado.getAssento().getIdAssento() + ";" +
                            salaAssentoEditado.getSala().getIdSala());
                } else {
                    linhasAtualizadas.add(linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao editar SalaAssento: " + e.getMessage());
            return false;
        }

        if (!encontrou) return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (String linha : linhasAtualizadas) {
                bw.write(linha);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar alterações: " + e.getMessage());
            return false;
        }
    }

    public SalaAssento consultar(int idSalaAssento) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == idSalaAssento) {
                    Assento assento = new Assento(Integer.parseInt(dados[1]), null);


                    Sala sala = new Sala(Integer.parseInt(dados[2]), 100, "Sala Fictícia", "Disponível");

                    return new SalaAssento(idExistente, assento, sala);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar SalaAssento: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<SalaAssento> listar() {
        ArrayList<SalaAssento> salaAssentos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                Assento assento = new Assento(Integer.parseInt(dados[1]), null);


                Sala sala = new Sala(Integer.parseInt(dados[2]), 100, "Sala Fictícia", "Disponível");

                salaAssentos.add(new SalaAssento(id, assento, sala));
            }
        } catch (IOException e) {
            System.err.println("Erro ao listar SalaAssentos: " + e.getMessage());
        }
        return salaAssentos;
    }
}
