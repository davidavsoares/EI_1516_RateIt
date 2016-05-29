package RateIt.modelo.logica;


/**
 * I.S.E.C.
 *
 * @author DavidSoares [21220084] && NomeAluno [123456]
 */
public interface IEstado {

    public IEstado CarregarAplicacao();

    public IEstado NovoJogo();
    
    public IEstado AdicionarProduto(String texto);
    
    public IEstado RemoverProduto();

}
