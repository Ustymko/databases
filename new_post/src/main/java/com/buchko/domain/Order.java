package com.buchko.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    private Integer id;
    private Integer senderClientId;
    private Integer receiverClientId;
    private Integer senderDepartmentId;
    private Integer receiverDepartmentId;
    private Integer senderOperatorId;
    private Integer receiverOperatorId;
    private Integer courierBetweenDepartmentsId;
    private Integer courierOnSpotId;
    private LocalDateTime plannedSendingDatetime;
    private LocalDateTime plannedReceivingDatetime;
    private LocalDateTime actualSendingDatetime;
    private LocalDateTime actualReceivingDatetime;
    private Double parcelPrice;
    private Double deliveryPrice;
    private Double totalPrice;
}
