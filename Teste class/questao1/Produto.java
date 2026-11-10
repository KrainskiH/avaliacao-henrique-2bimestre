import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Produto {
    private final String nome;
    private int quantidade;
    private final List<AreaInteressada> areas = new ArrayList<>();

    public Produto(String nome, int quantidadeInicial) {
        if (quantidadeInicial < 0) {
            throw new IllegalArgumentException("Estoque nao pode ser negativo");
        }
        this.nome = nome;
        this.quantidade = quantidadeInicial;
    }

    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }

    public void adicionarArea(AreaInteressada area) {
        if (area != null && !areas.contains(area)) {
            areas.add(area);
        }
    }

    public void removerArea(AreaInteressada area) {
        areas.remove(area);
    }

    public void setQuantidade(int novaQuantidade) {
        if (novaQuantidade < 0) {
            throw new IllegalArgumentException("Estoque nao pode ser negativo");
        }
        if (novaQuantidade == this.quantidade) {
            return;
        }
        this.quantidade = novaQuantidade;
        notificarAreas();
    }

    private void notificarAreas() {
        for (AreaInteressada area : new ArrayList<>(areas)) {
            area.atualizar(nome, quantidade);
        }
    }
}