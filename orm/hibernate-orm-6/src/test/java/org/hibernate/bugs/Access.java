package org.hibernate.bugs;

import java.util.function.BiConsumer;
import java.util.function.Function;

class Access {
    
    Function<I18n, String> getter;
    BiConsumer<I18n, String> setter;
    
    
    public Access(Function<I18n, String> getter, BiConsumer<I18n, String> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public Function<I18n, String> getter() {
        return getter;
    }

    public BiConsumer<I18n, String> setter() {
        return setter;
    }
}
