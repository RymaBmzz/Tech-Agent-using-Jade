package acheteur;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;


public class AcheteurContainer {

	public static void main(String[] args) {
		try {
			Runtime runtime= Runtime.instance();
			ProfileImpl profiles=new ProfileImpl(false);
			profiles.setParameter(ProfileImpl.MAIN_HOST,"localhost"); //relier au container main
			AgentContainer agentC= runtime.createMainContainer(profiles);
			//AgentContainer agentC= runtime.createAgentContainer(profile);
		    AgentController agController = agentC.createNewAgent("Acheteur1", AgAcheteur.class.getName(),new Object[]{});//argument new Object[]{}
		    agController.start();
		//    AgentContainer a = runtime.createMainContainer(profile);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
