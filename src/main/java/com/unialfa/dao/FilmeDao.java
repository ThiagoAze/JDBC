package com.unialfa.dao;

import com.unialfa.model.Filme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDao {
    private Connection connection;

    public FilmeDao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javadb?useTimezone=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void inserir(Filme filme) throws SQLException {
            String sql = "insert into filme(nome,diretor) values(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, filme.getNome());
            ps.setString(2, filme.getDiretor());
            ps.execute();
    }

    public List<Filme> listarTodos() throws SQLException {
        List<Filme> filmes = new ArrayList<Filme>();

        ResultSet rs = connection.prepareStatement("select * from filme").executeQuery();
        while (rs.next()) {
            filmes.add(new Filme(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("diretor")));
        }
        rs.close();

        return filmes;
    }

    public void atualizar(Filme filme) throws SQLException {
        String sql = "update filme set nome = ?, diretor = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, filme.getNome());
        ps.setString(2, filme.getDiretor());
        ps.setInt(3, filme.getId());
        ps.execute();
    }

    public void deletar(int id) throws SQLException {
        String sql = "delete from filme where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
    }

    public Filme consultarPorId(int id) throws SQLException {
        Filme filme = new Filme("", "");

        String sql = "select * from Filme where idFilme = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            filme.setNome(rs.getString("nome"));
        }
        rs.close();

        return filme;
    }

    public List<Filme> listarContenhaNome(String nome) throws SQLException {
        List<Filme> Filmes = new ArrayList<Filme>();

        String sql = "select * from Filme where nome like ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + nome + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Filme Filme = new Filme(rs.getString("nome"), rs.getString("diretor"));
            Filmes.add(Filme);
        }
        rs.close();

        return Filmes;
    }
}
