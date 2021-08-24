package com.braintreepayments.cardform;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.braintreepayments.cardform.utils.ColorUtils;
import com.braintreepayments.cardform.view.CardForm;

public class CardScanningFragment extends Fragment {

    private static final int CARD_IO_REQUEST_CODE = 12398;
    private static final String TAG = "com.braintreepayments.cardform.CardScanningFragment";

    private CardForm mCardForm;

    public static void requestScan(Activity activity, CardForm cardForm) {
        CardScanningFragment fragment = (CardScanningFragment) activity.getFragmentManager().findFragmentByTag(TAG);

        if (fragment != null) {
            activity.getFragmentManager()
                    .beginTransaction()
                    .remove(fragment)
                    .commit();
        }

        fragment = new CardScanningFragment();
        fragment.mCardForm = cardForm;

        activity.getFragmentManager()
                .beginTransaction()
                .add(fragment, TAG)
                .commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

       if (mCardForm != null) {
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CARD_IO_REQUEST_CODE && mCardForm != null) {
            mCardForm.handleCardIOResponse(data);

            if (getActivity() != null) {
                getActivity().getFragmentManager()
                        .beginTransaction()
                        .remove(this)
                        .commit();
            }
        }
    }
}
