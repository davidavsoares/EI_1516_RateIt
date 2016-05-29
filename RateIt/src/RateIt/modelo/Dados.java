package RateIt.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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
    private HashMap<Produto, ArrayList<Cliente>> Avaliacao;       // Cada produto tem um array de clientes coma  respectiva avaliacao
    private String Password;

    public Dados(String Nome, String Password) {
        this.Nome = Nome;
        this.Password = Password;
        Avaliacao = new HashMap<>();
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

    public HashMap<Produto, ArrayList<Cliente>> getAvaliacao() {
        return Avaliacao;
    }

    public ArrayList<Produto> getProdutos() {
        return Produtos;
    }

    public String toStringProdutos() {
        return "\n" + Produtos + '\n' + ClientesRegistados;
    }

    public boolean ContemCliente(int NIF) {
        for (Cliente Cli : ClientesRegistados) {
            if (Cli.equals(NIF)) {
                return true;
            }
        }
        return false;
    }

}
