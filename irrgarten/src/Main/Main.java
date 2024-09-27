/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import irrgarten.Controller.*;
import irrgarten.*;
import irrgarten.UI.*;
import irrgarten.controller.Controller;

/**
 *
 * @author josem
 */
public class Main {

    public static void main(String[] args) {
         //Main para jugar de forma normal
        UI vista = new GraphicalUI();
        Game game = new Game(2,false);
        Controller controlador = new Controller(game, vista);
        
        controlador.play();
        vista.showGame(game.getGameState());
        
        
        //Main para probar modo debug
        //Game game = new Game(2,true);
        //TextUI vista = new TextUI();
        //Prueba combate
        /*vista.showGame(game.getGameState());
        game.nextStep(Directions.DOWN);
        vista.showGame(game.getGameState());*/
        
        //Prueba resurreccion
        /*vista.showGame(game.getGameState());
        game.nextStep(Directions.DOWN);
        vista.showGame(game.getGameState());
        game.getCurrentPlayer().setHealth(0);
        for(int i=0; i<10; i++){
            game.nextStep(Directions.LEFT);
            vista.showGame(game.getGameState());
        }
        */
        
    }
    
            
}
