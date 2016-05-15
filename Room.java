import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{

    private String description;
    private HashMap<String, Room> salidas;
    private ArrayList<Item> listaItems;
    private boolean dobleIntento;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, boolean dobleIntento) 
    {
        this.description = description;
        salidas = new HashMap<String, Room>();
        listaItems = new ArrayList<>();
        this.dobleIntento = dobleIntento;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Metodo que si devuelve true es que es una calle que restara 2 intentos al jugador.
     */
    public boolean getDobleIntento(){
        return dobleIntento;
    }

    /**
     * Metodo que devuelve la salida de la habitacion 
     */
    public Room getExit(String direccion){
        Room exitRoom = null;
        exitRoom = salidas.get(direccion);
        return exitRoom;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String direcciones = "";
        Iterator it = salidas.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry e = (Map.Entry)it.next();
            if(e.getValue() != null)
                direcciones = direcciones + e.getKey() + " ";
        }
        return direcciones;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor){
        salidas.put(direction, neighbor);
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        String descripcion = "";
        descripcion = "Estas " + description + "\nSalidas: " + getExitString();
        if(listaItems.size()!=0){
            descripcion += "\n\nHay estos item en la calle: ";
            for(Item itemCalle : listaItems){
                descripcion += "\n" + itemCalle.getDescripcion() + ", " + "Peso: " + itemCalle.getPeso();
            }
        }
        else{
            descripcion += "\nNo hay ningun item en esta calle.";
        }
        return descripcion;
    }
    
    /**
     * Metodo que nos permite añadir un Item a la calle.
     */
    public void addItem(Item item){
        listaItems.add(item);
    }
    
    /**
     * Metodo que buscara un item en la habitacion
     */
    public Item buscarItem(String descripcion){
        int i = 0;
        boolean encontrado = false;
        Item item = null;
        while(i < listaItems.size() && !encontrado){
            if(listaItems.get(i).getDescripcion().equals(descripcion)){
                item = listaItems.get(i);
                encontrado = true;
            }
            i++;
        }
        return item;
    }
    
    /**
     * Meotodo que elimina un item de una calle
     */
    public void eliminaItemCalle(Item item){
        int i = 0;
        boolean encontrado = false;
        while(i < listaItems.size() && !encontrado){
            if(listaItems.get(i).getDescripcion().equals(item.getDescripcion())){
                listaItems.remove(listaItems.get(i));
                encontrado = true;
            }
            i++;
        }
    }
}
