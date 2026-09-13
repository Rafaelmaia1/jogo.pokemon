package arenaelemental.modelo;

public class Terron extends Monstrinho {
    public Terron() {
        super("Terron", "TERRA", 5, 130, 21, 28,
                new Ataque("Tremor", "Abala o terreno."),
                new Ataque("Fortificar", "Aumenta a defesa."),
                new Ataque("Reduzir Ataque", "Diminui o ataque inimigo."),
                new Ataque("Pedra Pesada", "Ataca com uma enorme pedra."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return vida > vidaMaxima * 0.75 ? 1.15 : 1.0;
    }
}
