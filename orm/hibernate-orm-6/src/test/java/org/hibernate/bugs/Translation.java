package org.hibernate.bugs;

import jakarta.persistence.Embeddable;
import java.util.Locale;
import java.util.Objects;

@Embeddable
public class Translation extends I18n<Translation> implements CharSequence {

    private static final long serialVersionUID = -7010598137989944320L;

    public String getValueInCurrentLanguage() {
        return getAccess().getter().apply(this);
    }

    public final Translation setValueInCurrentLanguage(String aValue) {
        getAccess().setter().accept(this, aValue);
        return this;
    }

    public Translation() {
        //default constructor
    }

    public Translation(I18n aI18n) {
        if (aI18n != null) {
            LOCALES.values().forEach(a -> a.setter().accept(this, a.getter().apply(aI18n)));
        }
    }

    Access getAccess() {
        Locale locale = Locale.getDefault();
        return LOCALES.getOrDefault(locale.stripExtensions(), new Access(I18n::getDe, I18n::setDe));
    }

    /**
     * Takes all non-null locale-values of given translation. If given
     * translation is null, set value for current locale to null.
     *
     * @param aTranslation
     * @return
     */
    public Translation updateWith(Translation aTranslation) {
        if (aTranslation == null) {
            getAccess().setter().accept(this, null);
        } else {
            LOCALES.values().forEach(a -> {
                String v = a.getter().apply(aTranslation);
                if (v != null) {
                    a.setter().accept(this, v);
                }
            });
        }
        return this;
    }

    //<editor-fold defaultstate="collapsed" desc="overridden getter just needed to hide properties in OpenApi">
    @Override
    public String getFr() {
        return super.getFr();
    }

    @Override
    public String getEn() {
        return super.getEn();
    }

    @Override
    public String getDe() {
        return super.getDe();
    }

    @Override
    public String getJa() {
        return super.getJa();
    }

    @Override
    public String getPl() {
        return super.getPl();
    }

    @Override
    public String getEs() {
        return super.getEs();
    }

    @Override
    public String getPt() {
        return super.getPt();
    }

    @Override
    public String getKo() {
        return super.getKo();
    }

    @Override
    public String getHi() {
        return super.getHi();
    }

    @Override
    public String getIt() {
        return super.getIt();
    }

    @Override
    public String getZh() {
        return super.getZh();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="implement CharSequence">
    @Override
    public int length() {
        String valueInCurrentLanguage = getValueInCurrentLanguage();
        return valueInCurrentLanguage == null
                ? 0
                : valueInCurrentLanguage.length();
    }

    @Override
    public char charAt(int aIndex) {
        String valueInCurrentLanguage = getValueInCurrentLanguage();
        if (valueInCurrentLanguage == null) {
            throw new StringIndexOutOfBoundsException("index " + aIndex + ", length 0");
        }
        return getValueInCurrentLanguage().charAt(aIndex);
    }

    @Override
    public CharSequence subSequence(int aStart, int aEnd) {
        String valueInCurrentLanguage = getValueInCurrentLanguage();
        if (valueInCurrentLanguage == null) {
            throw new StringIndexOutOfBoundsException("begin " + aStart + ", end " + aEnd + ", length 0");
        }
        return valueInCurrentLanguage.subSequence(aStart, aEnd);
    }
    //</editor-fold>
}
