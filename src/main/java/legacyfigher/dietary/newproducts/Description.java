package legacyfigher.dietary.newproducts;

public class Description {

    private final String shortDesc;
    private final String longDesc;

    private Description(String shortDesc, String longDesc) {
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
    }

    public static Description of(String shortDesc, String longDesc) {
        if (longDesc == null || longDesc.isEmpty() || shortDesc == null || shortDesc.isEmpty()) {
            throw new IllegalStateException("null or empty desc");
        }
        return new Description(shortDesc, longDesc);
    }

    @Deprecated
    public static Description withoutValidation(String shortDesc, String longDesc) {
        return new Description(shortDesc, longDesc);
    }

    public Description replaceCharFromDesc(String charToReplace, String replaceWith) {
        if (longDesc == null || longDesc.isEmpty() || shortDesc == null || shortDesc.isEmpty()) {
            throw new IllegalStateException("null or empty desc");
        }
        return new Description(shortDesc.replace(charToReplace, replaceWith), longDesc.replace(charToReplace, replaceWith));
    }

    public String format() {
        if (longDesc == null || longDesc.isEmpty() || shortDesc == null || shortDesc.isEmpty()) {
            return "";
        }
        return shortDesc + " *** " + longDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }
}
