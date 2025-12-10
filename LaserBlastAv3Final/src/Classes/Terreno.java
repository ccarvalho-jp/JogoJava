package Classes;

import ElementosTextoUtil.Cenario;
import java.awt.*;
import java.util.Random;

public class Terreno extends Cenario {
    Random rand = new Random();
    private boolean progredindo = false;
    private int contador = 0;

    public Terreno(int largura, int altura) {
        super(largura, altura);
        carregar();
    }

    @Override
    public void carregar() {
    }

    @Override
    public void descarregar() {
    }

    @Override
    public void atualizar() {
    }

    public boolean isProgredindo() {
        return progredindo;
    }

    public void setProgredindo(boolean progredindo) {
        this.progredindo = progredindo;
    }

    @Override
    public void desenhar(Graphics2D g) {
        // Desenhar piso verde na parte inferior
        g.setColor(new Color(60, 74, 31));

        // Desenha criar efeito de colinas/terreno
        for(int i = 0; i < 5; i++){
            int x = (i * 150) - 50;
            int y = altura - 150; // Posição fixa na parte inferior
            g.fillOval(x, y, 250, 180);
        }
    }

    public void progredir(Graphics2D g){
        int r = rand.nextInt(10);
        g.setColor(new Color(60, 74 + r, 31));

        // Animação de progressão
        for (int i = 0; i < 5; i++) {
            int x = (i * 150) - 50;
            int y = altura - 150;
            g.fillOval(x, y, 250, 180);
        }
    }

    public void resetar(Graphics2D g2){
        desenhar(g2);
        contador = 0;
    }
}