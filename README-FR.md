# AI-TicTacToe

## Description

Ce projet est un jeu de Tic-Tac-Toe qui peut être joué entre un humain et un ordinateur. L'ordinateur peut jouer en utilisant deux algorithmes différents : Minimax et Minimax avec élagage Alpha-Beta. Le jeu est implémenté en Java et permet au joueur de commencer la partie ou de laisser l'ordinateur commencer, avec la possibilité de choisir l'algorithme utilisé par l'IA.

---

## Fonctionnalités

- **Deux Algorithmes IA** :
    - **Minimax** : Un algorithme de prise de décision qui évalue tous les coups possibles et sélectionne celui avec le meilleur score.
    - **Minimax avec élagage Alpha-Beta** : Une version optimisée de l'algorithme Minimax avec un élagage pour réduire le nombre de nœuds évalués.

- **Gameplay Humain vs IA** : Les joueurs peuvent choisir s'ils veulent jouer en tant que 'X' ou 'O' et sélectionner qui commencera la partie.

- **Représentation Graphique** : Le plateau de jeu est affiché après chaque coup pour montrer l'état actuel de la partie.

- **Mode de Jeu Personnalisable** : Les joueurs peuvent choisir si la partie commence avec eux ou avec l'ordinateur et sélectionner quel algorithme l'ordinateur utilisera.

- **Reprise de Jeu** : Après la fin d'une partie, l'utilisateur a l'option de redémarrer la partie.

---

## Prérequis

- Java 8 ou supérieur.
- Un IDE (Environnement de Développement Intégré) comme IntelliJ IDEA, Eclipse, ou un éditeur de texte comme Visual Studio Code.

---

## Utilisation

1. **Exécution du Jeu** :
    - Compilez les fichiers Java et exécutez la classe `Main.java` pour démarrer le jeu.
    - Suivez les invites à l'écran pour décider si vous voulez commencer la partie, quel algorithme utiliser et si vous voulez redémarrer la partie après chaque manche.

2. **Gameplay** :
    - Les joueurs devront choisir s'ils veulent commencer la partie. Ensuite, ils sélectionneront l'algorithme pour l'IA :
        - **Option 1** : Algorithme Minimax.
        - **Option 2** : Algorithme Minimax avec élagage Alpha-Beta.
    - Le jeu demandera au joueur de faire un coup et affichera le plateau après chaque tour. La partie se termine lorsqu'il y a un gagnant ou lorsque le plateau est complet.

3. **Rejouer** :
    - Après la fin d'une partie, les joueurs peuvent choisir de rejouer ou de quitter.

---

## Structure du Projet

```
├── Board.java           # Gère le plateau de jeu et évalue l'état de la partie.
├── CPUPlayer.java       # Implémente la logique pour le joueur CPU, utilisant les algorithmes Minimax ou Alpha-Beta.
├── Main.java            # Point d'entrée pour le jeu, gère l'entrée utilisateur et l'initialisation du jeu.
├── Mark.java            # Enum pour représenter les marques sur le plateau : X, O ou VIDE.
├── Move.java            # Représente un coup avec une ligne et une colonne.
├── TicTacToe.java       # Gère le déroulement de la partie et les tours des joueurs.
└── README.md            # Ce fichier.
```

---

## Avertissements

- Ce jeu ne gère pas actuellement les entrées invalides de manière robuste. Si une entrée invalide est donnée (par exemple, saisir une lettre alors que le jeu attend un nombre), le jeu peut planter ou se comporter de manière inattendue. Des améliorations futures pourraient inclure la validation des entrées.

- La prise de décision de l'IA est gourmande en ressources, surtout lorsque le plateau est presque plein. La performance peut être optimisée davantage pour des grilles plus grandes ou des algorithmes plus complexes.
