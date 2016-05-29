package RateIt.iu;

import RateIt.modelo.*;
import RateIt.modelo.logica.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * I.S.E.C.
 *
 * @author DavidSoares [21220084] && NomeAluno [123456] & ...
 */
public class InterfaceUtilizador {

    private MaquinaDeEstados ME;
    private final Scanner sc;

    public InterfaceUtilizador(MaquinaDeEstados ME) {
        this.ME = ME;
        sc = new Scanner(System.in);
    }

    public void Corre() {
        CarregaEstabelecimento();
        while (!(ME.getEstado() instanceof Fim)) {
            ClearScreen();
            if (ME.getEstado() instanceof MenuInicial) {
                //CarregaEstabelecimento();
                iuMenuInicial();
            } else if (ME.getEstado() instanceof Administrador) {
                iuAdministrador();
            } else if (ME.getEstado() instanceof Utilizador) {
                while (!(ME.getEstado() instanceof MenuInicial)) {
                    iuUtilizador();
                }
            }
        }
        MaquinaDeEstados.guardaEmFicheiroBinario(ME);
    }

    private void iuMenuInicial() {
        int opcao;
        System.out.println("1 - Utilizador");
        System.out.println("2 - Administrador");
        System.out.println("3 - Guardar");
        System.out.println("0 - Sair");
        System.out.print("> ");

        while (!sc.hasNextInt()) {
            sc.next();
        }
        opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                ME.setEstado(new Utilizador(ME.getInfos()));
                break;
            case 2:
                ME.setEstado(new Administrador(ME.getInfos()));
                break;
            case 0:
                ME.setEstado(new Fim(ME.getInfos()));
                break;
            default:
                ME.setEstado(ME.getEstado());
                break;
        }
    }

    private void iuAdministrador() {
//        boolean validacao;
//        do {
//            System.out.print("Password: ");
//            if (validacao = ME.VerificaPassword(sc.next())) {
        ClearScreen();
        int opcao;
        System.out.println("1 - Adicionar Produto");
        System.out.println("2 - Remover Produto");
        System.out.println("3 - Alterar nome");
        System.out.println("4 - Editar produto");
        System.out.println("9 - Reset");
        System.out.println("0 - Sair");
        System.out.print("> ");

        while (!sc.hasNextInt()) {
            sc.next();
        }
        opcao = sc.nextInt();
        String texto;
        switch (opcao) {
            case 1:
                System.out.print("Introduza o nome do novo produto: ");
                ME.AdicionarProduto(sc.next());
                break;
            case 2:
                ME.RemoverProduto();
                break;
            case 3:
                System.out.print("Introduza o nome pretendido: ");
                ME.getDados().setNome(sc.next());
                break;
            case 4:
                System.out.print("Introduza o nome pretendido para o produto: ");
                break;
            case 9:
                System.out.print("Isto irá apagar todos os dados guardados!\nIntroduza a password: ");
                if (ME.VerificaPassword(sc.next())) {
                    CriaEstabelecimento();
                }
                break;
            case 0:
                ME.setEstado(new MenuInicial(ME.getInfos()));
                break;
            default:
                ME.setEstado(ME.getEstado());
                break;
        }
//            }
        ClearScreen();
//        } while (!validacao);
    }

    private void iuUtilizador() {
        int NIF;
        System.out.println("Pressione 0 para sair ou");
        System.out.print("Introduza NIF para continuar:");       // deve obter um login, pode ser texto ou numeros
        System.out.print("> ");

        while (!sc.hasNextInt()) {
            sc.next();
        }
        NIF = sc.nextInt();
        if (!ME.getInfos().ContemCliente(NIF)) {
            switch (NIF) {
                case 0:
                    ME.setEstado(new MenuInicial(ME.getInfos()));
                    break;
                default:
                    if (9 != (int) (Math.log10(NIF) + 1)) {     //Calcula o numero de algarismos, deve ser 9!
                        System.out.println("NIF INVALIDO");     // NIF deve ser começado por 1 ou 2 para pessoas singulares
                    }
                    break;
            }
        }//PEDIR NOME para criar novo cliente
    }

    private void CarregaEstabelecimento() {
        String aux;
        boolean ExisteLogin = false;
        do {                                                //Deve introduzir o nome do ficheiro do restaurante que pretende carregar ou criar um novo
            try {
                ME = MaquinaDeEstados.carregaDeFicheiroBinario();
                ME.setEstado(new MenuInicial(ME.getInfos()));
                ExisteLogin = true;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Exception:" + e);
                System.out.print("Não existe um estabelecimento criado, deseja crialo? (S/N): ");
                aux = sc.next();
                if ("S".equals(aux)) {
                    CriaEstabelecimento();
                }
            }
        } while (!ExisteLogin);
    }

    private void CriaEstabelecimento() {
        String aux, aux2;
        System.out.print("Introduza o nome do estabelecimento: ");
        aux = sc.next();
        do {
            System.out.print("Introduza uma password: ");
            aux2 = sc.next();
            System.out.print("Introduza novamente a password: ");
        } while (!aux2.equals(sc.next()));
        ME.setEstado(ME.NovoEstabelecimento(aux, aux2));
    }

    private void ClearScreen() {
        for (int i = 0; i < 100; ++i) {
            System.out.println();
        }
        System.out.println("             Rate  It             ");
        if (!ME.getDados().getNome().isEmpty()) {
            System.out.println("             [" + ME.getDados().getNome() + "]");
        }
        System.out.println("----------------------------------");
        System.out.println();
        System.out.println(ME.getDados().toStringProdutos());
    }

}
