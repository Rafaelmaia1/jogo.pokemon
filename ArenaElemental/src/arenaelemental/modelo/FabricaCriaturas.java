package arenaelemental.modelo;

import java.util.ArrayList;

public final class FabricaCriaturas {
    private FabricaCriaturas() {
    }

    public static ArrayList<Monstrinho> criarBanco() {
        ArrayList<Monstrinho> banco = new ArrayList<>();
        banco.add(new Braseiro());
        banco.add(new Marulho());
        banco.add(new Folharal());
        banco.add(new Pedregor());
        banco.add(new Ventus());
        banco.add(new Lumina());
        banco.add(new Sombrio());
        banco.add(new Eletrix());
        banco.add(new Geleon());
        banco.add(new Aquilon());
        banco.add(new Flamion());
        banco.add(new Terron());
        return banco;
    }

}
