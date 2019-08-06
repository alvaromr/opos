package com.alvaro.opos.presentation.presenter;

public interface Presenter<V> {
    void viewReady();
    void setView(V view);
    interface View {
        void informPresenterViewIsReady();
        void showErrorMessage(String message);
    }
}
