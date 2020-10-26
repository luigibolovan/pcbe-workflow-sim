package com.company;

public class EnvironmentVariables {
    private boolean isDocumentationOk;
    private static EnvironmentVariables instance = null;

    private EnvironmentVariables(){

    }

    public static EnvironmentVariables getInstance(){
        if (instance == null)
            instance = new EnvironmentVariables();

        return  instance;
    }

    public boolean isDocumentationOk(){
        return isDocumentationOk;
    }

    public void setDocumentation(boolean state){
        isDocumentationOk = state;
    }

}
