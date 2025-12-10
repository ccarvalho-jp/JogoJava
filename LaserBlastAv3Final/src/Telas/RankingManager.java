package Telas;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankingManager {
    private static final int MAX_RANKING_SIZE = 10;

    private ArrayList<PlayerScore> ranking = new ArrayList<>();
    private String arquivo = "ranking.txt";

    public void adicionar(String nome, int pontos) {
        ranking.add(new PlayerScore(nome.toUpperCase(), pontos));


        Collections.sort(ranking);

        if (ranking.size() > MAX_RANKING_SIZE) {
            ranking.remove(MAX_RANKING_SIZE);
        }
    }

    public void salvar() {
        try (PrintWriter w = new PrintWriter(arquivo)) {
            for (PlayerScore p : ranking)
                w.println(p.getName() + ";" + p.getScore());
        } catch (IOException e) {
            System.err.println("Erro ao salvar ranking: " + e.getMessage());
        }
    }

    public void carregar() {
        ranking.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = r.readLine()) != null) {
                if (linha.trim().isEmpty() || !linha.contains(";")) {
                    continue;
                }

                String[] dados = linha.split(";");

                if (dados.length >= 2) {
                    try {
                        int pontuacao = Integer.parseInt(dados[1].trim());
                        if (!dados[0].trim().isEmpty()) {
                            ranking.add(new PlayerScore(dados[0].trim(), pontuacao));
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("RankingManager: Linha ignorada - pontuação inválida.");
                    }
                }
            }
            Collections.sort(ranking);
            while (ranking.size() > MAX_RANKING_SIZE) {
                ranking.remove(MAX_RANKING_SIZE);
            }

        } catch (IOException e) {
            System.out.println("Arquivo de ranking não encontrado. Será criado ao salvar.");
        }
    }

    // Retorna uma cópia imutável
    public List<PlayerScore> getRanking() {
        return Collections.unmodifiableList(ranking);
    }
}