package com.example.jason.roulette;

/**
 * Contains cuisines in alphabetical order by their toString() values.
 * Names are the Yelp suggested search parameter for each cuisine and was
 * obtained from the list of restaurant categories that Yelp supports:
 *   https://www.yelp.com/developers/documentation/v3/all_category_list
 */
public enum Cuisine {
    TRADAMERICAN,
    BRITISH,
    CARIBBEAN,
    CHINESE,
    FRENCH,
    GREEK,
    INDPAK,
    ITALIAN,
    JAPANESE,
    MEDITERRANEAN,
    MEXICAN,
    MOROCCAN,
    SPANISH,
    THAI,
    TURKISH,
    VIETNAMESE;

    @Override
    public String toString() {
        switch (this) {
            case TRADAMERICAN:
                return "American";
            case INDPAK:
                return "Indian";
            default:
                // Capitalize first letter only
                return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    /**
     * @return The resource id of the drawable that represents this cuisine.
     */
    public int resourceId() {
        switch (this) {
            case TRADAMERICAN: return R.drawable.tradamerican;
            case BRITISH: return R.drawable.british;
            case CARIBBEAN: return R.drawable.caribbean;
            case CHINESE: return R.drawable.chinese;
            case FRENCH: return R.drawable.french;
            case GREEK: return R.drawable.greek;
            case INDPAK: return R.drawable.indpak;
            case ITALIAN: return R.drawable.italian;
            case JAPANESE: return R.drawable.japanese;
            case MEDITERRANEAN: return R.drawable.mediterranean;
            case MEXICAN: return R.drawable.mexican;
            case MOROCCAN: return R.drawable.moroccan;
            case SPANISH: return R.drawable.spanish;
            case THAI: return R.drawable.thai;
            case TURKISH: return R.drawable.turkish;
            case VIETNAMESE: return R.drawable.vietnamese;
            default: return 0;
        }
    }
}
