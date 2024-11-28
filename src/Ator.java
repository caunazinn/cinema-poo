import java.util.ArrayList;
class Ator extends Pessoa {
    private int registro;

    public Ator(String cpf, String nome, String email, int registro) {
        super(cpf, nome, email);
        this.registro = registro;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public boolean cadastrar(Ator ator) {
        // Lógica de cadastro
        return true;
    }

    public boolean editar(Ator ator) {
        // Lógica de edição
        return true;
    }

    public Ator consultar(int registro) {
        // Lógica de consulta
        return this;
    }

    public ArrayList<Ator> listar() {
        return new ArrayList<>();
    }

}