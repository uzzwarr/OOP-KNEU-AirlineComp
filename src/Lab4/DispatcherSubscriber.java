package Lab4;

public class DispatcherSubscriber implements Observer{

    @Override
    public void update(String message) {
        System.out.println("[Dispatcher has gotten a message]" + message);
    }
}
