package factory.filters;

import factory.pipe.FurniturePipe;
import factory.pipelines.Pipeline;

/**
 * @author Luigi Bolovan
 *
 * Backrest assembler chair.
 * Waits for a ChairInProgress to pull from the pipe and pushes it afterwards in the next pipe.
 */

public class BackrestAssembler {
    private FurniturePipe       mLoadPipe;
    private FurniturePipe       mGetPipe;
    private Pipeline            mPipeline;
    private final static int    BACKREST_ASSEMBLING_TIME = 1500;

    public BackrestAssembler(Pipeline pipeline){
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
        while (true) {
            if (mGetPipe.getUnassembledChair() == null) {
                //wait until a wild chair in progress appears
            }
            if (mGetPipe.getUnassembledChair() != null) {
                try{
                    Thread.sleep(BACKREST_ASSEMBLING_TIME);
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }

                while (mLoadPipe.getUnassembledChair() != null); //i hate waiting
                noOfAssembledChairs++;
                mLoadPipe.setUnassembledChair(mGetPipe.getUnassembledChair());
                System.out.println("Backrest assembler: Backrest sent!");
                mGetPipe.clearPipe();
            }

            //ready to stop
            if(noOfAssembledChairs == mPipeline.getNoOfChairsToBuild()){
                break;
            }
        }
    }
}