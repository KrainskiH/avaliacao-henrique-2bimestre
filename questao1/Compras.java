import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Compras implements AreaInteressada {
    private static final int LIMITE = 5;

    @Override
    public void atualizar(String nomeProduto, int novaQuantidade) {
        if (novaQuantidade < LIMITE) {
            System.out.println("Compras alert: '" + nomeProduto
                    + "'com estoque baixo (" + novaQuantidade + " un.)");
        }
    }
}