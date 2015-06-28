package de.xants.capitalista;

import android.app.IntentService;
import android.content.Intent;

import de.xants.capitalista.model.Game;
import timber.log.Timber;

public class GameService extends IntentService {

    private boolean mRunning = true;
    private Game mGame = new Game();

    public GameService() {
        super("CapitalistaGameEngine");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Timber.d("Starting GameService");
        long time = System.currentTimeMillis();
        while (this.mRunning) {
            this.mGame.tick();
        }
    }

    @Override
    public void onDestroy() {
        Timber.d("Stoping GameService");
        this.mRunning = false;
        super.onDestroy();
    }

}
