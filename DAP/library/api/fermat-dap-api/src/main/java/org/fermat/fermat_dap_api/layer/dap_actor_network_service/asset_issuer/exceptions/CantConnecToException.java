package org.fermat.fermat_dap_api.layer.dap_actor_network_service.asset_issuer.exceptions;

/**
 * Created by franklin on 15/10/15.
 */
public class CantConnecToException extends org.fermat.fermat_dap_api.layer.all_definition.exceptions.DAPException {

    public static final String DEFAULT_MESSAGE = "CAN'T CONNECT TO";

    public CantConnecToException(final String message, final Exception cause, final String context, final String possibleReason) {
        super(message, cause, context, possibleReason);
    }

    public CantConnecToException(final String message, final Exception cause) {
        this(message, cause, "", "");
    }

    public CantConnecToException(final String message) {
        this(message, null);
    }

    public CantConnecToException(final Exception exception) {
        this(exception.getMessage());
        setStackTrace(exception.getStackTrace());
    }

    public CantConnecToException() {
        this(DEFAULT_MESSAGE);
    }
}
