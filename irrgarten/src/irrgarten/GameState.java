
package irrgarten;

/*Esta clase permite almacenar el estado completo del juego: del laberinto,
jugadores, monstruos, indice del jugador que tiene elturno, indicador sobre si
hay un ganador, y un atributo adicional para guardar en una cadena de caracteres
eventos interesantes que hayan ocurrido desde el turno anterior
*/
public class GameState {
    
    //Atributos de instancia
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;      //indice jugador actual
    private boolean winner;
    private String log;
    
    //Constructor
    public GameState(String labyrinth, String players, String monsters, 
     int currentPlayer, boolean winner, String log){
        this.labyrinth = labyrinth;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }
    
    //Consultores

    public String getLabyrinth() {
        return labyrinth;
    }

    public String getPlayers() {
        return players;
    }

    public String getMonsters() {
        return monsters;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isWinner() {
        return winner;
    }

    public String getLog() {
        return log;
    }
    
    
}
