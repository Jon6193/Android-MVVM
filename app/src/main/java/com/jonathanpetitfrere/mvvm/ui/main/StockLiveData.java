package com.jonathanpetitfrere.mvvm.ui.main;

import android.arch.lifecycle.LiveData;
import android.icu.math.BigDecimal;

/**
 * @author jpetit
 */

class StockLiveData extends LiveData<BigDecimal> {

    private StockManager mStockManager;

    private SimplePriceListener mListener = this::setValue;

    public StockLiveData(String symbol) {
        mStockManager = new StockManager(symbol);
    }

    @Override
    protected void onActive() {
        mStockManager.requestPriceUpdates(mListener);
    }

    @Override
    protected void onInactive() {
        mStockManager.removeUpdates(mListener);
    }
}


interface SimplePriceListener {
    void onPriceChanged(BigDecimal price);
}

class StockManager {

    private final String symbol;

    StockManager(String symbol) {
        this.symbol = symbol;
    }

    void requestPriceUpdates(SimplePriceListener listener) {
        //send price updates to listener
    }

    void removeUpdates(SimplePriceListener listener) {
        //stop sending price updates to listener
    }
}