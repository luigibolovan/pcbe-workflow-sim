package ro.upt.ac.pcbe.system;

import ro.upt.ac.pcbe.dev.DevTeam;
import ro.upt.ac.pcbe.pub.PubTeam;

public class WorkflowSystem {

    private static boolean          documentationDone;
    private static Thread           devThread;
    private static Thread           pubThread;
    private static ProjectPhases    currentState;
    private static boolean          clientDeliverDone;
    private static boolean          internetPublishingDone;

    private final static DevTeam devTeam = new DevTeam();
    private final static PubTeam pubTeam = new PubTeam();

    private WorkflowSystem(){ }

    public synchronized static boolean isInternetPublishingDone() {
        return internetPublishingDone;
    }

    public synchronized static void setInternetPublishingDone(boolean internetPublishingDone) {
        WorkflowSystem.internetPublishingDone = internetPublishingDone;
    }

    public synchronized static boolean isClientDeliverDone() {
        return clientDeliverDone;
    }

    public synchronized static void setClientDeliverDone(boolean clientDeliverDone) {
        WorkflowSystem.clientDeliverDone = clientDeliverDone;
    }

    public static synchronized ProjectPhases getCurrentState() {
        return currentState;
    }

    public static synchronized void setCurrentState(ProjectPhases currentState) {
        WorkflowSystem.currentState = currentState;
    }

    public synchronized static boolean isDocumentationDone() {
        return documentationDone;
    }

    public synchronized static void setDocumentationDone(boolean documentationState){
        documentationDone = documentationState;
    }

    public static void init() {
        WorkflowSystem.setCurrentState(ProjectPhases.NONE);
        devThread = new Thread(devTeam::run);
        pubThread = new Thread(pubTeam::run);
    }

    public static void start() {
        devThread.start();
        pubThread.start();

        while (getCurrentState() != ProjectPhases.NO_OF_STATES);

        try {
            devThread.join(2000);
            pubThread.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
