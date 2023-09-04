package org.hibernate.bugs;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.AbstractMap.SimpleEntry;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * Pojo holds a String for all supported languages
 *
 * @param <T>
 * @since 20.06.2023
 */
@Embeddable
@MappedSuperclass
public class I18n<T extends I18n> implements Serializable {

    private static final long serialVersionUID = -1003383281201287866L;

    public static final Map<Locale, Access> LOCALES = Map.ofEntries(
            new SimpleEntry<>(Locale.GERMAN,    new Access(I18n::getDe, I18n::setDe)),
            new SimpleEntry<>(Locale.ENGLISH,   new Access(I18n::getEn, I18n::setEn)),
            new SimpleEntry<>(Locale.FRENCH,    new Access(I18n::getFr, I18n::setFr)),
            new SimpleEntry<>(Locale.CHINESE,   new Access(I18n::getZh, I18n::setZh)),
            new SimpleEntry<>(Locale.ITALIAN,   new Access(I18n::getIt, I18n::setIt)),
            new SimpleEntry<>(new Locale("hi"), new Access(I18n::getHi, I18n::setHi)),
            new SimpleEntry<>(Locale.KOREAN,    new Access(I18n::getKo, I18n::setKo)),
            new SimpleEntry<>(new Locale("pt"), new Access(I18n::getPt, I18n::setPt)),
            new SimpleEntry<>(new Locale("es"), new Access(I18n::getEs, I18n::setEs)),
            new SimpleEntry<>(new Locale("pl"), new Access(I18n::getPl, I18n::setPl)),
            new SimpleEntry<>(Locale.JAPANESE,  new Access(I18n::getJa, I18n::setJa)));

    private String de;
    private String en;
    private String fr;
    private String zh;
    private String it;
    private String hi;
    private String ko;
    private String pt;
    private String es;
    private String pl;
    private String ja;

    public I18n() {
        //default constructor
    }

    public I18n(Translation aTranslation) {
        if (aTranslation != null) {
            LOCALES.values().forEach(a -> a.setter().accept(this, a.getter().apply(aTranslation)));
        }
    }

    //<editor-fold defaultstate="collapsed" desc="getter & setter">
    public String getDe() {
        return de;
    }

    public T setDe(String aDe) {
        this.de = aDe;
        return (T) this;
    }

    public String getEn() {
        return en;
    }

    public T setEn(String aEn) {
        this.en = aEn;
        return (T) this;
    }

    public String getFr() {
        return fr;
    }

    public T setFr(String aFr) {
        this.fr = aFr;
        return (T) this;
    }

    public String getZh() {
        return zh;
    }

    public T setZh(String aZh) {
        this.zh = aZh;
        return (T) this;
    }

    public String getIt() {
        return it;
    }

    public T setIt(String aIt) {
        this.it = aIt;
        return (T) this;
    }

    public String getHi() {
        return hi;
    }

    public T setHi(String aHi) {
        this.hi = aHi;
        return (T) this;
    }

    public String getKo() {
        return ko;
    }

    public T setKo(String aKo) {
        this.ko = aKo;
        return (T) this;
    }

    public String getPt() {
        return pt;
    }

    public T setPt(String aPt) {
        this.pt = aPt;
        return (T) this;
    }

    public String getEs() {
        return es;
    }

    public T setEs(String aEs) {
        this.es = aEs;
        return (T) this;
    }

    public String getPl() {
        return pl;
    }

    public T setPl(String aPl) {
        this.pl = aPl;
        return (T) this;
    }

    public String getJa() {
        return ja;
    }

    public T setJa(String aJa) {
        this.ja = aJa;
        return (T) this;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + "de=" + de + ", "
                + "en=" + en + ", "
                + "fr=" + fr + ", "
                + "zh=" + zh + ", "
                + "it=" + it + ", "
                + "hi=" + hi + ", "
                + "ko=" + ko + ", "
                + "pt=" + pt + ", "
                + "es=" + es + ", "
                + "pl=" + pl + ", "
                + "jp=" + ja
                + '}';
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode & equals">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.de);
        hash = 97 * hash + Objects.hashCode(this.en);
        hash = 97 * hash + Objects.hashCode(this.fr);
        hash = 97 * hash + Objects.hashCode(this.zh);
        hash = 97 * hash + Objects.hashCode(this.it);
        hash = 97 * hash + Objects.hashCode(this.hi);
        hash = 97 * hash + Objects.hashCode(this.ko);
        hash = 97 * hash + Objects.hashCode(this.pt);
        hash = 97 * hash + Objects.hashCode(this.es);
        hash = 97 * hash + Objects.hashCode(this.pl);
        hash = 97 * hash + Objects.hashCode(this.ja);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final I18n<?> other = (I18n<?>) obj;
        if (!Objects.equals(this.de, other.de)) {
            return false;
        }
        if (!Objects.equals(this.en, other.en)) {
            return false;
        }
        if (!Objects.equals(this.fr, other.fr)) {
            return false;
        }
        if (!Objects.equals(this.zh, other.zh)) {
            return false;
        }
        if (!Objects.equals(this.it, other.it)) {
            return false;
        }
        if (!Objects.equals(this.hi, other.hi)) {
            return false;
        }
        if (!Objects.equals(this.ko, other.ko)) {
            return false;
        }
        if (!Objects.equals(this.pt, other.pt)) {
            return false;
        }
        if (!Objects.equals(this.es, other.es)) {
            return false;
        }
        if (!Objects.equals(this.pl, other.pl)) {
            return false;
        }
        return Objects.equals(this.ja, other.ja);
    }
    //</editor-fold>
}
