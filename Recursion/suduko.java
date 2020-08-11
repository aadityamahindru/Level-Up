import java.util.*;
class suduko{
    public static void main(String[] args) {
        sudukoFn2();
    }
    public static void sudukoFn(){
        int[][] board = {{3, 0, 0, 6, 0, 0, 0, 9, 2},  
                      {5, 2, 0, 0, 0, 0, 4, 0, 8},  
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},  
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},  
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},  
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},  
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},  
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},  
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}};  
        System.out.println(sudukoSolver(board,0));
    }
    public static boolean isSafePlace(int r,int c,int board[][],int num){
        //row
        for(int i=0;i<9;i++){
            if(board[i][c]==num){
                return false;
            }
        }
        //col
        for(int j=0;j<9;j++){
            if(board[r][j]==num){
                return false;
            }
        }
        //3X3 matrix check
        r=(r/3)*3;
        c=(c/3)*3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[r+i][c+j]==num){
                    return false;
                }
            }
        }
        return true;
    }
    public static int sudukoSolver(int board[][],int idx){
        if(idx==81){
            display(board);
            return 1;
        }
        int r=idx/9;
        int c=idx%9;
        if(board[r][c]!=0)
            return sudukoSolver(board,idx+1);
        int count=0;
        for(int num=1;num<=9;num++){
            if(isSafePlace(r,c,board,num)){
                board[r][c]=num;
                count+=sudukoSolver(board,idx+1);
                board[r][c]=0;
            }
        }
        return count;
    }
    public static void display(int board[][]){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //  optimized processing non empty places only
    public static void sudukoFn2(){
        int[][] board = {{3, 0, 0, 6, 0, 0, 0, 9, 2},  
                      {5, 2, 0, 0, 0, 0, 4, 0, 8},  
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},  
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},  
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},  
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},  
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},  
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},  
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}}; 
        ArrayList<Integer> board2d=new ArrayList<>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]==0){
                    board2d.add((i*9)+j);
                }
            }
        } 
        System.out.println(sudukoSolver2(board,0,board2d));
    }
    public static int sudukoSolver2(int board[][],int idx,ArrayList<Integer> board2d){
        if(idx==board2d.size()){
            display(board);
            return 1;
        }
        int rcIdx=board2d.get(idx);
        int r=rcIdx/9;
        int c=rcIdx%9;
        int count=0;
        for(int num=1;num<=9;num++){
            if(isSafePlace(r,c,board,num)){
                board[r][c]=num;
                count+=sudukoSolver(board,idx+1);
                board[r][c]=0;
            }
        }
        return count;
    }
}