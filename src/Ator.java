import java.util.ArrayList;

public class Ator extends Pessoa {
    private int registro;
    private ArrayList<String> filmesParticipados;


    public Ator(String cpf, String nome, String email, int registro) {
        super(cpf, nome, email);
        this.registro = registro;
        this.filmesParticipados = new ArrayList<>();
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public ArrayList<String> getFilmesParticipados() {
        return filmesParticipados;
    }

    public void adicionarFilme(String filme) {
        filmesParticipados.add(filme);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Ator: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("E-mail: " + getEmail());
        System.out.println("Registro: " + registro);
        System.out.println("Filmes Participados: " + filmesParticipados);
    }
}
