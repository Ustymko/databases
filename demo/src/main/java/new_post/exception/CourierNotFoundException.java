package new_post.exception;

public class CourierNotFoundException extends RuntimeException{

    public CourierNotFoundException(Long id){
        super("Courier not found by id " + id);
    }
}
