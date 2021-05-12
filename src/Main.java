import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    //Para leer datos por teclado
    private final static Scanner scanner= new Scanner(System.in);
    //Variables que necesitaremos para nuestra clase Main
    private static int opcionElegida;
    private static String nombre;
    private static int edad;
    private static String dni;
    private static Usuario usuario;
    private static String description;
    private static double importe;
    private final static List <Ingreso> listaDeIngresos = new ArrayList<Ingreso>();
    private final static List <Gasto> listaDeGastos = new ArrayList<Gasto>();

    public static void main(String[] args) throws GastoException {
        //Invocamos el primer método que necesitamos, y si está correcto...
        if (introduceDatos()) {
            creaUsuario(); //Creamos el usuario

            //Creamos el objeto cuenta para almacenar todos nuestros datos
            Cuenta cuenta = new Cuenta(usuario);

            //Damos paso a nuestro menu de acceso para empezar a trabajar con el programa
            menuDeAcceso();

            do {
                switch (opcionElegida) {
                    case 1: //Introducir gasto
                        //Llamamos al método para introducir un gasto
                        if (introduceGasto()) {
                            //Si el importe sobrepasa el gasto que tenemos actualmente lanzaremos la excepción
                            if (importe > cuenta.getSaldo()) {
                                System.out.println(new GastoException());
                            } else {
                                //Añadimos el gasto y lo escribiremos en la lista
                                gasto();
                                cuenta.addGastos(description, importe);
                                cuenta.setGastos(listaDeGastos);
                            }
                        }
                        menuDeAcceso();
                        break;

                    case 2: //Introducir ingresos
                        if (introduceIngreso()) {//Llamamos al método
                            ingreso();
                            //Actualizamos el saldo y lo añadimos a la lista
                            cuenta.addIngresos(description, importe);
                            cuenta.setIngresos(listaDeIngresos);
                        }
                        menuDeAcceso();
                        break;

                    case 3: //Mostrar gastos
                        if (cuenta.getGastos() == null) { //Si no hay gastos introducidos
                            System.out.println("Aun no tienes gastos");
                        } else { //Si hay gastos, nos lo mostrará
                            System.out.println(cuenta.getGastos());

                        }
                        menuDeAcceso();
                        break;

                    case 4: //Mostrar ingresos
                        if (cuenta.getIngresos() == null) { //Si no hay ingresos introducidos
                            System.out.println("Aun no tienes ingresos.");
                        } else { //En caso contrario nos mostrará la información
                            System.out.println(cuenta.getIngresos());
                        }
                        menuDeAcceso();
                        break;

                    case 5: //Mostrar saldo
                        System.out.println(cuenta.toString());
                        menuDeAcceso();
                        break;

                    case 0: //Salida del programa y mensaje de despedida
                        System.out.println("Fin del programa.\nGracias por utilizar la aplicación.");
                }
                //No saldremos del bucle hasta que no elijamos una opción correcta
            } while (opcionElegida != 0 || opcionElegida > 5);

            scanner.close();
        }
    }

    private static boolean introduceDatos() {
        System.out.println("Introduce tu nombre de usuario: ");
        do {
            //Almacenamos el nombre en la variable nombre
            nombre = scanner.nextLine();
        } while (nombre.isEmpty());
        System.out.println("Hola " + nombre);

        System.out.println("Introduce tu edad: ");
        do {
            //Almacenamos la edad introducida en la variable edad
            edad = scanner.nextInt();
            if(edad<0){
                System.out.println("Introduce una edad mayor que 0 años.");
            }
        } while (edad <= 0);

        System.out.println("Introduce tu DNI: ");
        do {
            //Almacenamos la información en la variable dni
            dni = scanner.nextLine();
            //Pasaremos la letra del DNI a mayúsculas
            dni = dni.toUpperCase();
            //Por si el usuario no escribe nada, o escribe con o sin "-" su DNI
        } while (dni.isEmpty() || dni.length() < 9 || dni.length() > 10);
        return true;
    }

    private static void creaUsuario() {
        //Creamos un objeto de la clase usuario y asignamos los valores a sus setters
        usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEdad(edad);
        usuario.setDNI(dni);
        //Llamamos a la clase toString
        System.out.println(usuario);
    }

    private static void menuDeAcceso(){
        //Escribimos el menu de acceso en un solo "sout" para evitarnos la repetición de código
        System.out.println("Realiza una nueva acción:\n1 Introduce un nuevo gasto\n2 Introduce un nuevo ingreso" +
                "\n3 Mostrar gastos\n4 Mostrar ingresos\n5 Mostrar Saldo\n0 Salir");
        //Controlamos las opciones con un bucle do while hasta que se seleccione una opción correcta
        do {
            System.out.println("Escoja la opción deseada escribiendo su número:");
            opcionElegida=scanner.nextInt();
        }while(opcionElegida<0 || opcionElegida>5);
        //Limpieza de buffer
        scanner.nextLine();
    }

    private static boolean introduceGasto() {
        //Método para guardar los datos de la opción gastos que nos devolverá el valor true cuando sean correctos
        do {
            System.out.println("¿Para qué vas a usar el gasto?");
            description=scanner.nextLine();
        }while(description.isEmpty());

        do {
            System.out.println("Introduce el importe:");
            importe=scanner.nextDouble();
        }while(importe<=0);
        return true;
    }

    private static boolean introduceIngreso() {
        //Método para guardar los datos de la opción ingresos que nos devolverá el valor true cuando sean correctos
        do {
            System.out.println("Descripción del ingreso:");
            description=scanner.nextLine();
        }while(description.isEmpty());

        do {
            System.out.println("Introduce el importe:");
            importe=scanner.nextDouble();
        }while(importe<=0);
        return true;
    }

    private static void ingreso() {
        //Creamos el objeto ingreso
        Ingreso ingreso = new Ingreso(importe, description);
        //Le damos los valores antes recogidos a los setters
        ingreso.setDescription(description);
        ingreso.setDinero(importe);
        //Añadimos el ingreso a la lista
        listaDeIngresos.add(ingreso);
    }

    private static void gasto() {
        //Creamos el objeto gasto
        Gasto gasto=new Gasto(importe,description);
        //Le asignamos los valores a sus setters
        gasto.setDescription(description);
        gasto.setDinero(importe);
        //Añadimos el gasto a la lista
        listaDeGastos.add(gasto);
    }
}