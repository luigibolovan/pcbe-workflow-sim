package com.company;

import States.TeamDebates;
import States.WriteDocumentation;

public class Main {
    public static void main(String[] args) {
        WorkflowSystem workflowSystem = new WorkflowSystem();

        try{
            workflowSystem.run();
        }
        catch (InterruptedException e){
            System.out.println("Some error was throw during the process");
        }

        System.out.println("Success");
    }
}
