package Cuenta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Cuenta {

    private int numero_cuenta;
    private int dinero_pesos = 1000000;
    private int dinero_dolares = 1000;
    private int numero_operaciones = 4;
    public int sesiones = 3;

    public Cuenta(int numero_cuenta, boolean test) {
        this.numero_cuenta = numero_cuenta;
        if (test) {
            this.numero_operaciones = 100;
        }
    }

    public int getDinero_dolares() {
        return dinero_dolares;
    }

    public int getDinero_pesos() {
        return dinero_pesos;
    }

    public boolean check_operations() {
        if ( this.numero_operaciones <= 0) {
            System.out.println("Máximo de operaciones sobrepasada");
            return false;
        } else {
            return true;
        }
    }
    public boolean check_session() {
        if ( this.sesiones <= 0) {
            System.out.println("Máximo de sesiones sobrepasada");
            return false;
        } else {
            return true;
        }
    }


    public boolean retirar(String moneda, int retire) {
        if(this.check_operations() && this.check_session()) {
            this.numero_operaciones -= 1;
            String tipo_moneda = new String(moneda).toUpperCase();
            if (tipo_moneda.equals("PESO")) {
                if (0 <= retire && retire <= 200000 && this.dinero_pesos - retire >= 0) {
                    this.dinero_pesos -= retire;
                    String state_log = "CLP: ".concat(String.valueOf(this.dinero_pesos)).concat(" || USD: ").concat(String.valueOf(this.dinero_dolares));
                    this.write_transaction(state_log);
                    return true;
                }
            } else if (tipo_moneda.equals("DOLAR")) {
                if (0 <= retire && retire <= 100 && this.dinero_pesos - retire >= 0) {
                    this.dinero_dolares -= retire;
                    String state_log = "CLP: ".concat(String.valueOf(this.dinero_pesos)).concat(" || USD: ").concat(String.valueOf(this.dinero_dolares));
                    this.write_transaction(state_log);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean depositar(String moneda, int deposito) {
        if(this.check_operations() && this.check_session() && deposito >= 0) {
            this.numero_operaciones -= 1;
            String tipo_moneda = new String(moneda).toUpperCase();
            if (tipo_moneda.equals("PESO")) {
                this.dinero_pesos += deposito;
                String state_log = "CLP: ".concat(String.valueOf(this.dinero_pesos)).concat(" || USD: ").concat(String.valueOf(this.dinero_dolares));
                this.write_transaction(state_log);
                return true;
            } else if (tipo_moneda.equals("DOLAR")) {
                this.dinero_dolares += deposito;
                String state_log = "CLP: ".concat(String.valueOf(this.dinero_pesos)).concat(" || USD: ").concat(String.valueOf(this.dinero_dolares));
                this.write_transaction(state_log);
                return true;
            }
        }
        return false;
    }


    public void write_transaction(String state) {
        Logger logger = Logger.getLogger("transactions");
        FileHandler fh;

        try {
            fh = new FileHandler("./transactions.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info(state);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }


    public void see_transactions() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./transactions.log"));
            String line;
            line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        this.sesiones -= 1;
    }
}
