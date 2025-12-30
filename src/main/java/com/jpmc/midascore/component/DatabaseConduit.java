package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConduit {
    private final UserRepository userRepository;
    private final TransactionRecordRepository transactionRecordRepository;
    private final IncentiveQuerier incentiveQuerier;

    public DatabaseConduit(UserRepository userRepository, TransactionRecordRepository transactionRecordRepository,  IncentiveQuerier incentiveQuerier) {
        this.userRepository = userRepository;
        this.transactionRecordRepository = transactionRecordRepository;
        this.incentiveQuerier = incentiveQuerier;
    }

    public void save(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

    public void save(Transaction transaction) {
        UserRecord sender = queryUser(transaction.getSenderId());
        UserRecord recipient = queryUser(transaction.getRecipientId());
        float incentiveAmount = incentiveQuerier.query(transaction).getAmount();
        TransactionRecord transactionRecord = new TransactionRecord(sender, recipient, transaction.getAmount(), incentiveAmount);
        transactionRecordRepository.save(transactionRecord);

        sender.setBalance(sender.getBalance() - transactionRecord.getAmount());
        save(sender);
        recipient.setBalance(recipient.getBalance() + transactionRecord.getAmount() +  transactionRecord.getIncentive());
        save(recipient);
    }

    public boolean isValid(Transaction transaction) {
        UserRecord sender = queryUser(transaction.getSenderId());
        if(sender == null) {
            return false;
        }
        UserRecord recipient = queryUser(transaction.getRecipientId());
        if(recipient == null) {
            return false;
        }
        return sender.getBalance() >= transaction.getAmount();
    }

    public UserRecord queryUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public float queryUserBalance(Long userId) {
        UserRecord sender = queryUser(userId);
        if(sender == null) {
            return 0;
        }
        return sender.getBalance();
    }

}
