package new_post.exception;

public class NoOperatorsInSuchDepartmentException extends RuntimeException{

    public NoOperatorsInSuchDepartmentException(Long id){
        super("There are no operators in department with id " + id);
    }
}
