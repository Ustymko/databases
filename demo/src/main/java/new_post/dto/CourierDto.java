package new_post.dto;

import new_post.domain.DepartmentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "courier", collectionRelation = "couriers")
public class CourierDto extends RepresentationModel<CourierDto> {
    private final Long id;

    private final String name;

    private final String surname;

    private final String phoneNumber;

    private final DepartmentEntity department;
}
