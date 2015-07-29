package barqsoft.footballscores;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by Nick on 7/28/2015.
 */
public class ScoresWidgetIntentRecever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.appwidget.action.APPWIDGET_UPDATE")) {
            // update stuff here
            updateWidget(context);
        }
    }

    private void updateWidget(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.scores_widget);
        remoteViews.setTextViewText(R.id.title, "Updated");
    }
}
