import java.io.*;
import java.util.ArrayList;

public class Genero {
    private int idGenero;
    private String descricao;
    private String status;

    private final String ARQUIVO_GENEROS = "generos.txt";

    public Genero() {
    }

    public Genero(int idGenero, String descricao, String status) {
        this.idGenero = idGenero;
        this.descricao = descricao;
        this.status = status;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean cadastrarGenero(Genero genero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_GENEROS, true))) {
            int novoId = obterProximoId();
            writer.write(novoId + ";" + genero.getDescricao() + ";" + genero.getStatus());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Genero consultarGenero(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_GENEROS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == id) {
                    return new Genero(Integer.parseInt(dados[0]), dados[1], dados[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Genero> consultarTodosGeneros() {
        ArrayList<Genero> generos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_GENEROS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Genero genero = new Genero(Integer.parseInt(dados[0]), dados[1], dados[2]);
                generos.add(genero);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generos;
    }

    public boolean editarGenero(Genero generoAtualizado) {
        File arquivo = new File(ARQUIVO_GENEROS);
        File arquivoTemp = new File("generos_temp.txt");

        boolean atualizado = false;

        try (
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp))
        ) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                if (id == generoAtualizado.getIdGenero()) {
                    writer.write(id + ";" + generoAtualizado.getDescricao() + ";" + generoAtualizado.getStatus());
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
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_GENEROS))) {
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

    public void exibirTodosGeneros() {
        ArrayList<Genero> generos = consultarTodosGeneros();
        for (Genero genero : generos) {
            System.out.println("ID: " + genero.getIdGenero() + ", Descrição: " + genero.getDescricao() + ", Status: " + genero.getStatus());
        }
    }
}
