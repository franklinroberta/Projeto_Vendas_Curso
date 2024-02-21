/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.pacote.jdbc.ConnectionFactory;
import br.com.projeto.model.Vendas;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author frank
 */
public class VendasDAO {

    //conexao com banco de dados
    private Connection con;

    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Cadastrar venda
    public void cadastrarVendas(Vendas obj) {
        try {
            String sql = "insert into tb_vendas(cliente_di, data_venda, total_venda, observacoes) values (?,?,?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());

            //3 passo - execultar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Venda Registrada com sucesso!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }

    //Retorna a ultima venda
    public int retornaUltimaVenda() {
        try {

            int idvenda = 0;

            String sql = "select max(id) id from tb_vendas";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Vendas p = new Vendas();

                p.setId(rs.getInt("id"));

                idvenda = p.getId();

            }

            return idvenda;

        } catch (Exception e) {
            throw new RuntimeException(e);
            
        }
    }

}
