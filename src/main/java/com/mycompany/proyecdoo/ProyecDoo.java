package com.mycompany.proyecdoo;

import javax.swing.SwingUtilities;
import Vista.Login;

public class ProyecDoo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

}
