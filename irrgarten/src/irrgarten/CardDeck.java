/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author josem
 * @param <T>
 */
public abstract class CardDeck<T> {
    
    private ArrayList<T> cardDeck;
    
    //Constructor
    public CardDeck(){
        cardDeck = new ArrayList<>();
    }
    
    //Métodos
    abstract protected void addCards();
    
    protected void addCard(T card){
        cardDeck.add(card);
    }
    
    public T nextCard(){
        if(cardDeck.isEmpty()){         //Si la baraja está vacía
            this.addCards();        //Se le añade contenido
            Collections.shuffle(cardDeck);  //Se baraja
        }
   
        T carta = cardDeck.getFirst();  //Se escoge la primera carta
        cardDeck.remove(carta);       //Se elimina
        
        return carta;                   //Se devuelve esta carta
    }
    
}
