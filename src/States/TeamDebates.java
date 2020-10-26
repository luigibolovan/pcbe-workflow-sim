package States;

import com.company.EnvironmentVariables;

public class TeamDebates {
    public TeamDebates(){

    }

    public void run(){
        double randomValue = Math.random();
        EnvironmentVariables instance = EnvironmentVariables.getInstance();

        if (randomValue < 0.7)
            instance.setDocumentation(true);
        else
            instance.setDocumentation(false);
    }
}
