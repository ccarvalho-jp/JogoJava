package Classes;

import ElementosTextoUtil.Elemento;
import ElementosTextoUtil.Util;
import java.awt.*;

public class Disco extends Elemento {
    private int vidas = 3;
    private int vidasMaximas = 3; // Guarda as vidas iniciais
    private boolean atirando = false;
    private int[] origemLaser = new int[2];
    private long contador = 0;
    private Laser[] laser = new Laser[10];

    // ADICIONADO: Sistema de pontuação
    private int pontuacao = 0;
    private int distancia = 0;

    public Laser[] getLaser() {
        return laser;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public boolean isAtirando() {
        return atirando;
    }

    public void setAtirando(boolean atirando) {
        this.atirando = atirando;
    }

    // GETTERS E SETTERS PARA PONTUAÇÃO
    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getVidasMaximas() {
        return vidasMaximas;
    }

    public Disco() {
        setAtivo(true);
        setAltura(30);
        setLargura(50);
        setPy(100);
        setPx(100);
        setCor(new Color(220, 192, 132));
        setVel(10);
        atualizarOrigem();
    }

    public void desenha(Graphics g) {
        setAtivo(vidas >= 0);
        if (isAtivo()) {
            g.setColor(getCor());
            g.fillOval(getPx(), getPy(), getLargura(), getAltura());

            // Desenhar vidas
            for (int i = 0; i < vidas; i++) {
                g.fillOval((i * 30) + 20, 20, 15, 7);
            }

            // MOSTRAR PONTUAÇÃO NA TELA
            g.setColor(Color.WHITE);
            g.setFont(new Font("Monospaced", Font.BOLD, 16));
            g.drawString("PONTOS: " + pontuacao, 20, 60);
            g.drawString("DISTANCIA: " + distancia + "M", 20, 80);

            if (atirando && contador < 30) {
                for (int i = 0; i < laser.length; i++) {
                    int[] propagar = {origemLaser[0], origemLaser[1] + (50 * i)};
                    laser[i] = new Laser(propagar);
                    laser[i].desenhar(g);
                }
                contador++;
            }
        }
    }

    public void atirar(Tanques[] inimigos) {
        atualizarOrigem();
        setVel(1);
        atirando = true;

        for (int i = 0; i < laser.length; i++) {
            if (laser[i] != null && laser[i].isAtivo()) {
                for (int j = 0; j < inimigos.length; j++) {
                    if (inimigos[j].isAtivo() && Util.colide(laser[i], inimigos[j])) {
                        inimigos[j].setAtivo(false);
                        // ADICIONAR PONTOS QUANDO ACERTAR INIMIGO
                        pontuacao += 100;
                    }
                }
            }
        }

        if (contador >= 30) {
            naoAtirar();
        }
    }

    public void naoAtirar() {
        setVel(10);
        atirando = false;
        contador = 0;
    }

    public void atualizarOrigem() {
        origemLaser[1] = getPy() + getAltura();
        origemLaser[0] = getPx() + (getLargura() / 2);
    }
}