import java.util.ArrayList;

public class Sessao {
    private int idSessao;
    private String dataHoraSessao;
    private Filme filme;
    private Sala sala;
    private Funcionario funcionario;
    private String status;
    private static ArrayList<Sessao> sessoes = new ArrayList<>();

    public Sessao(int idSessao, String dataHoraSessao, Filme filme, Sala sala, Funcionario funcionario, String status) {
        this.idSessao = idSessao;
        this.dataHoraSessao = dataHoraSessao;
        this.filme = filme;
        this.sala = sala;
        this.funcionario = funcionario;
        this.status = status;
    }

    public boolean cadastrar(Sessao sessao) {
        for (Sessao s : sessoes) {
            if (s.getIdSessao() == sessao.getIdSessao()) {
                return false; // Sessão com ID duplicado
            }
        }
        return sessoes.add(sessao);
    }

    public boolean editar(Sessao sessao) {
        for (int i = 0; i < sessoes.size(); i++) {
            if (sessoes.get(i).getIdSessao() == sessao.getIdSessao()) {
                sessoes.set(i, sessao);
                return true;
            }
        }
        return false;
    }

    public Sessao consultar(int idSessao) {
        for (Sessao s : sessoes) {
            if (s.getIdSessao() == idSessao) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Sessao> listar() {
        return sessoes;
    }

    // Getters e Setters
    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public String getDataHoraSessao() {
        return dataHoraSessao;
    }

    public void setDataHoraSessao(String dataHoraSessao) {
        this.dataHoraSessao = dataHoraSessao;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
