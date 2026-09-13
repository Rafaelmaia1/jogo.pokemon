package arenaelemental.modelo;

public class Sombrio extends Monstrinho {
    public Sombrio() {
        super("Sombrio", "SOMBRA", 5, 95, 29, 16,
                new Ataque("Sombra", "Um ataque de energia sombria."),
                new Ataque("Intimidar", "Reduz o ataque inimigo."),
                new Ataque("Fúria Sombria", "Aumenta o próprio ataque."),
                new Ataque("Veneno", "Pode envenenar o inimigo."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return vida <= vidaMaxima * 0.40 ? 1.25 : 1.0;
    }
}
