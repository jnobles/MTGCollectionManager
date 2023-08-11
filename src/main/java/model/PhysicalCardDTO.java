package model;

public class PhysicalCardDTO {
    private final CardDTO cardInformation;
    private final String location;
    private final CardCondition condition;

    public CardDTO getCardInformation() {
        return cardInformation;
    }

    public String getLocation() {
        return location;
    }

    public CardCondition getCondition() {
        return condition;
    }

    private PhysicalCardDTO(PhysicalCardBuilder builder) {
        this.cardInformation = builder.cardInformation;
        this.location = builder.location;
        this.condition = builder.condition;
    }

    public static class PhysicalCardBuilder {
        private CardDTO cardInformation;
        private String location;
        private CardCondition condition;

        public PhysicalCardBuilder withCardInformation(CardDTO cardInformation) {
            this.cardInformation = cardInformation;
            return this;
        }

        public PhysicalCardBuilder withLocation(String location) {
            this.location = location;
            return this;
        }

        public PhysicalCardBuilder withCondition(CardCondition condition) {
            this.condition = condition;
            return this;
        }

        public PhysicalCardDTO build() {
            return new PhysicalCardDTO(this);
        }
    }
}
