package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Lamina extends JPanel {
	public Lamina() {
		inicio=true;
		setLayout(new BorderLayout());
		display=new JButton("0");
		display.setEnabled(false);
		add(display, BorderLayout.NORTH);
		delete1=new JButton("DELETE");
		delete1.setBackground(Color.RED);
		ActionListener deleteAc=new ActionDelete();
		delete1.addActionListener(deleteAc);
		add(delete1, BorderLayout.EAST);
		lamina_botones=new JPanel();
		lamina_botones.setLayout(new GridLayout(4,4));
		add(lamina_botones, BorderLayout.CENTER);
		ActionListener inserta=new InsertaNumero();
		ActionListener operador=new InsertaOperador();
		insertaBotones("7", inserta);
		insertaBotones("8", inserta);
		insertaBotones("9", inserta);
		insertaBotones("/", operador);
		insertaBotones("4", inserta);
		insertaBotones("5", inserta);
		insertaBotones("6", inserta);
		insertaBotones("*", operador);
		insertaBotones("1", inserta);
		insertaBotones("2", inserta);
		insertaBotones("3", inserta);
		insertaBotones("-", operador);
		insertaBotones("0", inserta);
		insertaPDecimal(inserta);
		insertaBotones("+", operador);
		insertaBotones("=", operador);
		ultima_operacion= "=";	
	}
	private void insertaBotones(String valor, ActionListener oyente) {
		JButton boton=new JButton(valor);
		boton.addActionListener(oyente);
		lamina_botones.add(boton);
	}
	private void insertaPDecimal(ActionListener oyente) {
		pdecimal=new JButton(".");
		pdecimal.addActionListener(oyente);
		lamina_botones.add(pdecimal);
	}
	private class InsertaNumero implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			delete1.setEnabled(true);
			String entrada=e.getActionCommand();
			if(inicio) {
				display.setText("");
				inicio=false;
			}else if(entrada.equals(".")){
				pdecimal.setEnabled(false);	
			}
			delete1.setBackground(Color.GREEN);
			display.setText(display.getText() + entrada);
		}
	}
	private class InsertaOperador implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			pdecimal.setEnabled(true);
			delete1.setEnabled(false);
			String operacion=e.getActionCommand();
			calculaOperacion(Double.parseDouble(display.getText()));
			ultima_operacion=operacion;
			inicio=true;
			display.setText("" + resultado);
		}
		public void calculaOperacion(double x) {
			if(ultima_operacion.equals("+")) {
				resultado+= x;
			}else if(ultima_operacion.equals("-")) {
				resultado-=x;
			}else if(ultima_operacion.equals("*")) {
				resultado*=x;
			}else if(ultima_operacion.equals("/")) {
				resultado/=x;
			}else if(ultima_operacion.equals("=")) {
				resultado=x;
			}
		}	
	}
	private class ActionDelete implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(display.getText().length()>0) {
				display.setText(display.getText().substring(0,display.getText().length()-1));
			}	
		}	
	}
	private JButton display;
	private JButton delete1;
	private JButton pdecimal;
	private JPanel lamina_botones;
	private boolean inicio;
	private double resultado;
	private String ultima_operacion;
}
