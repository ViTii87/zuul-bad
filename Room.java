import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    
    private String description;
    private HashMap<String, Room> salidas;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<String, Room>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room southEast, Room south, Room west, Room northWest) 
    {
        salidas.put("norte", north);
        salidas.put("este", east);
        salidas.put("sur", south);
        salidas.put("oeste", west);
        salidas.put("sureste", southEast);
        salidas.put("noroeste", northWest); 
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
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
}
