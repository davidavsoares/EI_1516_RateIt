package RateIt.modelo.logica;

import RateIt.modelo.Dados;
import java.io.Serializable;

/**
 * I.S.E.C.
 *
 * @author DavidSoares [21220084] && NomeAluno [123456]
 */
public abstract class Estado_Base implements IEstado, Serializable {

    public Dados Infos;

    public Estado_Base(Dados GameInfo) {
        this.Infos = GameInfo;
    }

    @Override
    public IEstado CarregarAplicacao() {
        return this;
    }

    @Override
    public IEstado NovoJogo() {
        return this;
    }

    public Dados getInfos() {
        return Infos;
    }

    @Override
    public IEstado AdicionarProduto(String texto) {
        return this;
    }

    @Override
    public IEstado RemoverProduto() {
        return this;
    }

    @Override
    public IEstado VerificaPassword(String Password) {
        return this;
    }

}
