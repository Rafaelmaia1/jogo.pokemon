package arenaelemental.modelo;

import java.awt.Color;

public enum TipoElemental {
    FOGO(new Color(230, 75, 50)),
    AGUA(new Color(55, 135, 225)),
    PLANTA(new Color(70, 175, 80)),
    TERRA(new Color(160, 110, 60)),
    AR(new Color(100, 195, 225)),
    ELETRICO(new Color(235, 205, 40)),
    GELO(new Color(140, 215, 240)),
    SOMBRA(new Color(115, 70, 155)),
    LUZ(new Color(245, 220, 100));

    private final Color cor;

    TipoElemental(Color cor) {
        this.cor = cor;
    }

    public Color getCor() {
        return cor;
    }

        public double vantagemSobre(TipoElemental outro) {
        if (outro == null || outro == this) {
            return 1.0;
        }

        return switch (this) {
            case FOGO -> outro == PLANTA || outro == GELO ? 1.5
                : (outro == AGUA || outro == TERRA ? 0.67 : 1.0);
            case AGUA -> outro == FOGO || outro == TERRA ? 1.5
                : (outro == PLANTA || outro == ELETRICO ? 0.67 : 1.0);
            case PLANTA -> outro == AGUA || outro == TERRA ? 1.5
                : (outro == FOGO || outro == GELO ? 0.67 : 1.0);
            case TERRA -> outro == ELETRICO || outro == GELO ? 1.5
                : (outro == AGUA || outro == PLANTA ? 0.67 : 1.0);
            case AR -> outro == PLANTA || outro == SOMBRA ? 1.5
                : (outro == ELETRICO || outro == GELO ? 0.67 : 1.0);
            case ELETRICO -> outro == AGUA || outro == AR ? 1.5
                : (outro == TERRA || outro == GELO ? 0.67 : 1.0);
            case GELO -> outro == AR || outro == PLANTA ? 1.5
                : (outro == FOGO || outro == TERRA ? 0.67 : 1.0);
            case SOMBRA -> outro == LUZ || outro == AR ? 1.5
                : (outro == FOGO || outro == PLANTA ? 0.67 : 1.0);
            case LUZ -> outro == SOMBRA || outro == ELETRICO ? 1.5
                : (outro == TERRA || outro == GELO ? 0.67 : 1.0);
        };
        }

    public static TipoElemental de(String nome) {
        if (nome == null) {
            return null;
        }
        String normalizado = nome.trim().toUpperCase()
                .replace("Á", "A")
                .replace("É", "E");
        try {
            return valueOf(normalizado);
        } catch (IllegalArgumentException erro) {
            return null;
        }
    }
}
