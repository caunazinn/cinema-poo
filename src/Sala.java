import java.util.ArrayList;
class Sala {
    private int idSala;
    private int capacidadeSala;
    private String descricao;
    private String status;
    private static ArrayList<Sala> salas = new ArrayList<>();

    public Sala(int idSala, int capacidadeSala, String descricao, String status) {
        this.idSala = idSala;
        this.capacidadeSala = capacidadeSala;
        this.descricao = descricao;
        this.status = status;
    }

    public boolean cadastrar(Sala sala) {
        return salas.add(sala);
    }

    public boolean editar(Sala sala) {
        for (int i = 0; i < salas.size(); i++) {
            if (salas.get(i).getIdSala() == sala.getIdSala()) {
                salas.set(i, sala);
                return true;
            }
        }
        return false;
    }

    public Sala consultar(int idSala) {
        for (Sala s : salas) {
            if (s.getIdSala() == idSala) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Sala> listar() {
        return salas;
    }

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
}