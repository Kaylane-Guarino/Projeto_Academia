package br.com.academia.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getActionCommand().equals("masc")) {
            System.out.println("Masculino");

        }else if (event.getActionCommand().equals("fem")){
            System.out.println("Feminino");

        }
    }
}

