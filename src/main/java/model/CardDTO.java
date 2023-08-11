package model;

public class CardDTO {
    private final String colorIdentity;
    private final String colors;
    private final String manaCost;
    private final float manaValue;
    private final String name;
    private final String number;
    private final String power;
    private final String[] subtypes;
    private final String[] supertypes;
    private final String text;
    private final String toughness;
    private final String type;
    private final String[] types;
    private final String setCode;

    public String getColorIdentity() {
        return colorIdentity;
    }

    public String getColors() {
        return colors;
    }

    public String getManaCost() {
        return manaCost;
    }

    public float getManaValue() {
        return manaValue;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPower() {
        return power;
    }

    public String[] getSubtypes() {
        return subtypes;
    }

    public String[] getSupertypes() {
        return supertypes;
    }

    public String getText() {
        return text;
    }

    public String getToughness() {
        return toughness;
    }

    public String getType() {
        return type;
    }

    public String[] getTypes() {
        return types;
    }

    public String getSetCode() {
        return setCode;
    }

    private CardDTO(CardDTOBuilder builder) {
        this.colorIdentity = builder.colorIdentity;
        this.colors = builder.colors;
        this.manaCost = builder.manaCost;
        this.manaValue = builder.manaValue;
        this.name = builder.name;
        this.number = builder.number;
        this.power = builder.power;
        this.subtypes = builder.subtypes;
        this.supertypes = builder.supertypes;
        this.text = builder.text;
        this.toughness = builder.toughness;
        this.type = builder.type;
        this.types = builder.types;
        this.setCode = builder.setCode;
    }

    @Override
    public String toString() {
        return "CardDTO{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", setCode='" + setCode + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public static class CardDTOBuilder {
        private String colorIdentity;
        private String colors;
        private String manaCost;
        private float manaValue;
        private String name;
        private String number;
        private String power;
        private String[] subtypes;
        private String[] supertypes;
        private String text;
        private String toughness;
        private String type;
        private String[] types;
        private String setCode;

        public CardDTOBuilder withColorIdentity(String colorIdentity) {
            this.colorIdentity = colorIdentity;
            return this;
        }

        public CardDTOBuilder withColors(String colors) {
            this.colors = colors;
            return this;
        }

        public CardDTOBuilder withManaCost(String manaCost) {
            this.manaCost = manaCost;
            return this;
        }

        public CardDTOBuilder withManaValue(float manaValue) {
            this.manaValue = manaValue;
            return this;
        }

        public CardDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CardDTOBuilder withNumber(String number) {
            this.number = number;
            return this;
        }

        public CardDTOBuilder withPower(String power) {
            this.power = power;
            return this;
        }

        public CardDTOBuilder withSubtypes(String[] subtypes) {
            this.subtypes = subtypes;
            return this;
        }

        public CardDTOBuilder withSupertypes(String[] supertypes) {
            this.supertypes = supertypes;
            return this;
        }

        public CardDTOBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public CardDTOBuilder withToughness(String toughness) {
            this.toughness = toughness;
            return this;
        }

        public CardDTOBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public CardDTOBuilder withTypes(String[] types) {
            this.types = types;
            return this;
        }

        public CardDTOBuilder withSetCode(String setCode) {
            this.setCode = setCode;
            return this;
        }

        public CardDTO build() {
            return new CardDTO(this);
        }
    }
}
