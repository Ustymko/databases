package new_post.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id){
        super("Order not found by id " + id);
    }
}
