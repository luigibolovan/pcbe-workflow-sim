package ro.upt.ac.pcbe.dev;

import ro.upt.ac.pcbe.dev.phases.DebatePhase;
import ro.upt.ac.pcbe.dev.phases.DocumentationPhase;
import ro.upt.ac.pcbe.system.ProjectPhases;
import ro.upt.ac.pcbe.system.WorkflowSystem;

public class DevTeam {

    private final DocumentationPhase docPhase = new DocumentationPhase();
    private final DebatePhase debatePhase     = new DebatePhase();

    public void run(){
        while(true) {
            if (WorkflowSystem.getCurrentState() == ProjectPhases.NONE) {
                docPhase.run();
            }

            if (WorkflowSystem.getCurrentState() == ProjectPhases.DOCUMENTATION){
                debatePhase.run();
            }

            if (WorkflowSystem.isDocumentationDone()){
                WorkflowSystem.setCurrentState(ProjectPhases.PUBLISH);
                System.out.println("Development team finished its job");
                break;
            }
        }
    }
}
