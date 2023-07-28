package site.nomoreparties.stellarburgers.constants;

public enum ConstructorLabelName {
    SOUSES("Соусы"),
    FILLINGS("Начинки"),
    BUNS("Булки");

    public String getLabelName() {
        return labelName;
    }

    private final String labelName;

    ConstructorLabelName(String labelName){
        this.labelName = labelName;
    }
}
