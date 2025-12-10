
package Classes;

import ElementosTextoUtil.Elemento;
import ElementosTextoUtil.MatUtil;
import ElementosTextoUtil.Util;
import java.awt.*;
import java.util.Random;

public class Tanques extends Elemento {
    private static Random rand = new Random();
    private static int[] origemLaser = new int[2];
    static Laser[] laser = new Laser[40];
    private boolean atirando = false;

    public boolean isAtirando() {
        return atirando;
    }

    public void setAtirando(boolean atirando) {
        this.atirando = atirando;
    }

    public Tanques(){
        setAtivo(true);
        setAltura(30);
        setLargura(30);
        setPx(100);
        setPy(480);
        setCor(new Color(116,116,116));
        setVel(10);
    }


    public Tanques(int x, int y) {
        setAtivo(true);
        setAltura(30);
        setLargura(30);
        setPx(x);
        setPy(y);
        setCor(new Color(116,116,116));
    }

    public void destruir(){

    }

    public void desenhar(Graphics2D g){
        if(isAtivo()){
            g.setColor(getCor());
            g.fillRect(getPx(), getPy(), getLargura(), getAltura());
        }
    }

    public static void desenha(Graphics2D g, Tanques[] tanques, Disco player){
        for(Tanques t: tanques){
            if (t.isAtivo()) {
                g.setColor(t.getCor());
                g.fillRect(t.getPx(), t.getPy(), t.getLargura(), t.getAltura());
                if(t.atirando){
                    t.atacar(g, player);
                }
            }

        }
    }

    public static boolean todosMorreram(Tanques[] t){
        int c = 0;
        for(int i = 0; i < t.length; i++){
            c += (!t[i].isAtivo()) ? 1 : 0;
        }
        return c == t.length;
    }

    public static Tanques[] spawnarTanques(){
        int r = rand.nextInt(10);
        Tanques[] tanques = new Tanques[3];
        for( int i = 0 ; i<3 ; i++){
            tanques[i] = new Tanques();
            tanques[i].setPx(tanques[i].getPx() + (100*i) + (25*r));
        }
        return tanques;
    }

    public static void mover(Tanques[] tanques){
        for(Tanques t: tanques){
            Util.corrigePosicao(t,600,480);
            MatUtil.move(t,180,t.getVel());
        }
    }

    public void atacar(Graphics2D g, Disco player){
        atirando = false;
        atualizarOrigem();
        for (int i = 0; i < laser.length; i++) {
            int[] propagar = {origemLaser[0], origemLaser[1] - (50 * i)};
            laser[i] = new Laser(propagar);
            laser[i].setCor(new Color(116, 116, 255));
            laser[i].desenhar(g);
            if(Util.colide(laser[i],player) || (i > 2 && Util.colide(laser[i-1],player))){
                player.setVidas(player.getVidas()-1);
                laser[i].setAtivo(false);
            }
        }
    }

    public void atualizarOrigem(){
        origemLaser[1] = getPy() + getAltura();
        origemLaser[0] = getPx() + (getLargura()/2);
    }

}
