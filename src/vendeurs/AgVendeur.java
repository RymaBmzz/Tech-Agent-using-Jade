package vendeurs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;


public class AgVendeur extends Agent {
	private VendeurGui gui;
	private Map<String, Double> data=new HashMap<>();
protected void setup()
{
	/*gui=new VendeurGui();
	gui.setVendeurAg(this);//on associe l'agent à l'interface
*/
	System.out.println("Initialisation de l'agent vendeur: "+this.getAID().getLocalName());
	ParallelBehaviour pb=new ParallelBehaviour();
	addBehaviour(pb);	
//	data.put("XML", new Double(230+Math.random()*200));
//	data.put("JAVA", new Double(460+Math.random()*200));
//	data.put("IOT", new Double(540+Math.random()*200));
switch(this.getLocalName()){
	
	case "Vendeur1" :  data.put("XML", new Double(230+Math.random()*200));
	                   data.put("JAVA", new Double(460+Math.random()*200));
	                   data.put("IOT", new Double(540+Math.random()*200));
	                   data.put("Csharp",new Double(130+Math.random()*200));
	                   data.put("larousse", new Double(200+Math.random()*200));
	           		   data.put("Hachette", new Double(560+Math.random()*200));
	           		  data.put("Le robert", new Double(540+Math.random()*200));
	           		  data.put("Oxford", new Double(560+Math.random()*200));

	case "Vendeur2" :
		
		data.put("XML", new Double(230+Math.random()*200));
		data.put("JAVA", new Double(460+Math.random()*200));
		data.put("IOT", new Double(540+Math.random()*200));
		data.put("Csharp",new Double(130+Math.random()*200));
		data.put("Compatabilité ", new Double(330+Math.random()*200));
		

	case "Vendeur3" :
		
		data.put("ANATOMIE", new Double(200+Math.random()*200));
		data.put("CYTHOLOGIE", new Double(560+Math.random()*200));
		data.put("GASTRON", new Double(540+Math.random()*200));
		data.put("CARDIOLOGIE",new Double(230+Math.random()*200));	
			
		
	case "Vendeur4" :
		data.put("ANATOMIE", new Double(200+Math.random()*200));
		data.put("CYTHOLOGIE", new Double(560+Math.random()*200));
		data.put("GASTRON", new Double(540+Math.random()*200));
		data.put("Compatabilité ",new Double(230+Math.random()*200));

	case "Vendeur5" :
		
		 data.put("XML", new Double(230+Math.random()*200));
		data.put("JAVA", new Double(460+Math.random()*200));
		data.put("IOT", new Double(540+Math.random()*200));
		data.put("Csharp",new Double(130+Math.random()*200));
		data.put("Compatabilité ",new Double(230+Math.random()*200));
		
	case "Vendeur6" :
		data.put("larousse", new Double(200+Math.random()*200));
		data.put("Hachette", new Double(560+Math.random()*200));
		data.put("Le robert", new Double(540+Math.random()*200));
		data.put("Oxford", new Double(560+Math.random()*200));
	    data.put("XML", new Double(230+Math.random()*200));
		data.put("JAVA", new Double(460+Math.random()*200));
		data.put("IOT", new Double(540+Math.random()*200));
		data.put("Csharp",new Double(130+Math.random()*200));
	
		
	}
	
	
	
				


pb.addSubBehaviour(new CyclicBehaviour() {

	@Override
	public void action() {
		//envoyer reponse à l'acheteur qui a envoyer la requete
		/*ACLMessage msg1=receive();
		ACLMessage msg2=msg1.createReply();
		msg2.setPerformative(ACLMessage.INFORM);
		gui.showMsg(msg1.getContent(), true);
		msg2.setContent("Recherche dans le stock en cours !");
		send(msg2);*/
		ACLMessage msg=receive();
		
	if(msg != null ){
		switch (msg.getPerformative()) {
		case ACLMessage.CFP : //call for proposal
		// gui.showMsg(msg.getContent(), true);

//			System.out.println("--------------------------------");
//			System.out.println("Conversation ID:"+msg.getConversationId());
			String livre=msg.getContent();
			String compteur=msg.getUserDefinedParameter("compteur");
//			System.out.println("Réception d'un message :"+compteur);
//			System.out.println("Expéditeur :"+msg.getSender().getName());
//			System.out.println("Contenu:"+livre);
//			System.out.println("--------------------------------");
			Double prix=(Double)data.get(livre);
			ACLMessage reply=msg.createReply();
			reply.setPerformative(ACLMessage.PROPOSE);
			try {
				reply.setContentObject(prix);
//				System.out.println(msg.getSender().getLocalName());
//				System.out.println(prix);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("...... En cours");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			send(reply);
			break;
			
		case ACLMessage.ACCEPT_PROPOSAL: //quand un agent a accepté la proposition
			System.out.println("--------------------------------");
			System.out.println("Conversation ID:"+msg.getConversationId());
			System.out.println("Validation de la transaction .....");
			ACLMessage reply2=msg.createReply();
			reply2.setPerformative(ACLMessage.CONFIRM);
			System.out.println("...... En cours");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			send(reply2);
			
			break;

		default:
			break;
		}
		}
		else block();
		
	}
});
}

public void onGuiEvent(GuiEvent gev) {
	
}

}
