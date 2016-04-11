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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
    private Room northWestExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
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
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southEast != null)
            southEastExit = southEast;
        if(northWest != null)
            northWestExit = northWest;
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
        if(direccion.equals("norte"))
            exitRoom = northExit;
        if(direccion.equals("este"))
            exitRoom = eastExit;
        if(direccion.equals("sureste"))
            exitRoom = southEastExit;
        if(direccion.equals("sur"))
            exitRoom = southExit;
        if(direccion.equals("oeste"))
            exitRoom = westExit;
        if(direccion.equals("noroeste"))
            exitRoom = northWestExit;
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
        if(northExit != null)
            direcciones = direcciones + " norte";
        if(eastExit != null)
            direcciones = direcciones + " este";
        if(southEastExit != null)
            direcciones = direcciones + " sureste";
        if(southExit != null)
            direcciones = direcciones + " sur";
        if(westExit != null)
            direcciones = direcciones + " oeste";
        if(northWestExit != null)
            direcciones = direcciones + " noroeste";
        return direcciones;
    }
}
