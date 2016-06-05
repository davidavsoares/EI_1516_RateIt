package RateIt.modelo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * I.S.E.C.
 *
 * @author DavidSoares [21220084] && NomeAluno [123456]
 */
public final class Dados implements Serializable {

    static final long serialVersionUID = 42L;
    public String Nome;
    private ArrayList<Cliente> ClientesRegistados;
    public ArrayList<Produto> Produtos;
    private String Password;
    private HashMap<Integer, Integer> Avaliacao;

    public Dados(String Nome, String Password) {
        this.Nome = Nome;
        this.Password = Password;
        //Avaliacao = new HashMap<>();
        ClientesRegistados = new ArrayList<>();
        Produtos = new ArrayList<>();
        Avaliacao = new HashMap<>();

    }

    public Dados() {
        ClientesRegistados = new ArrayList<>();
        Produtos = new ArrayList<>();
    }

    // ------------ CARACTERISTICAS DO ESTABELECIMENTO ------------------------- //
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getNome() {
        return Nome;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
// ------------ CLIENTES E PRODUTOS ------------------------- //

    public ArrayList<Cliente> getClientesRegistados() {
        return ClientesRegistados;
    }

    public ArrayList<Produto> getProdutos() {
        return Produtos;
    }

    public String toStringProdutos() {
        String s;
        s = "\n\n" + "Produtos: ";
        for (int i = 0; i < Produtos.size(); i++) {
            s += "\n" + " - " + Produtos.get(i).toString();
        }
        return s;

    }

    public boolean ContemCliente(int NIF) {
        return ClientesRegistados.stream().anyMatch((Cli) -> (Cli.equals(NIF)));
    }

    @SuppressWarnings("empty-statement")
    public Cliente getCliente(int NIF) throws Exception {
        for (Cliente Cli : ClientesRegistados) {
            if (Cli.equals(NIF)) {
                return Cli;
            }
        }
        return null;
    }

    public String getAvaliacoesCliente(int NIF) {
        String s;
        s= "";
        for (int i = 0; i < Produtos.size(); i++) {
            s +="\n" + (i + 1) + " - " + Produtos.get(i).toString() + "\tA sua avaliação: ";
            if (Produtos.get(i).getAvaliacao().containsKey(NIF)) {
                s += Produtos.get(i).getAvaliacao().get(NIF).toString();
            } else {
                s += "Ainda não efectuou nenhuma avaliacao";

            }
        }
        return s;
    }

    public String getStrProdutos() {
        String s;
        s = "\n\n" + "Produtos: ";
        for (int i = 0; i < Produtos.size(); i++) {
            s += "\n" + (i + 1) + " - " + Produtos.get(i).toString();
        }
        return s;
    }

    public HashMap<Integer, Integer> getAvaliacao() {
        return Avaliacao;
    }

    public String getAvaliacaoSitio() {
        DecimalFormat decimal = new DecimalFormat("0.0");
        int AvaliacaoMedia = 0;
        if (Avaliacao.isEmpty()) {
            return Nome + " ainda não foi classificado";
        } else {
            for (Integer Aval : Avaliacao.keySet()) {
                AvaliacaoMedia += Avaliacao.get(Aval);
                
            }
//            Avaliacao.keySet().stream().forEach((nif) -> {
//                AvaliacaoMedia += Avaliacao.get(nif);
//            });
            return decimal.format(AvaliacaoMedia = AvaliacaoMedia / Avaliacao.size()) + "   (" + Avaliacao.size() + ")";
        }

    }

}
