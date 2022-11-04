package new_post.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "new_post_lab5", catalog = "")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "sending_datetime")
    private Timestamp sendingDatetime;
    @Basic
    @Column(name = "receiving_datetime")
    private Timestamp receivingDatetime;
    @Basic
    @Column(name = "parcel_price")
    private Double parcelPrice;
    @Basic
    @Column(name = "delivery_price")
    private Double deliveryPrice;
    @ManyToOne
    @JoinColumn(name = "sender_client_id", referencedColumnName = "id", nullable = false)
    private ClientEntity senderClient;
    @ManyToOne
    @JoinColumn(name = "receiver_client_id", referencedColumnName = "id", nullable = false)
    private ClientEntity receiverClient;
    @ManyToOne
    @JoinColumn(name = "sender_department_id", referencedColumnName = "id", nullable = false)
    private DepartmentEntity senderDepartment;
    @ManyToOne
    @JoinColumn(name = "receiver_department_id", referencedColumnName = "id", nullable = false)
    private DepartmentEntity receiverDepartment;
    @ManyToOne
    @JoinColumn(name = "sender_operator_id", referencedColumnName = "id", nullable = false)
    private OperatorEntity senderOperator;
    @ManyToOne
    @JoinColumn(name = "receiver_operator_id", referencedColumnName = "id", nullable = false)
    private OperatorEntity receiverOperator;
    @ManyToOne
    @JoinColumn(name = "courier_between_departments_id", referencedColumnName = "id", nullable = false)
    private CourierEntity courierBetweenDepartments;
    @ManyToOne
    @JoinColumn(name = "courier_on_spot_id", referencedColumnName = "id", nullable = false)
    private CourierEntity courierOnSpot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getSendingDatetime() {
        return sendingDatetime;
    }

    public void setSendingDatetime(Timestamp sendingDatetime) {
        this.sendingDatetime = sendingDatetime;
    }

    public Timestamp getReceivingDatetime() {
        return receivingDatetime;
    }

    public void setReceivingDatetime(Timestamp receivingDatetime) {
        this.receivingDatetime = receivingDatetime;
    }

    public Double getParcelPrice() {
        return parcelPrice;
    }

    public void setParcelPrice(Double parcelPrice) {
        this.parcelPrice = parcelPrice;
    }

    public Double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(sendingDatetime, that.sendingDatetime) && Objects.equals(receivingDatetime, that.receivingDatetime) && Objects.equals(parcelPrice, that.parcelPrice) && Objects.equals(deliveryPrice, that.deliveryPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sendingDatetime, receivingDatetime, parcelPrice, deliveryPrice);
    }

    public ClientEntity getSenderClient() {
        return senderClient;
    }

    public void setSenderClient(ClientEntity senderClient) {
        this.senderClient = senderClient;
    }

    public ClientEntity getReceiverClient() {
        return receiverClient;
    }

    public void setReceiverClient(ClientEntity receiverClient) {
        this.receiverClient = receiverClient;
    }

    public DepartmentEntity getSenderDepartment() {
        return senderDepartment;
    }

    public void setSenderDepartment(DepartmentEntity senderDepartment) {
        this.senderDepartment = senderDepartment;
    }

    public DepartmentEntity getReceiverDepartment() {
        return receiverDepartment;
    }

    public void setReceiverDepartment(DepartmentEntity receiverDepartment) {
        this.receiverDepartment = receiverDepartment;
    }

    public OperatorEntity getSenderOperator() {
        return senderOperator;
    }

    public void setSenderOperator(OperatorEntity senderOperator) {
        this.senderOperator = senderOperator;
    }

    public OperatorEntity getReceiverOperator() {
        return receiverOperator;
    }

    public void setReceiverOperator(OperatorEntity receiverOperator) {
        this.receiverOperator = receiverOperator;
    }

    public CourierEntity getCourierBetweenDepartments() {
        return courierBetweenDepartments;
    }

    public void setCourierBetweenDepartments(CourierEntity courierBetweenDepartments) {
        this.courierBetweenDepartments = courierBetweenDepartments;
    }

    public CourierEntity getCourierOnSpot() {
        return courierOnSpot;
    }

    public void setCourierOnSpot(CourierEntity courierOnSpot) {
        this.courierOnSpot = courierOnSpot;
    }
}
