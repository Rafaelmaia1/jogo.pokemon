package arenaelemental.modelo;

public class Ventus extends Monstrinho {
    public Ventus() {
        super("Ventus", "AR", 5, 90, 28, 15,
                new Ataque("Rajada", "Uma rajada rápida de vento."),
                new Ataque("Distrair", "Pode diminuir a precisão do inimigo."),
                new Ataque("Acelerar", "Aumenta o ataque do usuário."),
                new Ataque("Vento Cortante", "Um ataque veloz de vento."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return alvo.paralisado ? 1.10 : 1.0;
    }
}
