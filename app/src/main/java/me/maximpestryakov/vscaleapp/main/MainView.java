package me.maximpestryakov.vscaleapp.main;

interface MainView {

    void showServerList();

    void showTokenWarning(String titile, String message);

    void showInternetConnectionProblem();

    void showLoading();

    class State {
        static final int SHOW_TOKEN_WARNING = 0;
        static final int SHOW_SERVER_LIST = 1;
        static final int SHOW_LOADING = 2;
        static final int SHOW_INTERNET_CONNECTION_PROBLEM = 3;

        private State() {
        }
    }
}
