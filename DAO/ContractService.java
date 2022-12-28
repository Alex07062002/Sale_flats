package org.example.DAO;

import org.example.Model.Contract.Contract;
import org.example.Model.Contract.PayType;
import org.example.Model.Contract.StatusType;

import java.util.ArrayList;
import java.util.List;

public class ContractService implements ContractDAO {
    private final SQLQuery requester = new SQLQuery();

    private final ExecuteAround<Contract> extractor = rs -> {
        List<Contract> list = new ArrayList<>();
        while (rs.next()) {
            Contract contract = new Contract(rs.getInt("flat_id"), rs.getInt("person_id"),
                    PayType.valueOf(rs.getString("type_of_pay")), StatusType.valueOf(rs.getString("status_of_contract")));
            contract.setContractId(rs.getInt("contract_id"));
            list.add(contract);
        }
        return list;
    };

    @Override
    public List<Contract> getAll() {
        String query = "SELECT * FROM contract";
        return requester.executeQuery(query, null, extractor);
    }

    @Override
    public void create(Contract contract) {
        String query = "INSERT INTO contract (flat_id,person_id,type_of_pay, status_of_contract) values (?,?,?::type_of_pay,?::status_of_contract)";
        StatementConsumer consumer = statement -> {
          statement.setInt(1, contract.getFlatId());
          statement.setInt(2, contract.getPersonId());
          statement.setString(3, contract.getPayType().toString());
          statement.setString(4, contract.getStatusType().toString());
        };
        requester.executeUpdate(query, consumer);
    }

    @Override
    public void update(int id, List<?> params) {
        String query = "UPDATE contract SET status_of_contract = ?::status_of_contract WHERE contract_id = ?"; // TODO SET type_of_pay = ?::type_of_pay
        StatementConsumer consumer = statement -> {
            requester.updateFromList(statement,params);
            statement.setInt(params.size()+1,id);
        };
        requester.executeUpdate(query,consumer);
    }


    @Override
    public void delete(int id) {
        String query = "DELETE FROM contract WHERE contract_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1,id);
        requester.executeUpdate(query,consumer);
    }

    @Override
    public Contract get(int contractId) {
        String query = "SELECT * FROM contract WHERE contract_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1,contractId);
        List<Contract> contracts = requester.executeQuery(query,consumer,extractor);
        return contracts.size() == 1 ? contracts.get(0) : null;
    }

    @Override
    public List<Contract> getByFlatId(int id) {
        String query = "SELECT * FROM contract WHERE flat_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1,id);
        return requester.executeQuery(query,consumer,extractor);
    }

    @Override
    public List<Contract> getByPersonId(int id) {
        String query = "SELECT * FROM contract WHERE person_id = ?";
        StatementConsumer consumer = statement -> statement.setInt(1,id);
        return requester.executeQuery(query,consumer,extractor);
    }

    @Override
    public List<Contract> getByStatus(StatusType type) {
        String query = "SELECT * FROM contract WHERE status_of_contract = ?::status_of_contract";
        StatementConsumer consumer = statement -> statement.setString(1,type.toString());
        return requester.executeQuery(query,consumer,extractor);
    }

    @Override
    public List<Contract> getByTypeOfPay(PayType type) {
        String query = "SELECT * FROM contract WHERE type_of_pay = ?::type_of_pay";
        StatementConsumer consumer = statement -> statement.setString(1,type.toString());
        return requester.executeQuery(query,consumer,extractor);
    }
}
