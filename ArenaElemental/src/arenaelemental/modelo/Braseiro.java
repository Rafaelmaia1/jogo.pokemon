package arenaelemental.modelo;

public class Braseiro extends Monstrinho {
    public Braseiro() {
        super("Braseiro", "FOGO", 5, 100, 25, 18,
                new Ataque("Chama", "Um ataque elemental de fogo."),
                new Ataque("Intimidar", "Reduz a força ofensiva do inimigo."),
                new Ataque("Fúria", "Aumenta temporariamente o poder de ataque."),
                new Ataque("Queimar", "Pode deixar o inimigo queimando."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return alvo.vida <= alvo.vidaMaxima * 0.30 ? 1.30 : 1.0;
    }
}
