package com.bitdubai.fermat_core.layer._12_middleware.wallet.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_api.layer._12_middleware.wallet.AccountStatus;
import com.bitdubai.fermat_api.layer._12_middleware.wallet.CryptoAccount;
import com.bitdubai.fermat_api.layer._1_definition.enums.CryptoCurrency;
import com.bitdubai.fermat_api.layer._2_os.database_system.DatabaseTable;
import com.bitdubai.fermat_api.layer._2_os.database_system.DatabaseTableRecord;

import java.util.UUID;

/**
 * Created by ciencias on 2/15/15.
 */

public class MiddlewareCryptoAccount implements CryptoAccount{

    
    /**
     * MiddlewareCryptoAccount Interface member variables.
     */
    private DatabaseTable table;
    private DatabaseTableRecord record;

    /**
     * FiatAccount Interface member variables.
     */
    private UUID id;
    private String label = "";
    private String name = "";
    private long balance = 0;
    private CryptoCurrency cryptoCurrency;
    private AccountStatus status;
    
    /**
     * Class constructor.
     */
    MiddlewareCryptoAccount (UUID id){
        this.id = id;
    }

    /**
     * MiddlewareCryptoAccount interface implementation.
     *
     * Note that the following methods are package-private.
     * * * * 
     */
    UUID getId(){
        return this.id;
    }
    
    void setBalance(long balance){
        this.balance = balance;
    }

    void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    void setStatus(AccountStatus status) {
        this.status = status;
    }
    
    void setTable (DatabaseTable table) {
        this.table = table;
    }

    DatabaseTable getTable(){
        return this.table;
    }

    void setRecord (DatabaseTableRecord record){
        this.record = record;
    }

    DatabaseTableRecord getRecord(){
        return this.record;
    }

    /**
     * CryptoAccount interface implementation.
     */
    public long getBalance() {
        return balance;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public void setLabel(String label) {
        this.label = label;
        this.record.setStringValue(MiddlewareDatabaseConstants.CRYPTO_ACCOUNTS_TABLE_LABEL_COLUMN_NAME, this.label);
        this.table.updateRecord(this.record);
    }

    public void setName(String name) {
        this.name = name;
        this.record.setStringValue(MiddlewareDatabaseConstants.CRYPTO_ACCOUNTS_TABLE_NAME_COLUMN_NAME, this.name);
        this.table.updateRecord(this.record);
    }

    public AccountStatus getStatus() {
        return this.status;
    }
    
    @Override
    public void openAccount() {
        this.status = AccountStatus.OPEN;
        this.record.setStringValue(MiddlewareDatabaseConstants.CRYPTO_ACCOUNTS_TABLE_STATUS_COLUMN_NAME, this.status.getCode());
        this.table.updateRecord(this.record);
    }

    @Override
    public void closeAccount() {
        this.status = AccountStatus.CLOSED;
        this.record.setStringValue(MiddlewareDatabaseConstants.CRYPTO_ACCOUNTS_TABLE_STATUS_COLUMN_NAME, this.status.getCode());
        this.table.updateRecord(this.record);
    }

    @Override
    public void deleteAccount() {
        this.status = AccountStatus.DELETED;
        this.record.setStringValue(MiddlewareDatabaseConstants.CRYPTO_ACCOUNTS_TABLE_STATUS_COLUMN_NAME, this.status.getCode());
        this.table.updateRecord(this.record);
    }
}
