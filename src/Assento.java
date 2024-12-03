import java.io.*;
import java.util.ArrayList;

public class Assento {
    private int idAssento;
    private TipoAssento tipoAssento;

    private static final String ARQUIVO = "assentos.txt";

    public Assento() {
    }

    public Assento(int idAssento, TipoAssento tipoAssento) {
        this.idAssento = idAssento;
        this.tipoAssento = tipoAssento;
    }

    public int getIdAssento() {
        return idAssento;
    }

    public void setIdAssento(int idAssento) {
        this.idAssento = idAssento;
    }

    public TipoAssento getTipoAssento() {
        return tipoAssento;
    }

    public void setTipoAssento(TipoAssento tipoAssento) {
        this.tipoAssento = tipoAssento;
    }


    public boolean cadastrar(Assento novoAssento) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));
             BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);
                if (idExistente == novoAssento.getIdAssento()) {
                    return false;
                }
            }

            bw.write(novoAssento.getIdAssento() + ";" +
                    (novoAssento.getTipoAssento() != null ? novoAssento.getTipoAssento().getIdTipoAssento() : "0"));
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao cadastrar assento: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(Assento assentoEditado) {
        ArrayList<String> linhasAtualizadas = new ArrayList<>();
        boolean encontrou = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == assentoEditado.getIdAssento()) {
                    encontrou = true;
                    linhasAtualizadas.add(assentoEditado.getIdAssento() + ";" +
                            (assentoEditado.getTipoAssento() != null ? assentoEditado.getTipoAssento().getIdTipoAssento() : "0"));
                } else {
                    linhasAtualizadas.add(linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao editar assento: " + e.getMessage());
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

    public Assento consultar(int idAssento) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == idAssento) {
                    TipoAssento tipoAssento = new TipoAssento();
                    return new Assento(idExistente, tipoAssento);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar assento: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Assento> listar() {
        ArrayList<Assento> assentos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                TipoAssento tipoAssento = new TipoAssento();
                assentos.add(new Assento(id, tipoAssento));
            }
        } catch (IOException e) {
            System.err.println("Erro ao listar assentos: " + e.getMessage());
        }
        return assentos;
    }
}
