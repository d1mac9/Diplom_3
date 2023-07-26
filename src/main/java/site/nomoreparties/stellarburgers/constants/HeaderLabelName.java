package site.nomoreparties.stellarburgers.constants;

public enum HeaderLabelName {
    LK("Личный кабинет"),
    CONSTRUCTOR("Конструктор");

    public String getHeaderName() {
        return labelName;
    }

    private final String labelName;

    HeaderLabelName(String labelName){
        this.labelName = labelName;
    }

}
