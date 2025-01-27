package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Board
{
    private Mark[][] board;

    public Board() {
        board = viderTableau();
    }
    public Mark[][] viderTableau()
    {
        Mark[][] board_empty = new Mark[3][3];
        for(int i = 0 ; i < 3 ; i ++)
        {
            for(int j = 0 ; j < 3 ; j ++)
            {
                board_empty[i][j] = Mark.EMPTY;
            }
        }
        return board_empty;
    }

    public Mark[][] getBoard() {
        return board;
    }

    public void setBoard(Mark[][] board) {
        this.board = board;
    }


    public void play(Move m, Mark mark){
        board[m.getRow()-1][m.getCol()-1] = mark;
    }

    public boolean isGameFinish()
    {
        int case_vide=0;
        for(int i = 0 ; i < 3 ; i ++)
        {
            for(int j = 0 ; j < 3 ; j ++)
            {
                if(this.board[i][j] == Mark.EMPTY){
                    case_vide++;
                }
            }
        }

        if(case_vide != 0 && evaluate(Mark.X) == 0 && evaluate(Mark.O) == 0)
        {
            return false;
        }
        else
        {
            return true;
        }

    }


    public int evaluate(Mark mark){
        int i =0;
        int gagnant = 0;
        List<List<String>> winscore = getWiningList();
        for ( List<String>  l:winscore){
            if(getPositionof(Mark.X).containsAll(l)){
                gagnant = 1;
            }
            if(getPositionof(Mark.O).containsAll(l)){
                gagnant = -1;
            }
        }
        if(mark == Mark.X && gagnant == 1 )
        {
            i = 100;
        }else if(mark == Mark.O && gagnant == -1 )
        {
            i = 100;
        }
        else if(gagnant != 0)
        {
            i = -100;
        }
        return i;
    }

    private ArrayList<String> getPositionof(Mark mark)
    {
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0 ; i < 3 ; i ++)
        {
            for(int j = 0 ; j < 3 ; j ++)
            {
                if(this.board[i][j] == mark){
                    list.add((i+1)+"-"+(j+1));
                }
            }
        }
        return list;
    }
    private List<List<String>> getWiningList()
    {
        List<String> topRow = Arrays.asList("1-1","1-2","1-3");
        List<String> MidRow = Arrays.asList("2-1","2-2","2-3");
        List<String> botRow = Arrays.asList("3-1","3-2","3-3");

        List<String> leftcol = Arrays.asList("1-1","2-1","3-1");
        List<String> Midcol = Arrays.asList("1-2","2-2","3-2");
        List<String> Rightcol = Arrays.asList("1-3","2-3","3-3");

        List<String> cross1 = Arrays.asList("1-1","2-2","3-3");
        List<String> cross2 = Arrays.asList("3-1","2-2","1-3");


        List<List<String>> winning = new ArrayList<List<String>>();
        winning.add(topRow);
        winning.add(MidRow);
        winning.add(botRow);
        winning.add(leftcol);
        winning.add(Midcol);
        winning.add(Rightcol);
        winning.add(cross1);
        winning.add(cross2);

        return winning;
    }
}
