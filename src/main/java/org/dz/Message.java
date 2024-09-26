package org.dz;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Hello world!
 *
 */
@AllArgsConstructor
@Data
public class Message{
    private String message;
    private String type;
    private boolean processed;
}
