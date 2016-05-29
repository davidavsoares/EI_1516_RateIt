package RateIt;

import RateIt.iu.InterfaceUtilizador;
import RateIt.modelo.MaquinaDeEstados;

public class Main {

    /**
     * I.S.E.C.
     *
     * @author DavidSoares [21220084] && NomeAluno [123456]
     * @param args
     */
    public static void main(String[] args) {
        InterfaceUtilizador ui = new InterfaceUtilizador(new MaquinaDeEstados());
        ui.Corre();
    }

}
