package ElementosTextoUtil;

import java.awt.Graphics2D;

// classe abstrata do livro
//

public abstract class Cenario {

    protected int altura, largura;

    public Cenario(int largura, int altura) {
        this.altura = altura;
        this.largura = largura;
    }

    public abstract void carregar();

    public abstract void descarregar();

    public abstract void atualizar();

    public abstract void desenhar(Graphics2D g);

}