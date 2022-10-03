package com.mycompany.calculadora;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    String num = "";
    String num2 = "";
    String op = "";
    String resul = "";
    String tela = "";
    boolean dec = false;
    boolean dec2 = false;
    boolean sinal = false;
    boolean res = false;

    @FXML
    private TextField tfTela;

    @FXML
    private void numClick(MouseEvent m) {
        Button b = (Button) m.getSource();
        
        if (sinal) {
            if (b.getText().equals(",")) {
                if (dec2 == false) {
                    num2 = num2 + ".";
                    dec2 = true;
                }
            } else {
                num2 = num2 + b.getText();
            }
        } else {
            if (b.getText().equals(",")) {
                if (dec == false) {
                    num = num + ".";
                    dec = true;
                }
            } else {
                num = num + b.getText();
            }
        }
        res = false;
        setTextTela();
    }

    public void opClick(MouseEvent m) {
        Button b = (Button) m.getSource();

        if (res) {
            num = resul;
            for (int i = 0; i < resul.length(); i++) {
                char a = resul.charAt(i);
                    
                if (a == '.') {
                    dec = true;
                }
            }
        }
        op = b.getText();
        sinal = true;
        res = false;
        setTextTela();
    }

    public void difClick(MouseEvent m) {
        Button b = (Button) m.getSource();
        if (b.getText().equals("CE")) {
            limpa();
            resul = "";
            setTextTela();
        } else if (b.getText().equals("x^2")) {
            if (res) {
                num = resul;
                for (int i = 0; i < num.length(); i++) {
                    char a = num.charAt(i);
                    if (a == '.') {
                        dec = true;
                    }
                }
            }
            op = "²";
            sinal = true;
            res = false;
            setTextTela();
        } else if (b.getText().equals("C")) {
            try{
                apagaDigito();
            }
            catch(StringIndexOutOfBoundsException e){
                
            }
            setTextTela();
        }
    }

    public void igualClick(MouseEvent m) {
        Button b = (Button) m.getSource();
        if (op != "²") {
            if (num2 != "") {
                calcula();
                tfTela.setText(resul);
                res = true;
                limpa();
            }
        } else {
            calcula();
            tfTela.setText(resul);
            res = true;
            limpa();
        }

    }

    public void setTextTela() {

        tela = num + op + num2;

        tfTela.setText(tela);
    }

    public void calcula() {
        double r;
        if (op.equals("*")) {
            r = Double.parseDouble(num) * Double.parseDouble(num2);
        } else if (op.equals("+")) {
            r = Double.parseDouble(num) + Double.parseDouble(num2);
        } else if (op.equals("-")) {
            r = Double.parseDouble(num) - Double.parseDouble(num2);
        } else if (op.equals("/")) {
            r = Double.parseDouble(num) / Double.parseDouble(num2);
        } else if (op.equals("%")) {
            r = Double.parseDouble(num) / 100 * Double.parseDouble(num2);
        } else if (op.equals("²")) {
            r = Double.parseDouble(num) * Double.parseDouble(num);
        } else {
            r = 0;
        }
        resul = Double.toString(r);
    }

    public void limpa() {
        num = "";
        num2 = "";
        op = "";
        tela = "";
        dec = false;
        dec2 = false;
        sinal = false;
    }

    public void apagaDigito() {
        boolean temOp = false;
        boolean opUltimoDigito = false;
        if (tfTela.getText() !="") {
            if (res) {
                num = resul;
                for (int i = 0; i < resul.length(); i++) {
                    char a = resul.charAt(i);

                    if (a == '.') {
                        dec = true;
                    }
                }
                setTextTela();
            }
            
            for (int i = 0; i < tela.length(); i++) {
                char a = tela.charAt(i);
                if (a == '%' || a == '²' || a == '*' || a == '/' || a == '-' || a == '+') {
                    temOp = true;
                    if (i == tela.length() - 1) {
                        opUltimoDigito = true;
                    }
                }
            }
            if (temOp && opUltimoDigito == false) {
                if (num2.charAt(num2.length() - 1) == '.') {
                    dec2 = false;
                }
                num2 = num2.substring(0, num2.length() - 1);

            } 
            else if (temOp && opUltimoDigito) {
                op = "";
                sinal = false;
            } 
            else if (temOp == false) {
                
                if (num.charAt(num.length() - 1) == '.') {
                    dec = false;
                }
                if (res) {
                    num = resul;
                    
                }
                res = false;
                num = num.substring(0, num.length() - 1);

            }
        }
    }

}
