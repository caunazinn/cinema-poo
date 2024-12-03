import java.io.*;
import java.util.ArrayList;

public class Ingresso {
    private int idIngresso;
    private double valorPago;
    private SalaAssento salaAssento;
    private Sessao sessao;

    private static final String ARQUIVO = "ingressos.txt";

    public Ingresso() {
    }

    public Ingresso(int idIngresso, double valorPago, SalaAssento salaAssento, Sessao sessao) {
        this.idIngresso = idIngresso;
        this.valorPago = valorPago;
        this.salaAssento = salaAssento;
        this.sessao = sessao;
    }

    public int getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(int idIngresso) {
        this.idIngresso = idIngresso;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public SalaAssento getSalaAssento() {
        return salaAssento;
    }

    public void setSalaAssento(SalaAssento salaAssento) {
        this.salaAssento = salaAssento;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public boolean cadastrar(Ingresso novoIngresso) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));
             BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);
                if (idExistente == novoIngresso.getIdIngresso()) {
                    return false;
                }
            }

            bw.write(novoIngresso.getIdIngresso() + ";" +
                    novoIngresso.getValorPago() + ";" +
                    (novoIngresso.getSalaAssento() != null ? novoIngresso.getSalaAssento().getIdSalaAssento() : "0") + ";" +
                    (novoIngresso.getSessao() != null ? novoIngresso.getSessao().getIdSessao() : "0"));
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao cadastrar ingresso: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(Ingresso ingressoEditado) {
        ArrayList<String> linhasAtualizadas = new ArrayList<>();
        boolean encontrou = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == ingressoEditado.getIdIngresso()) {
                    encontrou = true;
                    linhasAtualizadas.add(ingressoEditado.getIdIngresso() + ";" +
                            ingressoEditado.getValorPago() + ";" +
                            (ingressoEditado.getSalaAssento() != null ? ingressoEditado.getSalaAssento().getIdSalaAssento() : "0") + ";" +
                            (ingressoEditado.getSessao() != null ? ingressoEditado.getSessao().getIdSessao() : "0"));
                } else {
                    linhasAtualizadas.add(linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao editar ingresso: " + e.getMessage());
            return false;
        }

        if (!encontrou) {
            return false;
        }

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

    public Ingresso consultar(int idIngresso) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == idIngresso) {
                    double valor = Double.parseDouble(dados[1]);
                    SalaAssento salaAssento = new SalaAssento();
                    Sessao sessao = new Sessao();
                    return new Ingresso(idExistente, valor, salaAssento, sessao);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar ingresso: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Ingresso> listar() {
        ArrayList<Ingresso> ingressos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                double valor = Double.parseDouble(dados[1]);
                SalaAssento salaAssento = new SalaAssento();
                Sessao sessao = new Sessao();
                ingressos.add(new Ingresso(id, valor, salaAssento, sessao));
            }
        } catch (IOException e) {
            System.err.println("Erro ao listar ingressos: " + e.getMessage());
        }
        return ingressos;
    }
}
