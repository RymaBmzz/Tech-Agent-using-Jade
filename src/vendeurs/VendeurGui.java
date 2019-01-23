package vendeurs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jade.gui.GuiEvent;
import jade.wrapper.AgentController;


public class VendeurGui extends JFrame{
private JLabel jlabelAg = new JLabel("Nom Vendeur:");
private JTextField jtextfieldAg= new JTextField(12); //saisir acheteur
private JButton jbutenvoyer=new JButton("Déployer agent vendeur");
private JTextArea jtextAreamsg= new JTextArea();
private AgVendeur vendeurAg;



public AgVendeur getVendeurAg() {
	return vendeurAg;
}

public void setVendeurAg(AgVendeur vendeurAg) {
	this.vendeurAg = vendeurAg;
}

public VendeurGui()
{

JPanel jpanelNord=new JPanel();
jpanelNord.setLayout(new FlowLayout());
jpanelNord.add(jlabelAg);
jpanelNord.add(jtextfieldAg);

jpanelNord.add(jbutenvoyer);
this.setLayout(new BorderLayout());//diviser en 5 zones : Nord/sud/est/ouest/centre
this.add(jpanelNord,BorderLayout.NORTH);
this.add(new JScrollPane(jtextAreamsg), BorderLayout.CENTER);
this.setSize(500,200);
this.setTitle("Vendeur");
this.setVisible(true);
jbutenvoyer.addActionListener(new ActionListener() {
	//quand on clique sur le bouton, on envoi param(agent,livre) à notre agent acheteur
	public void actionPerformed(ActionEvent e) {
		String agent= jtextfieldAg.getText();
	  
	}
});

}

public void showMsg(String msg,boolean b)//l'agent l'appel pour afficher un truc dans la gui
{
	if(b == true) //concaténation
		jtextAreamsg.append(msg+"\n");
	else //on ecrase tjr et on reecris
		jtextAreamsg.setText(msg);
}

}
