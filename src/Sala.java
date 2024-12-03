import java.io.*;
import java.util.ArrayList;

public class Sala {
    private int idSala;
    private int capacidadeSala;
    private String descricao;
    private String status;

    private final String ARQUIVO_SALAS = "salas.txt";

    public Sala() {
    }

    public Sala(int idSala, int capacidadeSala, String descricao, String status) {
        this.idSala = idSala;
        this.capacidadeSala = capacidadeSala;
        this.descricao = descricao;
        this.status = status;
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

    public boolean cadastrarSala(Sala sala) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_SALAS, true))) {
            int novoId = obterProximoId();
            writer.write(novoId + ";" + sala.getCapacidadeSala() + ";" + sala.getDescricao() + ";" + sala.getStatus());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarSala(Sala sala) {
        try {
            ArrayList<Sala> salas = consultarTodasSalas();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_SALAS))) {
                for (Sala s : salas) {
                    if (s.getIdSala() == sala.getIdSala()) {
                        writer.write(sala.getIdSala() + ";" + sala.getCapacidadeSala() + ";" + sala.getDescricao() + ";" + sala.getStatus());
                    } else {
                        writer.write(s.getIdSala() + ";" + s.getCapacidadeSala() + ";" + s.getDescricao() + ";" + s.getStatus());
                    }
                    writer.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Sala consultarSala(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_SALAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == id) {
                    return new Sala(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), dados[2], dados[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Sala> consultarTodasSalas() {
        ArrayList<Sala> salas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_SALAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Sala sala = new Sala(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), dados[2], dados[3]);
                salas.add(sala);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salas;
    }

    private int obterProximoId() {
        int maxId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_SALAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                maxId = Math.max(maxId, Integer.parseInt(dados[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxId + 1;
    }

    public void exibirTodasSalas() {
        ArrayList<Sala> salas = consultarTodasSalas();
        for (Sala sala : salas) {
            System.out.println("ID: " + sala.getIdSala() + ", Capacidade: " + sala.getCapacidadeSala() +
                    ", Descrição: " + sala.getDescricao() + ", Status: " + sala.getStatus());
        }
    }
}
