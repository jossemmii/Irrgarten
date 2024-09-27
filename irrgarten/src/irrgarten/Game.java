
package irrgarten;
import irrgarten.GameState;
import irrgarten.GameCharacter;
import irrgarten.Directions;
import irrgarten.Dice;
import java.util.ArrayList;

public class Game {
    
    private static final int MAX_ROUNDS = 10;
    
    private int currentPlayerIndex;
    private String log;
    
    private ArrayList<Player> players;      
    private Player currentPlayer;
    private ArrayList<Monster> monsters;    
    private Labyrinth labyrinth;
    
    //AÑADIDO POR MI PARA AHORRAR TRABAJO(Los modifico dependiendo de como quiera que sea el juego)
    private static final int MAX_ROWS = 5;
    private static final int MAX_COLS = 5;
    private static final int EXIT_ROW = 4;
    private static final int EXIT_COL = 4;
    private static final int NUM_MONSTERS = 1;
    
    //Constructor
    public Game(int nplayers, boolean debug){
        
        if(debug){
            
            players = new ArrayList<>();
            Player player1 = new Player(Character.forDigit(0,2),1,1);
            Player player2 = new Player(Character.forDigit(1,3),1,1);
            player1.setPos(0, 0);
            player2.setPos(1, 1);
            labyrinth = new Labyrinth(5,5,3,3);
            labyrinth.putPlayer2D(-1,-1,0,0,player1);
            labyrinth.putPlayer2D(-1,-1,1,1,player2);
            players.add(player1);
            players.add(player2);
            this.currentPlayerIndex = 0;
            this.currentPlayer= players.get(currentPlayerIndex);
            this.log="";
            monsters = new ArrayList();
            
            this.configureLabyrinthDebug();   
        }
        else{
            players = new ArrayList<>();

            //Crear los jugadores y añadirlos al array
            /*for(int i = 0; i < nplayers; i++){
                players.add(new Player((char)i, Dice.randomIntelligence(), Dice.randomStrenght()));
            }*/

            players.add(new Player(Character.forDigit(0, 2), Dice.randomIntelligence(), Dice.randomStrenght()));
            players.add(new Player(Character.forDigit(1, 3), Dice.randomIntelligence(), Dice.randomStrenght()));

            //Determinar quien va a comenzar, y el jugador con el turno      
            this.currentPlayerIndex = Dice.whoStarts(nplayers);
            this.currentPlayer = players.get(currentPlayerIndex);

            this.log = "";

            //Inicializar el array de monstruos
            monsters = new ArrayList<>();

            //Instanciar laberinto
            labyrinth = new Labyrinth(MAX_ROWS,MAX_COLS,EXIT_ROW,EXIT_COL);            //Le doy los parámetros que yo quiera

            //Llamada al método que configura el laberinto
            this.configureLabyrinth();

            //Repartir jugadores por el laberinto
            labyrinth.spreadPlayers(players);    
        }
        
        
        
    }
    
    //Método que delega en el de haveAWinner e indica si hay un ganador
    public boolean finished(){
        if(labyrinth.haveAWinner()){
            return true;
        }
        else{
            return false;
        }
    }
    
    //Método para la P3                                      (Pendiente de Prueba)
    public boolean nextStep(Directions preferredDirection){
        
        log = "";       //Puede ser que no haga falta
        boolean dead = currentPlayer.dead();
        
        if(!dead){
            
            Directions direction = this.actualDirection(preferredDirection);
            
            if(direction != preferredDirection){
                this.logPlayerNoOrders();
            }
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if(monster == null){
                this.logNoMonster();
            }
            
            else{
                GameCharacter winner = this.combat(monster);
                this.manageReward(winner);
            }      
        }
        
        else{
            this.manageResurrection();
        }
        
        boolean endGame = this.finished();
        
        if(!endGame){
            this.nextPlayer();
        }
        
        return endGame;
    }
    
    //Método que genera instancia de GameState e integra la información de estado de juego  
    public GameState getGameState(){
        GameState infoJuego = new GameState(labyrinth.toString(), players.toString(), monsters.toString(), this.currentPlayerIndex, this.finished(), this.log);
        return infoJuego;
    }
    
    //Método que configura el laberinto añadiendo obstáculos y mosntruos
    //Los monstruos tienen que ser guardados en el contenedor de esta clase Monstruo
    //Metodo de randomPos es publico
    private void configureLabyrinth(){
        
        //Creo los monstruos que quiera para el laberinto
        Monster m1 = new Monster("Chaval", Dice.randomIntelligence(), Dice.randomStrenght());
        //Monster m2 = new Monster("Jezuz", Dice.randomIntelligence(), Dice.randomStrenght());
        //Monster m3 = new Monster("Godoy", Dice.randomIntelligence(), Dice.randomStrenght());
       
        //Añado los monstruos al vector de monstruos
        monsters.add(m1);
        //monsters.add(m2);
        //monsters.add(m3);
        
        //Los añado al laberinto (el método addMonster tiene en cuenta que la posición esté vacía, y dentro del laberinto)
        /*for(int i = 0; i < NUM_MONSTERS; i++){
            
            int row = Dice.randomPos(MAX_ROWS);
            int col = Dice.randomPos(MAX_COLS);
            
            labyrinth.addMonster(row, col, monsters.get(i));
            
        }*/
        
        labyrinth.addMonster(0, 0, m1);
        //labyrinth.addMonster(2, 1, m2);
        //labyrinth.addMonster(1, 1, m3);
        
        //Añado bloques de obstáculos al laberinto (hacer cuando haga P3)       RECORDAAAAAAR
        labyrinth.addBlock(Orientation.VERTICAL, 0, 0, 5);
        labyrinth.addBlock(Orientation.HORIZONTAL,4 , 1, 3);
    }
    
    //Metodo añadido por mi para el modo debug
    private void configureLabyrinthDebug(){
        Monster m1 = new Monster("Juan", 9.9f, 9.9f);
        monsters.add(m1);
        labyrinth.addMonster(4, 3, m1);
        labyrinth.addBlock(Orientation.HORIZONTAL, 4, 4, 1);
    }
    
    //Método que actualiza los dos atributos del jugador *current con el turno pasando al siguiente
    private void nextPlayer(){
        
        //Compruebo que haya jugadores
        if(players.size() > 0){
            
            int siguienteIndice = (this.currentPlayerIndex + 1) % (players.size());
            this.currentPlayer = players.get(siguienteIndice);
            
            this.currentPlayerIndex = siguienteIndice;
                    
        }
        
        else
            System.out.println("No hay jugadores disponibles");        
    }
    
    //Método para la P3                                                         (Pendiente de Prueba)
    private Directions actualDirection(Directions preferredDirection){
        
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        
        ArrayList<Directions> validMoves = new ArrayList<>();
        validMoves = labyrinth.validMoves(currentRow, currentCol);
        
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        
        return output;
    }
    
    //Método para la P3   Devuelve el ganador de un combate, y simula el combate  (Pendiente de prueba)
    private GameCharacter combat(Monster monster){
        
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while(!lose && rounds < MAX_ROUNDS){
            
            winner = GameCharacter.MONSTER;
            rounds++;
            
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            
            if(!lose){
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        
        this.logRounds(rounds, MAX_ROUNDS);
        
        return winner;
    }
    
    //Método para la P3     Gestiona el dar un premio al jugador que gana el combate    (Pendiente de prueba)
    private void manageReward(GameCharacter winner){
        
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            this.logPlayerWon();
        }
        else{
            this.logMonsterWon();
        }
    }
    
    //Método para la P3  Gestiona la resurreccion
    private void manageResurrection(){
        
        boolean resurrect = Dice.resurrectPlayer();
        
        if(resurrect){
            currentPlayer.resurrect();
            //Creo FuzzyPlayer
            FuzzyPlayer fuzzyPlayer = new FuzzyPlayer(this.currentPlayer);
            //Lo meto en la posición dónde estaba el jugador
            players.set(this.currentPlayerIndex , fuzzyPlayer);
            //Se igualan
            currentPlayer = fuzzyPlayer;
            this.logResurrected();
        }
        else{
            this.logPlayerSkipTurn();
        }
    }
    
    //Completar los logs
    
    private void logPlayerWon(){
        this.log += "El jugador " + this.currentPlayerIndex + " ha ganado el combate.\n";
    }
            
    private void logMonsterWon(){
        this.log += "El monstruo ha ganado el combate.\n";
    }
            
    private void logResurrected(){
        this.log += "El jugador ha sido resucitado.\n";
    }
    
    private void logPlayerSkipTurn(){
        this.log += "El jugador ha perdido el turno por estar muerto.\n";
    }
            
    private void logPlayerNoOrders(){
        this.log += "El jugador no ha seguido las instrucciones del jugador humano.\n";
    }
            
    private void logNoMonster(){
        this.log += "El jugador se ha movido a una celda vacía o no ha sido posible moverse.\n";
    }
    
    private void logRounds(int rounds, int max){
        this.log += "Se han producido " + rounds + " de " + max + " de combate.\n";
    }
    
    //GETTER USADO PARA PROBAR LA RESURRECCIÓN
    /*public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    */
}
