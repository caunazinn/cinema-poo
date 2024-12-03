import java.io.*;
import java.util.ArrayList;

public class FilmeAtor {
    private int idFilmeAtor;
    private Ator ator;
    private Filme filme;
    private String personagem;
    private boolean principal;

    private static final String ARQUIVO = "filme_ator.txt";

    public FilmeAtor() {
    }

    public FilmeAtor(int idFilmeAtor, Ator ator, Filme filme, String personagem, boolean principal) {
        this.idFilmeAtor = idFilmeAtor;
        this.ator = ator;
        this.filme = filme;
        this.personagem = personagem;
        this.principal = principal;
    }

    public int getIdFilmeAtor() {
        return idFilmeAtor;
    }

    public void setIdFilmeAtor(int idFilmeAtor) {
        this.idFilmeAtor = idFilmeAtor;
    }

    public Ator getAtor() {
        return ator;
    }

    public void setAtor(Ator ator) {
        this.ator = ator;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public boolean cadastrar(FilmeAtor novoFilmeAtor) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));
             BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);
                if (idExistente == novoFilmeAtor.getIdFilmeAtor()) {
                    return false;
                }
            }
            bw.write(novoFilmeAtor.getIdFilmeAtor() + ";" +
                    novoFilmeAtor.getAtor().getRegistro() + ";" +
                    novoFilmeAtor.getFilme().getIdFilme() + ";" +
                    novoFilmeAtor.getPersonagem() + ";" +
                    novoFilmeAtor.isPrincipal());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao cadastrar FilmeAtor: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(FilmeAtor filmeAtorEditado) {
        ArrayList<String> linhasAtualizadas = new ArrayList<>();
        boolean encontrou = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == filmeAtorEditado.getIdFilmeAtor()) {
                    encontrou = true;
                    linhasAtualizadas.add(filmeAtorEditado.getIdFilmeAtor() + ";" +
                            filmeAtorEditado.getAtor().getRegistro() + ";" +
                            filmeAtorEditado.getFilme().getIdFilme() + ";" +
                            filmeAtorEditado.getPersonagem() + ";" +
                            filmeAtorEditado.isPrincipal());
                } else {
                    linhasAtualizadas.add(linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao editar FilmeAtor: " + e.getMessage());
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

    public FilmeAtor consultar(int idFilmeAtor) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == idFilmeAtor) {
                    String cpfAtor = "000.000.000-00";
                    String nomeAtor = "Nome Ator";
                    String emailAtor = "email@example.com";
                    int registroAtor = Integer.parseInt(dados[1]);

                    int idFilme = Integer.parseInt(dados[2]);
                    String tituloFilme = "Título";
                    String classificacaoFilme = "Livre";
                    String generoFilme = "Ação";
                    String statusFilme = "Disponível";

                    Ator ator = new Ator(cpfAtor, nomeAtor, emailAtor, registroAtor);
                    Filme filme = new Filme(idFilme, tituloFilme, classificacaoFilme, generoFilme, statusFilme);
                    return new FilmeAtor(idExistente, ator, filme, dados[3], Boolean.parseBoolean(dados[4]));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao consultar FilmeAtor: " + e.getMessage());
        }
        return null;
    }


    public ArrayList<FilmeAtor> listar() {
        ArrayList<FilmeAtor> listaFilmeAtor = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idFilmeAtor = Integer.parseInt(dados[0]);
                String cpfAtor = "000.000.000-00";
                String nomeAtor = "Nome Ator";
                String emailAtor = "email@example.com";
                int registroAtor = Integer.parseInt(dados[1]);

                int idFilme = Integer.parseInt(dados[2]);
                String tituloFilme = "Título";
                String classificacaoFilme = "Livre";
                String generoFilme = "Ação";
                String statusFilme = "Disponível";

                Ator ator = new Ator(cpfAtor, nomeAtor, emailAtor, registroAtor);
                Filme filme = new Filme(idFilme, tituloFilme, classificacaoFilme, generoFilme, statusFilme);
                listaFilmeAtor.add(new FilmeAtor(idFilmeAtor, ator, filme, dados[3], Boolean.parseBoolean(dados[4])));
            }
        } catch (IOException e) {
            System.err.println("Erro ao listar FilmeAtor: " + e.getMessage());
        }
        return listaFilmeAtor;
    }
}
