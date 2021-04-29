package singleton;

public class AClass {

    private SocketClient socketClient;

    public AClass(){
        this.socketClient = SocketClient.getInstance();
    }

    public SocketClient getSocketClient() {
        return this.socketClient;
    }
}