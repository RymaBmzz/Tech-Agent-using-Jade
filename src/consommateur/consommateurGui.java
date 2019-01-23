package consommateur;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jade.gui.GuiEvent;


public class consommateurGui extends JFrame{
private JLabel jlabelAg = new JLabel("Nom Acheteur:");
private JLabel jlabelapp = new JLabel("Plateforme de vente de livre ");
private JTextField jtextfieldAg= new JTextField(12); //saisir acheteur
private JLabel jlabellivre = new JLabel("Livre:");
private JTextField jtextlivre=new JTextField(12);//saisir le livre
private JButton jbutenvoyer=new JButton("Envoyer");
private TextArea jtextAreamsg;
private JLabel jlabelcateg = new JLabel("Thème du livre :");
private JLabel jlabelsize = new JLabel("Format  :");
private JLabel jlabelstyle = new JLabel("Epaisseur :");
//private JTextField jtextfieldcateg= new JTextField(12); 
private JLabel jlabellang = new JLabel("Langue :");
private JLabel jlabeltitre = new JLabel("Titre :");
private JTextField jtextfieldtitre= new JTextField(12); 
private AgConsommateur consommAg;
java.awt.Choice categorie;
java.awt.Choice langue,size,style;
private String categchoisi;
private String languechoisi;
private String livre;
private String agent;
private String sizechoisi;
private String stylechoisi;

public AgConsommateur getConsommAg() {
	return consommAg;
}

public void setConsommAg(AgConsommateur consommAg) {
	this.consommAg = consommAg;
}

@SuppressWarnings("deprecation")
public consommateurGui()
{
	categorie = new java.awt.Choice(); //caractéristique 
	
	
	//String varName = categorie.getSelectedItem() ; 
	categorie.removeAll();
	categorie.addItem((String)"") ;
	categorie.addItem((String)"Médecine") ;
	categorie.addItem((String)"Informatique") ;
	categorie.addItem((String)"Economie") ;
	categorie.addItem((String)"Dictionnaire") ;
	
	
	langue = new java.awt.Choice(); //caractéristique 
   
	//String varName1 = langue.getSelectedItem() ; 
	langue.removeAll();
	langue.addItem((String)"") ;
	langue.addItem((String)"Français") ;
	langue.addItem((String)"Anglais") ;
	langue.addItem((String)"Arabe") ;
	
	size = new java.awt.Choice();
	size.removeAll();
	size.addItem((String) "");
	size.addItem((String) "Poche");
	size.addItem((String) "Normal");
	size.addItem((String) "Grand");
	
	style = new java.awt.Choice();
	style.removeAll();
	style.addItem((String) ""); 
	style.addItem((String) "Fin");
	style.addItem((String) "Epais");

this.setSize(700,400);
this.setTitle("Application Multi-Agents");
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setLocationRelativeTo(null);

setLayout(null);
addNotify();


jtextAreamsg = new java.awt.TextArea(" || Bienvenue sur notre application || \n\n || "); // log
jtextAreamsg.setFont(new Font("Arial",Font.BOLD,14));
jtextAreamsg.setEditable(false);
jtextAreamsg.setBackground(Color.gray);
jtextAreamsg.reshape(8,72,400,300);
add(jtextAreamsg);

jbutenvoyer.reshape(435,316,108,30);
add(jbutenvoyer);

jlabelapp.setFont(new Font("Arial",Font.BOLD,20));
jlabelapp.reshape(2, 0 , 300, 50);
add(jlabelapp);



jlabelAg.reshape(30, 20 ,150, 50);
add(jlabelAg);

jtextfieldAg.reshape(140, 40, 100, 20);
add(jtextfieldAg);

jlabelcateg.reshape(430, 80, 120, 24);
add(jlabelcateg);

//categorie.reshape(x, y, width, height);
categorie.reshape(550,80,120,24);
add(categorie);

jlabellang.reshape(430, 120, 70, 24);
add(jlabellang);

langue.reshape(550,120,120,24);
add(langue);

jlabelsize.reshape(430,160 , 70, 24);
add(jlabelsize);

size.reshape(550, 160, 120, 24);
add(size);


jlabelstyle.reshape(430, 200, 70, 24);
add(jlabelstyle);

style.reshape(550, 200, 120, 24);
add(style);

jlabeltitre.reshape(430, 240, 70, 24);
add(jlabeltitre);

jtextfieldtitre.reshape(550, 240, 120,24);
add(jtextfieldtitre);

this.setVisible(true);
jbutenvoyer.addActionListener(new ActionListener() {
	//quand on clique sur le bouton, on envoi param(agent,livre) à notre agent acheteur
	public void actionPerformed(ActionEvent e) {
		 agent= jtextfieldAg.getText();
		 livre= jtextfieldtitre.getText();
		 showMsg( agent, true);
		 showMsg( livre, true);
		
		GuiEvent gev= new GuiEvent(this,1);//1 =type du switch c'est num evenement
	    //envoyer les parametres
		Map<String,Object> params=new HashMap<>();
		params.put("agentAcheteur", agent);
		params.put("livre", livre);
		params.put("langue", languechoisi);
		params.put("categorie", categchoisi);
		gev.addParameter(params);
		consommAg.onGuiEvent(gev);
	
	
	
	}



});

}

//user selected a variable
void categorie_Clicked(Event event) {
categchoisi = categorie.getSelectedItem() ; 
showMsg( categchoisi, true);
}

void langue_Clicked(Event event){
	
	languechoisi = langue.getSelectedItem();
	showMsg(languechoisi, true);
}

void size_Clicked(Event event){
	
	sizechoisi = size.getSelectedItem();
	showMsg(sizechoisi, true);
}

void style_Clicked(Event event){
	
	stylechoisi = style.getSelectedItem();
	showMsg(stylechoisi, true);
}

public boolean handleEvent(Event event) 
{
	if (event.target == categorie && event.id == Event.ACTION_EVENT) {
		categorie_Clicked(event);
		return true;
		}
	
	if (event.target == langue && event.id == Event.ACTION_EVENT) {
		langue_Clicked(event);
		return true;
		}
	if (event.target == size && event.id == Event.ACTION_EVENT) {
		size_Clicked(event);
		return true;
		}
	
	if (event.target == style && event.id == Event.ACTION_EVENT) {
		style_Clicked(event);
		return true;
		}
	return false;	
}
public void showMsg(String msg,boolean b)//l'agent l'appel pour afficher un truc dans la gui
{
	if(b == true) //concaténation
		jtextAreamsg.append(msg+"\n");
	else //on ecrase tjr et on reecris
		jtextAreamsg.setText(msg);
}

}
