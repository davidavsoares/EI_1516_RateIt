package RateIt.modelo;

import RateIt.modelo.logica.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * I.S.E.C.
 *
 * @author DavidSoares [21220084] && NomeAluno [123456]
 */
public class MaquinaDeEstados implements Serializable {

    static final long serialVersionUID = 1L;
    private IEstado estado;
    public static final String FILE_TO_SAVE = "saved.bin";
    private Dados Infos;

    public MaquinaDeEstados() {
        estado = new MenuInicial(Infos);

    }

    public Dados getDados() {
        return Infos;
    }

    public IEstado getEstado() {
        return estado;
    }

    public void setEstado(IEstado estado) {
        this.estado = estado;
    }

    public IEstado NovoEstabelecimento(String Nome, String Password) {
        Infos = new Dados(Nome, Password);
        if (guardaEmFicheiroBinario(this)) {
            return new MenuInicial(Infos);
        }
        return new MenuInicial(Infos);
    }

    public IEstado AdicionarProduto(String texto) {
        return estado.AdicionarProduto(texto);
    }

    public IEstado RemoverProduto() {
        return estado.RemoverProduto();
    }

    public Dados getInfos() {
        return Infos;
    }

    public boolean VerificaPassword(String password) {
        return password.equals(Infos.getPassword());
    }

    public static MaquinaDeEstados carregaDeFicheiroBinario() throws IOException, ClassNotFoundException {
        MaquinaDeEstados ME;
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(new FileInputStream(FILE_TO_SAVE));
            ME = (MaquinaDeEstados) in.readObject();
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return ME;
    }

    public static boolean guardaEmFicheiroBinario(MaquinaDeEstados ME) {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(FILE_TO_SAVE));
            out.writeObject(ME);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("Erro Guardar: " + e);
                }
            }
        }
    }
}


