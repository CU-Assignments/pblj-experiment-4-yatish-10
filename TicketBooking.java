import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private int availableSeats;
    private final Lock lock = new ReentrantLock();

    public TicketBookingSystem(int totalSeats) {
        this.availableSeats = totalSeats;
    }

    public void bookTicket(String customerName) {
        lock.lock();
        try {
            if (availableSeats > 0) {
                System.out.println(customerName + " booked a seat. Seats left: " + (availableSeats - 1));
                availableSeats--;
            } else {
                System.out.println("No seats available for " + customerName);
            }
        } finally {
            lock.unlock();
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem bookingSystem;
    private String customerName;

    public BookingThread(TicketBookingSystem bookingSystem, String customerName) {
        this.bookingSystem = bookingSystem;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        bookingSystem.bookTicket(customerName);
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(5); //
