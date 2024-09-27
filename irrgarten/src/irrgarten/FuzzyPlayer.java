/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author josem
 */
public class FuzzyPlayer extends Player {
    
    //Constructor               
    public FuzzyPlayer(Player other){
        super(other);
    }
    
    //Método para moverse
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
      //1) Se calcula la dirección nueva como en la clase Player
      Directions nuevaDirection = super.move(direction, validMoves);
      //2)Se pasa lo anterior como primer parámetro de nuevo método Dice
      return Dice.nextStep(nuevaDirection, validMoves, this.getIntelligence());  
    }
    
    //Método para atacar
    @Override
    public float attack(){
        float sumaA = super.sumWeapons() + Dice.intensity(super.getStrength());
        return sumaA;
    }
    
    //Método para saber la energía de defensa
    @Override
    protected float defensiveEnergy(){
        float sumaD = Dice.intensity(super.getIntelligence()) + super.sumShields();
        return sumaD;
    }
    
    //Método toString
    @Override
    public String toString(){
        return "Fuzzy " + super.toString();
    }
    
    
}
