import { useEffect, useState } from 'react'

import { summarizeHabits, HabitWithoutDaysOfWeek } from './summarizeHabits';
import api from '../api/server'

export interface ActivityResponse {
    id : null | string;
    createdAt: string;
    updatedAt: null | string;
    habits : Array<string>;
}

export interface Activity {
    id : string;
    createdAt: string;
    updatedAt: string | null;
    practicedHabits : HabitWithoutDaysOfWeek[];
    notPracticedHabits: HabitWithoutDaysOfWeek[];
}

export const summarizeDaysActivities = () => {

    const { habitsMappedByDayOfWeek } = summarizeHabits();
    const [summary, setSummary] = useState<Activity[]>([]);
    
    const callApi = async () => {
        const { data } = await api.get('/days/summary/')

        const summarizedActivities : Activity[] = data.map((activityResp : ActivityResponse) => {
            const dayOfWeek = new Date(activityResp.createdAt).getUTCDay()
            const practicedHabitsIds = activityResp.habits 
            const practicedHabits = habitsMappedByDayOfWeek[dayOfWeek].filter((habit : HabitWithoutDaysOfWeek) => practicedHabitsIds.includes(habit.id))
            const notPracticedHabits = habitsMappedByDayOfWeek[dayOfWeek].filter(habit => !practicedHabits.includes(habit))
            
            return { 
                id : activityResp.id, 
                createdAt : activityResp.createdAt, 
                updatedAt: activityResp.updatedAt,
                practicedHabits,
                notPracticedHabits
            }
        })
        setSummary(summarizedActivities)
    }

    useEffect(() => {
        console.debug("Calling Activities Api")
        callApi()
    }, [habitsMappedByDayOfWeek])

    return { summary, callApi }
}