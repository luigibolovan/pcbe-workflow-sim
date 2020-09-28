package factory.pipe;

import factory.chairs.ChairInProgress;

/**
 * @author Luigi Bolovan
 *
 * Pipe class.
 * Stores ChairInProgress objects that are pushed or pulled by the filters.
 */
public class FurniturePipe {
    private ChairInProgress mUnassembledChair;

    public synchronized void setUnassembledChair(ChairInProgress unassembledChair){
        mUnassembledChair = unassembledChair;
    }

    public synchronized void clearPipe(){
        this.mUnassembledChair = null;
    }

    public synchronized ChairInProgress getUnassembledChair(){
        return mUnassembledChair;
    }
}
