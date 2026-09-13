package arenaelemental.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PainelImagemFundo extends JPanel {
    private final BufferedImage imagemFundo;
    private int alturaImagemFundo = -1;

    public PainelImagemFundo(String caminhoImagem) {
        imagemFundo = carregarImagem(caminhoImagem);
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (imagemFundo == null) {
            return;
        }

        Graphics2D desenho = (Graphics2D) graphics.create();
        desenho.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        int altura = alturaImagemFundo > 0 ? alturaImagemFundo : getHeight();
        desenho.drawImage(imagemFundo, 0, 0, getWidth(), altura, null);
        desenho.dispose();
    }

    protected final void limitarAlturaImagemFundo(int altura) {
        alturaImagemFundo = altura;
    }

    private static BufferedImage carregarImagem(String caminhoImagem) {
        try {
            var recurso = PainelImagemFundo.class.getResource(caminhoImagem);
            if (recurso != null) {
                return ImageIO.read(recurso);
            }

            File arquivo = new File("ArenaElemental", caminhoImagem.substring(1));
            if (arquivo.isFile()) {
                return ImageIO.read(arquivo);
            }

            arquivo = new File(caminhoImagem.substring(1));
            if (arquivo.isFile()) {
                return ImageIO.read(arquivo);
            }
        } catch (IOException | RuntimeException erro) {
            return null;
        }
        return null;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
    }
}
