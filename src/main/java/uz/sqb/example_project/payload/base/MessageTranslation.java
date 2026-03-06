package uz.sqb.example_project.payload.base;

public record MessageTranslation(String uz, String en, String ru) {
    public String getMessage(String lang) {
        return switch (lang) {
            case "en", "EN" -> en;
            case "ru", "RU" -> ru;
            default -> uz;
        };
    }

    public String toString() {
        return "[" + uz + ":" + en + ":" + ru + "]";
    }
}

