package ro.upt.ac.pcbe.pub;

import ro.upt.ac.pcbe.pub.phases.ClientDeliverPhase;
import ro.upt.ac.pcbe.pub.phases.InternetPublishingPhase;
import ro.upt.ac.pcbe.system.ProjectPhases;
import ro.upt.ac.pcbe.system.WorkflowSystem;

public class PubTeam {

    private final ClientDeliverPhase      clientPhase   = new ClientDeliverPhase();
    private final InternetPublishingPhase internetPhase = new InternetPublishingPhase();

    private final Thread internetPhaseThread = new Thread(internetPhase::run);

    private final Thread clientPhaseThread   = new Thread(clientPhase::run);

    public void run(){
        while (WorkflowSystem.getCurrentState() != ProjectPhases.PUBLISH);
        internetPhaseThread.start();
        clientPhaseThread.start();

        // wait to publish and deliver
        while (!WorkflowSystem.isInternetPublishingDone());
        while (!WorkflowSystem.isClientDeliverDone());

        System.out.println("Publishing team job is done");

        WorkflowSystem.setCurrentState(ProjectPhases.NO_OF_STATES);

        try {
            internetPhaseThread.join(2000);
            clientPhaseThread.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
