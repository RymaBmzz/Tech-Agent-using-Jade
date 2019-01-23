package acheteur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class AgAcheteur extends Agent {
  
	int requesterCount;
	private String conversationID;
	private List<AID> vendeurs=new ArrayList<>(),listvendeurs=new ArrayList<>();	
	static String livre_trans;
	int nb_trans=0;
	private AID meilleureOffre,client;
	private Double meilleurPrix;
	private int index;
	private Double prix_prod;
	Map<String, String> paramsrecu;
	private String livre,categorie,langue;
	int test=0;
protected void setup()
{
	
ParallelBehaviour pb=new ParallelBehaviour();
addBehaviour(pb);	

System.out.println("Recherche des services...");



pb.addSubBehaviour(new CyclicBehaviour() {

	@Override
	public void action() {
		ACLMessage msg=receive();
		if(msg != null ){
			switch (msg.getPerformative()) {
			
			case ACLMessage.REQUEST:
				try {
					 paramsrecu= (Map<String, String>) msg.getContentObject();
				} catch (UnreadableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				 livre=(String)paramsrecu.get("livre");
				 System.out.println("livre de parametres =" + livre);
				langue=(String)paramsrecu.get("langue");
				categorie=(String)paramsrecu.get("categorie");
			    System.out.println();
			client=msg.getSender() ;
			livre_trans=livre;
			System.out.println(" || Demande d'achats du livre :"+livre+" reçu" +"de la catégorie "+categorie);
		
		//envoyer reponse au consommateur qui a envoyer la requete
			ACLMessage msg2=msg.createReply();
			msg2.setPerformative(ACLMessage.INFORM);
			msg2.setContent(" || Demande reçu, traitement en cours !\n");
			send(msg2);
			
			ACLMessage msg3=new ACLMessage(ACLMessage.CFP);
			msg3.setContent(livre);//envoyer nom livre à la liste des vendeurs
		
			if(categorie.equals("Informatique") && ( langue.equals("Français") || langue.equals("Arabe"))) //informatique + français + anglais
			{
			AID f=new AID("Vendeur1",AID.ISLOCALNAME);
			AID f2= new AID("Vendeur2",AID.ISLOCALNAME);
			msg3.addReceiver(f);
			msg3.addReceiver(f2);
			vendeurs.add(f); vendeurs.add(f2);
            test++;
			//System.out.println("AID vendeur= "+r);
			}
			
			if(categorie.equals("Médecine") && (langue.equals("Français") || langue.equals("Arabe") )) //informatique + français + anglais
			{
				AID f3=new AID("Vendeur3",AID.ISLOCALNAME);
				AID f4= new AID("Vendeur4",AID.ISLOCALNAME);
			msg3.addReceiver(f3);
			msg3.addReceiver(f4);
			vendeurs.add(f3); vendeurs.add(f4);
			test++;
			//System.out.println("AID vendeur= "+r);
			}
			if(categorie.equals("Informatique") && langue.equals("Anglais")) //informatique + français + anglais
			{
			AID f6=new AID("Vendeur6",AID.ISLOCALNAME);
			AID f5=new AID("Vendeur5",AID.ISLOCALNAME);
			
			msg3.addReceiver(f5);
			msg3.addReceiver(f6);
			vendeurs.add(f5);
		    vendeurs.add(f6);
		    test++;
			//System.out.println("AID vendeur= "+r);
			}
			if(categorie.equals("Economie") && (langue.equals("Français")||langue.equals("Anglais")||langue.equals("Arabe"))) //informatique + français + anglais
			{
			AID f4=new AID("Vendeur4",AID.ISLOCALNAME);
			AID f5=new AID("Vendeur5",AID.ISLOCALNAME);
			
			msg3.addReceiver(f4);
			msg3.addReceiver(f5);
			vendeurs.add(f4);
		    vendeurs.add(f5);
		    test++;
			//System.out.println("AID vendeur= "+r);
			}
			
			if(categorie.equals("Dictionnaire") && (langue.equals("Français")||langue.equals("Anglais")||langue.equals("Arabe"))) //informatique + français + anglais
			{
			AID f1=new AID("Vendeur1",AID.ISLOCALNAME);
			AID f6=new AID("Vendeur6",AID.ISLOCALNAME);
			
			msg3.addReceiver(f1);
			msg3.addReceiver(f6);
			vendeurs.add(f1);
		    vendeurs.add(f6);
		    test++;
			//System.out.println("AID vendeur= "+r);
			}
			
			if(test == 0){
				System.out.println("pas de vendeurs");
			ACLMessage msgrep=new ACLMessage(ACLMessage.INFORM);
			msgrep.setPerformative(ACLMessage.INFORM);
			AID vd=new AID("consommateur",AID.ISLOCALNAME);
			msgrep.addReceiver(vd);
			String rep="Pas de vendeurs trouvé pour ces critères la\n\n Veuillez Refaire vos choix !\n";
			msgrep.setContent(rep);
			send(msgrep);
			}
			else{
			System.out.println("dans acheteur, livre ="+ livre);
			send(msg3);
			}
			break;
			
			case ACLMessage.PROPOSE: //Propose or confirm
			String prix= msg.getContent();	
			System.out.println("prix reçu= "+prix);
			AID requester= msg.getSender();//consommateur
			nb_trans++;
			conversationID=" || transaction num "+nb_trans+" livre: "+livre_trans;
			System.out.println(conversationID);
			//pb.addSubBehaviour(new RequestBehaviour(myAgent,livre_trans,requester,conversationID,msg));
			//msg à passer à la classe reste remplie et comme cyclic donc refait traitment
		    
			
				try {
					System.out.println("a");
					prix_prod=(Double)msg.getContentObject();
				} catch (UnreadableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			System.out.println("***********************************");
			//System.out.println("Conversation ID:"+msg.getConversationId());
			System.out.println("Réception de l'offre :");
			System.out.println("From :"+msg.getSender().getName());
			System.out.println("Prix="+prix);
			if(index==0)
			{
			meilleurPrix=prix_prod; 
			meilleureOffre= msg.getSender(); //aid du meilleur vendeur
			}
			else
			{
			if(prix_prod<meilleurPrix){
			meilleurPrix=prix_prod;
			meilleureOffre= msg.getSender(); //aid du meilleur vendeur
			} 
			}

			++index;
			if(index ==2)
			{//on a reçu des propositions de tous les vendeurs
			index=0; System.out.println("||||| ------------------------ |||||");
			System.out.println("Conclusion de la transaction.......");
			ACLMessage aclMessage2=new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
			aclMessage2.addReceiver(meilleureOffre); //repondre au meilleur vendeur
			aclMessage2.setConversationId(conversationID);
			System.out.println("...... En cours"); 
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myAgent.send(aclMessage2);
			}
			
			break;
            
			case ACLMessage.CONFIRM://vendeur confirmer la transaction
				System.out.println(".........................");
				System.out.println("Réception de la confirmation ...");
				//System.out.println("Conversation ID:"+msg.getConversationId());
				ACLMessage msg31=new ACLMessage(ACLMessage.INFORM);
				msg31.addReceiver(client);
				msg31.setConversationId(conversationID);
				msg31.setContent(" \n|| || transaction || || \n"
				+ " || livre acheté : "+livre_trans+"  ||\n"
				+ " || prix : "+meilleurPrix+"  ||\n"
				+ " || fournisseur: "+msg.getSender().getLocalName()+" || \n"//nom vendeur
				+ " || || Fin transaction || ||");
				myAgent.send(msg31);
			}//fin switch
			}
		else block();
		
	}
});

}
}

