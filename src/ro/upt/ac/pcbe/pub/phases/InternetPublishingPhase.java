package ro.upt.ac.pcbe.pub.phases;

import ro.upt.ac.pcbe.system.WorkflowSystem;

public class InternetPublishingPhase {
    private static final long INTERNET_PUBLISHING_TIME = 5000;

    public void run() {
        System.out.println("Publishing on the Internet");

        try {
            Thread.sleep(INTERNET_PUBLISHING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WorkflowSystem.setInternetPublishingDone(true);
    }
}
