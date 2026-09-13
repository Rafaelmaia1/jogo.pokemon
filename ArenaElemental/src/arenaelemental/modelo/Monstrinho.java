package arenaelemental.modelo;

import java.awt.Color;

public abstract class Monstrinho {
    public final String nome;
    public final String tipo;
    public int nivel;
    public int xp;
    public int xpParaProximoNivel;
    public int vida;
    public int vidaMaxima;
    public int ataque;
    public int defesa;
    public int ataqueBase;
    public int defesaBase;
    public final Ataque[] ataques;
    public boolean queimado;
    public boolean envenenado;
    public boolean paralisado;

    public Monstrinho(String nome, String tipo, int nivel, int vida, int ataque,
            int defesa, Ataque ataque1, Ataque ataque2, Ataque ataque3, Ataque ataque4) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.xpParaProximoNivel = 100;
        this.vidaMaxima = vida;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.ataqueBase = ataque;
        this.defesaBase = defesa;
        this.ataques = new Ataque[] { ataque1, ataque2, ataque3, ataque4 };
    }

    public Monstrinho copiar() {
        Monstrinho novo;
        try {
            novo = getClass().getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException erro) {
            throw new IllegalStateException("Criatura sem construtor padrão: " + getClass(), erro);
        }
        novo.nivel = nivel;
        novo.vidaMaxima = vidaMaxima;
        novo.ataque = ataqueBase;
        novo.defesa = defesaBase;
        novo.ataqueBase = ataqueBase;
        novo.defesaBase = defesaBase;
        novo.xp = xp;
        novo.xpParaProximoNivel = xpParaProximoNivel;
        novo.vida = vida;
        novo.queimado = queimado;
        novo.envenenado = envenenado;
        novo.paralisado = paralisado;
        return novo;
    }

    public void recuperar() {
        vida = vidaMaxima;
        ataque = ataqueBase;
        defesa = defesaBase;
        queimado = false;
        envenenado = false;
        paralisado = false;
    }

    public void ganharXP(int quantidade) {
        xp += quantidade;
        while (xp >= xpParaProximoNivel) {
            xp -= xpParaProximoNivel;
            subirNivel();
        }
    }

    private void subirNivel() {
        nivel++;
        vidaMaxima += 15;
        ataqueBase += 4;
        defesaBase += 3;
        ataque = ataqueBase;
        defesa = defesaBase;
        vida = vidaMaxima;
        xpParaProximoNivel = 100 + ((nivel - 1) * 50);
    }

    public Color getCor() {
        TipoElemental elemental = TipoElemental.de(tipo);
        return elemental == null ? Color.GRAY : elemental.getCor();
    }

    public final double multiplicadorEspecial(Monstrinho alvo) {
        return bonusEspecial(alvo);
    }

    public final void executarEfeitoPosAtaque(Monstrinho alvo, int dano) {
        efeitoPosAtaque(alvo, dano);
    }

    protected abstract double bonusEspecial(Monstrinho alvo);

    protected void efeitoPosAtaque(Monstrinho alvo, int dano) {
    }
}
