import java.util.Stack;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room calleActual;
    private Stack<Room> listaCalles;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        calleActual = null;
        listaCalles = new Stack<>();
    }

    /**
     * Metodo que nos permitira fijar una calle al jugador.
     */
    public void fijarCalle(Room calle){
        if(calleActual != null){
            listaCalles.push(calleActual);
        }
        calleActual = calle;
    }
    
     /**
     * Metodo que imprime la informacion de localizacion
     */
    public void printLocationInfo(){
        System.out.println(calleActual.getLongDescription());
        System.out.println();
    }
    
    /**
     * Metodo que volvera a la calle anterior.
     */
    public void goToLastRoom(){
        if(!listaCalles.empty()){
            calleActual = listaCalles.pop();
            printLocationInfo();
        }
        else{
            System.out.println("No se puede volver!");
        }
    }
    
     /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("A donde vamos?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = calleActual.getExit(direction);

        if (nextRoom == null) {
            System.out.println("No hay salida!");
        }
        else {
            listaCalles.push(calleActual);
            calleActual = nextRoom;
            printLocationInfo();
            System.out.println();
        }
    }
}
