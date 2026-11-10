import java.util.Locale;

// ===== Estratégia (RQ2.8: novo perfil = nova classe que implementa esta interface) =====
interface EstrategiaCobranca {
    String getNome();
    double calcular(int minutosPermanencia);
}

// ===== Contexto (RQ2.1, RQ2.6, RQ2.7) =====
class Ticket {
    private final String placa;
    private final int minutosPermanencia;
    private EstrategiaCobranca estrategia;

    public Ticket(String placa, int minutosPermanencia, EstrategiaCobranca estrategia) {
        if (minutosPermanencia < 0) {
            throw new IllegalArgumentException("Tempo de permanência não pode ser negativo");
        }
        this.placa = placa;
        this.minutosPermanencia = minutosPermanencia;
        this.estrategia = estrategia;
    }

    public String getPlaca() { return placa; }
    public int getMinutosPermanencia() { return minutosPermanencia; }
    public EstrategiaCobranca getEstrategia() { return estrategia; }

    // RQ2.6: troca de perfil em tempo de execução
    public void setEstrategia(EstrategiaCobranca estrategia) {
        this.estrategia = estrategia;
    }

    // Sem if/else nem switch sobre o perfil: delega para a estratégia atual
    public double calcularValor() {
        return estrategia.calcular(minutosPermanencia);
    }
}

// ===== Utilitário compartilhado =====
class CalculoHoras {
    private static final int MINUTOS_POR_HORA = 60;

    // Qualquer fração de hora conta como hora iniciada (61 min -> 2 horas)
    static int horasIniciadas(int minutos) {
        return (minutos + MINUTOS_POR_HORA - 1) / MINUTOS_POR_HORA;
    }
}

// ===== Perfis concretos (RQ2.2, RQ2.3, RQ2.4) =====
class Rotativo implements EstrategiaCobranca {
    static final double VALOR_HORA = 10.00;

    @Override
    public String getNome() { return "Rotativo"; }

    @Override
    public double calcular(int minutosPermanencia) {
        return CalculoHoras.horasIniciadas(minutosPermanencia) * VALOR_HORA;
    }
}

class Mensalista implements EstrategiaCobranca {
    static final double VALOR_FIXO = 300.00;

    @Override
    public String getNome() { return "Mensalista"; }

    @Override
    public double calcular(int minutosPermanencia) {
        return VALOR_FIXO;
    }
}

class Conveniado implements EstrategiaCobranca {
    static final int MINUTOS_GRATUITOS = 30;
    static final double VALOR_HORA = 10.00;
    static final double PERCENTUAL_DESCONTO = 0.20; // 20%

    @Override
    public String getNome() { return "Conveniado"; }

    @Override
    public double calcular(int minutosPermanencia) {
        int minutosCobrados = Math.max(0, minutosPermanencia - MINUTOS_GRATUITOS);
        double valorBruto = CalculoHoras.horasIniciadas(minutosCobrados) * VALOR_HORA;
        return valorBruto * (1 - PERCENTUAL_DESCONTO);
    }
}

// ===== Programa principal (RQ2.9) =====
public class Estacionamento {
    private static final Locale BR = Locale.forLanguageTag("pt-BR");

    public static void main(String[] args) {
        int minutos = 125;
        EstrategiaCobranca[] perfis = { new Rotativo(), new Mensalista(), new Conveniado() };

        System.out.println("=== Mesmo tempo (" + minutos + " min) nos três perfis ===");
        for (EstrategiaCobranca perfil : perfis) {
            Ticket t = new Ticket("ABC-1234", minutos, perfil);
            System.out.println(formatar(t));
        }

        System.out.println("\n=== Troca de perfil no mesmo tíquete ===");
        Ticket ticket = new Ticket("XYZ-9876", minutos, new Rotativo());
        System.out.println(formatar(ticket));

        ticket.setEstrategia(new Conveniado());
        System.out.println(formatar(ticket));

        ticket.setEstrategia(new Mensalista());
        System.out.println(formatar(ticket));
    }

    private static String formatar(Ticket t) {
        return String.format(BR, "Placa %s | %d min | %-10s | R$ %.2f",
                t.getPlaca(), t.getMinutosPermanencia(),
                t.getEstrategia().getNome(), t.calcularValor());
    }
}
