package RateIt.modelo;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class Produto implements Serializable {

    static final long serialVersionUID = 42L;
    private String Nome;
    private int AvaliacaoMedia = 0;
    private final Dados Infos;

    public Produto(Dados Infos, String Nome) {
        this.Nome = Nome;
        this.Infos = Infos;
        Infos.getProdutos().add(this);
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setAvaliacaoMedia(int AvaliacaoMedia) {
        this.AvaliacaoMedia = AvaliacaoMedia;
    }

    @Override
    public String toString() {
        return Nome + ", " + AvaliacaoMedia;
    }
}
