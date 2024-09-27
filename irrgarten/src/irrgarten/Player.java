
package irrgarten;

import irrgarten.Directions;
import java.util.ArrayList;

public class Player extends LabyrinthCharacter {
    
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    private static final int NADA = 0;
    
    private char number;
    private int consecutiveHits; 
    
    //private String name;
    //private float intelligence;
    //private float strength;S
    //private float health;
    //private int row;
    //private int col;
    
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    //Añadidos por la P4
    WeaponCardDeck weaponCardDeck;
    ShieldCardDeck shieldCardDeck;
    
    public Player(char number, float intelligence, float strength){
        
        super("Player #" + number, intelligence, strength, INITIAL_HEALTH);
        this.number = number;
        this.consecutiveHits = NADA;
        
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        
        shieldCardDeck = new ShieldCardDeck();
        weaponCardDeck = new WeaponCardDeck();
    }
    
    //Constructor 2                 
    public Player(Player other){
        super(other);
        this.number = other.number;
        this.consecutiveHits = other.consecutiveHits;
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        
         shieldCardDeck = new ShieldCardDeck();
        weaponCardDeck = new WeaponCardDeck();
        
    }
    
    
    //Método para que el jugador cuando resucite, aparezca sin armas ni escudos,
    //con vida inicial, y con impactosconsecutivos a 0
    public void resurrect(){
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        super.setHealth(INITIAL_HEALTH);
        this.consecutiveHits = NADA;
    }
    
    /*Consultor para fila
    //public int getRow(){
        return this.row;
    }
    
    //Consultor para columna
    public int getCol(){
        return this.col;
    }*/
    
    //Consultor para numero de jugador
    public char getNumber(){
        return this.number;
    }
    
    /*Modificador de columna y fila
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }*/
    
    /*Método para ver si el jugador está muerto
    public boolean dead(){
        
        if(this.health == NADA){
            return true;
        }
        else{
            return false;
        }
        
    }*/
    
    //Método para la P3     LO USA GAME                             (
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if(size > 0 && !contained){
            Directions firstElement = validMoves.get(0);
            return firstElement;
        }
        else{
            return direction;
        }
        
    }
    
    //Suma de la fuerza del jugador y de sumWeapons
    @Override
    public float attack(){
        float sumaA = super.getStrength() + this.sumWeapons();
        return sumaA;
    }
    
    //Metodo cuyo resultado delega en manageHit
    @Override
    public boolean defend(float receivedAttack){
        boolean defensa = this.manageHit(receivedAttack);
        return defensa;
    }
    
    //Método para la P3     LO USA GAME                            
    public void receiveReward(){
        
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for (int i=1; i<=wReward; i++){
            Weapon wnew = this.newWeapon();
            this.receiveWeapon(wnew);
        }
        
        for (int j=1; j<=sReward; j++){
            Shield snew = this.newShield();
            this.receiveShield(snew);
        }
        
        int extraHealth = Dice.healthReward();
        super.setHealth(super.getHealth()+extraHealth);
    }
    

    
    //Método para ver estado del objeto
    @Override
    public String toString(){
        return "Player [ "  + super.toString() +  weapons.toString() + " , " + shields.toString() + " ]";
    }
    
    //Método para la P3                                     
    private void receiveWeapon(Weapon w){
        
        /*for(Weapon wi : weapons){
            boolean discard = wi.discard();
            
            if(discard){
                weapons.remove(wi);
            }
        }*/
        
        for(int i = weapons.size()-1; i >= 0;i--){
            Weapon wi = weapons.get(i);
            boolean discard = wi.discard();
            
            if(discard){
                weapons.remove(wi);
            }
        }
         
        int size = weapons.size();
        
        if(size < MAX_WEAPONS){
            weapons.add(w);
        }
    }
    
    //Método para la P3                                     
    private void receiveShield(Shield s){
        
        /*for(Shield si : shields){
            
            boolean discard = si.discard();
            
            if(discard){
                shields.remove(si);
            }
        }*/
        
        for(int i = shields.size()-1; i >= 0;i--){
            Shield si = shields.get(i);
            boolean discard = si.discard();
            
            if(discard){
                shields.remove(si);
            }
        }        
        
        int size = shields.size();
        
        if(size < MAX_SHIELDS){
            shields.add(s);
        }
        
    }
    
    //Método que genera un arma, pidiendo los parámetros al dado, y se añade a su array                   
    private Weapon newWeapon(){
        Weapon arma = weaponCardDeck.nextCard();
        weapons.add(arma);
        
        return arma;
    }
    
    //Método que genera un escudo, pidiendo los parámetros al dado, y se añade a su array                    
    private Shield newShield(){
        Shield escudo = shieldCardDeck.nextCard();
        shields.add(escudo);
        
        return escudo;
    }
    
    //Método que suma el resultado del método attack de todas las armas
    protected float sumWeapons(){
        float sumaW = 0f;
        for(Weapon weapon : weapons){
            sumaW += weapon.attack();
        }
        
        return sumaW;
    }
    
    //Método que suma el resultado del método protect de todos los escudos
    protected float sumShields(){
        float sumaS = 0f;
        for(Shield shield : shields){
            sumaS += shield.protect();
        }
        
        return sumaS;
    }
    
    //Método que suma la inteligencia y sumShields
    protected float defensiveEnergy(){
        float sumaE = super.getIntelligence() + this.sumShields();
        return sumaE;
    }
    
    //Método para P3
    private boolean manageHit(float receivedAttack){
        float defense = this.defensiveEnergy();
        boolean lose;
        
        if(defense < receivedAttack){
            super.gotWounded();
            this.incConsecutiveHits();
        }
        else{
            this.resetHits();
        }
        
        if((consecutiveHits == HITS2LOSE) || (super.dead())){
            this.resetHits();
            lose = true;
        }
        else{
            lose = false;
        }
        
        return lose;
    }
    
    //Método para fijar el contador de impactos a 0
    private void resetHits(){
        this.consecutiveHits = NADA;
    }
    
    /*Método para decrementar en 1 la salud del jugador
    private void gotWounded(){
        this.health -= 1;
    }*/
    
    //Método para incrementar en 1 el contador de impactos consecutivos
    private void incConsecutiveHits(){
        this.consecutiveHits += 1;
    }
    
    //SETTER USADO PARA PROBAR LA RESURRECCIÓN
    /*public void setHealth(int health){
        this.health = health;
    }
    */
    
    //Sobreescribo el método equals por problema de direcciones de memoria
    //En putPlayer2D se utiliza
    public boolean equals(Player player){
        return player.number == this.number;
    }
}
