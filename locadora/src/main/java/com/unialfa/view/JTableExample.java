package com.unialfa.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class JTableExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JDBC to JTable Example with Selection");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTable table = new JTable();

            // Preenche a tabela com dados do banco de dados
            fillTable(table);

            // Bloqueia a edição da tabela
            table.setDefaultEditor(Object.class, null);

            // Adiciona um ouvinte de seleção à tabela
            table.getSelectionModel().addListSelectionListener(e -> selecionarFilme(table, e));

            frame.add(new JScrollPane(table));
            frame.pack();
            frame.setLocationRelativeTo(null); // Centraliza o frame na tela
            frame.setVisible(true);
        });
    }

    private static void selecionarFilme(JTable table, ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Recupera os dados da linha selecionada
                int id = (int) table.getValueAt(selectedRow, 0);
                String nome = (String) table.getValueAt(selectedRow, 1);
                String diretor = (String) table.getValueAt(selectedRow, 2);
                System.out.println("ID: " + id + ", Nome: " + nome + ", Diretor: " + diretor);
            }
        }
    }

    private static void fillTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Diretor");

        // Conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/javadb?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM filme";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String diretor = resultSet.getString("diretor");
                    model.addRow(new Object[]{id, nome, diretor});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setModel(model);
    }
}
