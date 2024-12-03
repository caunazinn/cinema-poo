import java.io.*;
import java.util.ArrayList;

class Filme {
    private static final String ARQUIVO_FILMES = "filmes.txt";

    private int idFilme;
    private String titulo;
    private String classificacao;
    private String genero;
    private String status;

    public Filme(int idFilme, String titulo, String classificacao, String genero, String status) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.classificacao = classificacao;
        this.genero = genero;
        this.status = status;
    }


    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean cadastrarFilme(Filme filme) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_FILMES, true))) {
            int novoId = obterProximoId();
            writer.write(novoId + ";" + filme.getTitulo() + ";" + filme.getClassificacao() + ";" +
                    filme.getGenero() + ";" + filme.getStatus());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Filme consultarFilme(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_FILMES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == id) {
                    return new Filme(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3], dados[4]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Filme> listarFilmes() {
        ArrayList<Filme> filmes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_FILMES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Filme filme = new Filme(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3], dados[4]);
                filmes.add(filme);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmes;
    }

    public boolean editarFilme(Filme filmeAtualizado) {
        File arquivo = new File(ARQUIVO_FILMES);
        File arquivoTemp = new File("filmes_temp.txt");

        boolean atualizado = false;

        try (
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp))
        ) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);

                if (id == filmeAtualizado.getIdFilme()) {
                    writer.write(id + ";" + filmeAtualizado.getTitulo() + ";" +
                            filmeAtualizado.getClassificacao() + ";" +
                            filmeAtualizado.getGenero() + ";" + filmeAtualizado.getStatus());
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
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_FILMES))) {
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
