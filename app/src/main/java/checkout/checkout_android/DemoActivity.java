package checkout.checkout_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.checkout.android_sdk.CheckoutAPIClient;
import com.checkout.android_sdk.Response.CardTokenisationFail;
import com.checkout.android_sdk.Response.CardTokenisationResponse;
import com.checkout.android_sdk.CheckoutAPIClient.OnTokenGenerated;

public class DemoActivity extends Activity {

    private CheckoutAPIClient mCheckout;

    private final OnTokenGenerated mTokenListener = new OnTokenGenerated() {

        @Override
        public void onTokenGenerated(CardTokenisationResponse token) {
            Toast.makeText(DemoActivity.this, token.getLast4(),
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(CardTokenisationFail error) {
            Toast.makeText(DemoActivity.this, error.getEventId(),
                    Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mCheckout = findViewById(R.id.checkout_card_form);

        mCheckout
                .setEnvironment("sandbox")
                .setKey("pk_test_6e40a700-d563-43cd-89d0-f9bb17d35e73")
                .setTokenListener(mTokenListener)
                .includeBilling(true);

    }
}