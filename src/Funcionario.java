import java.util.ArrayList;

class Funcionario extends Pessoa {
    private int matricula;
    private String horarioTrabalho;
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public Funcionario(String cpf, String nome, String email, int matricula, String horarioTrabalho) {
        super(cpf, nome, email);
        this.matricula = matricula;
        this.horarioTrabalho = horarioTrabalho;
    }

    public boolean cadastrar(Funcionario funcionario) {
        for (Funcionario f : funcionarios) {
            if (f.getMatricula() == funcionario.getMatricula()) {
                return false; // Matrícula duplicada
            }
        }
        return funcionarios.add(funcionario);
    }

    public boolean editar(Funcionario funcionario) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getMatricula() == funcionario.getMatricula()) {
                funcionarios.set(i, funcionario);
                return true;
            }
        }
        return false;
    }

    public Funcionario consultar(int matricula) {
        for (Funcionario f : funcionarios) {
            if (f.getMatricula() == matricula) {
                return f;
            }
        }
        return null;
    }

    public ArrayList<Funcionario> listar() {
        return funcionarios;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(String horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }
}
