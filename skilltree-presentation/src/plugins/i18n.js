import { createI18n } from 'vue-i18n';

const locale = import.meta.env.VITE_I18N_LOCALE;

const i18n = createI18n({
    locale: locale,
    fallbackLocale: import.meta.env.VITE_I18N_FALLBACK_LOCALE,
    messages: {
        [locale]: await loadMessages(locale)
    }
});

async function loadMessages(locale) {
    const module = await import(`../messages/${locale}.js`);
    return module.default;
}

const html = document.querySelector('html');
html.lang = locale;

export default i18n;
