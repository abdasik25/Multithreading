/**
 * Created by Alexander Lomat on 29.05.19
 * Version 0.0.1
 */

package by.epam.multithreading.validator;

import java.util.List;

public interface Validator {
    boolean validate(List<String> list);
}
