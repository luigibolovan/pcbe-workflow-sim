package ro.upt.ac.pcbe.pub.phases;

import ro.upt.ac.pcbe.system.WorkflowSystem;

public class ClientDeliverPhase {
    private final long CLIENT_DELIVER_TIME = 6000;

    public void run() {
        try {
            Thread.sleep(CLIENT_DELIVER_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Delivering to client");
        WorkflowSystem.setClientDeliverDone(true);
    }
}
