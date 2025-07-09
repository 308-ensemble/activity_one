package Fleet;

public class DeliveryTracker {
    // Using the custom LinkedListQueue
    private LinkedListQueue<Delivery> deliveryQueue;

    public DeliveryTracker() {
        this.deliveryQueue = new LinkedListQueue<>();
    }

    public void addDelivery(Delivery delivery) {
        deliveryQueue.enqueue(delivery);
        System.out.println("New delivery added to queue: " + delivery.getPackageId());
    }

    public Delivery dispatchNextDelivery() {
        return deliveryQueue.dequeue();
    }

    public Delivery viewNextDelivery() {
        return deliveryQueue.peek();
    }
    
    public boolean hasPendingDeliveries(){
        return !deliveryQueue.isEmpty();
    }
    
    public void printPendingDeliveries() {
        if (deliveryQueue.isEmpty()) {
            System.out.println("No pending deliveries.");
            return;
        }

        System.out.println("\n--- Pending Deliveries Queue ---");
        // To print without destroying the queue, we need to iterate carefully
        Node<Delivery> current = deliveryQueue.peek() != null ? new Node<>(deliveryQueue.peek()) : null;
        if(current != null) {
            // This is a simplified print and doesn't show the full queue state without a proper iterator
            // In a real scenario, the queue would have an iterator or a method to get all elements
            System.out.println(current.data);
            System.out.printf("(%d more in queue)\n", deliveryQueue.size() - 1);
        }
        System.out.println("---------------------------------");
    }
}
