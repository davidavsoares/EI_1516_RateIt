package RateIt.modelo.logica;

import RateIt.modelo.*;

/**
 *
 * @author David
 */
public class Administrador extends Estado_Base {

    public Administrador(Dados Infos) {
        super(Infos);
    }

    @Override
    public IEstado AdicionarProduto(String nome) {
        //getInfos().getAvaliacao().put(new Produto(Infos, nome), null);
        new Produto(Infos, nome);
        return new MenuInicial(Infos);
    }

    public void AlterarEstabelecimento(String Estabelecimento) {
        getInfos().setNome(Estabelecimento);
    }

}
