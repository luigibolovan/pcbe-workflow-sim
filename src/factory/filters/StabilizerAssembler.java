package factory.filters;

import factory.pipe.FurniturePipe;
import factory.pipelines.Pipeline;


/**
 * @author Luigi Bolovan
 *
 * Stabilizer bar assembler class.
 * Once it gets the chairs from the GET pipe, it assembles the stabilizer bar and pushes them in the LOAD pipe.
 */

public class StabilizerAssembler {
    private FurniturePipe       mGetPipe;
    private FurniturePipe       mLoadPipe;
    private Pipeline            mPipeline;
    private final static int    STABILIZER_ASSEMBLING_TIME = 2000;

    public StabilizerAssembler(Pipeline pipeline){
        this.mPipeline = pipeline;
    }

    public void setLeftPipe(FurniturePipe leftPipe) {
        mGetPipe = leftPipe;
    }

    public void setRightPipe(FurniturePipe rightPipe) {
        mLoadPipe = rightPipe;
    }

    public void build() {
        int noOfChairsAssembled = 0;
        while(true) {
            if (mGetPipe.getUnassembledChair() == null) {
                // wait for a chair to put a stabilizer bar on
            } else {
                try{
                    Thread.sleep(STABILIZER_ASSEMBLING_TIME);
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
                while(mLoadPipe.getUnassembledChair() != null);//wait for others to finish their job....
                mLoadPipe.setUnassembledChair(mGetPipe.getUnassembledChair());
                noOfChairsAssembled++;
                System.out.println("Stabilizer assembler: chair with stabilizer sent!");
                mGetPipe.setUnassembledChair(null);
            }
            //my job here is done
            if(noOfChairsAssembled == mPipeline.getNoOfChairsToBuild()){
                break;
            }
        }
    }
}
