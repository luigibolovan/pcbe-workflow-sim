package ro.upt.ac.pcbe.dev.phases;

import ro.upt.ac.pcbe.system.ProjectPhases;
import ro.upt.ac.pcbe.system.WorkflowSystem;

public class DebatePhase {
    public void run() {
        WorkflowSystem.setCurrentState(ProjectPhases.DEBATE);
        System.out.println("Project under discussions");
        double randomValue = Math.random();

        for (int i = 0; i < 200000; i++);

        if (randomValue > 0.75) {
            WorkflowSystem.setDocumentationDone(true);
        } else {
            WorkflowSystem.setDocumentationDone(false);
            WorkflowSystem.setCurrentState(ProjectPhases.NONE);
        }
    }
}
