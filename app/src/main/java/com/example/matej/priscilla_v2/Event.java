package com.example.matej.priscilla_v2;

import androidx.annotation.Nullable;

public class Event<T> {

    private boolean hasBeenHandled = false;
    private final T content;

    public Event(T content) {
        this.content = content;
    }

//    public final boolean getHasBeenHandled() { return hasBeenHandled; }

    @Nullable
    public final T getContentIfNotHandled() // one observer
    {
        if(hasBeenHandled){
            return null;
        }else {
            hasBeenHandled = true;
            return content;
        }
    }



    public final T peekContent()
    {
        return content;
    }   // multiple observers
}
