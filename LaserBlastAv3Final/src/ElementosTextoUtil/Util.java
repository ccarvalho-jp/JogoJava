package ElementosTextoUtil;

public class Util {

    //classe do livro

    public static boolean colide(Elemento a, Elemento b) {
        if (!a.isAtivo() || !b.isAtivo())
            return false;
        final int plA = a.getPx() + a.getLargura();
        final int plB = b.getPx() + b.getLargura();
        final int paA = a.getPy() + a.getAltura();
        final int paB = b.getPy() + b.getAltura();
        if (plA > b.getPx() && a.getPx() < plB && paA > b.getPy() && a.getPy() < paB) {
            return true;
        }
        return false;
    }

    public static boolean colideX(Elemento a, Elemento b) {
        if (!a.isAtivo() || !b.isAtivo())
            return false;
        if (a.getPx() + a.getLargura() >= b.getPx() && a.getPx() <= b.getPx() + b.getLargura()) {
            return true;
        }
        return false;
    }

    public static void centraliza(Elemento e, int largura, int altura) {
        e.setPx((largura - e.getLargura()) / 2);
        e.setPy((altura - e.getAltura()) / 2);
    }

    public static void corrigePosicao(Elemento el, int limiteX, int limitY) {
        int nx = el.getPx(); // Nova posi��o x
        int ny = el.getPy(); // Nova posi��o y

        if (nx + el.getLargura() < 0)
            nx = limiteX;
        else if (nx > limiteX)
            nx = -el.getLargura();

        if (ny + el.getAltura() < 0)
            ny = limitY;
        else if (ny > limitY)
            ny = -el.getAltura();

        el.setPx(nx);
        el.setPy(ny);
    }
}