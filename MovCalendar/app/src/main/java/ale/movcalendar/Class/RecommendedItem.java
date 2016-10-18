package ale.movcalendar.Class;

public class RecommendedItem {

    protected int imagen;;
    private String nombre, genero, año;

    public RecommendedItem (int imagen, String nombre, String genero, String año){
        this.imagen = imagen;
        this.nombre = nombre;
        this.genero = genero;
        this.año = año;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) { this.imagen = imagen; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAño() { return año; }

    public void setAño(String año) { this.año = año; }

    @Override
    public String toString() { return nombre + "\n" + genero; }
}
