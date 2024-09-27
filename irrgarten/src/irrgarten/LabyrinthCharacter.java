/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author josem
 */
public abstract class LabyrinthCharacter {
    
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    private static final int NADA = -1;
    
    //Constructor 1
    public LabyrinthCharacter(String name, float intelligence, float strength, float health){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        this.col = NADA;
        this.row = NADA;
    }
    
    //Constructor 2                     
    public LabyrinthCharacter(LabyrinthCharacter other){
        this.name = other.name;
        this.intelligence = other.intelligence;
        this.strength = other.strength;
        this.health = other.health;
        this.row = other.row;
        this.col = other.col;
    }
    
    //Método que devuelve true si el jugador o monstruo está muerto
    public boolean dead(){
        if(this.health == 0.0f){
            return true;
        }
        else{
            return false;
        }
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getCol(){
        return this.col;
    }
    
    protected float getIntelligence(){
        return this.intelligence;
    }
    
    protected float getStrength(){
        return this.strength;
    }
    
    protected float getHealth(){
        return this.health;
    }
    
    protected void setHealth(float health){
        this.health = health;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString(){
        return name + " , " + intelligence + " , " + strength + " , " + health + " , " + row + " , " + col;
    }
    
    protected void gotWounded(){
        this.health-=1;
    }
    
    public abstract float attack();
    
    public abstract boolean defend(float attack);
}
