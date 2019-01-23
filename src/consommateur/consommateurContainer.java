package consommateur;

import jade.wrapper.AgentContainer;
import jade.core.MainContainer;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class consommateurContainer {

	public static void main(String[] args) {
		try {
			Runtime runtime= Runtime.instance();
			ProfileImpl profiles=new ProfileImpl(false);
			profiles.setParameter(ProfileImpl.MAIN_HOST,"localhost"); //relier au container main
	    	//profile.setParameter(ProfileImpl.LOCAL_PORT, "1000");
			//profile.setProperty(ProfileImpl.LOCAL_PORT, "1050");
		//	AgentContainer agentC= runtime.createMainContainer(profiles);
			AgentContainer agentC= runtime.createAgentContainer(main.MainContainer.profile);
		    AgentController agController = agentC.createNewAgent("consommateur", AgConsommateur.class.getName(),new Object[]{});//argument new Object[]{}
		    agController.start();
		//    AgentContainer a = runtime.createMainContainer(profile);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
