package id.fatih.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import id.fatih.justjava.Handler.SummaryHandler;

public class SummaryActivity extends AppCompatActivity
{
    private SummaryHandler handler;

    public SummaryActivity()
    {
        handler = new SummaryHandler(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.summary);

        String summary = getHandler().getIntent().getStringExtra("summary");
        getHandler().setSummary(summary);

    }

    private SummaryHandler getHandler()
    {
        return handler;
    }
}
