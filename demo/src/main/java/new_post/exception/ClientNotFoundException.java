package new_post.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(Long id){
        super("Client not found by id " + id);
    }
}
