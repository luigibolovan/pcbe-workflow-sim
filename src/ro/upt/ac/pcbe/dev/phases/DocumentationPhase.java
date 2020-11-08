package ro.upt.ac.pcbe.dev.phases;

import ro.upt.ac.pcbe.system.ProjectPhases;
import ro.upt.ac.pcbe.system.WorkflowSystem;

public class DocumentationPhase {
    public void run() {
        WorkflowSystem.setCurrentState(ProjectPhases.DOCUMENTATION);
        System.out.println("Documentation phase");

        for (int i = 0; i < 200000; i++);
    }
}
