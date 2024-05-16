package com.unialfa;

import com.unialfa.view.FilmeForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var form = new FilmeForm();
            form.setVisible(true);
        });
    }
}