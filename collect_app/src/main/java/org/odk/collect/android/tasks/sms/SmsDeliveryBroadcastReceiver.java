package org.odk.collect.android.tasks.sms;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.odk.collect.android.application.Collect;
import org.odk.collect.android.tasks.sms.models.MessageResultStatus;
import org.odk.collect.android.tasks.sms.models.SentMessageResult;

import javax.inject.Inject;

public class SmsDeliveryBroadcastReceiver extends BroadcastReceiver {

    @Inject
    SmsService smsService;

    @Override
    public void onReceive(Context context, Intent intent) {

        Collect.getInstance().getComponent().inject(this);

        SentMessageResult result = new SentMessageResult();

        result.setMessageId(intent.getExtras().getInt(SmsUtils.SMS_MESSAGE_ID));
        result.setInstanceId(intent.getExtras().getString(SmsUtils.SMS_INSTANCE_ID));

        switch (getResultCode()) {
            case Activity.RESULT_OK:
                result.setMessageResultStatus(MessageResultStatus.Delivered);
                break;
            case Activity.RESULT_CANCELED:
                result.setMessageResultStatus(MessageResultStatus.NotDelivered);
                break;
        }
    }
}
