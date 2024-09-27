
package irrgarten;

/*Esta clase representa los escudos que utiliza el jugador cuando
se defiende del ataque de un monstruo
*/

public class Shield extends CombatElement {
    
    //Atributos de instancia privados
    private float protection;                    //Nivel de protección, maximo 2
    private int uses;                            //Usos del escudo, maximo 5
    private static final float nadaF = 0.0f;       //Para evitar número mágico
    
    //Constructor
    public Shield(float protection, int uses){
       super(protection, uses);
    }
    
    //Método de instancia para protegerse y modificar
    //Si aún hay usos, se quita uno, y se devuelve la protección
    public float protect(){
        return super.produceEffect();                 //Usa el método de la clase padre
    }
    
    //Metodo que determina si un escudo debe ser usado o descartado
    //public boolean discard(){
        //return Dice.discardElement(uses);
    //}
    
    
    //Método toString para devolver información sobre la defensa
    @Override
    public String toString(){
        return "S " + super.toString();
    }
}
