package barqsoft.footballscores;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Created by Nick on 7/28/2015.
 */
public class ScoresWidgetProvider extends AppWidgetProvider {

    public static int scoreOne = 0;
    public static int scoreTwo = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.scores_widget);

        remoteViews.setTextViewText(R.id.title, "This is a Widget");
        remoteViews.setTextViewText(R.id.away_score_widget, Integer.toString(scoreOne));
        remoteViews.setTextViewText(R.id.home_score_widget, Integer.toString(scoreTwo));

        pushWidgetUpdate(context, remoteViews);
    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
        scoreOne++;
        scoreTwo++;

        ComponentName myWidget = new ComponentName(context, ScoresWidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }
}
