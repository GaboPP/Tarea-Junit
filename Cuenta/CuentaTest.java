package Cuenta;

import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {
    private Cuenta wallet_test;
    private Cuenta wallet;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        wallet_test = new Cuenta(1, true);
        wallet = new Cuenta(1, false);
    }

    @org.junit.jupiter.api.Test
    void check_operations() {
        //Given

        //When
        wallet.retirar("dolar", 10);
        wallet.retirar("pesos", 100);
        wallet.retirar("dolar", 20);

        boolean checking_operations_1 = wallet.check_operations();
        wallet.retirar("pesos", 5000);
        boolean checking_operations_2 = wallet.check_operations();
        //Then
        assertTrue(checking_operations_1, "Debía permitir transacción");
        assertFalse(checking_operations_2, "No debía permitir transacción");
    }

    @org.junit.jupiter.api.Test
    void depositar() {
        //Given

        //When
        boolean deposito_dolar_1 = wallet_test.depositar("Dolar", 0);
        boolean deposito_dolar_2 = wallet_test.depositar("dOlar", 1999999999);
        boolean deposito_dolar_3 = wallet_test.depositar("DOLAR", -20);

        boolean deposito_pesos_1 = wallet_test.depositar("peso", 0);
        boolean deposito_pesos_2 = wallet_test.depositar("PESO", 1999999999);
        boolean deposito_pesos_3 = wallet_test.depositar("pEsO", -300);
        //Then
        assertTrue(deposito_dolar_1, "Falló el deposito_dolar_1");
        assertTrue(deposito_dolar_2, "Falló el deposito_dolar_2");
        assertFalse(deposito_dolar_3, "No debió depositar");

        assertTrue(deposito_pesos_1, "Falló el deposito_dolar_1");
        assertTrue(deposito_pesos_2, "Falló el deposito_dolar_2");
        assertFalse(deposito_pesos_3, "No debió depositar");
    }

    @org.junit.jupiter.api.Test
    void retirar() {
        //Given

        //When
        boolean retiro_dolar_1 = wallet_test.retirar("dolar", 99);
        boolean retiro_dolar_2 = wallet_test.retirar("dolar", 100);
        boolean retiro_dolar_3 = wallet_test.retirar("dolar", 101);
        boolean retiro_dolar_4 = wallet_test.retirar("dolar", -101);
        boolean retiro_dolar_5 = wallet_test.retirar("dolar", 0);

        boolean retiro_pesos_1 = wallet_test.retirar("peso", 199999);
        boolean retiro_pesos_2 = wallet_test.retirar("PESO", 200000);
        boolean retiro_pesos_3 = wallet_test.retirar("pEsO", 200001);
        boolean retiro_pesos_4 = wallet_test.retirar("pEsO", -10);
        boolean retiro_pesos_5 = wallet_test.retirar("pEsO", 0);

        int budget_usd = wallet_test.getDinero_dolares();
        int budget_clp = wallet_test.getDinero_pesos();
        boolean retiro_pesos_6 = wallet_test.retirar("peso", budget_clp + 1000);
        boolean retiro_dolar_6 = wallet_test.retirar("dolar", budget_usd + 100);

        //Then
        assertTrue(retiro_dolar_1, "Falló el retiro_dolar_1");
        assertTrue(retiro_dolar_2, "Falló el retiro_dolar_2");
        assertFalse(retiro_dolar_3, "No debió retirar");
        assertFalse(retiro_dolar_4, "No debió retirar");
        assertTrue(retiro_dolar_5, "Falló el retiro_dolar_5");

        assertTrue(retiro_pesos_1, "Falló el retiro_dolar_1");
        assertTrue(retiro_pesos_2, "Falló el retiro_dolar_2");
        assertFalse(retiro_pesos_3, "No debió retirar");
        assertFalse(retiro_pesos_4, "No debió retirar");
        assertTrue(retiro_pesos_5, "Falló el retiro_dolar_5");

        assertFalse(retiro_pesos_6, "No debió retirar más");
        assertFalse(retiro_dolar_6, "No debió retirar más");
    }


    @AfterEach
    void sessions() {
        //Given

        //When
        wallet.logout();
        wallet.logout();
        boolean check_login_1 = wallet.check_session();

        wallet.logout();
        boolean check_login_2 = wallet.check_session();

        //Then
        assertTrue(check_login_1, "Debería permitir una sesion más");
        assertFalse(check_login_2, "No debería permitir ua sesion más");

    }

}