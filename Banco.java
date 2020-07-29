import java.util.Scanner;
import Cuenta.Cuenta;

public class Banco {
    public static void main(String[] args)
    {
        Cuenta Cuenta = new Cuenta(1, false);
        Scanner myObj = new Scanner(System.in);

        boolean response;
        String Option = "0";
        label:
        while (Cuenta.sesiones > 0) {
            switch (Option) {
                case "0": {
                    System.out.println("\n Bienvenido a Banco Azul, selecciona operación a realizar:");
                    System.out.println("\"1\" Depósito\n\"2\" Retiro \n\"3\" Ver transacciones\n\"4\" Cerrar sesión");
                    Option = myObj.nextLine();  // Read user input
                    break;
                }
                case "1": {
                    if (Cuenta.check_operations()) {
                        System.out.println("deposito formato moneda[dolar/peso]/dinero  (ejem. dolar/20):");
                        String deposito = myObj.nextLine();  // Read user input

                        String[] arr_money = deposito.split("/", 2);
                        if (arr_money.length == 2) {
                            response = Cuenta.depositar(arr_money[0], Integer.parseInt(arr_money[1]));
                            if (response) {
                                System.out.println("---Depósito exitoso---");
                            } else {
                                System.out.println("No se pudo realizar la transacción. Déposito fallido");
                            }
                        } else {
                            System.out.println("Error de formato");
                        }
                    } else {
                        System.out.println("----------------Alcanzaste el límite de operaciones----------------");
                    }
                    Option = "0";
                    break;
                }
                case "2": {
                    if (Cuenta.check_operations()) {
                        System.out.println("Retiro formato moneda[dolar/peso]/dinero (ejem. peso/100):");
                        String retiro = myObj.nextLine();  // Read user input

                        String[] arr_money = retiro.split("/", 2);
                        if (arr_money.length == 2) {
                            response = Cuenta.retirar(arr_money[0], Integer.parseInt(arr_money[1]));
                            if (response) {
                                System.out.println("---Retiro exitoso---");
                            } else {
                                System.out.println("No se pudo realizar la transacción. Retiro fallido");
                            }
                        } else {
                            System.out.println("Error de formato");
                        }
                    } else {
                        System.out.println("----------------Alcanzaste el límite de operaciones----------------");
                    }

                    Option = "0";
                    break;
                }
                case "3": {
                    Cuenta.see_transactions();
                    Option = "0";
                    break;
                }
                case "4": {
                    Cuenta.logout();
                    System.out.println("Se ha cerrado la sesión, para volver a iniciar sesión presione \"1\"");
                    String client_response = myObj.nextLine();  // Read user input
                    if (client_response.equals("1")) {
                        Option = "0";
                    } else {
                        break label;
                    }
                    break;
                }
                default: {
                    System.out.println("XXXXXXXXXXXXX- Elige un comando válido -XXXXXXXXXXXXX");
                    System.out.println("\"1\" Depósito\n\"2\" Retiro \n\"3\" Ver transacciones\n\"4\" Cerrar sesión");
                    Option = myObj.nextLine();  // Read user input
                    break;
                }
            }
        }
        System.out.println("Máximo de sesiones alcanzado. Adios!");
    }
}
