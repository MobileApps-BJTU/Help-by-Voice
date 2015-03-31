package com.kim.help_by_voice.Activitys;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.kim.help_by_voice.fragment.PlaceChooseFragment;
import com.kim.help_by_voice.fragment.ToDialectAskFragment;

public class AskToDialectActivity extends Activity implements PlaceChooseFragment.OnFragmentInteractionListener,ToDialectAskFragment.OnFragmentInteractionListener {
    private String mPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_to_dialect);
        this.setTitle(R.string.infer_choose_place);
        if (savedInstanceState == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.container, new PlaceChooseFragment());
            ft.commit();
        }
    }

    //implements the method in the place choose fragment
    @Override
    public void getChoosedPlace(String place) {
        mPlace = place;
        this.setTitle(mPlace);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    //return to the another activity
    @Override
    public void onButtonClicked() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, new PlaceChooseFragment());
        ft.commit();
        Intent intent = new Intent(AskToDialectActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
