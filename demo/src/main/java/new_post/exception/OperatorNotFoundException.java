package new_post.exception;

public class OperatorNotFoundException extends RuntimeException{

    public OperatorNotFoundException(Long id){
        super("Operator not found by id " + id);
    }
}
