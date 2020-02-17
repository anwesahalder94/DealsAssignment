package com.example.dealsassignment

interface BaseContract {

    interface View<in T : Presenter> {
        fun setPresenter(presenter: T)
    }

    interface Presenter {
    }
}