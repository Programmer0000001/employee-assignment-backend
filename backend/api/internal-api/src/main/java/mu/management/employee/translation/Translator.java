package mu.management.employee.translation;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * <p>This class will do the translation</p>
 *
 * @author oudayrao.ittoo
 */
@Component
public class Translator {
    private static ResourceBundleMessageSource messageSource;

    @Autowired
    Translator(ResourceBundleMessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    public static String toLocale(String msgCode) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }

    public static String toLocale(String msgCode, @Nullable Object[] args) {
        return messageSource.getMessage(msgCode, args, LocaleContextHolder.getLocale());
    }
}
