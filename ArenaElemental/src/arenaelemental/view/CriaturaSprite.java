package arenaelemental.view;

import arenaelemental.modelo.Monstrinho;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CriaturaSprite extends JPanel {
        private final Monstrinho monstrinho;
        private final int largura;
        private final int altura;
        private final BufferedImage imagem;

        public CriaturaSprite(Monstrinho monstrinho, int x, int y, int largura, int altura) {
                this.monstrinho = monstrinho;
                this.largura = largura;
                this.altura = altura;
                setOpaque(false);
                setBounds(x, y, largura, altura);
                setPreferredSize(new Dimension(largura, altura));
                imagem = carregarImagem(monstrinho);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                if (imagem == null) {
                        desenhar(graphics, monstrinho, largura / 2, altura / 2 + 8,
                                        Math.min(largura, altura) / 120.0);
                        return;
                }
                Graphics2D desenho = (Graphics2D) graphics.create();
                desenho.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                desenharImagem(desenho);
                desenho.dispose();
        }

        public static void desenhar(Graphics graphics, Monstrinho monstrinho,
                        int centroX, int centroY, double escala) {
                Graphics2D desenho = (Graphics2D) graphics.create();
                desenho.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
                int tamanho = Math.max(20, (int) (120 * escala));
                int x = centroX - tamanho / 2;
                int y = centroY - tamanho / 2;
                Color cor = monstrinho.getCor();

                desenho.setColor(new Color(0, 0, 0, 70));
                desenho.fillOval(x - 10, y + tamanho - 5, tamanho + 20, Math.max(8, tamanho / 5));
                desenho.setColor(cor);
                desenho.fillOval(x, y, tamanho, tamanho);
                desenho.setColor(cor.brighter());
                desenho.fillOval(x + tamanho / 4, y + tamanho / 3, tamanho / 2, tamanho / 2);

                int olho = Math.max(5, (int) (18 * escala));
                desenho.setColor(Color.WHITE);
                desenho.fillOval(x + tamanho / 4, y + tamanho / 4, olho, olho);
                desenho.fillOval(x + tamanho * 3 / 5, y + tamanho / 4, olho, olho);
                desenho.setColor(Color.BLACK);
                int pupila = Math.max(3, (int) (8 * escala));
                desenho.fillOval(x + tamanho / 4 + olho / 3, y + tamanho / 4 + olho / 3,
                                pupila, pupila);
                desenho.fillOval(x + tamanho * 3 / 5 + olho / 3, y + tamanho / 4 + olho / 3,
                                pupila, pupila);
                desenho.drawArc(x + tamanho / 3, y + tamanho / 2, tamanho / 3, tamanho / 4, 0, -180);

                desenharAcessorio(desenho, monstrinho.tipo, x, y, tamanho, cor);
                desenho.dispose();
        }

        private void desenharImagem(Graphics2D desenho) {
                double proporcao = Math.min((double) largura / imagem.getWidth(),
                                (double) altura / imagem.getHeight());
                int imagemLargura = Math.max(1, (int) (imagem.getWidth() * proporcao));
                int imagemAltura = Math.max(1, (int) (imagem.getHeight() * proporcao));
                int imagemX = (largura - imagemLargura) / 2;
                int imagemY = (altura - imagemAltura) / 2;
                desenho.drawImage(imagem, imagemX, imagemY, imagemLargura, imagemAltura, null);
        }

        private static BufferedImage carregarImagem(Monstrinho monstrinho) {
                String nome = monstrinho.nome.toLowerCase().replace(" ", "_") + ".jpeg";
                BufferedImage imagem = lerImagem(nome);
                if (imagem == null) {
                        String tipo = monstrinho.tipo.toLowerCase() + ".jpeg";
                        imagem = lerImagem(tipo);
                }
                return imagem;
        }

        private static BufferedImage lerImagem(String nomeArquivo) {
                try {
                        BufferedImage imagem = ImageIO.read(new File("sprite", nomeArquivo));
                        if (imagem != null) {
                                return removerFundoCinza(imagem);
                        }
                        var recurso = CriaturaSprite.class.getResource("/sprite/" + nomeArquivo);
                        return recurso == null ? null : removerFundoCinza(ImageIO.read(recurso));
                } catch (IOException | RuntimeException erro) {
                        return null;
                }
        }

        private static BufferedImage removerFundoCinza(BufferedImage origem) {
                BufferedImage resultado = new BufferedImage(origem.getWidth(), origem.getHeight(),
                                BufferedImage.TYPE_INT_ARGB);
                int fundo = origem.getRGB(0, 0);
                int vermelhoFundo = (fundo >> 16) & 0xff;
                int verdeFundo = (fundo >> 8) & 0xff;
                int azulFundo = fundo & 0xff;

                for (int y = 0; y < origem.getHeight(); y++) {
                        for (int x = 0; x < origem.getWidth(); x++) {
                                int cor = origem.getRGB(x, y);
                                int vermelho = (cor >> 16) & 0xff;
                                int verde = (cor >> 8) & 0xff;
                                int azul = cor & 0xff;
                                int distancia = Math.abs(vermelho - vermelhoFundo)
                                                + Math.abs(verde - verdeFundo) + Math.abs(azul - azulFundo);
                                boolean cinza = Math.max(vermelho, Math.max(verde, azul))
                                                - Math.min(vermelho, Math.min(verde, azul)) < 18;
                                resultado.setRGB(x, y, cinza && distancia < 75 ? cor & 0x00ffffff : cor | 0xff000000);
                        }
                }
                return resultado;
        }

        private static void desenharAcessorio(Graphics2D desenho, String tipo,
                        int x, int y, int tamanho, Color cor) {
                int centro = x + tamanho / 2;
                int topo = y + tamanho / 8;
                desenho.setColor(cor.darker());
                desenho.setStroke(new BasicStroke(Math.max(2, tamanho / 18f)));
                switch (tipo) {
                        case "FOGO" -> {
                                desenho.fillOval(centro - tamanho / 8, topo - tamanho / 6,
                                                tamanho / 4, tamanho / 3);
                                desenho.setColor(Color.YELLOW);
                                desenho.fillOval(centro - tamanho / 16, topo - tamanho / 10,
                                                tamanho / 8, tamanho / 5);
                        }
                        case "AGUA" -> desenho.drawArc(x + tamanho / 3, y - tamanho / 8,
                                        tamanho / 3, tamanho / 3, 200, 140);
                        case "PLANTA" -> desenho.fillOval(x + tamanho / 2, y - tamanho / 10,
                                        tamanho / 3, tamanho / 5);
                        case "TERRA" -> desenho.fillRect(x + tamanho / 3, y - tamanho / 12,
                                        tamanho / 3, tamanho / 6);
                        case "AR" -> desenho.drawArc(x + tamanho / 4, y - tamanho / 8,
                                        tamanho / 2, tamanho / 3, 20, 140);
                        case "ELETRICO" -> {
                                int[] pontosX = { centro, centro - tamanho / 8, centro + tamanho / 20,
                                                centro - tamanho / 20 };
                                int[] pontosY = { topo - tamanho / 8, topo + tamanho / 10,
                                                topo + tamanho / 20, topo + tamanho / 4 };
                                desenho.fillPolygon(pontosX, pontosY, pontosX.length);
                        }
                        case "GELO" -> desenho.drawLine(centro, topo - tamanho / 8,
                                        centro, topo + tamanho / 5);
                        case "SOMBRA" -> desenho.fillOval(centro - tamanho / 8, topo - tamanho / 8,
                                        tamanho / 4, tamanho / 4);
                        case "LUZ" -> {
                                desenho.drawLine(centro, topo - tamanho / 6, centro, topo + tamanho / 5);
                                desenho.drawLine(centro - tamanho / 6, topo, centro + tamanho / 6, topo);
                        }
                        default -> {
                        }
                }
        }
}
