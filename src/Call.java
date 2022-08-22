public class Call {
    private int id;
    private String client;

    public Call(int id, String client) {
        this.id = id;
        this.client = client;
    }

    @Override
    public String toString() {
        return "вызов {" +
                "id=" + id +
                ", клиент ='" + client + '\'' +
                '}';
    }
}