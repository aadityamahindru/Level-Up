class crossWord{
    public static void main(String[] args) {
        crossword();
    }
    public static void crossword(){
        char[][] board = {
            {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
            {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
            {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
            {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
            {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
            {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
            {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
            {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
            {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
            {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};
    
        String[] words = {"agra", "norway", "england", "gwalior"};
        System.out.println(crossword_(board,words,0));
    }
    public static int crossword_(char board[][],String[] words,int wIdx){
        if(wIdx==words.length){
            display(board);
            return 1;
        }
        String word=words[wIdx];
        int count=0;
        for(int r=0;r<board.length;r++){
            for(int c=0;c<board[0].length;c++){
                if(board[r][c]=='-'||board[r][c]==word.charAt(0)){
                    if(canPlaceH(board,r,c,word)){
                        boolean loc[]=placeH(board,r,c,word);
                        count+=crossword_(board,words,wIdx+1);
                        unPlaceH(board,r,c,word,loc);
                    }
                    if(canPlaceV(board,r,c,word)){
                        boolean loc[]=placeV(board,r,c,word);
                        count+=crossword_(board,words,wIdx+1);
                        unPlaceV(board,r,c,word,loc);
                    }
                }
            }
        }
        return count;
    }
    public static boolean canPlaceH(char board[][],int r,int c,String word){
        for(int i=0;i<word.length();i++){
            if(c+i>=board[0].length||(board[r][c+i]!='-'&&board[r][c+i]!=word.charAt(i)))
                return false;
        }
        return true;
    }
    public static boolean[] placeH(char board[][],int r,int c,String word){
        boolean loc[]=new boolean[word.length()];
        for(int i=0;i<word.length();i++){
            if(board[r][c+i]=='-'){
                board[r][c+i]=word.charAt(i);
                loc[i]=true;
            }
        }
        return loc;
    }
    public static void unPlaceH(char board[][],int r,int c,String word,boolean loc[]){
        for(int i=0;i<word.length();i++){
            if(loc[i]){
                board[r][c+i]='-';
            }
        }
    }
    public static boolean canPlaceV(char board[][],int r,int c,String word){
        for(int i=0;i<word.length();i++){
            if(r+i>=board.length||(board[r+i][c]!='-'&&board[r+i][c]!=word.charAt(i)))
                return false;
        }
        return true;
    }
    public static boolean[] placeV(char board[][],int r,int c,String word){
        boolean loc[]=new boolean[word.length()];
        for(int i=0;i<word.length();i++){
            if(board[r+i][c]=='-'){
                board[r+i][c]=word.charAt(i);
                loc[i]=true;
            }
        }
        return loc;
    }
    public static void unPlaceV(char board[][],int r,int c,String word,boolean loc[]){
        for(int i=0;i<word.length();i++){
            if(loc[i]){
                board[r+i][c]='-';
            }
        }
    }
    public static void display(char board[][]){
        for(char row[]: board){
            for(char col:row){
                System.out.print(col+" ");
            }
            System.out.println();
        }
    }
}