package com.nabil.nahla.countries.listeners;

public interface OnGetDataListener<T,D> {
    void onSuccess(T data);
    void onFailed(D errorMsg);
}
