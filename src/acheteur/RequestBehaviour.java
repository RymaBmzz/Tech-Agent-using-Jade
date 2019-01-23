package acheteur;
import jade.core.AID; import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException; import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription; import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate; import java.util.ArrayList; import java.util.List;

class RequestBehaviour extends OneShotBehaviour{
private String conversationID;
private AID requester; private String livre;
private double prix; private int compteur;
private List<AID> vendeurs=new ArrayList<>();
private AID meilleureOffre;
private double meilleurPrix;
private int index;
private ACLMessage msg_recu_acht;
public RequestBehaviour(Agent agent,String livre,AID requester,String
conversationID,ACLMessage msg_recu_acht) {
super(agent);
this.msg_recu_acht=msg_recu_acht;
this.livre=livre; this.requester=requester;
this.conversationID=conversationID;
System.out.println("Recherche des services...");

//remplir list vendeurs
AID f=new AID("Vendeur1",AID.ISLOCALNAME);
AID f2=new AID("Vendeur2",AID.ISLOCALNAME);
vendeurs.add(f); vendeurs.add(f2);

System.out.println("Liste des vendeurs trouvés :");
try {
for(AID aid:vendeurs){
System.out.println("===="+aid.getLocalName());
}

} catch (Exception e) {
e.printStackTrace();
}
}
//@Override
public void action() {
try {

/*if(this.msg_recu_acht!=null)
{*/
switch(this.msg_recu_acht.getPerformative()){
case ACLMessage.PROPOSE :
prix=Double.parseDouble(this.msg_recu_acht.getContent());
System.out.println("***********************************");
System.out.println("Conversation ID:"+this.msg_recu_acht.getConversationId());
System.out.println("Réception de l'offre :");
System.out.println("From :"+this.msg_recu_acht.getSender().getName());
System.out.println("Prix="+prix);
if(index==0)
{
meilleurPrix=prix; 
meilleureOffre= this.msg_recu_acht.getSender(); //aid du meilleur vendeur
}
else
{
if(prix<meilleurPrix){
meilleurPrix=prix;
meilleureOffre= this.msg_recu_acht.getSender(); //aid du meilleur vendeur
} 
}

++index;
if(index==vendeurs.size())
{//on a reçu des propositions de tous les vendeurs
index=0; System.out.println("-----------------------------------");
System.out.println("Conclusion de la transaction.......");
ACLMessage aclMessage2=new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
aclMessage2.addReceiver(meilleureOffre); //repondre au meilleur vendeur
aclMessage2.setConversationId(conversationID);
System.out.println("...... En cours"); 
Thread.sleep(5000);
myAgent.send(aclMessage2);
}
break;

case ACLMessage.CONFIRM://vendeur confirmer la transaction
System.out.println(".........................");
System.out.println("Réception de la confirmation ...");
System.out.println("Conversation ID:"+this.msg_recu_acht.getConversationId());
ACLMessage msg3=new ACLMessage(ACLMessage.INFORM);
msg3.addReceiver(requester);
msg3.setConversationId(conversationID);
msg3.setContent("<transaction>"
+ "<livre>"+livre+"</livre>"
+ "<prix>"+meilleurPrix+"</prix>"
+ "<fournisseur>"+this.msg_recu_acht.getSender().getName()+"</fournisseur>"//nom vendeur
+ "</transaction");
myAgent.send(msg3);
break;
} /*}
else{ block(); }*/
} catch (Exception e) { e.printStackTrace(); }
}
}