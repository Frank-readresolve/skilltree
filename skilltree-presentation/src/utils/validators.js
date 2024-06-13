import { toIsoDate, toIsoDates } from './converters';

const isDate = (value, regex) => {
    if(value === null || value === '') {
        return true;
    }
    const matches = value.match(regex);
    if (matches === null) {
        return false;
    }
    const groups = matches.groups;
    const isoDate = `${groups.yyyy}-${groups.mm}-${groups.dd}`;
    const candidate = new Date(isoDate);
    if (!(candidate instanceof Date && !isNaN(candidate))) {
        return false;
    }
    const candidateIsoDate = candidate.toISOString().substring(0, 10);
    return candidateIsoDate === isoDate;
};

const isBefore = (date, other, regex)  => {
    if(date === null || date === '' || other === null || !isDate(other, regex)) {
        return true;
    }
    const [dateIso, otherIso] = toIsoDates([date, other], regex);
    return dateIso < otherIso;
};

const isAfter = (date, other, regex) => {
    if(date === null || date === '' || other === null || !isDate(other, regex)) {
        return true;
    }
    const [dateIso, otherIso] = toIsoDates([date, other], regex);
    return dateIso > otherIso;
};

export { isDate, isAfter, isBefore };
