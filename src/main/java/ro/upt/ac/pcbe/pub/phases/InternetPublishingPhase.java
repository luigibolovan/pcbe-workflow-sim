package ro.upt.ac.pcbe.pub.phases;

import ro.upt.ac.pcbe.system.WorkflowSystem;

public class InternetPublishingPhase {
    private final long INTERNET_PUBLISHING_TIME = 3000;

    public void run() {
        try {
            Thread.sleep(INTERNET_PUBLISHING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Publishing on the Internet");
        WorkflowSystem.setInternetPublishingDone(true);
    }
}
