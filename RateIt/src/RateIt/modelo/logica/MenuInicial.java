package RateIt.modelo.logica;

import RateIt.modelo.Dados;

/**
 * I.S.E.C.
 *
 * @author DavidSoares [21220084] && NomeAluno [123456]
 */
public class MenuInicial extends Estado_Base {

    public MenuInicial(Dados Infos) {
        super(Infos);
    }

    @Override
    public IEstado VerificaPassword(String password) {
        if (Infos.getPassword().equals(password)) {
            return new Administrador(Infos);
        } else {
            return new MenuInicial(Infos);
        }
    }

}
