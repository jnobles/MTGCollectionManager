package model;

public class PhysicalCardDTO {
    private final CardDTO cardInformation;
    private final String setCode;
    private final String number;
    private String location;
    private CardCondition condition;

    public CardDTO getCardInformation() {
        return cardInformation;
    }

    public String getSetCode() {
        return setCode;
    }

    public String getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }

    public CardCondition getCondition() {
        return condition;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCondition(CardCondition condition) {
        this.condition = condition;
    }

    public PhysicalCardDTO(CardDTO cardInformation) {
        this(cardInformation, "", CardCondition.NOT_SET);
    }

    public PhysicalCardDTO(CardDTO cardInformation, String location) {
        this(cardInformation, location, CardCondition.NOT_SET);
    }

    public PhysicalCardDTO(CardDTO cardInformation, CardCondition condition) {
        this(cardInformation, "", condition);
    }

    public PhysicalCardDTO(CardDTO cardInformation, String location, CardCondition condition) {
        this.cardInformation = cardInformation;
        this.setCode = cardInformation.getSetCode();
        this.number = cardInformation.getNumber();
        this.location = location;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "PhysicalCardDTO{" +
                "name=" + cardInformation.getName() +
                ", setCode='" + setCode + '\'' +
                ", number='" + number + '\'' +
                ", location='" + location + '\'' +
                ", condition=" + condition +
                '}';
    }
}


