package RateIt.modelo;

import java.io.Serializable;
import java.util.ArrayList;

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
    //   private HashMap<Produto, ArrayList<Cliente>> Avaliacao;       // Cada produto tem um array de clientes coma  respectiva avaliacao
    private String Password;

    public Dados(String Nome, String Password) {
        this.Nome = Nome;
        this.Password = Password;
        //Avaliacao = new HashMap<>();
        ClientesRegistados = new ArrayList<>();
        Produtos = new ArrayList<>();

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
//        String Avaliacoes = "Produtos avaliados\n";
//        for (Produto produto : Produtos) {
//            if (produto.getAvaliacao().containsKey(NIF)) {
//                Avaliacoes += produto.toString() + "\t";
//                Avaliacoes += "A sua avaliacao:" + produto.getAvaliacao().get(NIF).toString();
//            }
//        }
//        return Avaliacoes;

        String s;
        s = "\n";
        for (int i = 0; i < Produtos.size(); i++) {
            s += "\n" + (i + 1) + " - " + Produtos.get(i).toString() + "\tA sua avaliação: ";
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

}
