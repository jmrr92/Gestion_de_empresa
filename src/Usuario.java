import java.util.Scanner;

public class Usuario {

    private String nombre;
    private int edad;
    private String DNI;

    public Usuario(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDNI() {
        return DNI;
    }

    public boolean setDNI(String DNI) {

        if(DNI.matches("^[0-9]{8}[a-zA-Z]$") || DNI.matches("^[0-9]{8}[-][a-zA-Z]$")) {
            this.DNI=DNI;
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public String toString() {
        return "Nombre de usuario: " + nombre + "\nEdad: " + edad + "\nDNI: " + DNI+"";
    }

}
