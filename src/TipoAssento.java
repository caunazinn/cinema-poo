import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TipoAssento {
    private int idTipoAssento;
    private String descricao;
    private String status;

    public int getIdTipoAssento() {
        return idTipoAssento;
    }

    public void setIdTipoAssento(int idTipoAssento) {
        this.idTipoAssento = idTipoAssento;
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

    public boolean cadastrar(TipoAssento tipoAssento) {
        try {
            if (tipoAssento == null) {
                throw new SQLException("Erro ao acessar o banco: TipoAssento nulo.");
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar TipoAssento: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(TipoAssento tipoAssento) {
        try {
            if (tipoAssento == null) {
                throw new SQLException("Erro ao acessar o banco: TipoAssento nulo.");
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao editar TipoAssento: " + e.getMessage());
            return false;
        }
    }

    public TipoAssento consultar(int idTipoAssento) {
        try {
            if (idTipoAssento <= 0) {
                throw new SQLException("ID inválido.");
            }
            return new TipoAssento();
        } catch (SQLException e) {
            System.err.println("Erro ao consultar TipoAssento: " + e.getMessage());
            return null;
        }
    }

    public List<TipoAssento> listar() {
        return new ArrayList<>();
    }
}