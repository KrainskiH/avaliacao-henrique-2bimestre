import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Auditoria implements AreaInteressada {
    private final List<String> registros = new ArrayList<>();

    @Override
    public void atualizar(String nomeProduto, int novaQuantidade) {
        String registro = "Produto '" + nomeProduto + "' nova quantidade: " + novaQuantidade;
        registros.add(registro);
        System.out.println("[AUDITORIA] " + registro);
    }

    public List<String> getRegistros() {
        return new ArrayList<>(registros);
    }
}