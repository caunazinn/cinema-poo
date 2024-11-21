import java.util.ArrayList;

public class Genero {
    private int id;
    private String descricao;
    private String status; // Ativo ou Inativo

    // Lista estática para armazenar os gêneros criados
    private static ArrayList<Genero> listaGeneros = new ArrayList<>();

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

    public static void cadastrarGenero(Genero genero) {
        listaGeneros.add(genero);
        System.out.println("Gênero cadastrado com sucesso!");
    }

    // Método para buscar um gênero por ID
    public static Genero consultarGenero(int id) {
        for (Genero genero : listaGeneros) {
            if (genero.getId() == id) {
                return genero;
            }
        }
        System.out.println("Gênero não encontrado.");
        return null;
    }

    // Método para editar um gênero
    public static void editarGenero(int id, String novaDescricao, String novoStatus) {
        Genero genero = consultarGenero(id);
        if (genero != null) {
            genero.setDescricao(novaDescricao);
            genero.setStatus(novoStatus);
            System.out.println("Gênero editado com sucesso!");
        }
    }

    // Método para listar todos os gêneros
    public static void listarGeneros() {
        if (listaGeneros.isEmpty()) {
            System.out.println("Nenhum gênero cadastrado.");
        } else {
            System.out.println("Lista de Gêneros:");
            for (Genero genero : listaGeneros) {
                System.out.println("ID: " + genero.getId() + ", Descrição: " + genero.getDescricao() + ", Status: " + genero.getStatus());
            }
        }
    }
}
