package factory.pipelines;

import factory.filters.*;
import factory.chairs.ChairInProgress;
import factory.pipe.FurniturePipe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Luigi Bolovan
 *
 * Implementation of the Pipeline interface.
 * Pipeline responsible for backrest chairs making. It needs the workers' order to run.
 */
public class BackrestChairPipeline implements Pipeline {
    private ChairPacker                 mPacker;
    private Thread                      mPackerJob;
    private int                         noOfChairs;
    private SeatCutter                  mSeatCutter;
    private Thread                      mBackrestJob;
    private FeetAssembler               mFeetAssembler;
    private Thread                      mFeetAssemblerJob;
    private BackrestAssembler           mBackrestAssembler;
    private StabilizerAssembler         mStabilizerAssembler;
    private Thread                      mStabilizerAssemblerJob;
    private static final int            NUMBER_OF_PIPES = 4;
    private ArrayList<FurniturePipe>    furniturePipes  = new ArrayList<>();

    public BackrestChairPipeline() {
        for (int i = 0; i < NUMBER_OF_PIPES; i++) {
            furniturePipes.add(new FurniturePipe());
        }
        setPipelineOrder();
    }

    /**
     * User CLI
     */
    private void setPipelineOrder() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter the order of the pipeline: \n" +
                    "C - seat cutter\n" +
                    "B - backrest assembler\n" +
                    "F - feet assembler\n" +
                    "S - stabilizer assembler\n" +
                    "P - chair packer\n" +
                    "Constraints: \n" +
                    "\t - seat cutter must be first\n" +
                    "\t - packer is the last\n" +
                    "\t - stabilizer bar assembler is after feet assembler\n" +
                    "Example: CBFSP\n" +
                    "Order:");

            String order;
            order = reader.readLine();
            checkEnteredOrder(order); //might halt the program if order is wrong :)
            populateFilters(); //order is ok; create new workers and assign them

            //once the workers' order has been chosen, set the pipes between them
            mSeatCutter.setPipe(furniturePipes.get(order.toLowerCase().indexOf('c')));
            System.out.println("Cutter will put chair in the " + furniturePipes.indexOf(furniturePipes.get(order.toLowerCase().indexOf('c'))) + " pipe");
            mBackrestAssembler.setLeftPipe(furniturePipes.get(order.toLowerCase().indexOf('b') - 1));
            System.out.println("Backrest assembler will get chair from the " + furniturePipes.indexOf(furniturePipes.get(order.toLowerCase().indexOf('b') - 1)) + " pipe");
            mBackrestAssembler.setRightPipe(furniturePipes.get(order.toLowerCase().indexOf('b')));
            System.out.println("Backrest assembler will put chair in the " + furniturePipes.indexOf(furniturePipes.get(order.toLowerCase().indexOf('b'))) + " pipe");
            mStabilizerAssembler.setLeftPipe(furniturePipes.get(order.toLowerCase().indexOf('s') - 1));
            System.out.println("Stabilizer assembler will get chair from the " + furniturePipes.indexOf(furniturePipes.get(order.toLowerCase().indexOf('s') - 1)) + " pipe");
            mStabilizerAssembler.setRightPipe(furniturePipes.get(order.toLowerCase().indexOf('s')));
            System.out.println("Stabilizer assembler will put chair in the " + furniturePipes.indexOf(furniturePipes.get(order.toLowerCase().indexOf('s'))) + " pipe");
            mFeetAssembler.setLeftPipe(furniturePipes.get(order.toLowerCase().indexOf('f') - 1));
            System.out.println("feet assembler will get chair from the " + furniturePipes.indexOf(furniturePipes.get(order.toLowerCase().indexOf('f') - 1)) + " pipe");
            mFeetAssembler.setRightPipe(furniturePipes.get(order.toLowerCase().indexOf('f')));
            System.out.println("feet assembler will put chair in the " + furniturePipes.indexOf(furniturePipes.get(order.toLowerCase().indexOf('f'))) + " pipe");
            mPacker.setPipe(furniturePipes.get(order.toLowerCase().indexOf('p') - 1));
            System.out.println("packer assembler will get chair from the " + furniturePipes.indexOf(furniturePipes.get(order.toLowerCase().indexOf('p') - 1)) + " pipe");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * input logic
     *
     * @param order : char sequence that holds the order of the workers
     *              if the order does not respect the rules, the program will be halted
     */
    private void checkEnteredOrder(String order) {
        if (order.toLowerCase().charAt(0) != 'c') {
            System.out.println("Seat cutter must be the first");
            System.exit(1);
        }
        if (order.toLowerCase().charAt(4) != 'p') {
            System.out.println("Packer must be the last in the pipeline order");
            System.exit(1);
        }
        if (order.toLowerCase().indexOf('f') > order.toLowerCase().indexOf('s')) {
            System.out.println("Stabilizer bar assembler must be after feet assembler");
            System.exit(1);
        }
        if (order.length() != 5) {
            System.out.println("Insert 5 letters only");
            System.exit(1);
        }
        if (    order.toLowerCase().indexOf('c') == -1 ||
                order.toLowerCase().indexOf('b') == -1 ||
                order.toLowerCase().indexOf('f') == -1 ||
                order.toLowerCase().indexOf('s') == -1 ||
                order.toLowerCase().indexOf('p') == -1  ) {

            System.out.println("One or more elements from pipeline are missing");
            System.exit(1);
        }
    }

    private void populateFilters() {
        mPacker = new ChairPacker(this);
        mSeatCutter = new SeatCutter(this);
        mFeetAssembler = new FeetAssembler(this);
        mBackrestAssembler = new BackrestAssembler(this);
        mStabilizerAssembler = new StabilizerAssembler(this);
    }

    @Override
    public void assembleChair(ChairInProgress chair) {
        mSeatCutter.build(chair);
    }

    @Override
    public void putAssemblersToWork() {
        mPackerJob = new Thread(() -> mPacker.pack());
        mBackrestJob = new Thread(() -> mBackrestAssembler.build());
        mFeetAssemblerJob = new Thread(() -> mFeetAssembler.build());
        mStabilizerAssemblerJob = new Thread(() -> mStabilizerAssembler.build());

        mPackerJob.start();
        mBackrestJob.start();
        mFeetAssemblerJob.start();
        mStabilizerAssemblerJob.start();
    }

    @Override
    public boolean isReady() {
        return mSeatCutter.isReady();
    }

    @Override
    public void stop(int noOfMillis) {
        try {
            mPackerJob.join(noOfMillis);
            mBackrestJob.join(noOfMillis);
            mFeetAssemblerJob.join(noOfMillis);
            mStabilizerAssemblerJob.join(noOfMillis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n\n--------That's all folks--------\n\n");
    }

    @Override
    public synchronized void setNoOfChairsToBuild(int noOfInProgressChairs) {
        this.noOfChairs = noOfInProgressChairs;
    }

    @Override
    public synchronized int getNoOfChairsToBuild() {
        return noOfChairs;
    }

}
