package new_post.dto;

import new_post.domain.ClientEntity;
import new_post.domain.CourierEntity;
import new_post.domain.DepartmentEntity;
import new_post.domain.OperatorEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Timestamp;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "order", collectionRelation = "orders")
public class OrderDto extends RepresentationModel<OrderDto> {
    private Long id;

    private Timestamp sendingDatetime;

    private Timestamp receivingDatetime;

    private Double parcelPrice;

    private Double deliveryPrice;

    private ClientEntity senderClient;

    private ClientEntity receiverClient;

    private DepartmentEntity senderDepartment;

    private DepartmentEntity receiverDepartment;

    private OperatorEntity senderOperator;

    private OperatorEntity receiverOperator;

    private CourierEntity courierBetweenDepartments;

    private CourierEntity courierOnSpot;
}
