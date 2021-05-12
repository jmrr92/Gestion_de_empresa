import java.util.List;

public class Cuenta {
    private double saldo;
    private Usuario usuario;
    private List<Gasto> gastos;
    private List<Ingreso> ingresos;

    public Cuenta(Usuario usuario) {
        this.usuario = usuario;
        this.saldo=0;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario=usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public double addIngresos(String description, double cantidad) {
        this.saldo=this.saldo+cantidad;
        return saldo;
    }

    public double addGastos(String description, double cantidad) {
        this.saldo=this.saldo-cantidad;
        return saldo;
    }
    @Override
    public String toString() {
        return "El saldo de tu cuenta es de " + this.saldo + "€";
    }
}
