package com.example.core_bank.core_bank.core.repository;

import com.example.core_bank.core_bank.core.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

    //연+월 조회
    @Query("SELECT th FROM TransactionHistory th " +
            "JOIN FETCH th.classfication " +
            "WHERE th.account.id = :accountId " +
            "AND YEAR(th.transactionDate) = :year " +
            "AND MONTH(th.transactionDate) = :month " +
            "ORDER BY th.transactionDate ASC")
    List<TransactionHistory> findByAccountIdAndYearAndMonthWithClassfication(
            @Param("accountId") Integer accountId,
            @Param("year") Integer year,
            @Param("month") Integer month
    );

    // 연간 데이터
    @Query("SELECT th FROM TransactionHistory th " +
            "LEFT JOIN FETCH th.classfication " +
            "WHERE th.account.id = :accountId " +
            "AND FUNCTION('YEAR', th.transactionDate) = :year " +
            "AND th.isDeposit = true " +
            "ORDER BY th.transactionDate ASC")
    List<TransactionHistory> findByAccountIdYearlyWithClassfication(
            @Param("accountId") Integer accountId,
            @Param("year") Integer year
    );

//    // 1번의 쿼리로 2개의 데이터 삽입
//    @Modifying
//    @Query(value = "INSERT INTO transaction_history " +
//            "(transaction_date, amount, is_deposit, transaction_type, account_id, classfication_id) " +
//            "VALUES " +
//            "(:now, :amount, false, '이체', :fromAccountId, :fromClassificationId), " +
//            "(:now, :amount, true, '입금', :toAccountId, :toClassificationId)",
//            nativeQuery = true)
//    void saveTransactionHistories(
//            @Param("fromAccountId") Integer fromAccountId,
//            @Param("toAccountId") Integer toAccountId,
//            @Param("amount") Long amount,
//            @Param("fromClassificationId") Integer fromClassificationId,
//            @Param("toClassificationId") Integer toClassificationId,
//            @Param("now") LocalDate now);
}
