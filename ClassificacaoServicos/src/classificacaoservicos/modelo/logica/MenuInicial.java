import classificacaoservicos.modelo.Dados;
import classificacaoservicos.modelo.logica.Estado_Base;

/**
 * I.S.E.C.
 *
 * @author DavidSoares [21220084] && JorgeNogueira [21200794]
 */
public class MenuInicial extends Estado_Base {

    public MenuInicial(Dados Infos) {
        super(Infos);

    }

//    @Override
//    public IEstado CarregarAplicacao() {
//        return this;
//    }
//    @Override
//    public IEstado NovoJogo() {
//        return new Estado_1(getGameInfo());
//    }
}
