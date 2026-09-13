package arenaelemental.modelo;

public class Lumina extends Monstrinho {
    public Lumina() {
        super("Lumina", "LUZ", 5, 100, 26, 19,
                new Ataque("Clarão", "Um forte ataque luminoso."),
                new Ataque("Enfraquecer", "Reduz a defesa do inimigo."),
                new Ataque("Brilho", "Aumenta o poder do usuário."),
                new Ataque("Raio Solar", "Concentra energia luminosa."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return alvo.tipo.equals("SOMBRA") ? 1.30 : 1.0;
    }
}
