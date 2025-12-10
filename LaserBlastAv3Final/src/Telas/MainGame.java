package Telas;

import ElementosTextoUtil.Cenario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainGame extends JFrame {

    private static final int LARGURA = 600;
    private static final int ALTURA = 600;

    private Cenario cenarioAtual;
    private RankingManager ranking;

    public MainGame() {
        super("Laser Blast - Atari 2600");

        setSize(LARGURA, ALTURA);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ranking = new RankingManager();
        ranking.carregar();

        carregarMenu();
        setVisible(true);
    }

    private void carregarMenu() {

        JPanel painelMenu = criarPainelCenario();

        cenarioAtual = new MenuCenario(LARGURA, ALTURA, ranking);
        cenarioAtual.carregar();

        painelMenu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    pedirNomeEIniciarJogo();
                }
            }
        });

        trocarPainel(painelMenu);
    }

    private void pedirNomeEIniciarJogo() {

        String nome = JOptionPane.showInputDialog(
                this,
                "Digite seu nome para começar:",
                "Bem-vindo ao Laser Blast!",
                JOptionPane.PLAIN_MESSAGE
        );

        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Você precisa digitar um nome para jogar!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        carregarJogo(nome.trim());
    }

    private void carregarJogo(String nomeJogador) {

        final Jogo jogo = new Jogo();

        jogo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                jogo.setaTecla(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                jogo.setaTecla(e.getKeyCode(), false);
            }
        });

        trocarPainel(jogo);
        jogo.requestFocusInWindow();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                jogo.iniciar();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int pontos = jogo.getPlayer().getPontuacao();
                        int distancia = jogo.getPlayer().getDistancia();
                        int vidas = Math.max(0, jogo.getPlayer().getVidas());

                        carregarGameOver(nomeJogador, pontos, distancia, vidas);
                    }
                });
            }
        });

        t.start();
    }

    private void carregarGameOver(String nome, int pontos, int distancia, int vidas) {

        JPanel painelGO = criarPainelCenario();

        cenarioAtual = new GameOverCenario(LARGURA, ALTURA, pontos, distancia, vidas);
        cenarioAtual.carregar();

        GameOverCenario gameOver = (GameOverCenario) cenarioAtual;
        int total = gameOver.calcularTotal();

        ranking.adicionar(nome, total);
        ranking.salvar();

        painelGO.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    carregarMenu();
                }
            }
        });

        trocarPainel(painelGO);

        JOptionPane.showMessageDialog(
                this,
                "Pontuação salva!\n" + nome + ": " + total + " pontos",
                "Parabéns!",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Criador de painéis que desenham cenários

    private JPanel criarPainelCenario() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (cenarioAtual != null) {
                    cenarioAtual.desenhar((Graphics2D) g);
                }
            }
        };
    }

    // Troca o painel visível na janela

    private void trocarPainel(JPanel novoPainel) {
        novoPainel.setPreferredSize(new Dimension(LARGURA, ALTURA));
        novoPainel.setFocusable(true);

        getContentPane().removeAll();
        add(novoPainel);

        revalidate();
        repaint();
        novoPainel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGame();
            }
        });
    }
}
