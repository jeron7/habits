import { useEffect, useState } from 'react'

import api from '../api/server'

export interface Habit {
    id : string,
    title: string,
    createdAt: string,
    daysOfWeek : Array<number>
}

export interface HabitWithoutDaysOfWeek {
    id : string,
    title: string,
    createdAt: string
}

const INITIAL_ARRAY_DAYS_OF_WEEK = [...Array(7)].map((_) => []);

export const summarizeHabits = () => {
    const [habits, setHabits] = useState<Habit[]>([]);
    const [habitsMappedByDayOfWeek, setHabitsMappedByDayOfWeek] = useState<HabitWithoutDaysOfWeek[][]>(INITIAL_ARRAY_DAYS_OF_WEEK);

    const callApi = async () => {
        const { data } = await api.get('/habits/')

        data.forEach((habit : Habit) => {
            habit.daysOfWeek.forEach(dayOfWeek => {
                if (!habitsMappedByDayOfWeek[dayOfWeek - 1].some((h2) => h2.id == habit.id))
                    habitsMappedByDayOfWeek[dayOfWeek - 1].push({ id : habit.id, title: habit.title, createdAt: habit.createdAt })
            })
        })

        setHabits(habits)
        setHabitsMappedByDayOfWeek(habitsMappedByDayOfWeek)
    }

    useEffect(() => { 
        console.debug("Calling Habits Api")
        callApi() 
    }, [])

    return { habits, habitsMappedByDayOfWeek, callApi }
}