package fr.fradigoy.postapi.Tools;

public class PostNotFoundException extends Exception {
    public PostNotFoundException(Long id) {
        super("Could not find post "+ id);
    }
}
