package classificacaoservicos.modelo;

import classificacaoservicos.modelo.logica.*;

/**
 *
 * @author David
 */
public class MaquinaDeEstados {

    private IEstado estado;

    private Dados Infos;

    public MaquinaDeEstados() {
        Infos = new Dados();
        estado = new MenuInicial(Infos);
    }
}
