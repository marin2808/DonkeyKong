# Donkey Kong: Jogo em Java

Jogo inspirado no clássico **Donkey Kong**, desenvolvido em Java com programação orientada a objetos. O jogador controla o **Manel**, que tem de atravessar três salas cheias de perigos, desviar-se de obstáculos, escapar ao Donkey Kong e chegar à porta para resgatar a princesa — o mais depressa possível.

> Projeto desenvolvido no âmbito da unidade curricular **Programação Orientada por Objetos**, 2024/2025.

## Funcionalidades

- **3 níveis** carregados a partir de ficheiros de texto (`rooms/room0.txt` a `room2.txt`), fáceis de editar ou de expandir.
- Personagem com **barra de vida** (100 pontos) que sofre dano e pode recuperar.
- **Inimigos e perigos:** Donkey Kong (lança bananas), morcegos, armadilhas visíveis e **armadilhas escondidas**.
- **Itens:**
  - Espada: aumenta o ataque do Manel.
  - Carne: recupera vida, mas **estraga-se com o tempo** e passa a causar dano.
  - Bomba: pode ser apanhada e plantada; explode ao fim de poucos *ticks* ou ao contacto, destruindo o que está à volta.
- Escadas, portas e queda por gravidade.
- **Ranking** dos melhores tempos, guardado em ficheiro e mostrado no fim do jogo.
- Se o Manel perder toda a vida, o jogo recomeça no primeiro nível.

## Controlos

| Tecla | Ação |
|---|---|
| Setas do teclado | Mover o Manel (incluindo subir e descer escadas) |
| `B` | Plantar a bomba (depois de a apanhar) |

## Conceitos de POO aplicados

- **Herança e polimorfismo:** hierarquia `GameElement` → `MovingElement` → `Bat`, `Banana`, `DonkeyKong`, `Manel`...
- **Interfaces:** `Interactable`, `Attackable`, `Timer`.
- **Padrão Observer:** `Observer` / `Observed` ligam a interface gráfica ao motor de jogo.
- **Padrão Singleton:** `GameEngine` e `ImageGUI`.

## Tecnologias

- Java (JDK 17 ou superior recomendado)
- Eclipse (projeto Eclipse incluído)
- Pacote `pt.iscte.poo.gui` (`ImageGUI`), fornecido pela unidade curricular para a parte gráfica

## Estrutura do projeto

```
DonkeyKong/
├── images/                 # sprites e imagens
├── rooms/                  # níveis (room0.txt, room1.txt, room2.txt)
├── teste.txt               # ranking de tempos
└── src/
    ├── objects/            # elementos do jogo (Manel, Bat, Bomb, Sword...)
    └── pt/iscte/poo/
        ├── game/           # Main, GameEngine, Room, Player, Ranking, Time
        ├── gui/            # ImageGUI, ImageTile
        ├── observer/       # Observer, Observed
        └── utils/          # Point2D, Vector2D, Direction, Ops2D
```

## Como executar

Corre os comandos **a partir da pasta raiz do projeto**, porque as pastas `images/` e `rooms/` são lidas com caminhos relativos.

### Com Eclipse

1. *File > Import > Existing Projects into Workspace* e seleciona a pasta.
2. Corre a classe `pt.iscte.poo.game.Main`.

### Pela linha de comandos

```bash
# Linux / macOS
javac -d bin $(find src -name "*.java")
java -cp bin pt.iscte.poo.game.Main

# Windows (PowerShell)
javac -d bin (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp bin pt.iscte.poo.game.Main
```

Ao iniciar, o jogo pede o teu nome para o registar no ranking.

## Autores

- **Vicu Mindru**
- **Marin Mindru**

## Licença

Projeto académico.
