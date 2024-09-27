/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author josem
 */
public class TestP1 {
    public static void main(String[] args) {
    
        //Instancias de cada clase
        
        Shield s1 = new Shield(1,4);
        Shield s2 = new Shield(2,5);
        Shield s3 = new Shield(0,3);
        
        Weapon w1 = new Weapon(3,5);
        Weapon w2 = new Weapon(0,0);    
        Weapon w3 = new Weapon(1,2);
        
        //Supera los límites
        Weapon ww = new Weapon(4,6);
        Shield ss = new Shield(3,6);
        
        //100 veces cada método Dice para probabilidades
        
        int contador_true = 0;
        int contador_false = 0;
        for (int i = 0; i < 100; i++){
           Boolean resultado = Dice.discardElement(1);
           System.out.println(resultado); //Funciona 
           
           if(resultado == true){
               contador_true++;
           }
           else
               contador_false++;
        }
        
        System.out.println(contador_true);
        System.out.println(contador_false);
        
        /*System.out.println(Dice.randomPos(5));    //Funciona
        System.out.println(Dice.whoStarts(2)); //Funciona
        System.out.println(Dice.randomIntelligence()); //Funciona
        System.out.println(Dice.randomStrenght());    //Funciona
        System.out.println(Dice.resurrectPlayer());   //Funciona
        System.out.println(Dice.weaponsReward());     //Funciona
        System.out.println(Dice.shieldsReward());     //Funciona
        System.out.println(Dice.healthReward());      //Funciona
        System.out.println(Dice.weaponPower());       //Funciona
        System.out.println(Dice.shieldPower());       //Funciona
        System.out.println(Dice.usesLeft());          //Funciona
        System.out.println(Dice.intensity(20)); //Funciona
        
        System.out.println();
        System.out.println();
        System.out.println();*/
        
        System.out.println(Dice.randomPos(5));
        
        
      
    }
    
    
    
}
