package RateIt.modelo;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class Cliente implements Serializable {

    static final long serialVersionUID = 42L;

    Dados Infos;
    private final String Nome;
    private final int NIF;

    public Cliente(Dados Infos, String Nome, int NIF) {
        this.Infos = Infos;
        this.Nome = Nome;
        this.NIF = NIF;
        Infos.getClientesRegistados().add(this);
    }

    public int getNIF() {
        return NIF;
    }

    public String getNome() {
        return Nome;
    }

    public boolean equals(int NIF) {
        return this.NIF == NIF;
    }

    @Override
    public String toString() {

        return "\n" + Nome + "\t" + NIF + "\n";
    }
//    
//    public void Avalia(Produto p, int classificacao)
//    {
//        Infos.getAvaliacao().entrySet(p, this);//Fazer atribuicao
//    }

}
