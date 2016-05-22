package classificacaoservicos.modelo.logica;

import classificacaoservicos.modelo.Dados;

/**
 *
 * @author David
 */
public abstract class Estado_Base {

    private static final long serialVersionUID = 42L;
    private final Dados Infos;

    public Estado_Base(Dados GameInfo) {
        this.Infos = GameInfo;
    }
}
