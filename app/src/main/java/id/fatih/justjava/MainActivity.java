package id.fatih.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import id.fatih.justjava.Handler.ActivityMainHandler;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainHandler handler;

    private static final int INITIAL_QUANTITY = 1;

    public MainActivity()
    {
        handler = new ActivityMainHandler(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);

        getHandler().setQuantity(INITIAL_QUANTITY);
    }

    public void onOrderClick(View view)
    {
        getHandler().createOrder();
    }

    public void onResetClick(View view)
    {
        getHandler().setQuantity(0);
        getHandler().reset();
    }

    public void plusQuantity(View view)
    {
        getHandler().plusQuantity();
    }

    public void minusQuantity(View view)
    {
        getHandler().minusQuantity();
    }

    private ActivityMainHandler getHandler()
    {
        return this.handler;
    }
}
