import factory.FurnitureFactory;
import factory.pipelines.BackrestChairPipeline;

/**
 * @author Luigi Bolovan
 *
 * Client class used to order some chairs.
 */

public class Client {

    public static void main(String[] args){
        FurnitureFactory myFactory = new FurnitureFactory(new BackrestChairPipeline());
        myFactory.getOrders();
    }
}
