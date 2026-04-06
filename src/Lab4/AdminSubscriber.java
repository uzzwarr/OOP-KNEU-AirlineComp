package Lab4;

public class AdminSubscriber implements Observer {

    @Override
    public void update(String message) {
        System.out.println("[Admin has gotten a message]: " + message);
    }
}
