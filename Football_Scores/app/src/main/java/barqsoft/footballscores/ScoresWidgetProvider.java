package barqsoft.footballscores;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Date;

/**
 * Created by Nick on 7/28/2015.
 */
public class ScoresWidgetProvider extends AppWidgetProvider {

    public static int scoreOne = 0;
    public static int scoreTwo = 0;
    static ScoresProvider scoresProvider;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.scores_widget);

        pushWidgetUpdate(context, remoteViews);
    }
    @Override
    public void onReceive(final Context context, Intent intent) {

        if (intent.getAction().equals("Click")) {
            Log.d("tag", "Clickity");
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.scores_widget);
            pushWidgetUpdate(context, remoteViews);
        }

        super.onReceive(context, intent);

    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
        Date today = new Date();

        Cursor cursor = context.getContentResolver().query(
                DatabaseContract.BASE_CONTENT_URI,
                new String[] {
                        DatabaseContract.scores_table.DATE_COL,
                        DatabaseContract.scores_table.HOME_COL,
                        DatabaseContract.scores_table.AWAY_COL,
                        DatabaseContract.scores_table.HOME_GOALS_COL,
                        DatabaseContract.scores_table.AWAY_GOALS_COL
                },
                null,
                null,
                DatabaseContract.scores_table.DATE_COL + " desc"
        );

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            remoteViews.setTextViewText(R.id.away_score_widget, cursor.getString(4));
            remoteViews.setTextViewText(R.id.home_score_widget, cursor.getString(3));

            remoteViews.setTextViewText(R.id.away_team_widget, cursor.getString(2));
            remoteViews.setTextViewText(R.id.home_team_widget, cursor.getString(1));

            remoteViews.setImageViewResource(R.id.away_crest_widget, Utilies.getTeamCrestByTeamName(cursor.getString(2)));
            remoteViews.setImageViewResource(R.id.home_crest_widget, Utilies.getTeamCrestByTeamName(cursor.getString(1)));

            remoteViews.setTextViewText(R.id.date_widget, cursor.getString(0));
            //date_widget
        }
        else {

        }

        cursor.close();

        ComponentName myWidget = new ComponentName(context, ScoresWidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }

    private static ScoresProvider getScoresProvider()
    {
        if(scoresProvider == null) {
            scoresProvider = new ScoresProvider();
        }
        return scoresProvider;
    }
}
