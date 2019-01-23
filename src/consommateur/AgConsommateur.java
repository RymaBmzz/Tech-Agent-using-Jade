package consommateur;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class AgConsommateur extends GuiAgent{//agent obliger de redéfinir méthode le reliant à la gui
 private consommateurGui gui;
 boolean b=true;
	protected void setup()
	{
		gui=new consommateurGui();
		gui.setConsommAg(this);//on associe l'agent à l'interface
		System.out.println("Démarrage de l'agent consommateur");
		
		addBehaviour(new CyclicBehaviour(this) { //boucle infinie, agent autonome, car il reste en écoute tt le temps
			
			public void action() {
				
				gui.showMsg("En attente d'un message ! ", true);
				ACLMessage msg=receive();
				if( msg != null)
				{
					switch (msg.getPerformative()) {
				//System.out.println("Message non nul "+ msg);
				case ACLMessage.REQUEST:
				gui.showMsg("Emetteur du msg: "+msg.getSender().getLocalName(), true);
				gui.showMsg("Contenu du msg: "+msg.getContent(), true);
				break;
				
				case ACLMessage.INFORM:
				String resultat= msg.getContent();
				gui.showMsg(resultat, false);
				break;
					}//fin switch
					}
				else
					block();//avertit l'agent tant que y a pas de msg
			}
				
			});//fin cyclic	
	   
	
	} //fin setup
	
	
	
	protected void onGuiEvent(GuiEvent event) {
		switch(event.getType())
		{
		case 1://event 1:envoie request à ag acheteur (nomag,livre)
			
		    //recuperer params, pour envoyer le livre,le récuperer de notre obj map
			Map<String,String> params=(Map<String,String>) event.getParameter(0);
			String agAcheteur=(String)params.get("agentAcheteur"); //agent et livre c nom des vars qu'on a nommé dans GUI
			System.out.println("destinataire: "+agAcheteur);
//			String livre=(String)params.get("livre");
//			String langue=(String)params.get("langue");
//			String categorie=(String)params.get("categorie");
			//Creer notre msg
			ACLMessage msg= new ACLMessage(ACLMessage.REQUEST);
			//si on a plusieurs acheteurs possibles,on doit recuperer l'annuaire des ag et choisir l'acheteur
			//dans notre contexte,on a un seul ag acheteur qui a le nom "acheteur", c'est un nom local
			msg.addReceiver(new AID(agAcheteur,AID.ISLOCALNAME));//creer agent destination qui va recevoir msg
			try {
				msg.setContentObject( (Serializable) params);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg.setOntology("achat-vente");
		//	System.out.println("dans consommateur ,livre= "+livre);
			send(msg);
			
			
		break;
		
		default:
			break;
		
		}
		
	}
	
}
