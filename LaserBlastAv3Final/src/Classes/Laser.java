package Classes;

import ElementosTextoUtil.Elemento ;

import java.awt.*;

public class Laser extends Elemento {
    private int[] origemLaser;

    Laser(int[] origemDisco) {
        setAtivo(true);
        setAltura(100);
        setLargura(10);
        setPx(origemDisco[0]);
        setPy(origemDisco[1]);
        setCor(Color.RED);
        origemLaser = origemDisco;
    }

    public void setOrigemLaser(int[] origemLaser) {
        this.origemLaser = origemLaser;
    }

    public void desenhar(Graphics g){
        g.setColor(getCor());
        g.fillRect(origemLaser[0], origemLaser[1], getLargura(),getAltura());
    }

}
