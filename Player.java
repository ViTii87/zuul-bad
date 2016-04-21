import java.util.Stack;
import java.util.ArrayList;
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
    private ArrayList<Item> listaItems;
    private float pesoMaximo;
    private float pesoActual;

    /**
     * Constructor for objects of class Player
     */
    public Player(float pesoMaximo)
    {
        calleActual = null;
        listaCalles = new Stack<>();
        listaItems = new ArrayList<>();
        this.pesoMaximo = pesoMaximo;
        pesoActual = 0;
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

    /**
     * Metodo que añadira Items al jugador
     */
    public void takeItem(String descripcion){
        Item item = calleActual.buscarItem(descripcion);
        if(item != null && (pesoActual + item.getPeso()) < pesoMaximo){
            listaItems.add(item);
            pesoActual += item.getPeso();
            calleActual.eliminaItemCalle(item);
        }
        else{
            System.out.println("No se ha podido añadir el item");
        }
    }
    
    /**
     * Metodo que soltara el item en la habitacion
     */
    public void dropItem(String descripcion){
        int i = 0;
        boolean encontrado = false;
        while(i < listaItems.size() && !encontrado){
            if(listaItems.get(i).getDescripcion().equals(descripcion)){
                calleActual.addItem(listaItems.get(i));
                pesoActual -= listaItems.get(i).getPeso();
                listaItems.remove(listaItems.get(i));
                encontrado = true;
                System.out.println("Item soltado!");
            }
            i++;
        }
        if(!encontrado)
            System.out.println("No tengo nada que tirar");
    }
}
