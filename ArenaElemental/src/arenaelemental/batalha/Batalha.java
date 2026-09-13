package arenaelemental.batalha;

import arenaelemental.modelo.Ataque;
import arenaelemental.modelo.Monstrinho;
import arenaelemental.modelo.TipoElemental;
import java.util.Random;

public final class Batalha {
    private Batalha() {
    }

    public static ResultadoDano calcularDano(Monstrinho atacante, Monstrinho defensor,
            Random random) {
        int danoBase = Math.max(1, atacante.ataque - defensor.defesa / 3);
        danoBase += random.nextInt(8);
        TipoElemental tipoAtacante = TipoElemental.de(atacante.tipo);
        TipoElemental tipoDefensor = TipoElemental.de(defensor.tipo);
        double multiplicadorTipo = tipoAtacante == null
            ? 1.0 : tipoAtacante.vantagemSobre(tipoDefensor);
        double multiplicadorEspecial = atacante.multiplicadorEspecial(defensor);
        int dano = Math.max(1, (int) Math.round(
            danoBase * multiplicadorTipo * multiplicadorEspecial));
        boolean critico = random.nextInt(100) < 15;
        return new ResultadoDano(critico ? dano * 2 : dano, critico);
    }

        public static ResultadoDano atacar(Monstrinho atacante, Monstrinho defensor,
            Random random) {
        ResultadoDano resultado = calcularDano(atacante, defensor, random);
        defensor.vida = Math.max(0, defensor.vida - resultado.valor);
        atacante.executarEfeitoPosAtaque(defensor, resultado.valor);
        return resultado;
        }

    public static String aplicarEfeito(Ataque ataque, Monstrinho atacante,
            Monstrinho defensor, Random random) {
        switch (ataque.nome) {
            case "Intimidar":
            case "Reduzir Ataque":
                defensor.ataque = Math.max(1, defensor.ataque - 5);
                return "\nO ataque do inimigo diminuiu!";
            case "Enfraquecer":
            case "Reduzir Defesa":
                defensor.defesa = Math.max(1, defensor.defesa - 5);
                return "\nA defesa do inimigo diminuiu!";
            case "Fúria":
            case "Crescer":
            case "Carga":
            case "Concentração":
            case "Aumentar Ataque":
            case "Acelerar":
                atacante.ataque += 5;
                return "\nO ataque aumentou!";
            case "Fortificar":
            case "Endurecer":
                atacante.defesa += 5;
                return "\nA defesa aumentou!";
            case "Queimar":
                if (random.nextInt(100) < 35) {
                    defensor.queimado = true;
                    return "\nO inimigo ficou queimado!";
                }
                break;
            case "Veneno":
                if (random.nextInt(100) < 40) {
                    defensor.envenenado = true;
                    return "\nO inimigo foi envenenado!";
                }
                break;
            case "Paralisar":
            case "Congelar":
                if (random.nextInt(100) < 30) {
                    defensor.paralisado = true;
                    return "\nO inimigo ficou paralisado!";
                }
                break;
            case "Distrair":
                if (random.nextInt(100) < 40) {
                    return "\nO inimigo ficou distraído!";
                }
                break;
            default:
                break;
        }
        return "";
    }

    public static String aplicarStatus(Monstrinho criatura) {
        StringBuilder mensagem = new StringBuilder();
        if (criatura.queimado) {
            criatura.vida -= Math.max(1, criatura.vidaMaxima / 12);
            mensagem.append("\n").append(criatura.nome)
                    .append(" sofreu com a queimadura.");
        }
        if (criatura.envenenado) {
            criatura.vida -= Math.max(1, criatura.vidaMaxima / 10);
            mensagem.append("\n").append(criatura.nome)
                    .append(" sofreu com o veneno.");
        }
        criatura.vida = Math.max(0, criatura.vida);
        return mensagem.toString();
    }

    public static void fortalecerPorRank(Monstrinho inimigo, int nivelJogador,
            int rank, Random random) {
        inimigo.nivel = Math.max(1, nivelJogador + random.nextInt(3) + rank - 1);

        int niveisAcimaDoBase = Math.max(0, inimigo.nivel - 5);
        int bonusRank = Math.max(0, rank - 1);
        inimigo.vidaMaxima += (niveisAcimaDoBase * 10) + (bonusRank * 20);
        inimigo.ataqueBase += (niveisAcimaDoBase * 2) + (bonusRank * 4);
        inimigo.defesaBase += (niveisAcimaDoBase * 2) + (bonusRank * 3);
        inimigo.vida = inimigo.vidaMaxima;
        inimigo.ataque = inimigo.ataqueBase;
        inimigo.defesa = inimigo.defesaBase;
    }

    public static final class ResultadoDano {
        public final int valor;
        public final boolean critico;

        private ResultadoDano(int valor, boolean critico) {
            this.valor = valor;
            this.critico = critico;
        }
    }
}
