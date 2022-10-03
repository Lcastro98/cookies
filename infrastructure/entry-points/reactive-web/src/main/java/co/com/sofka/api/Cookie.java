package co.com.sofka.api;

public class Cookie {
    private String id;
    private String name;

    public Cookie(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
