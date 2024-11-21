import java.util.ArrayList;

public class Sala {
    private int idSala;
    private int capacidadeSala;
    private String descricao;
    private String status; // Ativo ou Inativo

    // Construtor
    public Sala(int idSala, int capacidadeSala, String descricao, String status) {
        this.idSala = idSala;
        this.capacidadeSala = capacidadeSala;
        this.descricao = descricao;
        this.status = status;
    }

    // Getters e Setters
    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getCapacidadeSala() {
        return capacidadeSala;
    }

    public void setCapacidadeSala(int capacidadeSala) {
        this.capacidadeSala = capacidadeSala;
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

    // Método para exibir os detalhes da sala
    public void exibirDetalhes() {
        System.out.println("ID: " + idSala + ", Capacidade: " + capacidadeSala + ", Descrição: " + descricao +
                ", Status: " + status);
    }

    // Listagem de salas
    public static ArrayList<Sala> listarSalas(ArrayList<Sala> listaSalas) {
        return listaSalas;
    }
}
