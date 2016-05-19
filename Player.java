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
    private int numIntentos;

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
        numIntentos = 10;
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
        printNumIntentos();
    }

    /**
     * Metodo que imprime el numero de intentos restantes.
     */
    public void printNumIntentos(){
        if(numIntentos < 0)
            numIntentos = 0;
        System.out.println("Intentos restantes: " + numIntentos);
        System.out.println();
    }

    /**
     * Metodo que volvera a la calle anterior.
     */
    public void goToLastRoom(){
        if(!listaCalles.empty()){
            calleActual = listaCalles.pop();
            quitaIntento();
            printLocationInfo();
        }
        else{
            System.out.println("No se puede volver!");
        }
    }

    /**
     * Metodo que quita un intento al jugador y otro adicional por calle lenta, tambien imprime un mensaje.
     */
    public void quitaIntento(){
        numIntentos --;
        if(calleActual.getDobleIntento()){
            numIntentos--;
            System.out.println("*********Calle con problemas, 2 intentos menos*********");
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
            quitaIntento();
            printLocationInfo();
            System.out.println();
        }
    }

    /**
     * Metodo que añadira Items al jugador
     */
    public void takeItem(String descripcion){
        Item item = calleActual.buscarItem(descripcion);
        if(item != null && (pesoActual + item.getPeso()) < pesoMaximo && item.getDisponible()){
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

    /**
     * Metodo que muestra el inventario del jugador
     */
    public void showItems(){
        if(listaItems.size() != 0){
            System.out.println("==============");
            System.out.println("Tu inventario:");
            System.out.println("==============");
            for(int i = 0; i < listaItems.size(); i++){
                System.out.println((i+1) + ". " + listaItems.get(i).getDescripcion()
                    + ":   " + listaItems.get(i).getPeso() + "Kg");
            }
            System.out.println("");
        }
        else
            System.out.println("No tienes items en el inventario.");
    }

    /**
     * Metodo que devuelve el numero de intentos.
     */
    public int getNumIntentos(){
        return numIntentos;
    }

    /**
     * Metodo que permite dar un item a un tio de ojos saltones, acto seguido escogemos una calle aleatoria.
     */
    public void darItem(String description){
        if(listaItems.size() == 0)
            System.out.println("No tienes items en el inventario.");
        int i = 0;
        boolean encontrado = false;
        if(calleActual.getEspecial()){
            while(i < listaItems.size() && !encontrado){
                if(listaItems.get(i).getDescripcion().equals(description) && description.equals("Helado")){
                    pesoActual -= listaItems.get(i).getPeso();
                    listaItems.remove(listaItems.get(i));
                    encontrado = true;
                    System.out.println("Le has dado un item al tio... Zuuuuuuum!!");
                    calleActual = null;
                    listaCalles = new Stack<>();
                    numIntentos--;
                    fijarCalle(Game.randomRoom());
                    printLocationInfo();
                }
                
                i++;
            }
        }
        else
            System.out.println("No tengo a nadie a quien darle nada");
        if(!encontrado)
                    System.out.println("No tienes el item requerido");
    }
}
