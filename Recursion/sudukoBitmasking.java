import java.util.*;
class sudukoBitmasking{
    public static void main(String[] args) {
        sudukoFn2();
    }
    static int rows[];
    static int cols[];
    static int mat[][];
    public static void sudukoFn2(){
        rows=new int[9];
        cols=new int[9];
        mat=new int[3][3];
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
                }else{
                    int val = board[i][j];
                    int mask= (1 << val);
                    rows[i] ^=mask;
                    cols[j] ^=mask;
                    mat[i/3][j/3] ^=mask;
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
            if( (rows[r] & (1<<num))==0 && (cols[c] & (1<<num))==0 && (mat[r/3][c/3] & (1<<num))==0 ){
                board[r][c]=num;
                rows[r] ^= (1<<num);
                cols[c] ^= (1<<num);
                mat[r/3][c/3] ^=(1<<num);
                count+=sudukoSolver2(board,idx+1,board2d);
                rows[r] ^= (1<<num);
                cols[c] ^= (1<<num);
                mat[r/3][c/3] ^=(1<<num);
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
 }
