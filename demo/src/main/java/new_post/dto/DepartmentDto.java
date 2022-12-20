package new_post.dto;

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
@Relation(itemRelation = "department", collectionRelation = "departments")
public class DepartmentDto extends RepresentationModel<DepartmentDto> {
    private final Long id;

    private final String address;

    private final Integer number;

    private final String city;

    private final String region;
}
