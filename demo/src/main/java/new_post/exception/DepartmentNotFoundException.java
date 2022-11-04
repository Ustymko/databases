package new_post.exception;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(Long id){
        super("Department not found by id " + id);
    }
}
