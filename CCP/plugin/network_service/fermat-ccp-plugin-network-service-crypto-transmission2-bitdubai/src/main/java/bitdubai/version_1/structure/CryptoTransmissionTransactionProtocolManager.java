package bitdubai.version_1.structure;

import com.bitdubai.fermat_api.layer.all_definition.transaction_transference_protocol.Action;
import com.bitdubai.fermat_api.layer.all_definition.transaction_transference_protocol.Specialist;
import com.bitdubai.fermat_api.layer.all_definition.transaction_transference_protocol.Transaction;
import com.bitdubai.fermat_api.layer.all_definition.transaction_transference_protocol.TransactionProtocolManager;
import com.bitdubai.fermat_api.layer.all_definition.transaction_transference_protocol.crypto_transactions.FermatCryptoTransaction;
import com.bitdubai.fermat_api.layer.all_definition.transaction_transference_protocol.exceptions.CantConfirmTransactionException;
import com.bitdubai.fermat_api.layer.all_definition.transaction_transference_protocol.exceptions.CantDeliverPendingTransactionsException;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.exceptions.PendingRequestNotFoundException;
import com.bitdubai.fermat_ccp_api.layer.network_service.crypto_transmission.interfaces.structure.CryptoTransmissionMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bitdubai.version_1.database.communications.CryptoTransmissionMetadataDAO_V2;
import bitdubai.version_1.exceptions.CantGetCryptoTransmissionMetadataException;
import bitdubai.version_1.exceptions.CantReadRecordDataBaseException;
import bitdubai.version_1.exceptions.CantUpdateRecordDataBaseException;

/**
 * Created by mati on 2015.11.18..
 */
public class CryptoTransmissionTransactionProtocolManager implements TransactionProtocolManager<FermatCryptoTransaction> {

    CryptoTransmissionMetadataDAO_V2 cryptoTransmissionMetadataDAO;

    public CryptoTransmissionTransactionProtocolManager(CryptoTransmissionMetadataDAO_V2 cryptoTransmissionMetadataDAO) {
        this.cryptoTransmissionMetadataDAO = cryptoTransmissionMetadataDAO;
    }

    @Override
    public void confirmReception(UUID transactionID) throws CantConfirmTransactionException {
        try {

            cryptoTransmissionMetadataDAO.confirmReception(transactionID);

        } catch (CantGetCryptoTransmissionMetadataException e) {
            e.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction<FermatCryptoTransaction>> getPendingTransactions(Specialist specialist) throws CantDeliverPendingTransactionsException {
        List<Transaction<FermatCryptoTransaction>> lst = new ArrayList<>();
        try {
                for(CryptoTransmissionMetadata cryptoTransmissionMetadata : cryptoTransmissionMetadataDAO.getPendings()){

                    FermatCryptoTransaction fermatCryptoTransaction = new FermatCryptoTransaction(
                            cryptoTransmissionMetadata.getRequestId()!=null,
                            cryptoTransmissionMetadata.getRequestId(),
                            cryptoTransmissionMetadata.getSenderPublicKey(),
                            cryptoTransmissionMetadata.getDestinationPublicKey(),
                            cryptoTransmissionMetadata.getAssociatedCryptoTransactionHash(),
                            cryptoTransmissionMetadata.getPaymentDescription());

                    Transaction transaction = new Transaction(
                            cryptoTransmissionMetadata.getTransactionId(),
                            fermatCryptoTransaction,
                            Action.APPLY,
                            System.currentTimeMillis()
                    );


                    lst.add(transaction);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
}
