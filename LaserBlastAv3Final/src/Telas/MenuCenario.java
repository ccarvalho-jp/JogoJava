package Telas;

import ElementosTextoUtil.Cenario;
import ElementosTextoUtil.Texto;
import java.awt.*;
import java.util.List;

public class MenuCenario extends Cenario {
    private static final Color LARANJA = new Color(240, 85, 36);
    private static final Color BEGE = new Color(236, 240, 213);
    private static final Color AMARELO = new Color(255, 242, 18);
    private static final Color PRETO = new Color(55, 52, 53);

    private RankingManager ranking;
    private Texto logoActivision, logoPresents, titulo, subtitulo, instrucao;

    public MenuCenario(int largura, int altura, RankingManager ranking) {
        super(largura, altura);
        this.ranking = ranking;
    }

    @Override
    public void carregar() {
        logoActivision = new Texto(new Font("Monospaced", Font.BOLD, 20));
        logoActivision.setPx(220);
        logoActivision.setPy(50);
        logoActivision.setCor(BEGE);

        logoPresents = new Texto(new Font("Monospaced", Font.PLAIN, 12));
        logoPresents.setPx(260);
        logoPresents.setPy(70);
        logoPresents.setCor(BEGE);

        titulo = new Texto(new Font("Monospaced", Font.BOLD, 48));
        titulo.setPx(110);
        titulo.setPy(130);
        titulo.setCor(BEGE);

        subtitulo = new Texto(new Font("Monospaced", Font.PLAIN, 14));
        subtitulo.setPx(170);
        subtitulo.setPy(160);
        subtitulo.setCor(BEGE);

        instrucao = new Texto(new Font("Monospaced", Font.BOLD, 18));
        instrucao.setPx(150);
        instrucao.setPy(500);
        instrucao.setCor(AMARELO);
    }

    @Override
    public void descarregar() {
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(LARANJA);
        g.fillRect(0, 0, largura, altura);

        logoActivision.desenha(g, "ACTIVISION");
        logoPresents.desenha(g, "presents");
        titulo.desenha(g, "LASER BLAST");
        subtitulo.desenha(g, "VIDEO GAME SIMULATOR");

        // Painel do ranking
        g.setColor(PRETO);
        g.fillRoundRect(100, 200, 400, 250, 10, 10);
        g.setColor(AMARELO);
        g.setStroke(new BasicStroke(3));
        g.drawRoundRect(100, 200, 400, 250, 10, 10);

        desenharRanking(g);

        instrucao.desenha(g, "PRESS ENTER TO START");
    }

    private void desenharRanking(Graphics2D g) {
        Texto textoRanking = new Texto(new Font("Monospaced", Font.BOLD, 18));
        textoRanking.setCor(BEGE);

        List<PlayerScore> lista = ranking.getRanking();
        int y = 230;

        for (PlayerScore jogador : lista) {
            textoRanking.desenha(g, jogador.getName(), 130, y);
            textoRanking.desenha(g, String.valueOf(jogador.getScore()), 380, y);
            y += 22;
        }
    }
}