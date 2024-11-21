import java.time.LocalDateTime;

public class Sessao {
    private int idSessao;
    private LocalDateTime dataHoraSessao;
    private Filme filme;
    private Sala sala;
    private Funcionario funcionario; // Responsável pela sessão
    private String status; // Ativo ou Inativo

    // Construtor
    public Sessao(int idSessao, LocalDateTime dataHoraSessao, Filme filme, Sala sala, Funcionario funcionario, String status) {
        this.idSessao = idSessao;
        this.dataHoraSessao = dataHoraSessao;
        this.filme = filme;
        this.sala = sala;
        this.funcionario = funcionario;
        this.status = status;
    }

    // Getters e Setters
    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public LocalDateTime getDataHoraSessao() {
        return dataHoraSessao;
    }

    public void setDataHoraSessao(LocalDateTime dataHoraSessao) {
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

    // Método para exibir detalhes da sessão
    public void exibirDetalhes() {
        System.out.println("Sessão ID: " + idSessao +
                ", Filme: " + filme.getTitulo() +
                ", Sala: " + sala.getDescricao() +
                ", Data/Hora: " + dataHoraSessao +
                ", Funcionário: " + funcionario.getNome() +
                ", Status: " + status);
    }
}
