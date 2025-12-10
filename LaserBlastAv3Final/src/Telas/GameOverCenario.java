package Telas;

import ElementosTextoUtil.Cenario;
import ElementosTextoUtil.Texto;
import java.awt.*;

public class GameOverCenario extends Cenario {
    private static final Color LARANJA = new Color(240, 85, 36);
    private static final Color BEGE = new Color(236, 240, 213);
    private static final Color AMARELO = new Color(255, 242, 18);
    private static final Color PRETO = new Color(55, 52, 53);

    private int pontos, distancia, vidas;
    private Texto tituloGame, tituloOver, subtitulo;
    private Texto textoStats, textoTotal;

    public GameOverCenario(int largura, int altura, int pontos, int distancia, int vidas) {
        super(largura, altura);
        this.pontos = pontos;
        this.distancia = distancia;
        this.vidas = vidas;
    }

    @Override
    public void carregar() {
        tituloGame = new Texto(new Font("Monospaced", Font.BOLD, 72));
        tituloGame.setCor(LARANJA);

        tituloOver = new Texto(new Font("Monospaced", Font.BOLD, 72));
        tituloOver.setCor(LARANJA);

        subtitulo = new Texto(new Font("Monospaced", Font.BOLD, 20));
        subtitulo.setCor(BEGE);

        textoStats = new Texto(new Font("Monospaced", Font.BOLD, 24));
        textoStats.setCor(BEGE);

        textoTotal = new Texto(new Font("Monospaced", Font.BOLD, 24));
        textoTotal.setCor(AMARELO);
    }

    @Override
    public void descarregar() {
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(PRETO);
        g.fillRect(0, 0, largura, altura);

        tituloGame.desenha(g, "GAME", 160, 100);
        tituloOver.desenha(g, "OVER", 160, 170);
        subtitulo.desenha(g, "A TERRA VENCEU", 180, 210);

        textoStats.desenha(g, "PONTUAÇÃO", 150, 270);
        textoStats.desenha(g, String.valueOf(pontos), 370, 270);

        textoStats.desenha(g, "DISTANCIA", 150, 310);
        textoStats.desenha(g, distancia + "M", 370, 310);

        textoTotal.desenha(g, "TOTAL", 150, 400);
        textoTotal.desenha(g, String.valueOf(calcularTotal()), 370, 400);
    }

    public int calcularTotal() {
        return pontos + distancia;
    }
}