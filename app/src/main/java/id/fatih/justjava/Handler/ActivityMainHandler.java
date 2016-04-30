package id.fatih.justjava.Handler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.fatih.justjava.MainActivity;
import id.fatih.justjava.R;
import id.fatih.justjava.SummaryActivity;

public class ActivityMainHandler
{
    private MainActivity activity;

    private static final int WHIPPED_CREAM_PRICE = 2000;
    private static final int CHOCOLATE_PRICE = 5000;
    private static final int COFFEE_PRICE = 10000;

    public ActivityMainHandler(MainActivity mainActivity)
    {
        activity = mainActivity;
    }

    public void setQuantity(int quantity)
    {
        getQuantityView().setText(String.valueOf(quantity));
    }

    public void plusQuantity()
    {
        getQuantityView().setText(String.valueOf(getQuantity() + 1));
    }

    public void minusQuantity()
    {
        int qty = getQuantity();

        if (0 < qty) {
            getQuantityView().setText(String.valueOf(qty - 1));
        }
    }

    public void createOrder()
    {
        if (TextUtils.isEmpty(getCustomerName())) {
            String message = "Nama tidak boleh kosong";

            getCustomerView().setError(message);
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

            return;
        }

        if (!isWhippedCreamAdded() && !isChocolateAdded()) {
            Toast.makeText(getActivity(), "Pilih salah satu toping", Toast.LENGTH_LONG).show();

            return;
        }

        if (0 == getQuantity()) {
            Toast.makeText(getActivity(), "Quantity tidak boleh 0", Toast.LENGTH_LONG).show();

            return;
        }

        createSummary();
    }

    public void reset()
    {
        getCustomerView().setText("");
        getWhippedCreamView().setChecked(false);
        getChocolateView().setChecked(false);
        getPriceView().setText("Rp. 0");
    }

    private int calculatePrice()
    {
        int whippedCreamPrice = 0;
        int chocolatePrice = 0;
        int coffeePrice = COFFEE_PRICE * getQuantity();

        if (isWhippedCreamAdded()) {
            whippedCreamPrice += WHIPPED_CREAM_PRICE;
        }

        if (isChocolateAdded()) {
            chocolatePrice += CHOCOLATE_PRICE;
        }

        return (whippedCreamPrice + chocolatePrice + coffeePrice);
    }

    private void createSummary()
    {
        String summary = "Nama: " + getCustomerName() +
                "\nTopping Whipped Cream: " + (isWhippedCreamAdded() ? "Ya": "Tidak") +
                "\nTopping Cholocate: " + (isChocolateAdded() ? "Ya" : "Tidak") +
                "\nTotal: " + calculatePrice() +
                "\nThank you for ordering in coffee cafe";

        getPriceView().setText(summary);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah pesanan Anda sudah benar?");
        builder.setNegativeButton("Ga jadi pesan", null);
        builder.setPositiveButton("Ya", yesConfirmation(summary));
        builder.create().show();
    }

    private MainActivity getActivity()
    {
        return this.activity;
    }

    private String getCustomerName()
    {
        return getCustomerView().getText().toString();
    }

    private boolean isWhippedCreamAdded()
    {
        return getWhippedCreamView().isChecked();
    }

    private boolean isChocolateAdded()
    {
        return getChocolateView().isChecked();
    }

    private int getQuantity()
    {
        return Integer.parseInt(getQuantityView().getText().toString());
    }

    private EditText getCustomerView()
    {
        return (EditText) getActivity().findViewById(R.id.inputName);
    }

    private CheckBox getWhippedCreamView()
    {
        return (CheckBox) getActivity().findViewById(R.id.chkCream);
    }

    private CheckBox getChocolateView()
    {
        return (CheckBox) getActivity().findViewById(R.id.chkChocolate);
    }

    private TextView getQuantityView()
    {
        return (TextView) getActivity().findViewById(R.id.txtQuantity);
    }

    private TextView getPriceView()
    {
        return (TextView) getActivity().findViewById(R.id.txtPrice);
    }

    private DialogInterface.OnClickListener yesConfirmation(String summary)
    {
        final String data = summary;

        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), SummaryActivity.class);
                intent.putExtra("summary", data);

                getActivity().startActivity(intent);
            }
        };
    }
}
