import java.util.Locale;

// ===== Componente (RQ3.1): vale para bebida base e para pedido já decorado =====
interface Bebida {
    String getDescricao();
    double getPreco();
}

// ===== Bebidas base (RQ3.2) =====
class Espresso implements Bebida {
    static final double PRECO = 6.00;

    @Override
    public String getDescricao() { return "Espresso"; }

    @Override
    public double getPreco() { return PRECO; }
}

class Cha implements Bebida {
    static final double PRECO = 5.00;

    @Override
    public String getDescricao() { return "Chá"; }

    @Override
    public double getPreco() { return PRECO; }
}

// ===== Decorator abstrato (RQ3.4, RQ3.5, RQ3.7) =====
// Envolve qualquer Bebida (inclusive outro complemento), então o mesmo
// complemento pode ser empilhado várias vezes e novos complementos são
// só novas subclasses, sem mexer em nada existente.
abstract class Complemento implements Bebida {
    private final Bebida bebida;

    protected Complemento(Bebida bebida) {
        this.bebida = bebida;
    }

    protected abstract String getNome();
    protected abstract double getValor();

    @Override
    public String getDescricao() {
        return bebida.getDescricao() + " + " + getNome();
    }

    @Override
    public double getPreco() {
        return bebida.getPreco() + getValor();
    }
}

// ===== Complementos concretos (RQ3.3) =====
class Leite extends Complemento {
    static final double VALOR = 2.00;

    Leite(Bebida bebida) { super(bebida); }

    @Override
    protected String getNome() { return "Leite"; }

    @Override
    protected double getValor() { return VALOR; }
}

class Chantilly extends Complemento {
    static final double VALOR = 3.00;

    Chantilly(Bebida bebida) { super(bebida); }

    @Override
    protected String getNome() { return "Chantilly"; }

    @Override
    protected double getValor() { return VALOR; }
}

class Calda extends Complemento {
    static final double VALOR = 2.50;

    Calda(Bebida bebida) { super(bebida); }

    @Override
    protected String getNome() { return "Calda"; }

    @Override
    protected double getValor() { return VALOR; }
}

// ===== Programa principal (RQ3.8) =====
public class Cafeteria {
    private static final Locale BR = Locale.forLanguageTag("pt-BR");

    public static void main(String[] args) {
        // Pedido 1: Espresso com Leite e Chantilly
        Bebida pedido1 = new Chantilly(new Leite(new Espresso()));

        // Pedido 2: Chá com Calda
        Bebida pedido2 = new Calda(new Cha());

        // Pedido 3: Espresso com Leite repetido (duplo) e Calda
        Bebida pedido3 = new Calda(new Leite(new Leite(new Espresso())));

        // Pedido 4: Chá com Chantilly repetido e Leite
        Bebida pedido4 = new Leite(new Chantilly(new Chantilly(new Cha())));

        imprimir(1, pedido1);
        imprimir(2, pedido2);
        imprimir(3, pedido3);
        imprimir(4, pedido4);
    }

    private static void imprimir(int numero, Bebida pedido) {
        System.out.println(String.format(BR, "Pedido %d: %s | R$ %.2f",
                numero, pedido.getDescricao(), pedido.getPreco()));
    }
}
