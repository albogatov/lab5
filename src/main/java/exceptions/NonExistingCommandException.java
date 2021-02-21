package exceptions;

/**
 * Класс исключения для обработки вызова несуществующих команд
 */
public class NonExistingCommandException extends Exception {
    public NonExistingCommandException(String message) {
        super(message);
    }
}
