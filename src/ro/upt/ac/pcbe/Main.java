package ro.upt.ac.pcbe;

import ro.upt.ac.pcbe.system.WorkflowSystem;

public class Main {
    public static void main(String[] args) {
        WorkflowSystem.init();
        WorkflowSystem.start();

        java.lang.System.out.println("Success");
    }
}
