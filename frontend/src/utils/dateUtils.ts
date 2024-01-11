import dayjs from 'dayjs'
import 'dayjs/locale/pt-br'
dayjs.locale('pt-br')

export function getNDaysAgo(initial : Date, n : number) {
    const result = new Date()
    result.setDate(initial.getDate() - n)
    return result
}

export function getDatesBetween(starts : Date, ends : Date) {
    const currentDate = new Date(starts.getTime());
    const dates = [];
    while (currentDate <= ends) {
        dates.push(new Date(currentDate));
        currentDate.setDate(currentDate.getDate() + 1);
    }
    return dates;
}