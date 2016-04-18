
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

    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, float peso)
    {
        this.descripcion = descripcion;
        this.peso = peso;
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

}
