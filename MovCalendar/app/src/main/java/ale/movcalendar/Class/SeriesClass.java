package ale.movcalendar.Class;

public class SeriesClass {

    private String nombre, genero, temporada, descripcion;

    public SeriesClass(String nombre, String genero, String temporada, String descripcion) {
        this.nombre = nombre;
        this.genero = genero;
        this.temporada  = temporada;
        this.descripcion = descripcion;
    }

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

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
