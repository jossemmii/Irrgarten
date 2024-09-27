/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author josem
 */
public class ShieldCardDeck extends CardDeck<Shield> {
    
    @Override
    protected void addCards(){
        Shield s1 = new Shield(Dice.shieldPower(), Dice.usesLeft());
        Shield s2 = new Shield(Dice.shieldPower(), Dice.usesLeft());
        Shield s3 = new Shield(Dice.shieldPower(), Dice.usesLeft());
        Shield s4 = new Shield(Dice.shieldPower(), Dice.usesLeft());
        Shield s5 = new Shield(Dice.shieldPower(), Dice.usesLeft());
        
        super.addCard(s1);
        super.addCard(s2);
        super.addCard(s3);
        super.addCard(s4);
        super.addCard(s5);
    }
}
