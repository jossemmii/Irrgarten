
package irrgarten;
import java.util.ArrayList;
import java.util.Random;

/*Actua como dado, pero no proporciona algo tan simple como un numero del
1 al 6, sino que toma decisiones en base a la aletoriedad.
*/

public class Dice {
    
    //Atributos de clase
    private static final int MAX_USES = 5;              //Max uso de armas y escudos
    private static final float MAX_INTELLIGENCE = 10.0f; //Max inteligencia para jugadores y monstruos
    private static final float MAX_STRENGTH = 10.0f;    //Max fuerza para jugadores y monstruos
    private static final double RESURRECT_PROB = 0.3;   //Prob de resurreccion de un jugador en cada turno      //PREGUNTAR SI ES DOUBLE
    private static final int WEAPONS_REWARD = 2;        //Max armas recibidas al ganar un combate
    private static final int SHIELDS_REWARD = 3;        //Max escudos recibidos al ganar un combate
    private static final int HEALTH_REWARD = 5;         //Max unidades de salud recibidas al ganar un combate
    private static final int MAX_ATTACK = 3;            //Max potencia de las armas
    private static final int MAX_SHIELD = 2;            //Max potencia de los escudos
    
    //Atributo de clase que generará aleatorios
    private static Random generator = new Random();
    
    //Método para devolver una columna o fila aleatoria, siendo la primera 0
    //El tablero tiene que tener minimo 2 columnas o 2 filas, o más, sino,
    //dará error.
    public static int randomPos(int max){
        
        if(max >= 2){
            max = generator.nextInt(max);
        }
        else{
            System.err.println("Numero invalido de filas o columnas");
        }
        
        return max;
    }
    
    //Metodo que devuelve el indice del jugador que comienza, siendo el
    //primero el numero 0. Tiene que haber 2 jugadores o mas
    public static int whoStarts(int nplayers){
        
        if(nplayers >= 2){
            nplayers = generator.nextInt(nplayers);
        }
        else{
            System.err.println("Numero invalido de jugadores");
        }
        return nplayers;
    }
    
    //Metodo que devuelve un numero aleatorio entre [0,MAX_INTELLIGENCE)
    public static float randomIntelligence(){
        float inteligencia = generator.nextFloat() * MAX_INTELLIGENCE;
        return inteligencia;
    }
    
    //Metodo que devuelve un numero aleatorio entre [0,MAX_STRENGTH)
    public static float randomStrenght(){
        float fuerza = generator.nextFloat() * MAX_STRENGTH;
        return fuerza;
    }
    
    //Metodo que indica si un jugador muerto debe resucitar o no                    //Probar método
    public static boolean resurrectPlayer(){                                        
        double resurreccion = generator.nextDouble();
        return(resurreccion <= RESURRECT_PROB);
    }
    
    //Metodo que indica la cantidad de armas que recibe el jugador por ganar
    //un combate. El aleatorio estará en [0,WEAPONS_REWARD]                          //??DUDA EN TODOS LOS DE GANA UN COMBATE¿¿
    public static int weaponsReward(){
        int cantidadArmas = generator.nextInt(WEAPONS_REWARD + 1);
        return cantidadArmas;
    }
    
    //Metodo que indica la cantidad de escudos que recibe el jugador por ganar
    //un combate. El aleatorio estará en [0,SHIELDS_REWARD]
    public static int shieldsReward(){
        int cantidadEscudos = generator.nextInt(SHIELDS_REWARD + 1);
        return cantidadEscudos;
    }
    
    //Metodo que indica la cantidad de unidades de salud que recibe el jugador 
    //por ganar un combate. El aleatorio estará en [0,SHIELDS_REWARD]
    public static int healthReward(){
        int cantidadSalud = generator.nextInt(HEALTH_REWARD + 1);
        return cantidadSalud;
    }
    
    //Valor aleatorio entre [0,MAX_ATTACK) ataque
    public static float weaponPower(){
        float poderArma = generator.nextFloat() * MAX_ATTACK;
        return poderArma;
    }
    
    //Valor aleatorio entre [0,MAX_SHIELD) escudos
    public static float shieldPower(){
        float poderEscudo = generator.nextFloat() * MAX_SHIELD;
        return poderEscudo;
    }
    
    //Metodo que devuelve el número de usos para un arma o escudo. Intervalo de
    //[0,MAX_USES]
    public static int usesLeft(){
        int usosRestantes = generator.nextInt(MAX_USES + 1);
        return usosRestantes;
    }
    
    //Metodo que devuelve la cantidad de competencia aplicada. Intervalo de
    //[0, competence)
    public static float intensity(float competence){                                   
        float cantidadCompetencia = generator.nextFloat() * competence;
        return cantidadCompetencia;
    }
    
    //Este método devolverá true con una probabilidad inversamente proporcional
    //a lo cercano que esté usesLeft del MAX_USES. Cuanto más cerca, más probabi-       
    //lidad de devolver true
    public static boolean discardElement(int usesLeft){
        
        if(usesLeft == MAX_USES){
            return false;
        }
            else if(usesLeft == 0){
                return true;
            }
            
        else{
            //Declaro un array estático inicializado a 50 falses
            boolean[] probab = new boolean[50];
            
            //Calculo cuántos trues va a tener el array. EJ: si usesLeft = 3,
            //5-3 = 2, por lo que 2*10 = 20, habrá 20 trues
            int nTrues = (MAX_USES - usesLeft) * 10;
            
            //Introduzco en el array, los trues, de forma aleatoria
            while(nTrues > 0){
                //Genero la posicion en la que introducir el true
                int posAleatoria = generator.nextInt(50);
                //Si la posición contiene un false, introducimos un true
                if(probab[posAleatoria] == false){
                    probab[posAleatoria] = true;
                    nTrues--;           //Y decrementamos el nTrues a introducir
                }
            }
            
            //Devolvemos un resultado al azar del array
            return probab[generator.nextInt(50)];
        }   
            
    }
    
    //Nuevo método de la P4                                                     (Pendiente de prueba)
    public static Directions nextStep(Directions preference, ArrayList<Directions> validMoves, float intelligence){
       
        float probabilidad = generator.nextFloat();
        
        if (probabilidad < intelligence){
            return preference;
        }
        else{
            int aleatorio = generator.nextInt(validMoves.size());
            return validMoves.get(aleatorio);
        }  
    }
 
}
