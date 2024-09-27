
package irrgarten;
import irrgarten.Orientation;
import irrgarten.Directions;
import java.util.ArrayList;

public class Labyrinth {
    
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;
    
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    private char[][] labyrinth;
    private Monster[][] monsters;
    private Player[][] players;
    
    //Constructor
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        this.monsters = new Monster[nRows][nCols];
        this.players = new Player[nRows][nCols];
        this.labyrinth = new char[nRows][nCols];
        
        //Inicializo el laberinto a casillas vacías, y coloco la casilla de salida
        if (exitRow >= 0 && exitRow < labyrinth.length && exitCol >= 0 &&
                exitCol < labyrinth[0].length){
            
            for(int i = 0; i < nRows; i++){
                for(int j = 0; j < nCols; j++){
                    labyrinth[i][j] = EMPTY_CHAR;
                }
            }
            
            labyrinth[exitRow][exitCol] = EXIT_CHAR;
        }
        
    }
    
    //Método para la P3                                 (Pendiente de prueba)
    public void spreadPlayers(ArrayList<Player> players){
        
        for(Player p : players){
            int[] pos = this.randomEmptyPos();
            this.putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }
    }
    
    //Método que devuelve true si hay un jugador en la posición de exit , comprobar Null en la matriz de players
    public boolean haveAWinner(){
        
        if(this.players[exitRow][exitCol] == null){
            return false;
        }
        else
            return true;
        
    }
    
    //Método que representa el estado del laberinto
    public String toString(){
        String representacion = "";
        for(int i = 0; i < nRows; i++){
            for(int j = 0; j < nCols; j++){
                representacion += "  " + labyrinth[i][j];
            }
            representacion += "\n";
        }
        return representacion;
    }
    
    //Método para añadir un Monstruo al juego(posición dentro del laberinto, que esté vacía). 
    public void addMonster(int row, int col, Monster monster){
       if (this.posOk(row, col) && this.emptyPos(row, col)){
           labyrinth[row][col] = MONSTER_CHAR;
           monsters[row][col] = monster;
           monster.setPos(row, col);
       } 
    }
    
    //Método para la P3     LO USA GAME                             (Pendiente de prueba)
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        
        int[] newPos = this.dir2Pos(oldRow, oldCol, direction);
        Monster monster = this.putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        
        return monster;
    }
    
    //Método para la P3     LO USA GAME                                (Pendiente de prueba)
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        
        int incRow;
        int incCol;
        
        if(orientation == Orientation.VERTICAL){
            incRow = 1;
            incCol = 0;
        }
        else{
            incRow = 0;
            incCol = 1;
        }
        
        int row = startRow;
        int col = startCol;
        
        while(this.posOk(row, col) && this.emptyPos(row, col) && length > 0){
            labyrinth[row][col] = BLOCK_CHAR;
            
            length -= 1;
            row+=incRow;
            col+=incCol;
        }
    }
    
    //Método para la P3     LO USA GAME                         (Pendiente de prueba)
    public ArrayList<Directions> validMoves(int row, int col){
        
        ArrayList<Directions> output = new ArrayList<>();
        
        if(this.canStepOn(row + 1, col)){
            output.add(Directions.DOWN);
        }
        
        if(this.canStepOn(row-1, col)){
            output.add(Directions.UP);
        }
        
        if(this.canStepOn(row, col + 1)){
            output.add(Directions.RIGHT);
        }
        
        if(this.canStepOn(row, col - 1)){
            output.add(Directions.LEFT);
        }
        
        return output;
    }
    
    //Método para comprobar que una posición está dentro del laberinto
    private boolean posOk(int row, int col){
        if((row >= 0 && col >= 0) && (row < nRows && col < nCols)){
            return true;
        }
        else{
            return false;
        }
    }
    
    //Método que comprueba si una posición está vacía
    private boolean emptyPos(int row, int col){
        return ((labyrinth[row][col] == EMPTY_CHAR));
    }
    
    //Método que comprueba si en una posición SOLO hay un monstruo
    private boolean monsterPos(int row, int col){
        if(this.posOk(row, col) && this.labyrinth[row][col] == MONSTER_CHAR){
            return true;
        }
        else
            return false;
    }
    
    //Método que comprueba si en una casilla es la de salida
    private boolean exitPos(int row, int col){
        if(this.posOk(row, col) && labyrinth[row][col] == EXIT_CHAR){
            return true;
        }
        else
            return false;
    }
    
    //Método que comprueba si en una casilla hay un jugador y un monstruo peleando
    private boolean combatPos(int row, int col){
        if(this.posOk(row, col) && labyrinth[row][col] == COMBAT_CHAR){
            return true;
        }
        else
            return false;
    }
    
    //Método que devuelve true si el jugador puede avanzar(exit,monster,empty,posok)
    private boolean canStepOn(int row, int col){
        if(this.posOk(row, col) && (this.emptyPos(row, col)
                || (this.monsterPos(row, col)) || (this.exitPos(row, col)))){
            return true;
        }
        else
            return false;
    }
    
    //Método que se encarga de dejar la casilla en estado correcto cuando el jugador la abandona
    private void updateOldPos(int row, int col){
        
        if(this.posOk(row, col) && this.combatPos(row, col)){
            labyrinth[row][col] = MONSTER_CHAR;
            players[row][col] = null;
        }
        else if(this.posOk(row, col)){
            labyrinth[row][col] = EMPTY_CHAR;
            players[row][col] = null;
        }
        
        
    }
    
    //Método para calcular la posición que se  alcanzaría en base a los parámetros
    private int[] dir2Pos(int row, int col, Directions direction){
        
        int[] newPos = new int [2];
        
        if(direction == Directions.UP){
            newPos[ROW] = row - 1;
            newPos[COL] = col;
        }
        else if(direction == Directions.DOWN){
            newPos[ROW] = row + 1;
            newPos[COL] = col;
        }
        else if(direction == Directions.LEFT){
            newPos[ROW] = row;
            newPos[COL] = col - 1;
        }
        else if(direction == Directions.RIGHT){
            newPos[ROW] = row;
            newPos[COL] = col + 1;
        }
        
        return newPos;
        
    }
    
    //Método que calcula una posición aleatoria y comprueba que esté vacía
    private int[] randomEmptyPos(){
        
        int pos[] = new int [2];
        
        do{
            
            pos[ROW] = Dice.randomPos(this.nRows);
            pos[COL] = Dice.randomPos(this.nCols);
            
            if(this.emptyPos(pos[ROW], pos[COL])){
                return pos;
            }
            
        } while (true);
        
    }
    
    //Método para la P3                            Lo pongo publico para poder usarlo
    public Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        Monster output = null;
        
        if(this.canStepOn(row, col)){
            
            if(this.posOk(oldRow, oldCol)){
                Player p = players[oldRow][oldCol];
                //Aquí uso el equals
                if(p.equals(player)){
                    this.updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
            
            boolean monsterPos = this.monsterPos(row, col);
            
            if(monsterPos){
                labyrinth[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            }
            else{
                char number = player.getNumber();
                labyrinth[row][col] = number;
            }
            
            players[row][col] = player;
            player.setPos(row, col);
            
        }
        
        return output;
    }
    
}
