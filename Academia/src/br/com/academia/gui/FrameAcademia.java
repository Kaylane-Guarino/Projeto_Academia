package br.com.academia.gui;

import br.com.academia.actions.ComboBoxListenner;
import br.com.academia.actions.RadioButtonListener;
import br.com.academia.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class FrameAcademia {

    public void criarTela(){
        JFrame container = new JFrame();
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setSize(500, 650);
        container.setTitle("Academia Boa Forma");
        container.setLayout(null);

        JLabel nome = new JLabel();
        nome.setText("Nome do Aluno:");
        nome.setBounds(10, 10,100,30);

        JTextField textNome = new JTextField();
        textNome.setBounds(10,40,150, 30);

        JLabel peso = new JLabel();
        peso.setText("Peso do aluno:");
        peso.setBounds(10,80,100,30);

        JTextField textPeso = new JTextField();
        textPeso.setBounds(10, 110, 150, 30);

        JLabel altura = new JLabel();
        altura.setText("Altura do aluno:");
        altura.setBounds(10,150,100,30);

        JTextField textAltura = new JTextField();
        textAltura.setBounds(10,180,150,30);

        JLabel data = new JLabel();
        data.setText("Data de Nacimento:");
        data.setBounds(10,220, 100, 30);

        JTextField textData = new JTextField();
        textData.setBounds(10,250,150,30);

        JLabel sexo = new JLabel();
        sexo.setText("Sexo do aluno:");
        sexo.setBounds(10,290,100,30);

        JRadioButton fem = new JRadioButton("Feminino", true);
        fem.setBounds(10, 320, 100, 30);
        fem.setActionCommand("fem");

        JRadioButton masc = new JRadioButton("Masculino", false);
        masc.setBounds(110, 320, 100, 30);
        masc.setActionCommand("masc");

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(fem);
        radioGroup.add(masc);

        RadioButtonListener radioButtonListener = new RadioButtonListener();
        masc.addActionListener(radioButtonListener);
        fem.addActionListener(radioButtonListener);

        JLabel textNivelDeAtividade = new JLabel();
        textNivelDeAtividade.setText("Nivel de atividade do aluno:");
        textNivelDeAtividade.setBounds(10,360,250,30);

        JComboBox box = new JComboBox();
        box.setBounds(10, 390, 200, 25);
        box.setActionCommand("comboBox");
        box.addItem("Nenhum");
        box.addItem("Leve");
        box.addItem("Moderado");
        box.addItem("Intenso");

        System.out.println(box.getSelectedItem());

        ComboBoxListenner comboBoxListenner = new ComboBoxListenner();
        box.addActionListener((ActionListener) comboBoxListenner);

        JButton calcular = new JButton();
        calcular.setText("Calcular");
        Color verde = new Color(28, 123, 26);
        calcular.setBackground(verde);
        calcular.setBounds(10,450,100, 50);

        JButton limpar = new JButton();
        limpar.setText("Limpar");
        Color vermelho = new Color(255, 0, 0);
        limpar.setBackground(vermelho);
        limpar.setBounds(120,450,100,50);

        JLabel resultado = new JLabel();
        resultado.setText("Resultados");
        resultado.setBounds(200, 30, 200, 30);
        resultado.setFont(new Font("arial", Font.BOLD, 35));

        JLabel nomeResposta = new JLabel();
        nomeResposta.setText("Nome: ");
        nomeResposta.setBounds(200,70,70,30);

        JLabel nomeCliente = new JLabel();
        nomeCliente.setBounds(250,70,200,30);

        JLabel idade = new JLabel();
        idade.setText("Idade: ");
        idade.setBounds(200,100,70,30);

        JLabel idadeCliente = new JLabel();
        idadeCliente.setBounds(250,100,100, 30);

        JLabel imc = new JLabel();
        imc.setText("Imc: ");
        imc.setBounds(200,130,70,30);

        JLabel imcCliente = new JLabel();
        imcCliente.setBounds(250, 130, 70,30);

        JLabel ncd = new JLabel();
        ncd.setText("NCD: ");
        ncd.setBounds(200,160,70,30);

        JLabel ncdCliente = new JLabel();
        ncdCliente.setBounds(250,160,70,30);

        JLabel sttsImc = new JLabel();
        sttsImc.setText("Status IMC: ");
        sttsImc.setBounds(200,190,70,30);

        JLabel statusImc = new JLabel();
        statusImc.setBounds(280,190,100,30);

        calcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente();
                cliente.setNome(textNome.getText());
                cliente.setPeso(!textPeso.getText().equals("") ? Double.parseDouble(textPeso.getText()) : 0);
                cliente.setAltura(!textAltura.getText().equals("") ? Integer.parseInt(textAltura.getText()) : 0);
                cliente.setData(textData.getText());
                cliente.setSexo(radioGroup.getSelection().getActionCommand());
                cliente.setImc(imcCliente(cliente.getPeso(), cliente.getAltura()));



                try {
                    cliente.setIdade(descobrirIdade(cliente.getData()));
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }


                nomeCliente.setText(cliente.getNome());
                idadeCliente.setText(String.valueOf(cliente.getIdade()));
                imcCliente.setText(String.valueOf(cliente.getImc()));
                ncdCliente.setText(String.valueOf(calcularNcd(cliente)));
                statusImc.setText(calsularStatusImc(cliente.getImc()));

            }
        });

        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente();


                textNome.setText("");
                textPeso.setText("");
                textAltura.setText("");
                textData.setText("");
                
                nomeCliente.setText(cliente.getNome());
                idadeCliente.setText(String.valueOf(cliente.getIdade()));
                imcCliente.setText(String.valueOf(cliente.getImc()));
                ncdCliente.setText(String.valueOf(calcularNcd(cliente)));
                statusImc.setText(calsularStatusImc(cliente.getImc()));


            }
        });

        container.getContentPane().add(nome);
        container.getContentPane().add(altura);
        container.getContentPane().add(peso);
        container.getContentPane().add(data);
        container.getContentPane().add(textNome);
        container.getContentPane().add(textAltura);
        container.getContentPane().add(textPeso);
        container.getContentPane().add(textData);
        container.getContentPane().add(sexo);
        container.getContentPane().add(fem);
        container.getContentPane().add(masc);
        container.getContentPane().add(textNivelDeAtividade);
        container.getContentPane().add(box);
        container.getContentPane().add(calcular);
        container.getContentPane().add(limpar);
        container.getContentPane().add(resultado);
        container.getContentPane().add(nomeResposta);
        container.getContentPane().add(idade);
        container.getContentPane().add(imc);
        container.getContentPane().add(ncd);
        container.getContentPane().add(sttsImc);
        container.getContentPane().add(nomeCliente);
        container.getContentPane().add(idadeCliente);
        container.getContentPane().add(textAltura);
        container.getContentPane().add(textPeso);
        container.getContentPane().add(imcCliente);
        container.getContentPane().add(ncdCliente);
        container.getContentPane().add(statusImc);


        container.setVisible(true);
    }
    private Integer descobrirIdade(String data) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(data);

        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(date);
        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            idade--;
        }
        else
        {
            if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                idade--;
            }
        }

        return idade;
    }

    private double imcCliente(double peso, int altura ){
        System.out.println(altura);
        double alturaMetros = altura/100f;
        System.out.println(alturaMetros);
        double imc = peso/(alturaMetros * alturaMetros);
        return imc;
    }

    private double calcularNcd(Cliente cliente){
        double ncd = 0;
        if (cliente.getSexo().equals("masc")){
            ncd = 66 + (13.7 * cliente.getPeso()) + (5.0 * cliente.getAltura()) - (6.8 * cliente.getIdade());
        }else {
            ncd = 665 + (9.6 * cliente.getPeso()) + (1.8 * cliente.getAltura()) - (4.7 * cliente.getIdade());
        }
        return ncd;
    }

    private String calsularStatusImc(double imc){
        if (imc <18.5){
            return "Baixo peso";
        }else if (imc >=18.5 && imc <24.9){
            return "Peso Normal";
        }else if (imc >=25.0 && imc <29.9){
            return "Excesso de peso";
        }else if (imc >=30.0 && imc <34.9){
            return "Obesidade de Classe 1";
        }else if (imc >=35.0 && imc <39.9){
            return "Obesidade de Classe 2";
        }else {
            return "Obesidade de classe 3";
        }

    }

}
