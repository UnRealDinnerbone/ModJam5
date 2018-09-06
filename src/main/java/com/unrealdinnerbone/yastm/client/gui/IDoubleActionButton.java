package com.unrealdinnerbone.yastm.client.gui;

public interface IDoubleActionButton
{
    void updateString();

    void goBack();

    void goNext();

    default void onClick(int mouseButton) {
        if (mouseButton == 0) {
            goNext();
        } else {
            goBack();
        }
        updateString();
    }

}
