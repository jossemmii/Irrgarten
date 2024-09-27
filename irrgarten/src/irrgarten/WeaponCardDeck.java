/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author josem
 */
public class WeaponCardDeck extends CardDeck<Weapon> {
    
    @Override
    protected void addCards(){
        
        Weapon w1 = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        Weapon w2 = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        Weapon w3 = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        Weapon w4 = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        Weapon w5 = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        
        super.addCard(w1);
        super.addCard(w2);
        super.addCard(w3);
        super.addCard(w4);
        super.addCard(w5);
    }
}
