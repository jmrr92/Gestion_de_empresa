public class Ingreso extends Dinero {

    public Ingreso(double ingreso, String description) {
        this.dinero=ingreso;
        this.description=description;
    }
    @Override
    public String toString() {
        return "Ingreso de " + this.dinero + "€ en concepto de, " + this.getDescription() + ".";

}
}