package jrxie.jdemo.myException;

import java.io.IOException;

/**
 * Created by jrxie on 2019/5/4.
 */

public class GSON_ANALYZE_MESSAGE_FAILURE extends IOException
{
    private static final long serialVersionUID = -6547904276907101598L;

    public GSON_ANALYZE_MESSAGE_FAILURE() {
        super();
    }

    public GSON_ANALYZE_MESSAGE_FAILURE(String message) {
        super(message);
    }

    public GSON_ANALYZE_MESSAGE_FAILURE(String message, Throwable cause) {
        super(message, cause);
    }

    public GSON_ANALYZE_MESSAGE_FAILURE(Throwable cause) {
        super(cause);
    }
}
