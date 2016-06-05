package RateIt.iu;

import RateIt.modelo.*;
import RateIt.modelo.logica.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * I.S.E.C.
 *
 * @author DavidSoares [21220084] && NomeAluno [123456] & ...
 */
public class InterfaceUtilizador {

    private MaquinaDeEstados ME;
    private final Scanner sc;
    private int NIF_Actual;

    public InterfaceUtilizador(MaquinaDeEstados ME) {
        this.ME = ME;
        sc = new Scanner(System.in);
    }

    public void Corre() {
        CarregaEstabelecimento();
        while (!(ME.getEstado() instanceof Fim)) {
            ClearScreen();
            if (ME.getEstado() instanceof MenuInicial) {
                iuMenuInicial();
            } else if (ME.getEstado() instanceof Administrador) {
                iuAdministrador();
            } else if (ME.getEstado() instanceof Utilizador_Login) {
                iuUtilizador_Login();
            } else if (ME.getEstado() instanceof Utilizador) {
                iuUtilizador();
            }
        }
        //MaquinaDeEstados.guardaEmFicheiroBinario(ME);
    }

    private void iuMenuInicial() {
        int opcao;
        System.out.println("1 - Utilizador");
        System.out.println("2 - Administrador");
        System.out.println("0 - Sair");
        System.out.print("> ");

        while (!sc.hasNextInt()) {
            sc.next();
        }
        opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                ME.setEstado(new Utilizador_Login(ME.getInfos()));
                break;
            case 2:
                System.out.print("Introduza a password:");
                ME.VerificaPassword(sc.next());
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
        ClearScreen();
        int opcao, opcao2;
        System.out.println("1 - Adicionar Produto");
        System.out.println("2 - Remover Produto");
        System.out.println("3 - Alterar nome do estabelecimento");
        System.out.println("4 - Editar produto");
        System.out.println("9 - Reset");
        System.out.println("0 - Sair");
        System.out.print("> ");

        while (!sc.hasNextInt()) {
            sc.next();
        }
        opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                System.out.print("Introduza o nome do novo produto: ");
                ME.AdicionarProduto(sc.next());
                break;
            case 2:
                ME.RemoverProduto();
                break;
            case 3:
                System.out.print("Introduza o novo nome pretendido: ");
                ME.getInfos().setNome(sc.next());
                break;
            case 4:
                do {
                    System.out.println(ME.getInfos().getStrProdutos());
                    System.out.println("Seleccione o produto:");
                    System.out.print(">");
                    opcao2 = sc.nextInt();

                    if (opcao2 > 0 && opcao2 <= ME.getInfos().getProdutos().size()) {
                        System.out.print("Introduza o nome pretendido para o produto: ");
                        ME.getInfos().getProdutos().get(opcao2 - 1).setNome(sc.next());
                        break;
                    }
                } while (!(opcao2 <= ME.getInfos().getProdutos().size() && 0 <= opcao2));
                break;
            case 9:
                System.out.print("Isto irá apagar todos os dados guardados!\nIntroduza a password: ");
                ME.VerificaPassword(sc.next());
                iuNovo_Estabelecimento();
                break;
            case 0:
                ME.setEstado(new MenuInicial(ME.getInfos()));
                break;
            default:
                ME.setEstado(ME.getEstado());
                break;
        }
        ClearScreen();
    }

    private void iuUtilizador_Login() {
        int NIF;
        ClearScreen();
        System.out.println("0 - Sair");
        System.out.println("NIF - Login");       // deve obter um login, pode ser texto ou numeros
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
                    while (9 != (int) (Math.log10(NIF) + 1)) {
                        System.out.print("NIF inválido,\n"
                                + "0 - Sair\n"
                                + "Deve introduzir novo NIF: ");
                        NIF = sc.nextInt();
                        if (NIF == 0) {
                            break;
                        }

                    }
                    if (NIF != 0) {
                        ClearScreen();
                        System.out.print("Introduza o nome:");
                        String Nome = sc.next();
                        System.out.print("\nIntroduza o apelido:");
                        new Cliente(ME.getInfos(), Nome + " " + sc.next(), NIF);
                    }
                    break;
            }
            if (NIF != 0) {
                NIF_Actual = NIF;
                ME.setEstado(new Utilizador(ME.getInfos(), NIF));
            }

        } else {
            NIF_Actual = NIF;
            ME.setEstado(new Utilizador(ME.getInfos(), NIF));
        }
    }

    private void iuUtilizador() {
        if (!ME.getInfos().getProdutos().isEmpty()) {
            int opcao, avaliacao;
            if (!ME.getInfos().getAvaliacao().containsKey(NIF_Actual)) {
                System.out.print("Introduza avaliação de " + ME.getInfos().getNome() + ":");
                avaliacao = sc.nextInt();
                while (avaliacao > 5 || avaliacao <= 0) {
                    System.out.print("A avaliacao deve ser entre 1 e 5 \n\nIntroduza a avaliação pretendida:");
                    avaliacao = sc.nextInt();
                }
                ME.getInfos().getAvaliacao().put(NIF_Actual, avaliacao);
            }
            System.out.println("Escolha o produto que pretende avaliar:");
            System.out.println(ME.getInfos().getAvaliacoesCliente(NIF_Actual));// Vai a cada produto e imprime as avaliacoes daquele cliente.
            System.out.println("\n 0 - Avaliar " + ME.getInfos().Nome);
            System.out.println("-1 - Sair");
            System.out.print(">");

            opcao = sc.nextInt();

            switch (opcao) {
                case -1:
                    ME.setEstado(new Utilizador_Login(ME.getInfos()));
                    break;
                case 0:
                    System.out.print("Introduza avaliação de " + ME.getInfos().getNome() + ":");
                    avaliacao = sc.nextInt();
                    while (avaliacao > 5 || avaliacao <= 0) {
                        System.out.print("A avaliacao deve ser entre 1 e 5 \n\nIntroduza a avaliação pretendida:");
                        avaliacao = sc.nextInt();

                    }
                    ME.getInfos().getAvaliacao().put(NIF_Actual, avaliacao);
                    break;
                default:
                    System.out.print("Introduza a avaliação pretendida:");
                    if (opcao > 0 && opcao <= ME.getInfos().getProdutos().size()) {
                        avaliacao = sc.nextInt();
                        while (avaliacao > 5 || avaliacao <= 0) {
                            System.out.print("A avaliacao deve ser entre 1 e 5 \n\nIntroduza a avaliação pretendida:");
                            avaliacao = sc.nextInt();
                        }
                        ME.getInfos().getProdutos().get(opcao - 1).getAvaliacao().put(NIF_Actual, avaliacao);

                    }
                    break;
            }

        } else {
            ME.setEstado(new MenuInicial(ME.getInfos()));
        }
    }

    private void CarregaEstabelecimento() {
        try {
            ME = MaquinaDeEstados.carregaDeFicheiroBinario();
            ME.setEstado(new MenuInicial(ME.getInfos()));
        } catch (IOException | ClassNotFoundException e) {
            try {

                System.out.println("Não foram encontrados dados na aplicação"
                        + "\n\nSerá carregada uma nova");
                Thread.sleep(2000);

            } catch (InterruptedException ex) {
                Logger.getLogger(InterfaceUtilizador.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            iuNovo_Estabelecimento();
        }
    }

    private void iuNovo_Estabelecimento() {
        for (int i = 0; i < 100; ++i) {
            System.out.println();
        }
        String aux, aux2;
        System.out.print("Introduza o nome do estabelecimento: ");

//        while (!sc.hasNextLine()) {
//           sc.next();
//        }
        aux = sc.next();        // Falta validacoes para ler uma linha completa
        do {
            System.out.print("Introduza uma password: ");
            aux2 = sc.next();
            System.out.print("Introduza novamente a password: ");
        } while (!aux2.equals(sc.next()));
        ME.setEstado(ME.NovoEstabelecimento(aux, aux2));
    }

    private void ClearScreen() {
        MaquinaDeEstados.guardaEmFicheiroBinario(ME);
        for (int i = 0; i < 100; ++i) {
            System.out.println();
        }
        System.out.println("             Rate  It             ");
        if (!ME.getInfos().getNome().isEmpty()) {
            System.out.println("\t\t[" + ME.getInfos().getNome() + "]");
            System.out.println("\t\t " + ME.getInfos().getAvaliacaoSitio());
        }
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println(ME.getInfos().toStringProdutos() + "\n");
        System.out.println("\n\n\n");
    }
}
