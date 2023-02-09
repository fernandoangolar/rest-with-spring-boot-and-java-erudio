package br.com.erudio;

public class Greeting {

    private final long id;
    private final String contant;

    public Greeting(long id, String contant) {
        this.id = id;
        this.contant = contant;
    }

    public long getId() {
        return id;
    }

    public String getContant() {
        return contant;
    }
}
