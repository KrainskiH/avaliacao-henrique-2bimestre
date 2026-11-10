import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Vendas implements AreaInteressada {

    private final Set<String> zerados = new HashSet<>();

    @Override
    public void atualizar(String nomeProduto, int novaQuantidade) {
        if (novaQuantidade == 0) {
            zerados.add(nomeProduto);
        } else if (zerados.remove(nomeProduto)) {
            System.out.println("Vendas alerta: '" + nomeProduto
                    + "' voltou ao estoque (" + novaQuantidade + " un.)");
        }
    }
}