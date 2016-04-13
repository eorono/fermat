package com.bitdubai.sub_app.chat_community.app_connection;

import com.bitdubai.fermat_android_api.engine.NotificationPainter;
import com.bitdubai.fermat_ccp_api.layer.actor.intra_user.interfaces.IntraWalletUserActor;
import com.bitdubai.fermat_ccp_api.layer.module.intra_user.exceptions.CantGetIntraUsersListException;
import com.bitdubai.fermat_ccp_api.layer.module.intra_user.interfaces.IntraUserModuleManager;

/**
 * ChatCommunityBuildNotification
 *
 * @author Jose Cardozo josejcb (josejcb89@gmail.com) on 13/04/16.
 * @version 1.0
 */
public class ChatCommunityBuildNotification {

    public static NotificationPainter getNotification(IntraUserModuleManager moduleManager, String code)
    {
        NotificationPainter notification = null;
        try {
            String[] params = code.split("_");
            String notificationType = params[0];
            String senderActorPublicKey = params[1];

            switch (notificationType){
                case "CONNECTIONREQUEST":

                    if(moduleManager != null) {
                        //find last notification by sender actor public key
                        IntraWalletUserActor senderActor = null;

                        senderActor = moduleManager.getLastNotification(senderActorPublicKey);

                        notification = new ChatCommunityNotificationPainter("New Connection Request", "A new connection request was received from " + senderActor.getName(), "", "");

                    }else
                    {
                        notification = new ChatCommunityNotificationPainter("New Connection Request", "A new connection request was received.", "", "");
                    }
                    break;
                case "CONNECTIONACCEPT":

                    if(moduleManager != null) {
                        //find last notification by sender actor public key
                        IntraWalletUserActor senderActor = null;

                        senderActor = moduleManager.getLastNotification(senderActorPublicKey);

                        notification = new ChatCommunityNotificationPainter("Connection Request", "Your connection request was accepted to " + senderActor.getName(), "", "");

                    }else
                    {
                        notification = new ChatCommunityNotificationPainter("Connection Request Accepted", "Your connection request was accepted.", "", "");
                    }
                    break;

            }
        } catch (CantGetIntraUsersListException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return notification;
    }
}