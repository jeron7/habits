import api from '../api/server'

export const updateDailyActivity = async (dailyActivityId : string, practicedHabitsIds : string[]) => {
    try {
        await api.put(`/days/${dailyActivityId}/`, {
            habits : practicedHabitsIds
        })   
    } catch(error) {
        console.error(error)
    }
}