package code;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean arreter = false;
        String reponse = "";
        TicTacToe game = debuterLeJeu();
        while(!arreter){
            System.out.println("Voulez-vous recommencer la partie ? (o/n)");
            Scanner scanner = new Scanner(System.in);
            reponse = scanner.nextLine();
            if (reponse.equals("o")){game = debuterLeJeu();}
            else if (reponse.equals("n")){ arreter=true;}
            else {System.out.println("une erreur s'est produite sur le choix  (o ou n)");}
        }
    }
    public static TicTacToe debuterLeJeu(){
        TicTacToe game = null;
        System.out.println("Bienvenue sur TicTacToe");
        System.out.println("voulez vous commencer en premier ? (o/n)");

        boolean commencer = false;
        boolean lejoueurCommence=true;
        String reponse = "";
        while(commencer != true){
            Scanner scanner = new Scanner(System.in);
            reponse = scanner.nextLine();
            if (reponse.equals("o")){commencer=true;}
            else if (reponse.equals("n")){lejoueurCommence=false;commencer=true;}
            else {System.out.println("une erreur s'est produite sur le choix  (o ou n)");}
        }

        System.out.println("Entrez le choix d'algorithme (1 ou 2): ");
        System.out.println("1. MiniMax ");
        System.out.println("2. MiniMax - Alpha Beta ");

        boolean optionChoisie = false;
        String reponseOption = "";
        while(optionChoisie != true){
            Scanner scanner = new Scanner(System.in);
            reponseOption = scanner.nextLine();
            if (reponseOption.equals("1")){game=new TicTacToe(lejoueurCommence,"MinMax");optionChoisie=true;}
            else if (reponseOption.equals("2")){game=new TicTacToe(lejoueurCommence,"MinMaxAlphaBeta");optionChoisie=true;}
            else {System.out.println("une erreur s'est produite sur le choix  (1 ou 2)");}
        }

        return game;
    }

}