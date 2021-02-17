package br.com.academia.actions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxListenner implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("comboBox")){
            ItemSelectable itemselecionado = (ItemSelectable)e.getSource();
            System.out.println( selectedString(itemselecionado));
        }
    }

    static private String selectedString(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        return ((selected.length == 0) ? "null" : (String) selected[0]);
    }
}
