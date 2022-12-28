package org.example.DAO;

import org.example.Model.Contract.Contract;
import org.example.Model.Contract.PayType;
import org.example.Model.Contract.StatusType;

import java.util.List;

public interface ContractDAO extends DAO<Contract> {

        Contract get(int contractId);
        List<Contract> getByFlatId(int id);

        List<Contract> getByPersonId(int id);

        List<Contract> getByStatus(StatusType type);

        List<Contract> getByTypeOfPay(PayType type);
        }
