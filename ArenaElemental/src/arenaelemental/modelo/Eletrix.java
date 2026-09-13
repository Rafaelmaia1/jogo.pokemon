package arenaelemental.modelo;

public class Eletrix extends Monstrinho {
    public Eletrix() {
        super("Eletrix", "ELÉTRICO", 5, 92, 30, 15,
                new Ataque("Choque", "Um ataque elétrico."),
                new Ataque("Paralisar", "Pode impedir o inimigo de agir."),
                new Ataque("Carga", "Aumenta o ataque do usuário."),
                new Ataque("Raio", "Um forte ataque elétrico."));
    }

    @Override
    protected double bonusEspecial(Monstrinho alvo) {
        return alvo.paralisado ? 1.25 : 1.0;
    }
}
