/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
   public static boolean cadastrarProduto(ProdutosDTO p) {
       
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        
        try {
            conectaDAO conn = new conectaDAO();
            conn.connectDB();
              
            
            PreparedStatement ps = conn.connectDB().prepareStatement(sql);
           
            
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getValor());
            ps.setString(3, p.getStatus());
    
            int atualizacaoTabela = ps.executeUpdate();
            System.out.println("Produto cadastrado com sucesso");
            return atualizacaoTabela > 0;
        } catch (SQLException se) {
            System.err.println("erro ao cadastrar produto: " + se.getMessage());
            return false;
        }
    }
    
   public static List<ProdutosDTO> listarProdutos() {
    List<ProdutosDTO> p = new ArrayList<>();

    try {
        conectaDAO conexao = new conectaDAO();
        conexao.connectDB();

        String sql = "SELECT id, nome, valor, status FROM produtos";
        System.out.println("Executando a consulta SQL...");

        PreparedStatement ps = conexao.connectDB().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ProdutosDTO pr = new ProdutosDTO();
            pr.setId(rs.getInt("id"));
            pr.setNome(rs.getString("nome"));
            pr.setValor(rs.getInt("valor"));
            pr.setStatus(rs.getString("status"));
            

            p.add(pr);
        }

        System.out.println("Número de produtos encontrados: " + p.size());

        

    } catch (SQLException se) {
        System.err.println("Erro ao listar produtos: " + se.getMessage());
    }

    return p;
}
   
    public boolean venderProduto(int produtoId) {
        conectaDAO conexao = new conectaDAO();
        conexao.connectDB();
        
        
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        try (PreparedStatement statement = conexao.connectDB().prepareStatement(sql)) {
            // Define o status como "Vendido"
            statement.setString(1, "Vendido");
            // Define o ID do produto a ser atualizado
            statement.setInt(2, produtoId);

            // Executa a atualização
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0; // Retorna true se a atualização foi bem-sucedida
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a stack trace em caso de erro
            return false; // Retorna false se houve um erro
        }
    }
    
     public static List<ProdutosDTO> listarProdutosVendidos() {
    List<ProdutosDTO> p = new ArrayList<>();

    try {
        conectaDAO conexao = new conectaDAO();
        conexao.connectDB();

        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        System.out.println("Executando a consulta SQL...");

        PreparedStatement ps = conexao.connectDB().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ProdutosDTO pr = new ProdutosDTO();
            pr.setId(rs.getInt("id"));
            pr.setNome(rs.getString("nome"));
            pr.setValor(rs.getInt("valor"));
            pr.setStatus(rs.getString("status"));
            

            p.add(pr);
        }

        System.out.println("Número de produtos encontrados: " + p.size());

        

    } catch (SQLException se) {
        System.err.println("Erro ao listar Produtos: !" + se.getMessage());
    }

    return p;
}
  
}

