package in.codekerdos.classifierservice.exception;

import java.time.Instant;

public record ApiError(
    Instant timeStamp,
    int status,
    String message,
    String path

) {
    
}
