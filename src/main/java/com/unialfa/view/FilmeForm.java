package com.unialfa.view;

import com.unialfa.model.Filme;
import com.unialfa.service.FilmeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static java.lang.Integer.parseInt;

public class FilmeForm extends JFrame {

    private FilmeService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNomeFilme;
    private JTextField campoNomeFilme;
    private JLabel labelDiretor;
    private JTextField campoDiretor;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JTable tabelaFilme;

    public FilmeForm() {
        service = new FilmeService();

        setTitle("Filme");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        labelId = new JLabel("ID:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(labelId, constraints);

        campoId = new JTextField(20);
        campoId.setEnabled(false);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoId, constraints);

        labelNomeFilme = new JLabel("Nome do Filme:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNomeFilme, constraints);

        campoNomeFilme = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNomeFilme, constraints);

        labelDiretor = new JLabel("Diretor do Filme:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelDiretor, constraints);

        campoDiretor = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoDiretor, constraints);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> limparCampos());
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(botaoCancelar, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> executarAcaoDoBotao());
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(botaoSalvar, constraints);

        JPanel painelSaida = new JPanel(new BorderLayout());

        tabelaFilme = new JTable();
        tabelaFilme.setModel(carregarDadosLocadoras());

        JScrollPane scrollPane = new JScrollPane(tabelaFilme);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private void limparCampos() {
        campoNomeFilme.setText("");
        campoDiretor.setText("");
        campoId.setText("");
    }

    private DefaultTableModel carregarDadosLocadoras() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Diretor");

        service.listarFilmes().forEach(filme ->
                model.addRow(new Object[]{
                        filme.getId(),
                        filme.getNome(),
                        filme.getDiretor()
                })
        );

        return model;
    }

    private void executarAcaoDoBotao() {
        service.salvar(construirFilme());
        limparCampos();
        tabelaFilme.setModel(carregarDadosLocadoras());
    }

    private Filme construirFilme(){
        return campoId.getText().isEmpty()
                ? new Filme(campoNomeFilme.getText(), campoDiretor.getText())
                : new Filme(
                parseInt(campoId.getText()),
                campoNomeFilme.getText(),
                campoDiretor.getText());
    }

    private void selecionarFilme(){
//        var filme = listaDeFilmes.getSelectedValue();
//        if (filme == null) return;
//        campoNomeFilme.setText(filme.getNome());
//        campoDiretor.setText(filme.getDiretor());
//        campoId.setText(filme.getId().toString());
    }
}
