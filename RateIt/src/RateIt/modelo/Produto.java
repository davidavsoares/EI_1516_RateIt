package RateIt.modelo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;

/**
 *
 * @author David
 */
public class Produto implements Serializable {

    static final long serialVersionUID = 42L;
    private String Nome;
    private float AvaliacaoMedia = 0;
    private final Dados Infos;
    private HashMap<Integer, Integer> Avaliacao;

    public Produto(Dados Infos, String Nome) {
        this.Nome = Nome;
        this.Infos = Infos;
        Infos.getProdutos().add(this);
        Avaliacao = new HashMap<>();
        //Infos.getAvaliacao().put(this, Cliente);
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public float getAvaliacaoMedia() {
        AvaliacaoMedia = 0;
        if (Avaliacao.isEmpty()) {
            return 0;
        } else {
            Avaliacao.keySet().stream().forEach((nif) -> {
                AvaliacaoMedia += Avaliacao.get(nif);
            });
            return AvaliacaoMedia = AvaliacaoMedia / Avaliacao.size();
        }

    }

    @Override
    public String toString() {
        DecimalFormat decimal = new DecimalFormat("0.0");
        return "[" + Nome + "]\tAvaliação Média: " + decimal.format(getAvaliacaoMedia()) + "\t (" + Avaliacao.size() + ")";
    }

    public HashMap<Integer, Integer> getAvaliacao() {
        return Avaliacao;
    }

    public void Avalia(int NIF, int classificacao) {
        Avaliacao.put(NIF, classificacao);
    }

}
