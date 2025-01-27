package code;

import java.util.ArrayList;


class CPUPlayer
{


    private int numExploredNodes;

    private Mark Symbole;


    public CPUPlayer(Mark cpu){
        Symbole = cpu;
    }


    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }


    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        numExploredNodes = 0;

        ArrayList<String> caseDispo = getCaseDispo(board.getBoard());
        int[][] tableau = new int[caseDispo.size()][4];
        int compteur = 0;

        for(String unecase : caseDispo){
            int r = Integer.parseInt(unecase.split("-")[0]);
            int c = Integer.parseInt(unecase.split("-")[1]);
            board.play(new Move(r,c),Mark.X);

            Mark[][] tentative_board = board.getBoard();
            int score_de_X = minimax(tentative_board,false);

            board.play(new Move(r,c),Mark.EMPTY);

            if (score_de_X == 100) {
                tableau[compteur][0]=numExploredNodes;
                tableau[compteur][1]=r;
                tableau[compteur][2]=c;
                tableau[compteur][3]=100;
            } else if (score_de_X == 0) {
                tableau[compteur][0]=numExploredNodes;
                tableau[compteur][1]=r;
                tableau[compteur][2]=c;
                tableau[compteur][3]=0;

            } else{
                tableau[compteur][0]=numExploredNodes;
                tableau[compteur][1]=r;
                tableau[compteur][2]=c;
                tableau[compteur][3]=-100;
            }
            compteur++;
            numExploredNodes = 0;
        }
        return filtreMoiCa(tableau);

    }

    public int minimax(Mark[][] board, boolean isMax)
    {
        Board cobaille = new Board();
        cobaille.setBoard(board);
        numExploredNodes ++;
        if (cobaille.isGameFinish())
        {
            return cobaille.evaluate(Mark.X);
        }
        ArrayList<String> caseDispo = getCaseDispo(cobaille.getBoard());

        if(isMax){
            int valeur = -1000;
            for(String unecase : caseDispo){
                Move tentative = new Move(Integer.parseInt(unecase.split("-")[0]),
                                Integer.parseInt(unecase.split("-")[1]));
                cobaille.play(tentative,Mark.X);
                valeur =Math.max(valeur,
                        minimax(cobaille.getBoard(),false));
                cobaille.play(tentative,Mark.EMPTY);
            }
            return valeur;
        }
        else{
            int valeur = 1000;
            for(String unecase : caseDispo){
                Move tentative = new Move(Integer.parseInt(unecase.split("-")[0]),
                        Integer.parseInt(unecase.split("-")[1]));
                cobaille.play(tentative,Mark.O);
                valeur =Math.min(valeur,
                        minimax(cobaille.getBoard(),true));
                cobaille.play(tentative,Mark.EMPTY);
            }
            return valeur;
        }
    }


    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;
        ArrayList<String> caseDispo = getCaseDispo(board.getBoard());
        int[][] tableau = new int[caseDispo.size()][4];
        int compteur = 0;

        for(String unecase : caseDispo){
            int r = Integer.parseInt(unecase.split("-")[0]);
            int c = Integer.parseInt(unecase.split("-")[1]);
            board.play(new Move(r,c),Mark.X);

            Mark[][] tentative_board = board.getBoard();
            int score_de_X = minimaxAlphaBeta(tentative_board,false,Integer.MIN_VALUE, Integer.MAX_VALUE);

            board.play(new Move(r,c),Mark.EMPTY);

            if (score_de_X == 100) {
                tableau[compteur][0]=numExploredNodes;
                tableau[compteur][1]=r;
                tableau[compteur][2]=c;
                tableau[compteur][3]=100;
            } else if (score_de_X == 0) {
                tableau[compteur][0]=numExploredNodes;
                tableau[compteur][1]=r;
                tableau[compteur][2]=c;
                tableau[compteur][3]=0;

            } else{
                tableau[compteur][0]=numExploredNodes;
                tableau[compteur][1]=r;
                tableau[compteur][2]=c;
                tableau[compteur][3]=-100;
            }
            compteur++;
            numExploredNodes = 0;
        }
        return filtreMoiCa(tableau);
    }
    public int minimaxAlphaBeta(Mark[][] board, boolean isMax, int alpha, int beta)
    {
        Board cobaille = new Board();
        cobaille.setBoard(board);
        numExploredNodes++;

        if (cobaille.isGameFinish())
        {
            return cobaille.evaluate(Mark.X);
        }

        ArrayList<String> caseDispo = getCaseDispo(cobaille.getBoard());

        if (isMax)
        {
            int value = Integer.MIN_VALUE;

            for (String unecase : caseDispo)
            {
                Move tentative = new Move(Integer.parseInt(unecase.split("-")[0]),
                        Integer.parseInt(unecase.split("-")[1]));

                cobaille.play(tentative, Mark.X);

                value = Math.max(value, minimaxAlphaBeta(cobaille.getBoard(), false, alpha, beta));

                alpha = Math.max(alpha, value);

                cobaille.play(tentative, Mark.EMPTY);

                if (beta <= alpha)
                    break; // Beta cut-off
            }

            return value;
        }
        else
        {
            int value = Integer.MAX_VALUE;

            for (String unecase : caseDispo)
            {
                Move tentative = new Move(Integer.parseInt(unecase.split("-")[0]),
                        Integer.parseInt(unecase.split("-")[1]));

                cobaille.play(tentative, Mark.O);

                value = Math.min(value, minimaxAlphaBeta(cobaille.getBoard(), true, alpha, beta));

                beta = Math.min(beta, value);

                cobaille.play(tentative, Mark.EMPTY);

                if (beta <= alpha)
                    break; // Alpha cut-off
            }

            return value;
        }
    }

    public ArrayList<String> getCaseDispo(Mark[][] board){
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
        return caseDispo;
    }

    public ArrayList<Move> filtreMoiCa(int[][] tableau)
    {
        int compteur_victoire = 0;
        int compteur_defaite = 0;
        int compteur_null = 0;
        for (int i = 0 ; i<tableau.length ; i++) {
            if(tableau[i][3] == 100){
                compteur_victoire++;
            } else if (tableau[i][3] == -100) {
                compteur_defaite++;
            }else {
                compteur_null++;
            }
        }
//        for (int[] row : tableau) {
//            System.out.println(Arrays.toString(row));
//        }
        System.out.println("les donnés calculés:");
        System.out.println("================================== ");
        System.out.println("compteur_victoire "+ compteur_victoire );
        System.out.println("compteur_defaite "+  compteur_defaite );
        System.out.println("compteur_null "+  compteur_null );
        System.out.println("================================== ");


        ArrayList<Move> thats_it = new ArrayList<>();

        if(compteur_victoire==0&&compteur_null==0) {
            tabdecroissant(tableau);
        } else if (compteur_victoire>0 && compteur_null==0 && compteur_defaite>0) {
            tableau =donneMoiLeCheminDeLaVictoire(tableau);
            tabcroissant(tableau);
        } else if (compteur_victoire==0 && compteur_null>0 && compteur_defaite>0) {
            tableau =donneMoiLeCheminDuMatchNull(tableau);
            tabcroissant(tableau);
        } else
        {
            tabcroissant(tableau);
        }

//        for (int[] row : tableau) {
//            System.out.println(Arrays.toString(row));
//        }

        for (int i = 0 ; i< tableau.length ; i++) {
            thats_it.add(new Move(tableau[i][1],tableau[i][2]));
        }
        return thats_it;
    }

    private int[][] donneMoiLeCheminDuMatchNull(int[][] tableau) {
        ArrayList<String> tablito_liste = new ArrayList<String>();
        for (int i = 0; i < tableau.length ; i++){
            if (tableau[i][3] == 0){
                tablito_liste.add(tableau[i][0]+"-"+tableau[i][1]+"-"+tableau[i][2]+"-"+tableau[i][3]);
            }
        }
        int[][] tablito = new int[tablito_liste.size()][4];
        int i =0;
        for (String info:
             tablito_liste) {
            tablito[i][0]=Integer.parseInt(info.split("-")[0]);
            tablito[i][1]=Integer.parseInt(info.split("-")[1]);
            tablito[i][2]=Integer.parseInt(info.split("-")[2]);
            tablito[i][3]=Integer.parseInt(info.split("-")[3]);
            i++;
        }
        return tablito;
    }

    private int[][] donneMoiLeCheminDeLaVictoire(int[][] tableau) {
        ArrayList<String> tablito_liste = new ArrayList<String>();
        for (int i = 0; i < tableau.length ; i++){
            if (tableau[i][3] == 100){
                tablito_liste.add(tableau[i][0]+"-"+tableau[i][1]+"-"+tableau[i][2]+"-"+tableau[i][3]);
            }
        }
        int[][] tablito = new int[tablito_liste.size()][4];
        int i =0;
        for (String info:
                tablito_liste) {
            tablito[i][0]=Integer.parseInt(info.split("-")[0]);
            tablito[i][1]=Integer.parseInt(info.split("-")[1]);
            tablito[i][2]=Integer.parseInt(info.split("-")[2]);
            tablito[i][3]=Integer.parseInt(info.split("-")[3]);
            i++;
        }
        return tablito;
    }

    private int[][] tabdecroissant(int[][] tablito) {
        int n = tablito.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;

            // Trouver l'index du maximum dans la colonne [i][0]
            for (int j = i + 1; j < n; j++) {
                if (tablito[j][0] > tablito[maxIndex][0]) {
                    maxIndex = j;
                }
            }

            // Échanger les lignes
            int[] temp = tablito[i];
            tablito[i] = tablito[maxIndex];
            tablito[maxIndex] = temp;
        }
        return tablito;
    }

    private int[][] tabcroissant(int[][] tablito) {
        int n = tablito.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;

            // Trouver l'index du maximum dans la colonne [i][0]
            for (int j = i + 1; j < n; j++) {
                if (tablito[j][0] < tablito[maxIndex][0]) {
                    maxIndex = j;
                }
            }

            // Échanger les lignes
            int[] temp = tablito[i];
            tablito[i] = tablito[maxIndex];
            tablito[maxIndex] = temp;
        }
        return tablito;
    }



}
