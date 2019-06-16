/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.ProdutoVO;
import persistencia.ConexaoBanco;

public class ProdutoDAO {

    public void cadastrarProduto(ProdutoVO pVO) throws SQLException {

        //Buscando uma conexão com o Banco de Dados
        Connection con = ConexaoBanco.getConexao();
        /*Criando obj. capaz de executar instruções
         SQL no banco de dados*/
        Statement stat = con.createStatement();

        try {
            //String que receberá instrução SQL
            String sql;

            /* Montando a instrução INSERT para inserir
             um objeto pVO no Banco MySQL */
            sql = "insert into produto(idproduto,nome,valorcusto,quantidade)"
                    + "values(null, '" + pVO.getNome() + "', " + pVO.getValorCusto() + ", " + pVO.getQuantidade() + ")";

            //Executando o sql
            stat.execute(sql);

        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir produto!");
        } finally {
            con.close();
            stat.close();
        }//fecha finally
    }//fecha método

    public ArrayList<ProdutoVO> buscarProdutos() throws SQLException {

        Connection con = ConexaoBanco.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;

            //Montando o sql            
            sql = "select * from produto";

            /* Executando o SQL  e armazenando
             o ResultSet em um objeto do tipo
             ResultSet chamado rs */
            ResultSet rs = stat.executeQuery(sql);

            /* Criando ArrayList para armazenar 
             objetos do tipo produto */
            ArrayList<ProdutoVO> prod = new ArrayList<>();

            /* Enquanto houver uma próxima linha no 
             banco de dados o while roda */
            while (rs.next()) {
                //Criando um novo obj. ProdutoVO
                ProdutoVO p = new ProdutoVO();

                /* Mapeando a tabela do banco para objeto
                 chamado pVO */
                p.setIdProduto(rs.getLong("idproduto"));
                p.setNome(rs.getString("nome"));
                p.setValorCusto(rs.getDouble("valorcusto"));
                p.setQuantidade(rs.getInt("quantidade"));

                /* Inserindo o objeto pVO no ArrayList */
                prod.add(p);
            }//fecha while

            //Retornando o ArrayList com todos objetos
            return prod;

        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar produtos! " + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }//fecha finally
    }//fecha método

    public void deletar(ProdutoVO produto) throws SQLException {
        Connection con = ConexaoBanco.getConexao();

        PreparedStatement p = con.prepareStatement("delete from produto where idproduto = ?");
        p.setLong(1, produto.getIdProduto());
        p.execute();
        p.close();
    }

    public void alterar(ProdutoVO produto) throws SQLException {

        Connection con = ConexaoBanco.getConexao();
        PreparedStatement p = con.prepareStatement("update produto set nome=?,valorcusto=?,quantidade=? where idproduto=?");

        p.setString(1, produto.getNome());
        p.setDouble(2, produto.getValorCusto());
        p.setInt(3, produto.getQuantidade());
        p.setLong(4, produto.getIdProduto());

        p.execute();
        p.close();

    }

}//fecha classe
