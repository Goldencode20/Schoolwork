import java.util.Scanner;

class Sudoku {
    private static final int EMPTY = -1;

    private final int dim;
    private final int sdim;
    private final int[][] cells;
    private int backtracking;

    public Sudoku (int[][] cells) {
        this.dim = cells.length;
        this.sdim = (int)Math.sqrt(dim);
        this.cells = cells;
        this.backtracking = 0;
    }

    int getBacktracking () { return backtracking; }

    public boolean isBlank (int row, int col) { //TODO
        if (cells[col][row] == EMPTY) {return true;} else {return false;}
    }

    public boolean isValid (int val, int row, int col) {//TODO
        if(!isBlank(row, col)){ return false; }
        if(checkRow(val, row) && checkCol(val, col) && checkBlock(val, row, col)){ return true; }
        return false;
    }

    private boolean checkRow (int val, int row) { //TODO
        for (int i = 0; i < dim; i++){
            if(cells[i][row] == val) { return false; }
        }
        return true;
    }

    private boolean checkCol (int val, int col) { //TODO
        for (int i = 0; i < dim; i++){
            if(cells[col][i] == val) { return false; }
        }
        return true;
    }

    private boolean checkBlock (int val, int row, int col) { //TODO
        for(int i = (row/sdim) * sdim; i < ((row/sdim) * sdim) + sdim; i++) {
            for(int j = (col/sdim) * sdim; j < ((col/sdim) * sdim) + sdim; j++) {
                if(cells[j][i] == val) { return false; }
            }
        }
        return true;
    }

    public boolean solve () {//TODO
        return tryColumn(0);
    }

    private boolean tryColumn (int col) {
        if(col < dim) { return tryCell(col, 0); }
        return true;
    }

    private boolean tryCell (int col, int row) { //TODO
        if(row >= dim) { return tryColumn(col + 1); }
        if(isBlank(row, col)) {
            for(int i = 1; i <= dim; i++){
                if(isValid(i, row, col)){
                    cells[col][row] = i;
                    boolean temp = tryCell(col, row + 1);
                    if (temp) { return true; }
                    backtracking++;
                    cells[col][row] = EMPTY;
                }
            }
        } else {
            return tryCell(col, row + 1);
        }
        return false;
        //TODO try a cell then continue on from that cell
    }
    
    public String toString () {
        StringBuilder res = new StringBuilder();
        for (int j=0; j<dim; j++) {
            if (j % sdim == 0) res.append("――――――――――――――――――――――\n");
            for (int i=0; i<dim; i++) {
                if (i % sdim == 0) res.append("│");
                int c = cells[i][j];
                res.append(c == EMPTY ? "." : c);
                res.append(" ");
            }
            res.append("│\n");
        }
        res.append("――――――――――――――――――――――\n");
        return res.toString();
    }

    //------------------------------------------------------------

    public static Sudoku read (Scanner s) {
        int dim = s.nextInt();
        int[][] cells = new int[dim][dim];
        for (int j = 0; j < dim; j++)
            for (int i = 0; i < dim; i++) {
                String c = s.next();
                cells[i][j] = c.equals(".") ? EMPTY : Integer.parseInt(c);
            }
        return new Sudoku(cells);
    }
}
