package me.maximpestryakov.vscaleapp.server;

interface ServerView {

    void showServerInfo();

    void showLoading();

    void showInternetConnectionProblem();

    class State {
        static final int SHOW_SERVER_INFO = 0;
        static final int SHOW_LOADING = 1;

        private State() {
        }
    }
}
