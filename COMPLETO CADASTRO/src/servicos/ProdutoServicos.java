/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dao.DAOFactory;
import dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.ProdutoVO;

public class ProdutoServicos {

    /**
     * @param pVO Objeto proveniente da interface
     * @throws SQLException Lançando exceção de SQL
     */
    public void cadastrarProduto(ProdutoVO pVO) throws SQLException {
        ProdutoDAO pDAO = DAOFactory.getProdutoDAO();
        pDAO.cadastrarProduto(pVO);
    }//fecha método

    /**
     * @return Retornando um Array contendo todos os produtos do banco de dados.
     * @throws SQLException Lançando exceção de SQL
     */
    public ArrayList<ProdutoVO> buscarProdutos() throws SQLException {
        ProdutoDAO pDAO = DAOFactory.getProdutoDAO();
        return pDAO.buscarProdutos();
    }//fecha método
}//fecha classe