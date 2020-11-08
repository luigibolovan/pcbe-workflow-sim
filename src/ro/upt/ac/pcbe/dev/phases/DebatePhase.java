package ro.upt.ac.pcbe.dev.phases;

import ro.upt.ac.pcbe.system.ProjectPhases;
import ro.upt.ac.pcbe.system.WorkflowSystem;

public class DebatePhase {
    public void run() {
        WorkflowSystem.setCurrentState(ProjectPhases.DEBATE);
        System.out.println("Project under discussions");

        for (int i = 0; i < 100000; i++);

        double randomValue = Math.random();
        if (randomValue > 0.75) {
            WorkflowSystem.setDocumentationDone(true);
        } else {
            WorkflowSystem.setDocumentationDone(false);
            WorkflowSystem.setCurrentState(ProjectPhases.NONE);
        }
    }
}
