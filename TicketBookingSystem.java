import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private final boolean[] seats;
    private final Lock lock = new ReentrantLock();

    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats]; // false means available, true means booked
    }

    public void bookSeat(int seatNumber, String customerName) {
        lock.lock();
        try {
            if (seatNumber < 0 || seatNumber >= seats.length) {
                System.out.println(customerName + " requested an invalid seat number!");
                return;
            }
            if (!seats[seatNumber]) {
                seats[seatNumber] = true;
                System.out.println(customerName + " successfully booked seat " + seatNumber);
            } else {
                System.out.println("Seat " + seatNumber + " is already booked! " + customerName + " failed to book.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class Customer extends Thread {
    private final TicketBookingSystem bookingSystem;
    private final int seatNumber;
    private final String customerName;

    public Customer(TicketBookingSystem system, int seatNumber, String name, int priority) {
        this.bookingSystem = system;
        this.seatNumber = seatNumber;
        this.customerName = name;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(seatNumber, customerName);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10);
        
        Customer vip1 = new Customer(system, 3, "VIP-1", Thread.MAX_PRIORITY);
        Customer vip2 = new Customer(system, 5, "VIP-2", Thread.MAX_PRIORITY);
        Customer user1 = new Customer(system, 3, "User-1", Thread.NORM_PRIORITY);
        Customer user2 = new Customer(system, 5, "User-2", Thread.NORM_PRIORITY);
        Customer user3 = new Customer(system, 7, "User-3", Thread.MIN_PRIORITY);
        
        vip1.start();
        vip2.start();
        user1.start();
        user2.start();
        user3.start();
    }
}
