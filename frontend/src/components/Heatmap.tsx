import { useEffect, useState } from 'react'

import { DailyActivity } from './DailyActivity'
import { Activity, summarizeDaysActivities } from '../hooks/summarizeDaysActivities'
import { getDatesBetween, getNDaysAgo } from '../utils/dateUtils'
import { HabitWithoutDaysOfWeek, summarizeHabits } from '../hooks/summarizeHabits'

const weekDays = ["D", "S", "T", "Q", "Q", "S", "S"]

export function Heatmap() {

    const { habitsMappedByDayOfWeek } = summarizeHabits();
    const { summary } = summarizeDaysActivities()
    const [filledSummary, setFilledSummary] = useState<Activity[]>([]);

    const fillSummary = async () => {
        const ends = new Date()
        const starts = getNDaysAgo(ends, ends.getUTCDay() + 7 * 17)
        const filledSummary : Activity[] = getDatesBetween(starts, ends).map(date => {
            const createdAt = date.toISOString().slice(0, 10);

            const activity = summary.find(activity => activity.createdAt == createdAt)
            if (activity) return activity

            const dayOfWeek = date.getUTCDay();
            return {
                id: "", 
                createdAt, 
                updatedAt: null, 
                practicedHabits: new Array<HabitWithoutDaysOfWeek>(),
                notPracticedHabits : habitsMappedByDayOfWeek[dayOfWeek]
            }
        })

        console.log(filledSummary)

        setFilledSummary(filledSummary)
    }

    useEffect(() => { fillSummary() }, [summary])
    
    return (
        <div className="flex flex-row gap-3">
            <div className="grid grid-rows-7 grid-flow-col gap-2">
                {weekDays.map((weekDay, index) => {
                    return(
                        <div
                            key={index}
                            className="text-zinc-400 text-lg font-bold w-10 h-10 flex items-center justify-center"
                        >
                            {weekDay}
                        </div>
                    )
                })}    
            </div>
            <div className="grid grid-rows-7 grid-flow-col gap-2">
                {
                    filledSummary.map((activity, index) => {
                        return (
                            <DailyActivity 
                                key={index}
                                activity={activity}
                            />
                        )
                    })
                }
            </div>
        </div>
    )
}