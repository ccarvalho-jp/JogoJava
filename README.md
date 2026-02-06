# 🎮 Laser Blast - Atari 2600

Recriação do clássico jogo **Laser Blast** do Atari 2600 em Java, desenvolvido como projeto acadêmico da disciplina de Programação Orientada a Objetos.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-007396?style=for-the-badge&logo=java&logoColor=white)

## 📋 Sobre o Projeto

Este projeto é uma homenagem ao clássico Laser Blast da Activision, lançado originalmente em 1981 para o Atari 2600. O jogo foi desenvolvido utilizando conceitos de Programação Orientada a Objetos e a biblioteca gráfica Swing do Java.

### 🎯 Objetivo do Jogo

Controle uma nave espacial e destrua os tanques inimigos que surgem no terreno. Evite ser atingido pelos disparos inimigos e acumule a maior pontuação possível!

## ✨ Funcionalidades

- ✅ **Jogabilidade fiel** ao jogo original do Atari 2600
- ✅ **Sistema de pontuação** em tempo real
- ✅ **Ranking Top 10** com persistência de dados
- ✅ **Múltiplas ondas** de inimigos com dificuldade progressiva
- ✅ **Sistema de vidas** e game over
- ✅ **Interface gráfica** inspirada no design original da Activision

## 🕹️ Como Jogar

### Controles

| Tecla | Ação |
|-------|------|
| `↑` | Mover para cima |
| `↓` | Mover para baixo |
| `←` | Mover para esquerda |
| `→` | Mover para direita |
| `ESPAÇO` | Atirar |
| `ENTER` | Iniciar jogo / Confirmar |

### Pontuação

- **+100 pontos** por cada tanque destruído
- **+100 metros** de distância por onda completada
- **+1 ponto** por vida restante ao final

**Pontuação Total = Pontos + Distância + Vidas Restantes**

## 🚀 Como Executar

### Pré-requisitos

- Java JDK 8 ou superior
- IDE Java (IntelliJ IDEA, Eclipse, NetBeans) ou terminal

### Instalação

1. Clone o repositório:
bash
git clone https://github.com/seu-usuario/laser-blast-atari.git
cd laser-blast-atari


2. Compile o projeto:
bash
javac -d bin src/**/*.java

3. Execute o jogo:
bash
java -cp bin Telas.MainGame


### Executando pela IDE

1. Abra o projeto na sua IDE Java
2. Localize a classe `MainGame.java` em `src/Telas/`
3. Execute a classe (botão Run ou Shift+F10 no IntelliJ)

## 📁 Estrutura do Projeto

```
src/
├── Telas/
│   ├── MainGame.java           # Classe principal
│   ├── MenuCenario.java        # Tela de menu
│   ├── GameOverCenario.java   # Tela de game over
│   ├── Jogo.java               # Lógica do jogo
│   ├── RankingManager.java     # Gerenciador de ranking
│   └── PlayerScore.java        # Dados do jogador
├── Classes/
│   ├── Disco.java              # Nave do jogador
│   ├── Tanques.java            # Inimigos
│   ├── Laser.java              # Projéteis
│   └── Terreno.java            # Cenário
└── ElementosTextoUtil/
    ├── Elemento.java           # Classe base
    ├── Texto.java              # Renderização de texto
    ├── Cenario.java            # Sistema de cenas
    ├── MatUtil.java            # Funções matemáticas
    └── Util.java               # Utilitários
```

## 🎨 Paleta de Cores

Cores originais do Atari 2600:

- **Laranja**: `#F05524` (240, 85, 36) - Fundo do menu
- **Bege**: `#ECF0D5` (236, 240, 213) - Textos principais
- **Amarelo**: `#FFF212` (255, 242, 18) - Destaques
- **Preto**: `#373435` (55, 52, 53) - Fundo do game over
- **Verde**: `#3C4A1F` (60, 74, 31) - Terreno

## 💾 Sistema de Persistência

O jogo salva automaticamente o ranking dos 10 melhores jogadores em um arquivo `ranking.txt` no formato:

```
JOGADOR1;9999
JOGADOR2;8500
JOGADOR3;7200
```

## 🏆 Ranking

O ranking é exibido no menu principal e mostra:
- Nome do jogador (até 10 caracteres)
- Pontuação total
- Top 10 melhores jogadores em ordem decrescente

## 📚 Referências

Este projeto foi desenvolvido com base no livro:

> **A Lógica do Jogo: Recriando clássicos da história dos videogames**  
> Autor: Marcos Vinicius Bittencourt de Oliveira Santos

## 👨‍💻 Desenvolvimento

**Disciplina**: Programação Orientada a Objetos (T164)  
**Professor**: Gilson Pereira do Carmo Filho  
**Período**: 2025.2  
**Avaliação**: AV3

### Conceitos Aplicados

- ✅ Herança e Polimorfismo
- ✅ Encapsulamento
- ✅ Collections (ArrayList)
- ✅ Persistência de dados (I/O)
- ✅ Interface gráfica (Swing)
- ✅ Tratamento de eventos (KeyListener)
- ✅ Threads para game loop

## 📝 Licença

Este projeto foi desenvolvido para fins educacionais.

## 🎮 Screenshots

### Menu Principal
```
┌────────────────────────────────────┐
│         ACTIVISION presents        │
│                                    │
│          LASER BLAST               │
│      VIDEO GAME SIMULATOR          │
│                                    │
│  ╔════════════════════════════╗   │
│  ║  RANKING TOP 10            ║   │
│  ║  GILSON............9999    ║   │
│  ║  JOGADOR2..........8500    ║   │
│  ╚════════════════════════════╝   │
│                                    │
│      PRESS ENTER TO START          │
└────────────────────────────────────┘
### Gameplay
```
PONTOS: 500
DISTANCIA: 200M

        🛸 (Nave do jogador)

        ▪️  ▪️  ▪️ (Tanques inimigos)
▂▃▄▅▆▇███▇▆▅▄▃▂ (Terreno verde)
 
```

### Game Over
```
┌────────────────────────────────────┐
│          GAME OVER                 │
│        A TERRA VENCEU              │
│                                    │
│  PONTUAÇÃO...............500       │
│  DISTANCIA...............200M      │
│  VIDAS RESTANTES.........1         │
│                                    │
│  TOTAL...................701       │
│                                    │
│  [Digite seu nome e pressione      │
│   ENTER para salvar]               │
└────────────────────────────────────┘
```

## 📧 Contato

Para dúvidas sobre o projeto, entre em contato através do GitHub.
