const toIsoStringDate = (str, regex) => {
    const matches = str.match(regex);
    const groups = matches.groups;
    return `${groups.yyyy}-${groups.mm}-${groups.dd}`;
}

const toIsoStringDates = (str, regex) => {
    const dates = [];
    str.forEach(e => {
        dates.push(toIsoStringDate(e, regex));
    });
    return dates;
}

const toIsoDate = (str, regex) => {
    const matches = str.match(regex);
    const groups = matches.groups;
    const isoDate = `${groups.yyyy}-${groups.mm}-${groups.dd}`;
    return new Date(isoDate);
}

const toIsoDates = (str, regex) => {
    const dates = [];
    str.forEach(e => {
        dates.push(toIsoDate(e, regex));
    });
    return dates;
}

export { toIsoStringDate, toIsoStringDates, toIsoDate, toIsoDates };
