package factory.filters;

import factory.pipe.FurniturePipe;
import factory.pipelines.Pipeline;

/**
 * @author Luigi Bolovan
 *
 * Feet assembler chair.
 * Waits for chairs in the GET pipe, assembles feet to these chairs and pushes them in the LOAD pipe.
 */

public class FeetAssembler {
    private FurniturePipe       mGetPipe;
    private FurniturePipe       mLoadPipe;
    private Pipeline            mPipeline;
    private final static int    FEET_ASSEMBLING_TIME = 4000;

    public FeetAssembler(Pipeline pipeline){
        mPipeline = pipeline;
    }
    public void setLeftPipe(FurniturePipe leftPipe) {
        mGetPipe = leftPipe;
    }

    public void setRightPipe(FurniturePipe rightPipe) {
        mLoadPipe = rightPipe;
    }

    public void build() {
        int noOfAssembledChairs = 0;
        while(true) {
            if (mGetPipe.getUnassembledChair() == null) {
                //wait for a chair to join the GET pipe
            }else{
                try{
                    Thread.sleep(FEET_ASSEMBLING_TIME);
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }

                while(mLoadPipe.getUnassembledChair() != null); //waiting for others...

                noOfAssembledChairs++;
                mLoadPipe.setUnassembledChair(mGetPipe.getUnassembledChair());
                System.out.println("Feet assembler: chair with feet sent!");
                mGetPipe.clearPipe();
            }

            //my job here is done
            if(noOfAssembledChairs == mPipeline.getNoOfChairsToBuild()){
                break;
            }
        }
    }
}
