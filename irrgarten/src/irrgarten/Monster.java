
package irrgarten;
import irrgarten.Dice;

public class Monster extends LabyrinthCharacter {
    
    private static final int INITIAL_HEALTH = 5;
    private static final int NO_EXISTE = -1;    //Atributo añadido por mi
    
    /*private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;*/
    
    //Constructor
    public Monster(String name, float intelligence, float strength){
        super(name, intelligence, strength, INITIAL_HEALTH);
    }
    
    /*Método que devuelve true si el monstruo está muerto
    public boolean dead(){
        
        if(this.health == 0.0f){
            return true;
        }
        else{
            return false;
        }
        
    }*/
    
    //Método que representa el valor del ataque
    @Override
    public float attack(){
        
        float intensidad = Dice.intensity(super.getStrength());
        return intensidad;
        
    }
    
    //Método para la P3     LO USA GAME
    @Override
    public boolean defend(float receivedAttack){
        
        boolean isDead = this.dead();
        
        if(!isDead){
            float defensiveEnergy = Dice.intensity(super.getIntelligence());
            
            if(defensiveEnergy < receivedAttack){
                this.gotWounded();
                isDead = this.dead();
            }
        }
        
        return isDead;
    }
    
    
    /*Método para asignar valor a la columna y fila dónde estará el monstruo
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }*/
    
    /*Método toString para representar estado del mosntruo
    public String toString(){
        return "Monstruo " + "[ " + "nombre: " + name + " , " + "fuerza: " +
                strength + " , " + "vida: " + health + " , " + "inteligencia: "
                + intelligence + " , " + "columna: " +
                col + " , " + "fila: " + row + " ]";
    }*/
    
    /*Método que decrementa en uno la salud del monstruo
    private void gotWounded(){
        this.health -= 1;
    }*/

}
