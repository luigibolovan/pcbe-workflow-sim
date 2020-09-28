package factory.filters;

import factory.chairs.ChairInProgress;
import factory.pipe.FurniturePipe;
import factory.pipelines.Pipeline;

/**
 * @author Luigi Bolovan
 *
 * Seat cutter class.
 * Receives chairs from the outside the pipeline and pushes them in the LOAD pipe for the next filter to get them.
 */

public class SeatCutter {
    private FurniturePipe       mLoadPipe;
    private Pipeline            mPipeline;
    private final static int    SEAT_CUTTING_TIME = 1000;

    public SeatCutter(Pipeline pipeline){
        this.mPipeline = pipeline;
    }

    public void setPipe(FurniturePipe pipe) {
        mLoadPipe = pipe;
    }

    public void build(ChairInProgress chair) {
        try {
            Thread.sleep(SEAT_CUTTING_TIME);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        while (mLoadPipe.getUnassembledChair() != null);// waiting for others...
        System.out.println("Cutter: Seat sent!");
        mLoadPipe.setUnassembledChair(chair);
    }

    public boolean isReady() {
        return mLoadPipe.getUnassembledChair() == null;
    }
}

