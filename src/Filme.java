import java.util.ArrayList;

public class Filme {
    private int idFilme;
    private String titulo;
    private int classificacao;
    private Genero genero;
    private String status;

    private static ArrayList<Filme> listaFilmes = new ArrayList<>();

    public Filme(int idFilme, String titulo, int classificacao, Genero genero, String status) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.classificacao = classificacao;
        this.genero = genero;
        this.status = status;
    }

    // Getters e Setters
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

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void cadastrarFilme(Filme filme) {
        listaFilmes.add(filme);
        System.out.println("Filme cadastrado com sucesso!");
    }

    public static Filme consultarFilme(int idFilme) {
        for (Filme filme : listaFilmes) {
            if (filme.getIdFilme() == idFilme) {
                return filme;
            }
        }
        System.out.println("Filme não encontrado.");
        return null;
    }

    public static void editarFilme(int idFilme, String novoTitulo, int novaClassificacao, Genero novoGenero, String novoStatus) {
        Filme filme = consultarFilme(idFilme);
        if (filme != null) {
            filme.setTitulo(novoTitulo);
            filme.setClassificacao(novaClassificacao);
            filme.setGenero(novoGenero);
            filme.setStatus(novoStatus);
            System.out.println("Filme editado com sucesso!");
        }
    }

    public static void listarFilmes() {
        if (listaFilmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
        } else {
            System.out.println("Lista de Filmes:");
            for (Filme filme : listaFilmes) {
                System.out.println("ID: " + filme.getIdFilme() + ", Título: " + filme.getTitulo() +
                        ", Classificação: " + filme.getClassificacao() + ", Gênero: " + filme.getGenero().getDescricao() +
                        ", Status: " + filme.getStatus());
            }
        }
    }
}
