import java.util.ArrayList;
public class Genero {
    private int id;
    private String descricao;
    private String status;

    public Genero(int id, String descricao, String status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public boolean cadastrar(Genero genero) {
        return true;
    }

    public boolean editar(Genero genero) {
        return true;
    }

    public Genero consultar(int id) {
        return this;
    }

    public ArrayList<Genero> listar() {
        return new ArrayList<>();
    }
}