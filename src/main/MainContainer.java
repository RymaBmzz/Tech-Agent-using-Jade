package main;

import acheteur.AgAcheteur;
import consommateur.AgConsommateur;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import vendeurs.AgVendeur;

public class MainContainer extends Agent {
	public static ProfileImpl profile;
	
	public static void main(String[] args) {
		
		try {
			Runtime rt= Runtime.instance();
			Properties p= new ExtendedProperties();
		p.setProperty(Profile.GUI, "true"); //false pr pas afficher la gui
//			p.setProperty(Profile.LOCAL_HOST, "127.0.0.1");
//	    	p.setProperty(Profile.LOCAL_PORT, "1050");
			profile=new ProfileImpl(p);
			AgentContainer agentC= rt.createMainContainer(profile);
			AgentContainer agentA= rt.createAgentContainer(new ProfileImpl(p));
			
	AgentController agController = agentA.createNewAgent("consommateur", AgConsommateur.class.getName(),new Object[]{});//argument new Object[]{}
			    agController.start();
//		     
	   AgentController agAch = agentA.createNewAgent("Acheteur1", AgAcheteur.class.getName(),new Object[]{});
	   //nom local, classeAgent , argument new Object[]{}
		    agAch.start();
//			    
//			  //déployer autant d'agent vendeurs qu'on veut ici
	   AgentController agVend = agentA.createNewAgent("Vendeur1", AgVendeur.class.getName(),new Object[]{});//argument new Object[]{}
			    agVend.start();
	   
	AgentController agVend2 = agentA.createNewAgent("Vendeur2", AgVendeur.class.getName(),new Object[]{});//argument new Object[]{}
				    agVend2.start();
				    
   AgentController agVend3 = agentA.createNewAgent("Vendeur3", AgVendeur.class.getName(),new Object[]{});//argument new Object[]{}
				    agVend3.start();
		   
		AgentController agVend4 = agentA.createNewAgent("Vendeur4", AgVendeur.class.getName(),new Object[]{});//argument new Object[]{}
					    agVend4.start();
	 AgentController agVend5 = agentA.createNewAgent("Vendeur5", AgVendeur.class.getName(),new Object[]{});//argument new Object[]{}
					    agVend5.start();
	 AgentController agVend6 = agentA.createNewAgent("Vendeur6", AgVendeur.class.getName(),new Object[]{});//argument new Object[]{}
					    agVend6.start();
//		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
