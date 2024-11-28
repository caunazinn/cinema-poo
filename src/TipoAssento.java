import java.util.ArrayList;
class TipoAssento {
    private int idTipoAssento;
    private String descricao;
    private String status;
    private static ArrayList<TipoAssento> tiposAssento = new ArrayList<>();

    public TipoAssento(int idTipoAssento, String descricao, String status) {
        this.idTipoAssento = idTipoAssento;
        this.descricao = descricao;
        this.status = status;
    }

    public boolean cadastrar(TipoAssento tipo) {
        return tiposAssento.add(tipo);
    }

    public boolean editar(TipoAssento tipo) {
        for (int i = 0; i < tiposAssento.size(); i++) {
            if (tiposAssento.get(i).getIdTipoAssento() == tipo.getIdTipoAssento()) {
                tiposAssento.set(i, tipo);
                return true;
            }
        }
        return false;
    }

    public TipoAssento consultar(int idTipoAssento) {
        for (TipoAssento t : tiposAssento) {
            if (t.getIdTipoAssento() == idTipoAssento) {
                return t;
            }
        }
        return null;
    }

    public ArrayList<TipoAssento> listar() {
        return tiposAssento;
    }

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
}
