package Telas;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;
import Classes.*;
import ElementosTextoUtil.*;

public class Jogo extends JPanel {
    private static int FPS = 50;
    private boolean gameOver = false;
    private boolean progredindo = false;
    private Random rand = new Random();

    private Disco player = new Disco();
    private Terreno terreno = new Terreno(600, 600);
    private Tanques[] inimigos = new Tanques[3];

    private boolean[] controleTecla = new boolean[5];

    enum enumTecla {
        UP(0), DOWN(1), LEFT(2), RIGHT(3), SHOOT(4);
        public int valor;

        private enumTecla(int index) {
            valor = index;
        }
    }

    public void setaTecla(int tecla, boolean pressionada) {
        switch (tecla) {
            case KeyEvent.VK_UP:
                controleTecla[enumTecla.UP.valor] = pressionada;
                break;
            case KeyEvent.VK_DOWN:
                controleTecla[enumTecla.DOWN.valor] = pressionada;
                break;
            case KeyEvent.VK_LEFT:
                controleTecla[enumTecla.LEFT.valor] = pressionada;
                break;
            case KeyEvent.VK_RIGHT:
                controleTecla[enumTecla.RIGHT.valor] = pressionada;
                break;
            case KeyEvent.VK_SPACE:
                controleTecla[enumTecla.SHOOT.valor] = pressionada;
                break;
        }
    }

    public Jogo() {
        inimigos = Tanques.spawnarTanques();
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Fundo preto
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Sempre desenhar o terreno (piso verde)
        terreno.desenhar(g2);

        if (!progredindo) {
            player.desenha(g);
            Tanques.desenha(g2, inimigos, player);
        } else {
            terreno.progredir(g2);
            progredindo = false;
        }
    }

    public void iniciar() {
        long proxAtualizacao = 0;
        long frameCounter = 0;
        while (!gameOver) {
            if (System.currentTimeMillis() >= proxAtualizacao) {
                atualizar();
                proxAtualizacao = System.currentTimeMillis() + FPS;
                frameCounter++;

                if (frameCounter % (FPS - 10) == 0) {
                    int r = rand.nextInt(3);
                    inimigos[r].setAtirando(true);
                }
            }
            gameOver = !player.isAtivo();
        }
    }

    public void atualizar() {
        if (controleTecla[enumTecla.UP.valor]) {
            MatUtil.move(player, 270, player.getVel());
        }
        if (controleTecla[enumTecla.DOWN.valor]) {
            MatUtil.move(player, 90, player.getVel());
        }
        if (controleTecla[enumTecla.LEFT.valor]) {
            MatUtil.move(player, 180, player.getVel());
        }
        if (controleTecla[enumTecla.RIGHT.valor]) {
            MatUtil.move(player, 0, player.getVel());
        }
        if (controleTecla[enumTecla.SHOOT.valor]) {
            player.atirar(inimigos);
        } else {
            player.naoAtirar();
        }

        if (Tanques.todosMorreram(inimigos)) {
            progredindo = true;
            FPS -= (FPS > 30) ? 5 : 0;
            inimigos = Tanques.spawnarTanques();
            player.setAtirando(false);
            player.setDistancia(player.getDistancia() + 100);
        }

        Tanques.mover(inimigos);
        repaint();
    }

    public Disco getPlayer() {
        return player;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
