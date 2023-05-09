package calculator;

import javax.swing.JFrame;

public class Marco extends JFrame{
	public Marco() {
		setBounds(500,200,300,400);
		setVisible(true);
		Lamina lamina=new Lamina();
		add(lamina);
	}
}
