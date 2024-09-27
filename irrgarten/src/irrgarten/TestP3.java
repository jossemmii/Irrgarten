/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author josem
 */
public class TestP3 {
    public static void main(String[] args) {
        Player p1 = new Player('a', 2, 5);
        Labyrinth l1 = new Labyrinth(5,5,4,4);
        p1.setPos(4, 2);
        System.out.println(l1.toString());
        
        
    }
    
}
