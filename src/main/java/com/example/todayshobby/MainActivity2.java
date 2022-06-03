package com.example.todayshobby;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class MainActivity2 extends AppCompatActivity {
    TextView text,text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = findViewById(R.id.textView);
        text2=findViewById(R.id.textView2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        getSupportActionBar().setTitle(name);
        String str=intent.getStringExtra("linie");
        String []result=str.split("/");
        text.setText(result[1]);
        text2.setMovementMethod(new ScrollingMovementMethod());
        int t = Integer.parseInt(result[0]);
        if(t != 3 && t != 7)
        {
            if(t==9 || t == 11) {
                String rec="";
                String []rez=result[2].split("\\.");
                for(int i=0;i<rez.length;i++)
                    rec=rec+rez[i]+"\n";
                text2.setText(rec);
            }
            YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
            getLifecycle().addObserver(youTubePlayerView);
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                    String videoId = result[3];
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });
        } else {
            YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
            youTubePlayerView.setVisibility(View.INVISIBLE);
            String rec="";
            String []rez=result[2].split("\\.");
            for(int i=0;i<rez.length;i++)
                rec=rec+rez[i]+"\n";
            text2.setText(rec);
        }


    }
}