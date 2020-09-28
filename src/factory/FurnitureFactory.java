package factory;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import factory.pipelines.Pipeline;
import factory.chairs.ChairInProgress;

/**
 * @author Luigi Bolovan
 *
 * Furniture factory class.
 * It receives orders from the clients and passes them to its pipeline.
 */
public class FurnitureFactory {
    private Pipeline mPipeline;

    public FurnitureFactory(Pipeline pipeline){
        mPipeline = pipeline;
    }

    public void setPipeline(Pipeline pipeline){
        mPipeline = pipeline;
    }

    public void getOrders(){
        int noOfChairs = getNoOfChairs();
        int chairIndex = 0;

        if(noOfChairs > 0){
            mPipeline.putAssemblersToWork();
            mPipeline.setNoOfChairsToBuild(noOfChairs);
            System.out.println("\n\n----------------Chairs: " + noOfChairs + "----------------\n\n");

            while(chairIndex < noOfChairs){
                if(mPipeline.isReady()) {
                    mPipeline.assembleChair(new ChairInProgress());
                    chairIndex++;
                }
            }
        }
    }

    private int getNoOfChairs() {
        int noOfChairs = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the number of chairs:");
        try{
            noOfChairs = Integer.parseInt(reader.readLine());
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        return noOfChairs;
    }
}
