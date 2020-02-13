package com.android.samsung.codelab.guestbookdapp.presenter;

import com.android.samsung.codelab.guestbookdapp.contract.MapContract;
import com.android.samsung.codelab.guestbookdapp.util.AppExecutors;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MapPresenter implements MapContract.Presenter {

    private static final String TAG = MapPresenter.class.getSimpleName();
    private MapContract.View mContract;

    public MapPresenter(MapContract.View mContract) {
        this.mContract = mContract;
    }

    public void onClickButton() {

    }

    @Override
    public void loadMap() {

    }

    @Override
    public void loadBalance() {

    }

    private String getEthValueFromWei(BigInteger value, int minimumScale) {
        BigDecimal ethValue = (new BigDecimal(value)).multiply(BigDecimal.valueOf(1, 18));
        ethValue = ethValue.stripTrailingZeros();
        if (minimumScale > 0 && ethValue.scale() <= 0) {
            ethValue = ethValue.setScale(minimumScale, BigDecimal.ROUND_HALF_EVEN);
        }
        return ethValue.toPlainString();
    }
}
