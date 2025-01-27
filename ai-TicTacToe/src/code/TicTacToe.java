package code;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private Board BoardGame;
    private String algorithme;
    private CPUPlayer computer;

    public TicTacToe(boolean reponseJoueur,String algorithme) {
        BoardGame = new Board();
        computer = new CPUPlayer(Mark.X);
        System.out.println("Bonne chance");
        this.algorithme = algorithme;
        begin(reponseJoueur);
    }



    public void begin(boolean responseJoueur)
    {
        Mark[] players;
        if(responseJoueur){
            afficherTableau(BoardGame.getBoard());
            players = new Mark[]{Mark.O,Mark.X};
        }
        else{
            players = new Mark[]{Mark.X,Mark.O};
        }
        boolean game_statue = false;

        while (!game_statue) {
            for (Mark turn : players) {
                if (turn == Mark.O) {
                    humainPlay();
                } else {
                    BoardGame.play(getBetterPosition(algorithme), Mark.X);
                    afficherTableau(BoardGame.getBoard());
                }
                game_statue = BoardGame.isGameFinish();

                if (game_statue) {
                    break;
                }
            }
        }
        if(0 < BoardGame.evaluate(Mark.O))
        {
            System.out.println("Bravo tu as gagné");
        } else if (BoardGame.evaluate(Mark.O) < 0) {
            System.out.println("tu as perdu");
        }else
        {
            System.out.println( "Match null");
        }
    }

    private Move getBetterPosition(String option)
    {
        ArrayList<Move> bestMove = null;
        switch (option){
            case "MinMax":
                bestMove = computer.getNextMoveMinMax(BoardGame);
                break;
            case "MinMaxAlphaBeta":
                bestMove = computer.getNextMoveAB(BoardGame);
                break;
        }

        System.out.println("\nl'ordi réfléchis:");
        System.out.println("Hmm... je choisie: "+ bestMove.get(0).getRow()+"-"+bestMove.get(0).getCol());
        System.out.println(" ");
        return bestMove.get(0);
    }

    private Move getRandomPosition() {
        Mark[][] board = BoardGame.getBoard();
        ArrayList<String> caseDispo = new ArrayList<>();
        for(int i = 0 ; i < 3 ; i ++)
        {
            for(int j = 0 ; j < 3 ; j ++)
            {
                if(board[i][j] == Mark.EMPTY){
                    caseDispo.add((i+1)+"-"+(j+1));
                }
            }
        }
        Random rand = new Random();
        int indiceAleatoire = rand.nextInt(caseDispo.size());
        String Randomcase = caseDispo.get(indiceAleatoire);
        return new Move(Integer.parseInt(Randomcase.split("-")[0]),Integer.parseInt(Randomcase.split("-")[1]));
    }

    public void humainPlay()
    {
        int row = 0;
        int col = 0;

        boolean erreur_saisi ;
        do{
            Scanner scan = new Scanner(System.in);
            System.out.println("Entrez la case (ligne-collone):");
            String position = scan.nextLine();

            try{
                row = Integer.parseInt(position.split("-")[0]);
                col = Integer.parseInt(position.split("-")[1]);
                erreur_saisi = false;
                if(4<=row || 4<=col || row<0 || col<0)
                {
                    erreur_saisi = true;
                }
                if (BoardGame.getBoard()[row-1][col-1] != Mark.EMPTY)
                {
                    erreur_saisi = true;
                    System.out.println("case occupé.");
                }
            }catch (Exception e)
            {
                erreur_saisi = true;
            }
        }
        while (erreur_saisi);

        BoardGame.play(new Move(row,col),Mark.O);
    }


    public void afficherTableau(Mark[][] ll)
    {

        System.out.println("     1   |    2    |    3  ");
        for(int i = 0 ; i < 3 ; i ++)
        {
            System.out.println("  -------+---------+--------");
            String espace = "";
            for(int j = 0 ; j < 3 ; j ++)
            {
                if (ll[i][j] == Mark.O || ll[i][j] == Mark.X )
                {espace = "   ";}else {espace = " ";}
                if(j < 1)
                {
                    System.out.print((i+1)+" "+espace+ ll[i][j] +espace+"|");
                } else if (j==1) {
                    System.out.print(" "+espace+ ll[i][j] +espace+" |");
                } else {System.out.print(" "+espace+ ll[i][j] +espace+"");}
            }
            System.out.println("");

        }
    }
}
