package com.company;

import States.*;

public class WorkflowSystem {

    public WorkflowSystem(){ }

    public void run() throws InterruptedException {
        EnvironmentVariables instance = EnvironmentVariables.getInstance();
        WriteDocumentation writeDocumentation = new WriteDocumentation();
        TeamDebates teamDebates = new TeamDebates();

        do{
            try{
                writeDocumentation.run();
            }
            catch (InterruptedException e){
                System.out.println("Exception thrown at writing documentation");
            }

            teamDebates.run();

        }while (!instance.isDocumentationOk());

        SendForPublication sendForPublication = new SendForPublication();

        try{
            sendForPublication.run();
        }
        catch (InterruptedException e){
            System.out.println("Exception thrown at sending for publication");
        }

        PublisingOnTheInternet publisingOnTheInternet = new PublisingOnTheInternet();
        SendToCustomers sendToCustomers = new SendToCustomers();

        Thread publishingOnTheInternetThread = new Thread(){
            public void run(){
                try{
                    publisingOnTheInternet.run();
                }
                catch (InterruptedException e){
                    System.out.println("Exception thrown at publishing on the internet");
                }
            }
        };

        Thread sendToCustomersThread = new Thread(){
            public void run(){
                try{
                    sendToCustomers.run();
                }
                catch (InterruptedException e){
                    System.out.println("Exception thrown at sending to customers");
                }
            }
        };

        publishingOnTheInternetThread.start();
        sendToCustomersThread.start();

        publishingOnTheInternetThread.join();
        sendToCustomersThread.join();

    }
}
