package org.example.Model.Contract;

import org.jetbrains.annotations.NotNull;

public class Contract {

    private int contractId;

    public void setFlatId(int flatId) {
        this.flatId = flatId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    private int flatId;

    private int personId;

    @NotNull
    private PayType payType;

    @NotNull
    private StatusType statusType;

    public int getFlatId() {
        return flatId;
    }

    public int getPersonId() {
        return personId;
    }

    public PayType getPayType() {
        return payType;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public Contract(int flat_id, int person_id, @NotNull PayType payType, @NotNull StatusType statusType) {
        this.flatId = flat_id;
        this.personId = person_id;
        this.payType = payType;
        this.statusType = statusType;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }
}

