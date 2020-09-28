package factory.pipelines;

import factory.chairs.ChairInProgress;

/**
 * @author Luigi Bolovan
 *
 * Pipeline interface.
 */
public interface Pipeline {
    void    assembleChair(ChairInProgress chair);
    void    putAssemblersToWork();
    boolean isReady();
    void    stop(int noOfMillis);
    void    setNoOfChairsToBuild(int noOfInProgressChairs);
    int    getNoOfChairsToBuild();
}
