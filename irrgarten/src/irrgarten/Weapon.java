
package irrgarten;

/*Esta clase representa las armas que utiliza el jugador
durante los combates que tiene
*/

public class Weapon extends CombatElement {
    
    //Atributos de instancia
    private float power;                         //Poder del arma, Maximo es 3
    private int uses;                            //Usos del arma, Maximo es 5
    private static final float nadaF = 0.0f;       //Para evitar número mágico
    
    //Constructor
    public Weapon(float power, int uses){
        super(power, uses);
    }
    
    //Método de instancia para atacar y modificar
    //Si aún hay usos, se quita uno, y se devuelve el poder
    public float attack(){
      return super.produceEffect();                   //Usa el método de la clase padre
    }
    
    //Metodo que determina si un arma debe ser usada o descartada
    //public boolean discard(){
        //return Dice.discardElement(uses);
    //}
    
    
    
    
    //Método toString para devolver información sobre el ataque
    @Override
    public String toString(){
        return "W " + super.toString();
    }
    
    
    
    
    
    
    
}
