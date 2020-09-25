package tech.talci.redditclonespring.exceptions;

public class RedditException extends RuntimeException {

    public RedditException() {
    }

    public RedditException(String message) {
        super(message);
    }

    public RedditException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedditException(Throwable cause) {
        super(cause);
    }

    public RedditException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
