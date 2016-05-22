package classificacaoservicos.iu;

import classificacaoservicos.modelo.MaquinaDeEstados;
import java.util.Scanner;

/**
 *
 * @author David
 */
public class InterfaceUtilizador {

    private final MaquinaDeEstados ME;
    private final Scanner sc;

    public InterfaceUtilizador(MaquinaDeEstados ME) {
        this.ME = ME;
        sc = new Scanner(System.in);
    }

    public void IniciaAplicacao() {

//        while (!(ME.getEstado() instanceof Fim)) {
//
//            if (ME.getEstado() instanceof MenuInicial) {
//                iuMenuInicial();
//            } else if (ME.getEstado() instanceof Estado_1) {
//                iuEstado_1();
//            } else if (ME.getEstado() instanceof Estado_11) {
//                iuEstado_11();
//            } else if (ME.getEstado() instanceof Estado_2) {
//                iuEstado_2();
//            } else if (ME.getEstado() instanceof Estado_31) {
//                iuEstado_31();
//            } else if (ME.getEstado() instanceof Estado_32) {
//                iuEstado_32();
//            } else if (ME.getEstado() instanceof Estado_3) {
//                iuEstado_3();
//            }
//        }
    }
}
