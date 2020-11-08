package ro.upt.ac.pcbe.dev.phases;

import ro.upt.ac.pcbe.system.ProjectPhases;
import ro.upt.ac.pcbe.system.WorkflowSystem;

public class DocumentationPhase {
    public void run() {
        System.out.println("Documentation phase");

        for (int i = 0; i < 200000; i++);

        WorkflowSystem.setCurrentState(ProjectPhases.DOCUMENTATION);
    }
}
