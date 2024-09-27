/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author josem
 */
public abstract class CombatElement{
    
    private float effect;
    private int uses;
    private static final float nadaF = 0.0f;       //Para evitar número mágico
    
    //Constructor
    public CombatElement(float effect, int uses){
        this.effect = effect;
        this.uses = uses;
    }
    
    protected float produceEffect(){                //Hace lo que attack y defend
        if (effect > 0){
            this.uses = uses - 1;
            return effect;
        }
        else{
            return nadaF;
        }
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    public String toString(){
        return "[ " + this.effect + " , " + this.uses + " ]";
    }
    
}
