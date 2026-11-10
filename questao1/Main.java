import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

interface AreaInteressada {
    void atualizar(String nomeProduto, int novaQuantidade);
}

public class Main {
    public static void main(String[] args) {
        Produto notebook = new Produto("Notebook", 10);

        Compras compras = new Compras();
        Vendas vendas = new Vendas();
        Auditoria auditoria = new Auditoria();

        notebook.adicionarArea(compras);
        notebook.adicionarArea(vendas);
        notebook.adicionarArea(auditoria);

        System.out.println("10 - 8 (so auditoria reage)");
        notebook.setQuantidade(8);

        System.out.println("8 - 8 (sem mudança: ninguem e avisado)");
        notebook.setQuantidade(8);

        System.out.println("8 - 4 compras");
        notebook.setQuantidade(4);

        System.out.println("4 - 0 (Compras alerta; Vendas registra que zerou)");
        notebook.setQuantidade(0);

        System.out.println(" Removendo Compras");
        notebook.removerArea(compras);

        System.out.println("0 - 3 (Vendas alerta; Compras nao recebe mais nada)");
        notebook.setQuantidade(3);

        System.out.println("3 - 12 so auditoria");
        notebook.setQuantidade(12);

        System.out.println("\nTotal de registros na auditoria: " + auditoria.getRegistros().size());
    }
}
