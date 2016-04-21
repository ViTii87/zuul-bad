
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private String descripcion;
    private float peso;
    private boolean disponible;

    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, float peso, boolean disponible)
    {
        this.descripcion = descripcion;
        this.peso = peso;
        this.disponible = disponible;
    }
    
    /**
     * Metodo que devuelve la descripcion del objeto
     */
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Metodo que devuelve el peso del objeto
     */
    public float getPeso(){
        return peso;
    }
    
    /**
     * Metodo que devuelve si el objeto esta disponible
     */
    public boolean getDisponible(){
        return disponible;
    }

}
